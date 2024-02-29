package com.example.demo;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class Demok {

	/**
	 * stages:solution
	 * 
	 * @args type: 1 for stage#1,  2 for stage#2
	 */
	public String recusive(String str) {
		String result = str;
		
		while(countAAA(result)>0) {
			result = Arrays.stream(result.split("(?<=(.))(?!\\1)")).map(s -> (s.length() < 3 ? s : "")).collect(Collectors.joining());
		}
		return result;	
	}
	
	public String recusive2(String str) {
		String result = str;
		
		while(countAAA(result)>0) {
			result = Arrays.stream(result.split("(?<=(.))(?!\\1)")).map(s -> (s.length() < 3 ? s : (s.charAt(0) == 'a') ? "" : String.valueOf((char) ((int) s.charAt(0) - 1))))
			        .collect(Collectors.joining());
		}
		return result;	
	}
	
	private long countAAA(String str) {
		return 	Arrays.stream(str.split("(?<=(.))(?!\\1)"))
				.filter(s -> s.length() >2).count();
	}
}