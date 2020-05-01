package mapred.exam.air.multiple;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirMultipleMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);

	Text outputKey = new Text();

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
			String[] values = value.toString().split(",");
			if (values != null & values.length > 0) {
				// 출발지연
				func("departure", 15, false, values, context);
				func("arrival", 14, false, values, context);
				func("departure", 15, true, values, context);
				func("arrival", 14, true, values, context);
				
				/*if (!values[15].equals("NA") && Integer.parseInt(values[15]) > 0) {
					outputKey.set("departure, " + values[1] + "월");
					context.write(outputKey, outputVal);
				} else if (!values[14].equals("NA") && Integer.parseInt(values[14]) > 0) {
					outputKey.set("arrival, " + values[1] + "월");
					context.write(outputKey, outputVal);
				} else if (values[15].equals("NA")) {
					outputKey.set("depNA, " + values[1] + "월");
					context.write(outputKey, outputVal);
				} else if (values[14].equals("NA")) {
					outputKey.set("arrNA, " + values[1] + "월");
					context.write(outputKey, outputVal);
				}*/

			}
		}
	}
	
	private void func(String AorD, int calNum, boolean NA, String[] values, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		if(NA==false) {
			if(!values[calNum].equals("NA") && Integer.parseInt(values[calNum])>0) {
				outputKey.set(AorD+", "+ values[1]+"월");
				context.write(outputKey, outputVal);
			}
		}else {
			if (values[calNum].equals("NA")) {
				outputKey.set(AorD+"NA, " + values[1] + "월");
				context.write(outputKey, outputVal);
			}
			
		}
	}
}
