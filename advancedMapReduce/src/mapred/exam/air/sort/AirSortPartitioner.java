package mapred.exam.air.sort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import mapred.exam.air.sort.CustomKey;

//Partitioner class에는 Mapper의 key와 value타입을 generic으로 명시
//year를 기준으로 해시코드를 구해서 같은 year를 갖고있는 데이터를 같은 reducer에서 처리할 수 있도록 분배
//-> 같은 것끼리 메모리 버퍼에 쌓았다가 한꺼번에 전송
public class AirSortPartitioner extends Partitioner<CustomKey, IntWritable> {

	// numPartitions는 partition의 갯수 = reduce task의 갯수
	@Override
	public int getPartition(CustomKey key, IntWritable value, int numPartitions) {
		
		return key.getYear().hashCode()%numPartitions;
	}
	
}
