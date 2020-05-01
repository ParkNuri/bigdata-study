package mapred.exam.secondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupKeyComparator extends WritableComparator{

	//Constructor
	protected GroupKeyComparator() {
		super(MyKey.class, true);	
	}

	//func
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		MyKey key1 = (MyKey)obj1;
		MyKey key2 = (MyKey)obj2;
		return key1.getProductId().compareTo(key2.getProductId());	
	}
	
	
}
