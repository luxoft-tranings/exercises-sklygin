package com.luxoft.jva.multithreading.utils;

/**
 * This class is an example long running task.
 *
 * @author BKuczynski.
 */
public class PrimeValidator
{

    public boolean isPrime(long number)
    {
        if (number <= 2)
        {
            return true;
        }
        for (long i = 2; i < number; i++)
        {
            if ((number % i) == 0)
            {
                return false;
            }
        }
        return true;
    }
}
