package mapred.exam.air.sort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import mapred.exam.air.sort.CustomKey;

// 그룹키 비교기 : 같은 그룹으로 묶기 위해 year만 비교
public class GroupKeyComparator extends WritableComparator{

	//Constructor
	protected GroupKeyComparator() {
		super(CustomKey.class, true);	//CustomKey로 내부에서 적용될 수 있게 설정
	}

	//func
	//WritableComparable의 타입이 정확하지 않기 때문에 warning이 발생
	//타입에 대한 부분을 무시하고 체크하지 않고 처리하겠다는 의미
	@SuppressWarnings("rawtypes")
	@Override
	public int compare(WritableComparable obj1, WritableComparable obj2) {
		CustomKey key1 = (CustomKey)obj1;
		CustomKey key2 = (CustomKey)obj2;
		return key1.getYear().compareTo(key2.getYear());	// year를 그룹키로 사용하겠다
	}
	
	
}
