package com.luxoft.jva.multithreading.ch06_atomic;

import static java.lang.System.out;

/**
 * EXTRA!
 *
 * In this exercise we will play ping-pong again but this time we will implement
 * whole stuff using synchronization.
 *
 * @author BKuczynski.
 */
public class Exercise14 {

	public static final int GAME_LENGTH = 10_000_000;

	static class Ball {
		private volatile int ping = 0;
		private volatile int pong = 0;

		boolean isPing() { return ping == pong; }
		public int getPing() {return ping;}
		public int getPong() {return pong;}
		public synchronized void incrementPing() {ping++;}
		public synchronized void incrementPong() {pong++;}
	}

	static class Ping implements Runnable {

		private final Ball m_ball;

		Ping(Ball ball) { m_ball = ball; }

		@Override
		public void run() {
			while( m_ball.getPing() < GAME_LENGTH ) {
				if(m_ball.isPing()) {
					m_ball.incrementPing();
				}
			}
		}
	}

	static class Pong implements Runnable {

		private final Ball m_ball;

		Pong(Ball ball) {
			m_ball = ball;
		}

		@Override
		public void run() {
			while (m_ball.getPong() < GAME_LENGTH) {
				if(!m_ball.isPing()) {
						m_ball.incrementPong();
				}
			}
		}
	}


	public static void main(String[] args) {
		// your code goes here
		Ball ball = new Ball();

		final Thread pongTh = new Thread(new Pong(ball));
		final Thread pingTh = new Thread(new Ping(ball));

		final long start = System.nanoTime();

		pongTh.start();
		pingTh.start();

		try
		{
			pingTh.join();
			pongTh.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		final long duration = System.nanoTime() - start;

		out.printf("duration %,d (ns)%n", duration);
		out.printf("%,d ns/op%n", duration / (GAME_LENGTH * 2L));
		out.printf("%,d ops/s%n", (GAME_LENGTH * 2L * 1_000_000_000L) / duration);
		out.println("ping = " + ball.getPing() + ", pong = " + ball.getPong());

	}

}

