package mapred.exam.secondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyKeyPartitioner extends Partitioner<MyKey, IntWritable> {

	@Override
	public int getPartition(MyKey key, IntWritable value, int numPartitions) {
		
		return key.getProductId().hashCode()%numPartitions;
	}
	
}
