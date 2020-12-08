package com.luxoft.jva.multithreading.ch10_executor_framework;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * TODO: Fix problems, make workers produce diamonds.
 * Program should work with any number of BLANKS and WORKERS
 */
public class Ex23DiamondFactory extends JPanel
{
    private static final int WINDOW_WIDTH = 800;
    private static final int COUNT_OF_BLANKS = 100;
    private static final int COUNT_OF_WORKERS = 12;

    private List<Worker> workers = new ArrayList<>();

    private List<Blank> blanks = new ArrayList<>();

    private List<Blank> table = new ArrayList<>();
    private List<Diamond> diamonds = new ArrayList<>();

    private ExecutorService executor;

    private void run()
    {
        executor = Executors.newFixedThreadPool(10);

        generateBlanks();

        executor.submit(new Repainter(this));

        hireWorkers();
        startWorkingDay();
    }

    private void startWorkingDay()
    {
        for (Worker worker : workers)
        {
            executor.submit(worker);
        }
    }

    private void hireWorkers()
    {
        for (int i = 0; i < COUNT_OF_WORKERS; i++)
        {
            workers.add(new Worker(blanks, table, diamonds, executor));
        }
    }

    private void generateBlanks()
    {
        int x = 10;
        int y = 10;

        for (int i = 1; i <= COUNT_OF_BLANKS; i++)
        {
            blanks.add(new Blank(x, y));
            x += Blank.width + 3;

            if (i % 10 == 0)
            {
                y += Blank.width + 3;
                x = 10;
            }
        }
    }


    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Stargate Locks");
        frame.setSize(new Dimension(WINDOW_WIDTH, 400));
        frame.setLocation(150, 150);

        Ex23DiamondFactory app = new Ex23DiamondFactory();

        frame.add(app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        app.run();
    }

    private static void sleep(int millis)
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

        g.setColor(Blank.color);
        for (Blank blank : blanks)
        {
            g.fillRect(blank.x, blank.y, Blank.width, Blank.width);
        }

        g.setColor(Blank.color);
        for (Blank blank : table)
        {
            g.fillRect(blank.x, blank.y, Blank.width, Blank.width);
        }

        g.setColor(Diamond.color);
        for (Diamond diamond : diamonds)
        {
            g.fillRect(diamond.x, diamond.y, Blank.width, Blank.width);
        }
    }


    static class Move implements Runnable
    {
        private List<Blank> src;
        private List<Blank> dest;

        public Move(List<Blank> src, List<Blank> dest)
        {
            this.src = src;
            this.dest = dest;
        }

        @Override
        public void run()
        {
            Blank blank = src.remove(0);
            blank.y += 200;

            sleep(300);
            dest.add(blank);
        }
    }

    static class MakeDiamond implements Runnable
    {
        private List<Blank> table;
        private List<Diamond> diamonds;

        public MakeDiamond(List<Blank> table, List<Diamond> diamonds)
        {
            this.table = table;
            this.diamonds = diamonds;
        }

        @Override
        public void run()
        {
            Blank blank = table.remove(0);
            diamonds.add(new Diamond(blank.x + 200, blank.y));
            sleep(500);
        }
    }

    static class Worker implements Runnable
    {
        private List<Blank> blanks;
        private List<Blank> table;
        private List<Diamond> diamonds;
        private ExecutorService executor;

        public Worker(List<Blank> blanks, List<Blank> table, List<Diamond> diamonds, ExecutorService executor)
        {
            this.blanks = blanks;
            this.table = table;
            this.diamonds = diamonds;
            this.executor = executor;
        }

        @Override
        public void run()
        {
            while (true)
            {
                if (blanks.size() > 0)
                {
                    Future<?> future = executor.submit(new Move(blanks, table));

                    while (!future.isDone())
                    {
                        sleep(10);
                    }

                    sleep(200);
                    executor.submit(new MakeDiamond(table, diamonds));
                }
            }
        }
    }

    static class Blank
    {
        static int width = 10;
        static Color color = Color.BLACK;

        int x;
        int y;

        public Blank(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    static class Diamond extends Blank
    {
        static Color color = Color.BLUE;

        public Diamond(int x, int y)
        {
            super(x, y);
        }
    }

    class Repainter implements Runnable
    {
        private JPanel panel;

        public Repainter(JPanel panel)
        {
            this.panel = panel;
        }

        @Override
        public void run()
        {
            while (true)
            {
                panel.repaint();
                sleep(60 / 24);
            }
        }
    }
}
