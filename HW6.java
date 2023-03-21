package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class HW6 {
    public static void main(String[] args) {
        MySet ms = new MySet();
        ms.add(5);
        ms.add(5);
        ms.add(10);
        ms.add(10);
        ms.add(3);
        ms.print();
        MySet ms1 = new MySet();
        ms1.add(9);
        ms1.add(8);
        ms1.add(3);
        ms1.add(1);
        ms1.print();
        ms.unite(ms1);
        System.out.println(ms.toString());
        ms.delete(1);
        System.out.println("size: " + ms.size());
        System.out.println("element with index 2: " + ms.get(2));
        ArrayList<Integer> li = ms.toList();
        System.out.println(li);
        System.out.println(ms.toListUpToElement(8));
        System.out.println(ms.toListFromElement(8));
    }
}
class MySet {
    private HashMap<Integer, Object> mySet;
    private static final Object OOO = new Object();
    public MySet() {
        this.mySet = new HashMap<>();
    }
    public void add(Integer number) {
        this.mySet.put(number, OOO);
    }
    public void delete(Integer element) {
        this.mySet.remove(element);
    }
    public String toString() {
        StringBuilder output = new StringBuilder();
        mySet.forEach((k,v) -> output.append(k + " "));
        return output.toString();
    }
    public void print() {
        mySet.forEach((k, v) -> System.out.print(k+" "));
        System.out.println();
    }
    public void unite(MySet otherSet) {
        otherSet.mySet.forEach((k, v) -> add(k));
    }
    public Integer size() {
        return this.mySet.size();
    }
    public int get(int index) {
        return (int) mySet.keySet().toArray()[index];
    }
    public ArrayList<Integer> toList() {
        return new ArrayList<Integer>(this.mySet.keySet());
    }
    public ArrayList<Integer> toListUpToElement(int elem) {
        if (!mySet.containsKey(elem)) return null;
        else {
            ArrayList<Integer> output = new ArrayList<>();
            for (int k: mySet.keySet()) {
                output.add(k);
                if (k==elem) break;
            }
            return output;
        }
    }
    public ArrayList<Integer> toListFromElement(int elem) {
        if (!mySet.containsKey(elem)) return null;
        else {
            ArrayList<Integer> output = new ArrayList<>();
            boolean flag = false;
            for (int k: mySet.keySet()) {
                if (k==elem) flag = true;
                if (flag) output.add(k);
            }
            return output;
        }
    }
}

