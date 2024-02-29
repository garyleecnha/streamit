package com.example.demo;


import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemokTest {

	@Autowired
	private Demok theService;

	private static Stream<String> stringItemStream(){
		return Stream.of(/*input, output*/
				"aabcccbbad","d",
				"a","a",
				"aa","aa",
				"aab","aab",
				"aaab","b",
				"aaabb","bb",
				"aaabbb","",
				"aaabbbd","d",
				"aabbbad","d",
				"abcccb","abb",
				"abcccba","abba",
				"abcccbb","a",
				"abccccb","abb",
				"abcccccba","abba",
				"abcccbaa","abbaa",
				"abcccddda","aba",
				"abbbcddd","ac");
	}
	
	//unit test for stage#1
	@Test
	public void removeAAA() {
		System.out.println("unit test for stage#1");
		List<String> list = stringItemStream().toList();
        	Stream.iterate(0, i -> i + 2).limit(list.size()/2).forEach(i -> {
    		Assert.assertEquals( theService.recusive(list.get(i)), list.get(i+1) );
        	System.out.println("<"+i/2+">: "+String.format("%" + 20 + "s", list.get(i))+"-->"+list.get(i+1));
        });	
	}
	

	private static Stream<String> strItemStream(){
		return Stream.of(
			/*input, output*/
			"abcccbad","d",
			"ccc","b",
			"aaa","",
			"bbb","a",
			"aaab","b",
			"a","a",
			"b","b",
			"aaabbbd","ad",
			"aabbbad","d",
			"abcccb","aa",
			"ab","ab",
			"abcccbb","aa",
			"abccccb","aa",
			"abcccccba","",
			"abcccbaa","",
			"abcccddda","abbca",
			"abbbcddd","aacc");
	}
	

	@Test
	public void removeBBB() {

		System.out.println("\n\nunit test for stage#2");
		List<String> list = strItemStream().toList();
        	Stream.iterate(0, i -> i + 2).limit(list.size()/2).forEach(i -> {
    		Assert.assertEquals( theService.recusive2(list.get(i)), list.get(i+1) );
        	System.out.println("<"+i/2+">: "+String.format("%" + 20 + "s", list.get(i))+"-->"+list.get(i+1));
        });	
	}
}
