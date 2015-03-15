package com.multithreading;

/**
 * @author Aleksandr Konstantinovitch
 * @version 1.0
 * @date 14/07/2014
 * {@link https://software.intel.com/ru-ru/articles/writing-parallel-programs-a-multi-language-tutorial-introduction}
 ********************************************************************
 * Этот простой пример показывает, как создать распараллеленный вариант программы 'п' с помощью "чистых" потоков Java.
 */
public class PI1 {
    static long num_steps = 100000;
    static double step;
    static double sum = 0.0;
    static int part_step;
    static class PITask extends Thread {
        int part_number;
        double x = 0.0;
        double sum = 0.0;
        public PITask(int part_number) {
            this.part_number = part_number;
        }
        public void run() {
            for (int i = part_number; i < num_steps; i += part_step)
            {
                x = (i + 0.5) * step;
                sum += 4.0 / (1.0 + x * x);
            }
        }
    }
    public static void main(String[] args) {
        ;
        int i;
        double pi;
        step = 1.0 / (double) num_steps;
        part_step = Runtime.getRuntime().availableProcessors();
        PITask[] part_sums = new PITask[part_step];
        for (i = 0; i < part_step; i++) {
            (part_sums[i] = new PITask(i)).start();
        }
        for (i = 0; i < part_step; i++) {
            try {
                part_sums[i].join();
            } catch (InterruptedException e) {
            }
            sum += part_sums[i].sum;
        }
        pi = step * sum;
        System.out.println(pi);
    }
}