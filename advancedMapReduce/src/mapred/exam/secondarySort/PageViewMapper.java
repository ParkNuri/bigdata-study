package mapred.exam.secondarySort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PageViewMapper 
	extends Mapper<LongWritable, Text,MyKey,IntWritable>{	//partitioner로 넘어감 타입 맞춰줘야 한다.
	MyKey outputKey = new MyKey();//output key
	//output Value
	static final IntWritable one = new IntWritable(1);
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, MyKey, IntWritable>.Context context)
			throws IOException, InterruptedException {
			String[] line = value.toString().split("	");
			if(line!=null & line.length>0) {
				if(!line[2].equals("") && !line[9].equals("")) {
					outputKey.setProductId(line[2]);
					outputKey.setUserId(line[9]);

					context.write(outputKey, one);
				}
			}
	}
}
