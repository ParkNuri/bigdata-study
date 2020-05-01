package pattern.exam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest03 {
	public static void main(String[] args) {
		String str = "jaava programmaingm";
		//String str ="-@-ja1- -111aCva--@@-@@@@- 한글 --@@@@-- progra44568EmgFmiJng";
		//String str = "a 4 m 7 v 9 amJAVA _java aaaxl  programming and spring , hadoop";
		String patternStr =".*";
		//String patternStr ="-@+-";
		//String patternStr ="-@?-";
		//String patternStr ="[^ ]";//공백이 아닌문자
		//String patternStr =".{5}";
		//String patternStr ="[amv]{1,3}";//a,m,v가 1회,2회,3회인 문자
		                                //a, m, v
		                                //aa, am,av,mv...
		                                //aaa,aam,amv...
		//String patternStr ="[a-z]{3,}";//3글자이상
		//String patternStr ="\\W";//대문자,소문자,숫자 뺀 모두
		//String patternStr ="\\w";//대문자,소문자,숫자 모두
		//String patternStr ="\\d";//숫자만
		//String patternStr ="\\D";//숫자를 뺀 나머지
		equalsPattern(str, patternStr);
	}
	public static void equalsPattern(String str, 
								String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher m = pattern.matcher(str);
		
		while(m.find()) {
			System.out.println(m.group());
			System.out.println(m.start()+":"+(m.end()-1));
		}
											
	}
}






