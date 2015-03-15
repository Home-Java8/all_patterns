package com.multithreading;

/**
 * @author Aleksandr Konstantinovitch
 * @version 1.0
 * @date 12/07/2014
 * {@link http://www.javaprobooks.ru/java-программирование/java-threads.html}
 ******************************************************
 * Многопоточное приложение на Java.
 * ---------------------------------
 *   Имеется определённое количество сотрудников, занимаются звонками клиентам.
 * Но в промежутках между рабочими часами. Они также находят время на то, чтобы заняться чем-то своим.
 * Например попить кофе или поиграть в игру.
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Теперь создаём несколько сотрудников, запускаем потоки методос start и…
public class CallCenter {
    public static void main(String args[]) {
        TasksQueue queue = new TasksQueue();

        Caller caller = new Caller("Jack", queue);
        new Thread(caller).start();

        caller = new Caller("Barny", queue);
        new Thread(caller).start();

        caller = new Caller("Mike", queue);
        new Thread(caller).start();

        caller = new Caller("Harry", queue);
        new Thread(caller).start();

        caller = new Caller("Denny", queue);
        new Thread(caller).start();
    }
}

// Осталось только описать сам класс задания, Task. Он достаточно просто, хранит в себе описание и время выполнения
class Task {

    private String description;
    private long duration;

    public Task(String description, long duration) {
        this.description = description;
        this.duration = duration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration() {
        return this.duration;
    }

}

//   Кроме того, для того, чтобы работать, сотрудник должен получить задание. Для этого у каждого сотрудника будет ссылка на пул
// заданий TasksQueue, который будет выдавать по запросу новые задания, подставляя в него случайное описание и случайное время
// выполнения.
class TasksQueue {

    private Random random = new Random();

    private String[] descriptions = new String[]{
            "make coffe",
            "talk with co-worker",
            "call to the customer",
            "read forums",
            "send email",
            "play a computer game"
    };

    private String getRandomDescription() {
        return descriptions[random.nextInt(descriptions.length)];
    }

    public synchronized Task getTask() {
        return new Task(getRandomDescription(), random.nextInt(10) + 1);
    }

}

//   В первую очередь нам понадобится класс сотрудника.
// У него обязательно должно быть имя. Его мы будем хранить в поле name.
//   Сотрудник, получив задание, начинает его выполнение. Будем имитировать этот процесс с помощью приостановки потока Thread.sleep
// на время, указанное в задании.  После того, как сотрудник закончить выполнение, от отчитывается об это в System.out.
class Caller implements Runnable {

    private TasksQueue queue;
    private String name;

    public Caller(String name, TasksQueue queue) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            Task task = queue.getTask();
            process(task);
        }
    }

    private void process(Task task) {
        try {
            TimeUnit.SECONDS.sleep(task.getDuration());
            System.out.println(String.format("%s preformed task \"%s\" in %d seconds",
                    name,
                    task.getDescription(),
                    task.getDuration()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}