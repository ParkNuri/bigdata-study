

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
		String values[] = value.toString().split(",");

		if (values != null & values.length > 0 & values[0].equals("NASDAQ")) {
			double priceOpen = Double.parseDouble(values[3]);
			double priceClose = Double.parseDouble(values[6]);

			String date = values[2].split("-")[0];

			if (priceClose - priceOpen > 0) {
				outputKey.set(date);
				context.write(outputKey, outputVal);
			}
		}
	}

}
