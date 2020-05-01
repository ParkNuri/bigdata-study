package mapred.exam.air.option;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.transform.OutputKeys;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

// 하둡을 실행할때 사용자가 입력하는 욥션을 Mapper내부에서 사용할 수 있도록 
// 옵션이 어떤값으로 입력되었냐에 따라 다른 작업을 할 수 있도록 처리
public class AirOptionMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	static final IntWritable outputVal = new IntWritable(1);
	String jobType; // 사용자가 입력하는 옵션값을 저장하기 위한 변수
	Text outputKey = new Text();

	// Mapper가 실행될 때 한 번만 실행되는 메소드 (Init 메소드)
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		/*
		 * -d 옵션과 함께 사용자가 입력하는 jobType이라는 옵션의 지정한 값을 
		 * 
		 * 
		 */
		jobType = context.getConfiguration().get("jobType");
	}

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		if (key.get() > 0) {
			String[] values = value.toString().split(",");
			if (values != null & values.length > 0) {
				//출발지연
				if (jobType.equals("departure")) {
					if (!values[15].equals("NA") && Integer.parseInt(values[15]) > 0) {
						outputKey.set(values[1] + "월");
						context.write(outputKey, outputVal);
					}
				}else if (jobType.equals("arrival")) {
				//도착지연
					if (!values[14].equals("NA") && Integer.parseInt(values[14]) > 0) {
						outputKey.set(values[1] + "월");
						context.write(outputKey, outputVal);
					}
					
				}
			}
		}
	}

}
