package com.luxoft.jva.multithreading.utils;

import java.io.PrintWriter;
import java.util.Objects;

/**
 * Util class. Prints basic thread information to {@link System#out}.
 *
 * @author BKuczynski
 */
public class ThreadInfoPrinter {

	public void print(Thread thread) {
		Objects.requireNonNull(thread, "Thread can not be null");
		PrintWriter pw = new PrintWriter(System.out);
		pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
		pw.printf("Main : Priority: %d\n", thread.getPriority());
		pw.printf("Main : State: %s\n", thread.getState());
		pw.printf("Main : ************************************\n");
	}
}
