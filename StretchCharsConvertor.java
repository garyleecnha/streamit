package com.example.demo;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class StretchCharsConvertor {
    
	private static String REGEX = "(?<=(.))(?!\\1)";
	
	/**
	 * stages:solution
	 * 
	 * @args type: 
	 * strIn: a string with consecutive identical characters
	 * stage#1 replacement = s.length() < 3 ? s : ""
	 * stage#2 replacement = s.length() < 3 ? s : (s.charAt(0) == 'a') ? 
	 *                       "" : String.valueOf((char) ((int) s.charAt(0) - 1))
	 */
	
	public String stageProcessor(String strIn, String stageId) {
	
	  String strOut = stretchStrConvertor(strIn, strFunc -> {
		  
		  String strApply = switch (stageId) {
	          case "stage#1" -> strFunc.length() < 3 ? strFunc : "";
	          
	          case "stage#2" -> strFunc.length() < 3 ? strFunc : (strFunc.charAt(0) == 'a') ?
	        		  					"" : String.valueOf((char) ((int) strFunc.charAt(0) - 1));
	          
	          default -> throw new IllegalArgumentException("Unexpected value: " + stageId);
		  };
          return strApply;
      });
	  
	  return strOut;
	}
	
	private String stretchStrConvertor(String strIn, Function<String, String> replacement) {
		
		if(Objects.isNull(strIn)) return null;
		if(!strIn.matches("[a-z]*")) {
			throw new IllegalArgumentException("please input lower case letters ");
		}
		String result = strIn;
		
		while(countConsecutiveChars(result)>0) {
			result = Arrays.stream(result.split(REGEX)).map(s -> replacement.apply(s)).collect(Collectors.joining());
		}
		return result;	
	}
	
	private long countConsecutiveChars(String strIn) {
		return 	Arrays.stream(strIn.split(REGEX))
				.filter(s -> s.length() >2).count();
	}
}