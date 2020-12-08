package com.luxoft.jva.multithreading.ch04_synchronization;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * https://gist.github.com/oyushche/d9bbd477fcf5434219e81660fd68516b
 */
public class Ex11_StargateSeveralShips extends JPanel
{
    private final int GATE_OPEN_Y = 200;
    private final int GATE_CLOSED_Y = 90;

    private List<Ship> ships;

    private int gateX = 300;
    private int gateY = 90;

    private void run()
    {
        while (gateY < GATE_OPEN_Y)
        {
            gateY += 1;
            repaint();
            sleep(30);
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Stargate Several Ships Answer");
        frame.setSize(new Dimension(400, 400));
        frame.setLocation(150, 150);

        Ex11_StargateSeveralShips stargate = new Ex11_StargateSeveralShips();

        frame.add(stargate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        stargate.run();
    }

    public Ex11_StargateSeveralShips()
    {
        this.ships = new ArrayList<>(2);

        ships.add(new Ship(50, 130, Color.BLUE));
        ships.add(new Ship(50, 180, Color.GREEN));
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
        for (Ship ship : ships)
        {
            g.setColor(ship.color);
            g.fillRect(ship.x, ship.y, 20, 20);
        }

        g.setColor(Color.BLACK);
        g.fillRect(300, 0, 15, 90);
        g.fillRect(300, 200, 15, 200);


        g.setColor(Color.RED);
        g.fillRect(gateX, gateY, 15, 110);
    }

    class Ship
    {
        int x;
        int y;
        Color color;

        public Ship(int x, int y, Color color)
        {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
