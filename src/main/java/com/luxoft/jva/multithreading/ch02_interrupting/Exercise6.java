package com.luxoft.jva.multithreading.ch02_interrupting;

import java.time.LocalDateTime;

/**
 * In this exercise we will:
 * <ul>
 * <li>Create two classes that extends {@link Thread}.</li>
 * <li>First class will sleep for 5 seconds, second for 7 seconds .</li>
 * <li>Create new instance of our classes and start them.</li>
 * <li>Call {@link Thread#join()} on them.</li>
 * </ul>
 * <p>
 *
 * @author BKuczynski.
 */
public class Exercise6 {

	public static void main(String[] args) {
		System.out.printf("We started at %s\n", LocalDateTime.now());
		// your code goes here
		System.out.printf("We finished at %s\n", LocalDateTime.now());
	}

}
