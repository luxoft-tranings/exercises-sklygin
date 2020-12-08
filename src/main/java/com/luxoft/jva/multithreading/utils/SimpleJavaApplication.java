package com.luxoft.jva.multithreading.utils;

import java.util.Scanner;

/**
 * This is a example program to demonstrate how many threads runs on JVM. Set breakpoint in any line and run it. Observe list of threads in debugger window.
 *
 * @author BKuczynski.
 */
public class SimpleJavaApplication {

	public static void main(String[] args) {
		System.out.println("Just press enter");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
	}
}
