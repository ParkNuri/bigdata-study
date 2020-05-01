package mapred.exam.air.sort;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

import mapred.exam.air.sort.CustomKey;

//복합키 정의 - 사용자정의 키(정렬할 기준을 컬럼으로 갖고 있는 객체)
//맵리듀스 프레임워크 내부에서 키와 value는 네트워크에서 주고 받는 값이므로
//네트워크 전송을 하기 위해 제공되는 Writable타입이어야 하므로
//WriteableComparable을 상속받아 작성한다.
public class CustomKey implements WritableComparable<CustomKey>	 {

	private String year;
	private Integer month;
	private Long mapkey;
	
	//toString
	// reducer 의 결과 형태
	@Override
	public String toString() {
		//하나의 객체 (보통 자동완성으로 생성되는 toString은 여러개의 객체를 생성하지만 네트워크 통신 용량을 줄이기 위해 하나의 객체로)
		return (new StringBuffer()).append(year).append(",").append(month).append("월").toString();
	}


	//Getter & Setter
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Long getMapkey() {
		return mapkey;
	}


	public void setMapkey(Long mapkey) {
		this.mapkey = mapkey;
	}


	//Constructor
	public CustomKey(String year, Integer month) {
		this.year = year;
		this.month = month;
	}

	
	public CustomKey(String year, Integer month, Long mapkey) {
		this.year = year;
		this.month = month;
		this.mapkey = mapkey;
	}


	public CustomKey() {
		
	}

	
	//Functions
	//데이터를 쓰고 읽는 작업을 처리
	//데이터를 쓰기 - 직렬화
	//데이터를 읽기 - 역직렬화
	//하둡의 맵리듀스 내부에서 이런 작업을 처리할 수 있도록 구현된 메소드를 호출해서 처리한다.
	
	//직렬화될때 호출
	@Override
	public void write(DataOutput out) throws IOException {
		System.out.println("write function work");
		WritableUtils.writeString(out, year);	//String
		out.writeInt(month);	//기본형 데이터이기 때문에  패킷단위로 쪼갤 필요가 없다.
		out.writeLong(mapkey);
	}
	
	public void workLog() {
		System.out.println("work~~~~~~~~");
	}
	//역직렬화될때 호출 (패킷단위로)
	@Override
	public void readFields(DataInput in) throws IOException {
		year = WritableUtils.readString(in);
		month = in.readInt();
		mapkey = in.readLong();
	}

	//사용자가 만들어놓은 키를 기준으로 정렬하기 위해서 비교하게 할 메소드
	//year로 비교 year가 같으면 month로 비교
	@Override
	public int compareTo(CustomKey obj) {
		int result = year.compareTo(obj.year);
		if(result==0) {
			result = month.compareTo(obj.month);
		}		
		return result;
	}
	
	
}
