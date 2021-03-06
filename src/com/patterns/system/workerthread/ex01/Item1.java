package com.patterns.system.workerthread.ex01;

import java.io.Serializable;
/**Хранит исходные данные о треугольнике
 * @author Александр
 */
public class Item1 implements Serializable{
    private double x;
    private double y;
    private double p;

    /**автоматическая сгенерированная константа */
    private static final long serialVersionUID = 1L;

    /**Инициализация поля {@linkplain Item1#x}, {@linkplain Item1#y}, {@linkplain Item1#p}*/
    public Item1(){
        x = .0;
        y = .0;
        p = .0;
    }
    /**устанавливает значения полей
     * @param x - основание треугольника
     * @param y - высота треугольника
     */
    public Item1(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**устанавливает значения полей
     * @param p - периметр треугольника
     */
    public void setP(double p) {
        this.p = p;
    }

    /**Получение периметра треугольника
     *@return - возвращает периметр треугольника
     */
    public double getP(){
        return p;
    }

    /**Устанавливает значения
     * @param x - основание треугольника
     * @param y - высота треугольника
     * @return - возвращает значение основания и высоты
     */
    public Item1 setXY(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }
    /**предоставление информации в виде результата вычисления за прошедший день.*/
    public String toString(){
        return "x = " + x + " , y = " + y;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
