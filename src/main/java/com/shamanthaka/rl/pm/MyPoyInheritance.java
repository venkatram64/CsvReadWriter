package com.shamanthaka.rl.pm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyPoyInheritance {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        map.put(1, "venkat");
        map.put(1, "ram");
        System.out.println(map.get(1));
        System.out.println(map.size());

        /*Shape shape = new Circle(6);
        float circleArea = shape.area();
        System.out.println(circleArea);*/

        String s = "Hello, World";
        System.out.println(s);

        List<Integer> list = new ArrayList<>();

        list.add(4);
        list.add(5);
        list.add(7);

        //for loop is index based
        for(int i = 0; i < list.size(); i++){
            Integer v = list.get(i);
            System.out.println( v);
        }

        //for each is iterates automatically
        for(Integer i : list){
            System.out.println(i);
        }

    }
}
