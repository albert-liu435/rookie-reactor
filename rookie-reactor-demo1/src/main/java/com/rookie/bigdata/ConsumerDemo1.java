package com.rookie.bigdata;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Classname ConsumerDemo
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/25 17:16
 * @Version 1.0
 */
public class ConsumerDemo1 {


    public static void main(String[] args) {
        //静态方法的引用
        Consumer<String> consumer=Demo::dmoStaticFaction;
        consumer.accept("static");

        //实例方法引用
        Demo demo=new Demo();
        Consumer<String> consumer1=demo::demoFaction;
        consumer1.accept("实例方法引用");

        Supplier<Demo> supplier=Demo::new;
        System.out.println(supplier.get());
        System.out.println(supplier.get());



    }



}
