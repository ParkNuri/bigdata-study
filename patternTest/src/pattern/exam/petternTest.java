package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class petternTest {

	public static void main(String[] args) {
		String value ="배송이 너무 느려요~~ 모자가 좋아서 예뻐요!!! 이뻐요 !좋아... 좋아 ";
		Pattern p1 = Pattern.compile("은 |는 |이 |가 |요 |서 |께서 |에서 |다 |을 |를 |의 |에 |에게서 |에게 |한테 |로 |으로 |와 |과 ");
		Pattern p2 = Pattern.compile("[[0-9]$@$!%*#?&~.]"); 
		
		Matcher m2 = p2.matcher(value);

		StringBuffer sb2 = new StringBuffer();
		
		while (m2.find()) {
			String data = m2.group();
			
			m2.appendReplacement(sb2, " ");
		}
		m2.appendTail(sb2);
		
		System.out.println("sb2: "+sb2);
		
		Matcher m1 = p1.matcher(sb2);
		
		//패턴에 일치하지 않는 문자들만 추출해서 저장
		StringBuffer sb1 = new StringBuffer();
		
		while (m1.find()) {
			String data = m1.group();
			//System.out.println(data);
			
			//패턴에 만족하는 문자열을 ""로 치환한 후 전체 문자열을 StringBuffer에 저장 - /,$는 치환문자로 사용할 수 없다.
			m1.appendReplacement(sb1, " ");
			
		}
		//조건에 만족하지 않아도 추가할 수 있도록 구현
		m1.appendTail(sb1);
		System.out.println("sb1: "+sb1);
		String[] result = sb1.toString().split(" ");
		for (int i = 0; i < result.length; i++) {
			String a = result[i].trim();
			if(!a.isEmpty()) {
				System.out.println(a);
			}
		}
	}

}
