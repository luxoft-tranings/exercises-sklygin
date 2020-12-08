package com.luxoft.jva.multithreading.utils;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by BKuczynski on 2016-09-07.
 */
public class ThreadPoolExecutorInfoPrinter {

	public static void printInfo(ThreadPoolExecutor executor, String name) {
		System.out.printf("%n");
		System.out.printf("***************************%n");
		System.out.printf("%s executor executing %s tasks%n", name, executor.getTaskCount());
		System.out.printf("%s executor maximum pool size is %s%n", name, executor.getMaximumPoolSize());
		System.out.printf("%s executor largest pool size was %s%n", name, executor.getLargestPoolSize());
		System.out.printf("%s executor core pool size was %s%n", name, executor.getCorePoolSize());
		System.out.printf("***************************%n");
	}
}
