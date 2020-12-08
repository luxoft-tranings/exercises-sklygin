package com.luxoft.jva.multithreading.ch02_interrupting;

import com.luxoft.jva.multithreading.utils.PrimeValidator;

/**
 * In this exercise we will:
 * <ul>
 * <li>Create class {@link PrimeGenerator} that extends {@link Thread} and use {@link PrimeValidator}.</li>
 * <li>Create new instance of our class and start them.</li>
 * <li>Sleep main thread for few seconds and then call {@link Thread#interrupt()} on our task</li>
 * </ul>
 * <p>
 * <p>
 * Class {@link PrimeGenerator} should:
 * <ul>
 * <li>Takes long number (start from 1) and pass it to {@link PrimeValidator}.</li>
 * <li>After validator finish should check is thread interrupted.
 * <ul>
 * <li>If it's <samp>true</samp> finish by <samp>return</samp>.</li>
 * <li>Otherwise takes next number.</li>
 * </ul>
 * </li>
 * </ul>
 * <p>
 * After run of this code change {@link PrimeGenerator} to finish by throwing {@link InterruptedException}
 *
 * @author BKuczynski.
 */
public class Exercise4 {

	public static void main(String[] args) {
		// your code goes here
	}

}
