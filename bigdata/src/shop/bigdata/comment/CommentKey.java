package shop.bigdata.comment;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class CommentKey implements WritableComparable<CommentKey>	 {

	private String prd_no;
	private String comment;
	
	//toString
	// reducer 의 결과 형태
	@Override
	public String toString() {
		return new StringBuffer().append(prd_no).append("\t").append(comment).toString();
	}
	

	//Getter & Setter
	public String getPrd_no() {
		return prd_no;
	}
	
	
	public void setPrd_no(String prd_no) {
		this.prd_no = prd_no;
	}
	
	
	public String getComment() {
		return comment;
	}
	
	
	public void setComment(String comment) {
		this.comment = comment;
	}


	//Constructor
	public CommentKey() {
		
	}

	
	public CommentKey(String prd_no, String comment) {
		super();
		this.prd_no = prd_no;
		this.comment = comment;
	}


	//Functions
	//직렬화될때 호출
	@Override
	public void write(DataOutput out) throws IOException {
		System.out.println("write function work");
		WritableUtils.writeString(out, prd_no);	//String
		WritableUtils.writeString(out, comment);	//String
	}
	

	//역직렬화될때 호출 (패킷단위로)
	@Override
	public void readFields(DataInput in) throws IOException {
		prd_no = WritableUtils.readString(in);
		comment = WritableUtils.readString(in);
	}

	@Override
	public int compareTo(CommentKey obj) {
		int result = prd_no.compareTo(obj.prd_no);
		if(result==0) {
			result = comment.compareTo(obj.comment);
		}		
		return result;
	}
	
	
}
