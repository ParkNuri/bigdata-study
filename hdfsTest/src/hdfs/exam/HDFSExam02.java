package hdfs.exam;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/*
 * hadoop의 hdfs를 api로 제어 
 * - hadoop hdfs에 api를 이용해서 파일을 생성
 * - 사용자가 지정하는 경로에 파일 생성
 * 
 */
public class HDFSExam02 {

	public static void main(String[] args) {
		//1. hdfs를 제어하기 위해서 설정파일을 읽어서 사용해야 하므로
		Configuration conf = new Configuration();
		
		//2. HDFS를 접근하기 위해서 제공되는 객체 생성
		FileSystem hdfs = null;
		//3. HDFS에 output하기 위한 스트림 객체
		FSDataInputStream hdfsin = null;	//FilewWriter fw = null 처럼 정의하는 것과 동일
		//new해서 만둘지 않는 파일은 중요한 파일. single tone
		try {
			hdfs = FileSystem.get(conf);
			
			//4. HDFS의 경로를 표현할 수 있는 객체
			//	 => HDFS에 출력할 파일의 경로를 명령행매개변수로 받아서 적용하겠다는 의미
			Path path = new Path(args[0]);
			
			//5. HDFS에 저장된 파일을 읽기 위해 inputStream 생성하기
			hdfsin = hdfs.open(path);
			
			//6. inputStream의 데이터를 출력
			//	 readUTF => String
			System.out.println(hdfsin.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
