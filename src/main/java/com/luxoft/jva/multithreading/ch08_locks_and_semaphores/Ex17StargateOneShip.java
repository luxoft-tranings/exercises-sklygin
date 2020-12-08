package com.luxoft.jva.multithreading.ch08_locks_and_semaphores;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *
 * TODO: Rewrite your program to work with
 * {@link java.util.concurrent.locks.Condition} instead of java sync and monitors
 *
 * You can use version with several ships if you want.
 */
public class Ex17StargateOneShip extends JPanel
{
    private final int GATE_OPEN_Y = 200;
    private final int GATE_CLOSED_Y = 90;

    private int shipX = 50;
    private int shipY = 130;

    private int gateX = 300;
    private int gateY = 90;

    private void run()
    {

        new Thread(() ->
        {
            while (shipX < 400)
            {
                shipX += 1;
                sleep(30);
            }

        }).start();

        new Thread(() ->
        {
            while (true)
            {
                repaint();
                sleep(1000 / 30);
            }

        }).start();

    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("com.luxoft.jva.multithreading.ch04_synchronization.stargate.StargateOneShipAnswer");
        frame.setSize(new Dimension(400, 400));
        frame.setLocation(150, 150);

        Ex17StargateOneShip stargate = new Ex17StargateOneShip();

        frame.add(stargate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        stargate.run();
    }

    private void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            // ignore
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.BLUE);
        g.fillRect(shipX, shipY, 20, 20);

        g.setColor(Color.BLACK);
        g.fillRect(300, 0, 15, 90);
        g.fillRect(300, 200, 15, 200);


        g.setColor(Color.RED);
        g.fillRect(gateX, gateY, 15, 110);
    }
}
