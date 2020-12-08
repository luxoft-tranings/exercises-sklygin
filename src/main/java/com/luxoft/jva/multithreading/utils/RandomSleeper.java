package com.luxoft.jva.multithreading.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by BKuczynski on 2016-09-06.
 */
public class RandomSleeper {

	public static final void rsleep(long maxTime) {
		try {
			TimeUnit.SECONDS.sleep(new Random().nextInt((int) maxTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static final void sleep(long maxTime) {
		try {
			TimeUnit.SECONDS.sleep(maxTime);
		} catch (InterruptedException e) {
			System.out.printf("Thread %s were interrupted when sleep", Thread.currentThread().getName());
		}
	}
}
