package com.multithreading;

/**
 * @author Aleksandr Konstantinovitch
 * @version 1.0
 * @date 15/07/2014
 * {@link http://www.csa.ru/CSA/tutor/javathread.html}
 ********************************************************************
 * Следует учесть,что синхронизированные методы выполняются медленнее их несинхронизированных аналогов.
 * Ниже приводится классический тестовый пример вычисления Пи на Java
 ******
 *       Pi.java
 *
 *       compute pi by integrating f(x) = 4/(1 + x**2)
 *
 *       each thread:
 *               - receives the interval used in the approximation
 *               - calculates the areas of it's rectangles
 *               - synchronizes for a global summation
 *       process 0 prints the result and the time it took
 ******/


class Pi extends Thread
{
    int from,to ;
    static int nn,n =72000000,np = 4 ; // np - number of processors
    double h,sum,x;
    static double ssum = 0.0 ;
    static int j ;
    static long st,end ;

    public Pi(int from, int to)
    {
        this.from = from;
        this.to = to;
    }

    public double f(double a)

    {
        return(4.0 / (1.0 + a*a));
    }

// synchronization

    synchronized void count()
    {
        j = j + 1 ;
        ssum += h * sum ;
        System.out.println("ssum == " + ssum);
        if ( j == np )
            System.out.println(" ssum - pi = " + (ssum - Math.PI));
    }

    public void run()
    {
        st = System.currentTimeMillis();    // start
        System.out.println(this);
        h = 1.0 / (double) n;
        sum = 0.0;

        for (int i=from; i<to; i++)
        {
//               System.out.println("i== " + i);

            x = h * ((double) i - 0.5);
            sum += f(x);
        }
        count() ;
        end = System.currentTimeMillis();    // end
        System.out.println("Time == " + (end - st));

    }

    public static void main(String[] args)
    {
        j = 0 ;

// spawn  np  threads, each of wich calculates one area

        for (int i=0; i<np; i++)
        {
            Pi t = new Pi(i*n/np, (i+1)*n/np);
            t.start();
        }
//         System.out.println(" ssum - pi = " + (ssum - Math.PI));
    }
}
