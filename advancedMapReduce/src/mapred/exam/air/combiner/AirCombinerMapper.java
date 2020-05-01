package mapred.exam.air.combiner;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirCombinerMapper 
	extends Mapper<LongWritable, Text,Text,IntWritable>{
	Text outputKey = new Text();//output key
	//output Value
	static final IntWritable one = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if(key.get()>0) {
			String[] line = value.toString().split(",");
			if(line!=null & line.length>0) {
				if(!line[15].equals("NA") && 
						Integer.parseInt(line[15])>0) {
					outputKey.set(line[0]+"년 "
										+line[1]+"월");
					context.write(outputKey, one);
				}
			}
		}
	}
}







