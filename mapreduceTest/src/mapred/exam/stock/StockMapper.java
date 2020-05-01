package mapred.exam.stock;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(value.toString(), ",");
		String date = "";
		double priceOpen = 0;
		double priceClose = 0;
		if (st.nextToken().equals("NASDAQ")) {
			int i = 1;
			while (st.hasMoreTokens()) {
				i++;
				String token = st.nextToken();
				// 거래일자
				if (i == 3) {
					date = (token.split("-"))[0];
				}

				// 시가
				if (i == 4) {
					priceOpen = Double.parseDouble(token);
				}
				// 종가
				if (i == 7) {
					priceClose = Double.parseDouble(token);
				}
			}
		}
		if (priceClose - priceOpen > 0) {
			outputKey.set(date);
			context.write(outputKey, outputVal);
		}
	}

}
