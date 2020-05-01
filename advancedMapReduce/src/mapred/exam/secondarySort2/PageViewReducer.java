package mapred.exam.secondarySort2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PageViewReducer
	extends Reducer<MyKey, Text,MyKey,Text>{
	Text resultVal = new Text();
	MyKey resultKey = new MyKey();
	Text appenddata = new Text();
	String data = "";
	
	@Override
	protected void reduce(MyKey key,
			Iterable<Text> values,
			Reducer<MyKey, Text, MyKey, Text>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		int userSum = 0;
		String beforeUser = "";
		
		int count = 0;	//클릭한 사용자 수
		
		for (Text value:values) {
			String currentUser = value.toString();
			
			if(!beforeUser.equals(currentUser)) {
				userSum++;
			}
			sum ++;     //하나의 상품에 접속한 모든 횟수
			beforeUser = currentUser;
		}                           
		//상품코드가 바뀔때마다 출력
		resultKey.setProductId(key.getProductId());
		StringBuffer data = new StringBuffer();
		data.append(userSum).append("\t").append(sum);
		resultVal.set(data.toString());
		context.write(resultKey, resultVal);
		
		
	}
}







