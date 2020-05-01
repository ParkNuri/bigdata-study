package mapred.exam.secondarySort2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyKeyComparator extends WritableComparator{
	
	protected MyKeyComparator(Class<? extends WritableComparable> keyClass, boolean createInstances) {
		super(MyKey.class, true);
	}

	//func
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		MyKey key1 = (MyKey)obj1;
		MyKey key2 = (MyKey)obj2;
		return key1.compareTo(key2);
	}
	
	
}
