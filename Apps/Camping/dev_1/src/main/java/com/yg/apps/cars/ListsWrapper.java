package com.yg.apps.cars;

import java.util.List;
import com.yg.apps.cars.data.Garage;



public class ListsWrapper {

    public ListsWrapper() {}


    public Garage getGarageByIndex(List<Garage> list, int index) {
        return (index <= list.size()-1) ?
                list.get(index) : null;
    }
}
