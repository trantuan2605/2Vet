package com.twovet.widget.controllers;

import java.util.Collections;

public class testApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stu
//		String code ="";
//		String codeCharacter ="2VDV0101";
//		int next3ndCode = 10;
//		code = codeCharacter.concat(String.format("%03d", next3ndCode));
//		System.out.println(code);
		
		for (int i = 1; i <= 4; i++) {
			System.err.println("row : " + i);
			for (int j = (i-1)*6; j <= (6*i) -1 ; j++) {
				System.err.println("col : " + j);
			}
		}

	}

}
