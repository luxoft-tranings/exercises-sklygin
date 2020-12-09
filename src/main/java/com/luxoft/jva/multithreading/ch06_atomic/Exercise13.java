package com.luxoft.jva.multithreading.ch06_atomic;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;

/**
 * In this exercise we will play atomic ping-pong again:
 * <ul>
 * <li>Create classes {@link Ping} and {@link Pong} that implements {@link Runnable}.</li>
 * <li>Create class {@link Ball} that has two {@link AtomicInteger} fields ping and pong.</li>
 * </ul>
 * <p>
 * <p>
 * In loop
 * {@link Ping}:
 * <ul>
 * <li>Increase ping value by 1</li>
 * <li>Do nothing while current step != pong</li>
 * </ul>
 * <p>
 * <p>
 * {@link Pong}:
 * <ul>
 * <li>Do nothing while ping != current step</li>
 * <li>Increase pong value by 1</li>
 * </ul>
 *
 * @author BKuczynski.
 */
public class Exercise13 {

	public static final int GAME_LENGTH = 100_000_000;

	static class Ball {
		public AtomicInteger ping = new AtomicInteger(0);
		public AtomicInteger pong = new AtomicInteger(0);

		boolean isPing() { return ping.get() == pong.get(); }
	}

	static class Ping implements Runnable {

		private final Ball m_ball;

		Ping(Ball ball) { m_ball = ball; }

		@Override
		public void run() {
			while( m_ball.ping.get() < GAME_LENGTH ) {
				if (m_ball.isPing())
					m_ball.ping.incrementAndGet();
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
			while (m_ball.pong.get() < GAME_LENGTH) {
				if (!m_ball.isPing())
					m_ball.pong.incrementAndGet();
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

