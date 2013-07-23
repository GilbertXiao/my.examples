package com.shravan.ds.codechef;

import java.util.Scanner;

public class FactorialCalc {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long i = sc.nextInt(),res=1;
		
		for(int j =1; j<=i;j++){
			res = j*res;
		}
			
//		while(i--<0){
//			int f = i;
//			if(i==0||i ==1)
//			{
//				f=1;
//			}
//			res = i*f;
//		}
		System.out.println(res);
	}
}
