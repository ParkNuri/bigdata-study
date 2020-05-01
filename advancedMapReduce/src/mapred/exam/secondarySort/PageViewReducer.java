package mapred.exam.secondarySort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer
	extends Reducer<MyKey, IntWritable,MyKey,IntWritable>{
	IntWritable resultVal = new IntWritable();
	MyKey resultKey = new MyKey();
	
	@Override
	protected void reduce(MyKey key,
			Iterable<IntWritable> values,
			Reducer<MyKey, IntWritable, MyKey, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		int userSum = 1;
		String beforeUser = key.getUserId();
		
		int count = 0;
		
		for (IntWritable value:values) {
			if(!beforeUser.equals(key.getUserId())) {
				userSum+=1;
			}	// 유저 인원수 구하기
			sum = sum+value.get();	// 클릭수    
			beforeUser = key.getUserId();
		}                               
		resultKey.setProductId(key.getProductId());
		resultKey.setUserId(userSum+"");
		resultVal.set(sum);
		context.write(resultKey, resultVal);
		
		
	}
}







