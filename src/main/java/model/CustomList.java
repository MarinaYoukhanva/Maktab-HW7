package model;

import java.util.Arrays;

public class CustomList {

    private Object[] objects;
    private int index;

    public CustomList() {
        this.objects = new Object[10];
        this.index = 0;
    }

    public void add(Object o) {
        if (index == 0){
            objects[index] = o;
            index++;
        }else if (o.getClass() == objects[0].getClass()){
            objects[index] = o;
            index++;
        }
        if (index >= objects.length)
            objects = Arrays.copyOf(objects, objects.length * 2);
    }

    public void remove(Object o) {
        Integer foundIndex = find(o);
        if (foundIndex != null) {
            objects[foundIndex] = null;
            for (int i = foundIndex; i < index - 1; i++) {
                objects[i] = objects[i + 1];
            }
            index--;
            objects[index] = null;
        }
    }

    public Object get(int index) {
        return objects[index];
    }

    public void set(Object o, int j) {
        for (int i = index; i > j; i--)
            objects[i] = objects[i - 1];
        objects[j] = o;
        index++;
    }

    public int size() {
        return index;
    }

    public void clear() {
        objects = new Object[10];
        index = 0;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    private Integer find(Object o) {
        for (int i = 0; i < objects.length; i++)
            if (objects[i].equals(o))
                return i;

        return null;
    }

    public String print () {
        String out = "";
        for (int i = 0; i < index ; i++){
            System.out.println(objects[i].toString());
            out = out + " " + objects[i].toString();

        }
        return out;
    }
}
