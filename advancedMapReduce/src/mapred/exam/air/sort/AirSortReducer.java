package mapred.exam.air.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import mapred.exam.air.sort.CustomKey;

//input data 하나마다 실행됨
public class AirSortReducer
	extends Reducer<CustomKey, IntWritable,CustomKey,IntWritable>{
	IntWritable resultVal = new IntWritable();
	CustomKey resultKey = new CustomKey();
	Text appenddata = new Text();
	String data = "";
	
	@Override
	protected void reduce(CustomKey key,
			Iterable<IntWritable> values,
			Reducer<CustomKey, IntWritable, CustomKey, IntWritable>.Context context) throws IOException, InterruptedException {
		int sum = 0;
		data = data + "reduce 호출";
		appenddata.set(data);
		//month 데이터를 추출
		Integer beforeMonth = key.getMonth();
		
		int count = 0;
		
		for (IntWritable value:values) {
			if(count<10) {
				System.out.println("reduce=>"+key);
				count++;
			}
			if(beforeMonth!=key.getMonth()) {
				resultKey.setYear(key.getYear()+","+appenddata+"(테스트문자열), "+key.hashCode()+"(해시코드)"+key.getMapkey()+"(map에서 전달한 키)");
				resultKey.setMonth(beforeMonth);
				resultVal.set(sum);
				context.write(resultKey, resultVal);
				sum=0;
			}
			sum = sum+value.get();     
			beforeMonth = key.getMonth();
		}                               
		//values에 전달된 값들을 반복작업하며 마지막에 같은 키를 갖고 있는 값을 한꺼번에 출력 -키(year, month)가 같은 경우
		if(beforeMonth==key.getMonth()) {
			resultKey.setYear(key.getYear());
			resultKey.setMonth(key.getMonth());
			resultKey.workLog();
			resultVal.set(sum);
			context.write(resultKey, resultVal);
		}
		
	}
}







