package com.rookie.bigdata;

import org.junit.Test;

import java.util.function.BiFunction;

/**
 * @Classname BiFunctionDemo
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/26 10:25
 * @Version 1.0
 */
public class BiFunctionDemo {
    @Test
    public void test1(){
        //BiFunction
        BiFunction<Integer,Integer,Integer> biFunction = (x, y) -> x+y;
        Integer apply2 = biFunction.apply(1, 2);
        System.out.println(apply2);

    }
}
