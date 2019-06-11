package pl.kpp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class HandlingList {

    public static List<Object> createList(List<Object> list) {
        List<Object> listResult = new ArrayList<>();
        boolean isOnList = false;
        for (Object object : list) {
            if (object != null) {
                if (listResult.size() == 0) {
                    listResult.add(object);
                } else {
                    for (int i = 0; i < listResult.size(); i++) {
                        if (object.equals(listResult.get(i))) {
                            isOnList = true;
                        }
                    }
                    if (!isOnList) {
                        listResult.add(object);
                    }
                    isOnList = false;
                }
            }
        }
        return  listResult;
    }

    public static void addToList(List<Objects>list, Objects objects){
       boolean isOnList = false;
        for(Objects object:list){
            if(object.equals(objects)){
                isOnList = true;
            }
        }
        if (!isOnList){
            list.add(objects);
        }
    }
}