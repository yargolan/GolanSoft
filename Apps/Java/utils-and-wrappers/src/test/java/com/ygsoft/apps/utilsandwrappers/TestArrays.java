package com.ygsoft.apps.utilsandwrappers;

import org.junit.Test;


public class TestArrays {

    private String[] baseArray = {"Hello", "World"};
    private String[] emptyArray = new String[0];


    @Test
    public void testShiftToArray() {
        ArraysManipulations am = new ArraysManipulations(baseArray);
        String[] newArray = am.shift();
        assert (newArray.length == baseArray.length - 1);
    }



    @Test
    public void testShiftEmptyArray() {
        ArraysManipulations am = new ArraysManipulations(emptyArray);
        String[] newArray = am.shift();
        assert (newArray == null);
    }



    @Test
    public void testUnshiftToArray() {
        ArraysManipulations am = new ArraysManipulations(baseArray);
        String[] newArray = am.unshift("x");
        assert (newArray.length == baseArray.length + 1);
        assert newArray[0].equals("x");
    }



    @Test
    public void testUnshiftToEmptyArray() {
        ArraysManipulations am = new ArraysManipulations(emptyArray);
        String[] newArray = am.unshift("x");
        assert (newArray.length == 1);
        assert newArray[0].equals("x");
    }



    @Test
    public void testPushToArray() {
        ArraysManipulations am = new ArraysManipulations(baseArray);
        String[] newArray = am.push("x");
        assert (newArray.length == baseArray.length + 1);
        assert newArray[newArray.length-1].equals("x");
    }



    @Test
    public void testPushToEmptyArray() {
        ArraysManipulations am = new ArraysManipulations(emptyArray);
        String[] newArray = am.push("x");
        assert (newArray.length == 1);
        assert newArray[0].equals("x");
    }



    @Test
    public void testPopArray() {
        ArraysManipulations am = new ArraysManipulations(baseArray);
        String[] newArray = am.pop();
        assert (newArray.length == baseArray.length - 1);
    }



    @Test
    public void testPopFromEmptyArray() {
        ArraysManipulations am = new ArraysManipulations(emptyArray);
        String[] newArray = am.pop();
        assert (newArray == null);
    }
}

