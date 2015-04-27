package com.patterns.creational.factorymethod;

public class Start {
    
    public static void main(String[] args) {
        
        CarSelector carSelector = new CarSelector();

        System.out.println("Car(CITY):");
        Car car = carSelector.getCar(RoadType.CITY);
        car.drive();
        car.stop();

        System.out.println("\nCar(OFF_ROAD):");
        car = carSelector.getCar(RoadType.OFF_ROAD);
        car.drive();
        car.stop();

        System.out.println("\nCar(GAZON):");
        car = carSelector.getCar(RoadType.GAZON);
        car.drive();
        car.stop();
        
        
    }

}
