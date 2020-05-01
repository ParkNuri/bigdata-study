package mapreduce.basic;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	static final IntWritable outputVal = new IntWritable(1);
	// output데이터의 key는 문자열이므로 Text타입으로 정의
	Text outputKey = new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(value.toString()," ");
		
		//자를 토큰이 존재하는 동안 
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			outputKey.set(token);	//output 데이터의 키를 셋팅
			context.write(outputKey, outputVal);
		}
	
	}
	
}
