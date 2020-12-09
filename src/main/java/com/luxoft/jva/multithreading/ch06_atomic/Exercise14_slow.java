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
public class Exercise14_slow {

	public static final int GAME_LENGTH = 1_000_000;
	public static final Object BALL = new Object();

	static class Ball {
		public int ping = 0;
		public int pong = 0;

		boolean isPing() { return ping == pong; }
	}

	static class Ping implements Runnable {

		private final Ball m_ball;

		Ping(Ball ball) { m_ball = ball; }

		@Override
		public void run() {
			while( m_ball.ping < GAME_LENGTH ) {
				synchronized (BALL) {
					if(m_ball.isPing()) {
						m_ball.ping++;
						BALL.notify();
						if(m_ball.ping < GAME_LENGTH) {
							try {
								BALL.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
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
			while (m_ball.pong < GAME_LENGTH) {
				if(!m_ball.isPing()) {
					synchronized (BALL) {
						m_ball.pong++;
						BALL.notify();
						if(m_ball.pong < GAME_LENGTH) {
							try {
								BALL.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
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
		out.println("ping = " + ball.ping + ", pong = " + ball.pong);

	}

}

