package mapred.exam.secondarySort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer2
	extends Reducer<MyKey, IntWritable,Text,Text>{
	Text resultVal = new Text();
	Text resultKey = new Text();
	
	@Override
	protected void reduce(MyKey key,
			Iterable<IntWritable> values,
			Reducer<MyKey, IntWritable, Text, Text>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		int userSum = 1;
		String beforeUser = key.getUserId();
		
		int count = 0;
		
		
		for (IntWritable value:values) {
			System.out.println("key");
			if(!beforeUser.equals(key.getUserId())) {
				userSum+=1;
			}	// 유저 인원수 구하기
			sum = sum+value.get();	// 클릭수    
			beforeUser = key.getUserId();
		}                               
		resultKey.set(key.getProductId());
		resultVal.set(userSum+"/t"+sum);
		context.write(resultKey, resultVal);
		
		
	}
}







