package com.luxoft.jva.multithreading.ch03_priorities;

import com.luxoft.jva.multithreading.utils.PrimeValidator;

import java.time.LocalDateTime;

/**
 * In this exercise we will:
 * <ul>
 * <li>Create class that extends {@link Thread}.</li>
 * <li>Create few new instance of our class.</li>
 * <li>Set them different priorities.</li>
 * <li>And run them.</li>
 * <li>Wait to all threads using {@link Thread#join()}.</li>
 * </ul>
 * <p>
 * Class should make some time-consuming job and log start and end time.
 * You could use {@link PrimeValidator} to validate first 10k numbers.
 *
 * @author BKuczynski.
 */
public class Exercise7 {

	public static void main(String[] args) {
		System.out.printf("We started at %s\n", LocalDateTime.now());
		// your code goes here
		System.out.printf("We finished at %s\n", LocalDateTime.now());
	}

}
