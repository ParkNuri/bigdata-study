package mapred.exam.air.multiple;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class AirMultipleReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	IntWritable resultVal = new IntWritable();
	Text resultKey = new Text();
	MultipleOutputs<Text, IntWritable> outputs;
	
	@Override
	protected void setup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		outputs = new MultipleOutputs<>(context);
	}


	@Override
	protected void cleanup(Reducer<Text, IntWritable, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		outputs.close();
	}


	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		String [] data = key.toString().split(",");
		resultKey.set(data[1]);
		
		write(data, "departure", values);
		write(data, "arrival", values);
		write(data, "departureNA", values);
		write(data, "arrivalNA", values);
		
		/*if(data[0].equals("departure")) {
			int sum = 0;
			for(IntWritable value:values) {
				sum += value.get();
			}
			resultVal.set(sum);
				
			outputs.write("departure",resultKey, resultVal);
		}
		if(data[0].equals("arrival")) {
			int sum = 0;
			for(IntWritable value:values) {
				sum += value.get();
			}
			resultVal.set(sum);
			
			outputs.write("arrival",resultKey, resultVal);
		}
		if(data[0].equals("depNA")) {
			int sum = 0;
			for(IntWritable value:values) {
				sum += value.get();
			}
			resultVal.set(sum);
			
			outputs.write("arrNA",resultKey, resultVal);
		}
		if(data[0].equals("arrNA")) {
			int sum = 0;
			for(IntWritable value:values) {
				sum += value.get();
			}
			resultVal.set(sum);
			
			outputs.write("arrivalNA",resultKey, resultVal);
		}*/
	}
	
	private void write(String[] data ,String name, Iterable<IntWritable> values) throws IOException, InterruptedException {
		if(data[0].equals(name)) {
			int sum = 0;
			
			for(IntWritable value:values) {
				sum += value.get();
			}
			resultVal.set(sum);
			
			outputs.write(name,resultKey, resultVal);
		}
	}
}
