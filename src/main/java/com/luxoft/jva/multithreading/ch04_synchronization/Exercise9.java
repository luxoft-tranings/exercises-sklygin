package com.luxoft.jva.multithreading.ch04_synchronization;

import java.time.LocalDateTime;

/**
 * In this exercise we will:
 * <ul>
 *     <li>Create class {@link Cinema} that:
 *          <ul>
 *              <li>Has two fields that represents number of free seats at two halls with some initial value.</li>
 *              <li>Has two fields that represents mutexes.</li>
 *              <li>Methods for sell and return tickets.</li>
 *              <li>Those methods should be synchronized via mutexes</li>
 *          </ul>
 *     </li>
 *     <li>Create classes {@link TicketOffice1} and {@link TicketOffice2}
 *          <ul>
 *              <li>Both implements {@link Runnable}</li>
 *              <li>Both has reference to {@link Cinema}</li>
 *              <li>In {@link Runnable#run()} method put some sequence of selling and returning tickets.</li>
 *          </ul>
 *     </li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise9
{

	public static void main(String[] args) {
		System.out.printf("We started at %s\n", LocalDateTime.now());
		// your code goes here
		System.out.printf("We finished at %s\n", LocalDateTime.now());
	}

}

