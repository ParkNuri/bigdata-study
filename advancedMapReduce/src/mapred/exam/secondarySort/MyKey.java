package mapred.exam.secondarySort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class MyKey implements WritableComparable<MyKey>	 {

	private String productId;
	private String userId;
	
	//toString
	// reducer 의 결과 형태
	@Override
	public String toString() {
		return new StringBuffer().append(productId).append("\t").append(userId).toString();
	}
	

	//Getter & Setter
	public String getProductId() {
		return productId;
	}
	
	
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	
	public String getUserId() {
		return userId;
	}
	
	
	public void setUserId(String userId) {
		this.userId = userId;
	}


	//Constructor
	public MyKey() {
		
	}

	
	public MyKey(String productId, String userId) {
		super();
		this.productId = productId;
		this.userId = userId;
	}


	//Functions
	//직렬화될때 호출
	@Override
	public void write(DataOutput out) throws IOException {
		System.out.println("write function work");
		WritableUtils.writeString(out, productId);	//String
		WritableUtils.writeString(out, userId);	//String
	}
	
	public void workLog() {
		System.out.println("work~~~~~~~~");
	}
	//역직렬화될때 호출 (패킷단위로)
	@Override
	public void readFields(DataInput in) throws IOException {
		productId = WritableUtils.readString(in);
		userId = WritableUtils.readString(in);
	}

	@Override
	public int compareTo(MyKey obj) {
		int result = productId.compareTo(obj.productId);
		if(result==0) {
			result = userId.compareTo(obj.userId);
		}		
		return result;
	}
	
	
}
