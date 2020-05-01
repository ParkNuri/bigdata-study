package mapred.exam.secondarySort2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageViewMapper 
	extends Mapper<LongWritable, Text,MyKey,Text>{
	MyKey outputKey = new MyKey();//output key
	Text outputVal = new Text();
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, MyKey, Text>.Context context)
			throws IOException, InterruptedException {
			String[] line = value.toString().split("	");
			outputKey.setProductId(line[2]);
			outputKey.setUserId(line[9]);

			context.write(outputKey, outputVal);
	}
}
