package mapred.exam.secondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyKeyComparator extends WritableComparator{

	//Constructor
	protected MyKeyComparator() {
		super(MyKey.class, true);	//CustomKey로 내부에서 적용될 수 있게 설정
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
