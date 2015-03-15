package com.patterns.system.workerthread.ex05;

import java.util.concurrent.TimeUnit;
import com.patterns.system.workerthread.ex02.ViewResult;
import com.patterns.system.workerthread.ex02.View;
import com.patterns.system.workerthread.ex04.Command;

/** Задача, используемая
 * обработчиком потока;
 * шаблон Worker Thread
 * @author xone
 * @version 1.0
 * @see Command
 * @see CommandQueue
 */
public class MaxCommand implements Command, Runnable {

    /** Хранит результат обработки коллекции */
    private int result = -1;

    /** Флаг готовности результата */
    private int progress = 0;

    /** Обслуживает коллекцию объектов {@linkplain ex01.Item2d}  */
    private View viewResult;

    /** Возвращает поле {@linkplain MaxCommand#viewResult}
     * @return значение {@linkplain MaxCommand#viewResult}
     */
    public View getViewResult() {
        return viewResult;
    }

    /** Устанавливает поле {@linkplain MaxCommand#viewResult}
     * @param viewResult значение для {@linkplain MaxCommand#viewResult}
     * @return новое значение {@linkplain MaxCommand#viewResult}
     */
    public View setViewResult(View viewResult) {
        return this.viewResult = viewResult;
    }

    /** Инициализирует поле {@linkplain MaxCommand#viewResult}
     * @param viewResult объект класса {@linkplain ViewResult}
     */
    public MaxCommand(View viewResult) {
        this.viewResult = viewResult;
    }

    /** Возвращает результат
     * @return поле {@linkplain MaxCommand#result}
     */
    public int getResult() {
        return result;
    }

    /** Проверяет готовность результата
     * @return false - если результат найден, иначе - true
     * @see MaxCommand#result
     */
    public boolean running() {
        return progress < 100;
    }

    /** Используется обработчиком потока {@linkplain CommandQueue};
     * шаблон Worker Thread
     */
    @Override
    public void execute() {
        progress = 0;
        System.out.println("Max executed...");
        int size = viewResult.getX();
        int idx;
        for (idx = 1; idx < size; idx++) {
            result = idx;
        }
        progress = idx * 100 / size;
        if (idx % (size / 3) == 0) {
            System.out.println("Max " + progress + "%");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(3000 / size);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

//        System.out.println("Max done. Item #" + result + " found: " + viewResult.getItems().get(result));
        System.out.println("Max done. Item #" + result + " found: ");
        progress = 100;
    }

 @Override
 public void run() {
 execute();
 }

}