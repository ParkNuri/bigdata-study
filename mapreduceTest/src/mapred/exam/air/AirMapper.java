package mapred.exam.air;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
			String[] values = value.toString().split(",");
			outputKey.set(values[1] + "월");
			if (!values[15].equals("NA") && Integer.parseInt(values[15]) > 0) {

				context.write(outputKey, outputVal);
			}
		}
	}

}
