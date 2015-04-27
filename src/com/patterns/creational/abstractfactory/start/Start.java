package com.patterns.creational.abstractfactory.start;

import com.patterns.creational.abstractfactory.factory.interfaces.TransportCity;
import com.patterns.creational.abstractfactory.factory.interfaces.TransportFactory;

public class Start {
    public static CitySelector citySelector = new CitySelector();
    public static TransportFactory factory;
    
    public static void main(String[] args) {
        factory = citySelector.getFactory(TransportCity.RUSSIAN);

        factory.createAircraft().flight();
        factory.createCar().drive();
        factory.createCar().stop();
    }

}
