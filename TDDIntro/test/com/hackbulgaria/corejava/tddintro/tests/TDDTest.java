package com.hackbulgaria.corejava.tddintro.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import com.hackbulgaria.corejava.tddintro.TDD;

public class TDDTest {

    @Test
	public void getNumberOfDigitsTest() {
		assertEquals(1, TDD.getNumberOfDigits(0));
		assertEquals(2, TDD.getNumberOfDigits(63));
		assertEquals(6, TDD.getNumberOfDigits(123097));
	}
	 
	@Test
	public void connevtObjectsTest(){
	    assertEquals("Днес видях 5 ходещи медузи", TDD.connectObjects(" ", "Днес", "видях", 5, "ходещи", "медузи"));
	    assertEquals("Резултатът,**който**се**получава**е**1.89", TDD.connectObjects("**", 
	            "Резултатът,", "който", "се", "получава", 'е', 1.89));
	}

	@Test
	public void reduceFilePathTest(){
	    assertEquals("/", TDD.reduceFilePath("/")); 
	    assertEquals("/", TDD.reduceFilePath("/srv/../")); 
	    assertEquals("/srv/www/htdocs/wtf", TDD.reduceFilePath("/srv/www/htdocs/wtf/")); 
	    assertEquals("/srv/www/htdocs/wtf", TDD.reduceFilePath("/srv/www/htdocs/wtf"));
	    assertEquals("/srv", TDD.reduceFilePath("/srv/./././././"));
	    assertEquals( "/etc/wtf", TDD.reduceFilePath("/etc//wtf/"));
	    assertEquals("/", TDD.reduceFilePath("/etc/../etc/../etc/../")); 
	    assertEquals("/", TDD.reduceFilePath("//////////////")); 
	    assertEquals("/", TDD.reduceFilePath("/../")); 
	}
}

