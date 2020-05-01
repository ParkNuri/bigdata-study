package shop.bigdata.comment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CommentWordCountMapper_real extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	Text outputKey = new Text();
	static final IntWritable one = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String[] line = value.toString().split(",");
		if(line!=null & line.length>0) {
			
			String comment = line[2];
			
			ArrayList<String> result = wordPattern(line[2]);
			
			for (int i = 0; i < result.size(); i++) {
				outputKey.set(result.get(i));
				context.write(outputKey, one);
			}
		}
	}
	
	public static ArrayList<String> wordPattern(String value) {
		
		Pattern p1 = Pattern.compile("은 |는 |이예요 |예요 |이 |가 |서 |께서 |에서 |다 |을 |를 |의 |에 |에게서 |에게 |한테 |으로 |로 |와 |과 |해요 |해요|요 |$요");
		Pattern p2 = Pattern.compile("[[ㄱ-ㅎ|ㅏ-ㅣ][0-9]$@$!%*#?&~.]"); 
		
		Matcher m2 = p2.matcher(value);

		StringBuffer sb2 = new StringBuffer();
		
		while (m2.find()) {
			String data = m2.group();
			
			m2.appendReplacement(sb2, " ");
		}
		m2.appendTail(sb2);
		
		System.out.println("sb2: "+sb2);
		
		Matcher m1 = p1.matcher(sb2);
		
		//패턴에 일치하지 않는 문자들만 추출해서 저장
		StringBuffer sb1 = new StringBuffer();
		
		while (m1.find()) {
			String data = m1.group();
			//System.out.println(data);
			
			//패턴에 만족하는 문자열을 ""로 치환한 후 전체 문자열을 StringBuffer에 저장 - /,$는 치환문자로 사용할 수 없다.
			m1.appendReplacement(sb1, " ");
			
		}
		//조건에 만족하지 않아도 추가할 수 있도록 구현
		m1.appendTail(sb1);
		System.out.println("sb1: "+sb1);
		String[] strings = sb1.toString().split(" ");
		
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i = 0; i < strings.length; i++) {
			String a = strings[i].trim();
			if(!a.isEmpty()) {
				System.out.println(a);
				result.add(a);
			}
		}
		return result;
	}
	
	
	
}
