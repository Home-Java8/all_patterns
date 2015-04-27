package com.patterns.creational.abstractfactory.start;

import com.patterns.creational.abstractfactory.factory.impl.RussianFactory;
import com.patterns.creational.abstractfactory.factory.interfaces.TransportCity;
import com.patterns.creational.abstractfactory.factory.impl.USAFactory;
import com.patterns.creational.abstractfactory.factory.interfaces.TransportFactory;

// фабрика по созданию автомобилей
public class CitySelector {
    
    // фабричный метод, который создает нужный автомобиль
    public TransportFactory getFactory(TransportCity transportCity){
        TransportFactory factory = null;
        switch (transportCity){
            case RUSSIAN:
                factory = new RussianFactory();
                break;
            case USA:
                factory = new USAFactory();
                break;
        }

        return factory;
    }
}
