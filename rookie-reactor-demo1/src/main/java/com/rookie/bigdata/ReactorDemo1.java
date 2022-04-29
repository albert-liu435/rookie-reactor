package com.rookie.bigdata;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @Classname ReactorDemo1
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/25 17:00
 * @Version 1.0
 */
public class ReactorDemo1 {


    @Test
    public void test1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello thread");
            }
        }).start();

        new Thread(() -> System.out.println("hello thread2")).start();
    }


    @Test
    public void test2(){
        //方法引用写法
        Consumer<String> consumer= System.out::println;
        consumer.accept("方法引用");
        //lambda表达式写法
        Consumer<String> consumer1=s -> System.out.println(s);
        consumer1.accept("lambda表达式写法");
    }


    @Test
    public void test3(){

        String [] WORDS={"a","b","c","d","e"};


        AtomicInteger index=new AtomicInteger();
        Arrays.stream(WORDS)
                .map(word->String.format("%s.%s",index.getAndIncrement(),word))
                //.map(s -> s+index.getAndIncrement())
                .forEach(System.out::println);

    }

    @Test
    public void reactorTest4(){
        String [] WORDS={"a","b","c","d","e"};
        Flux.fromArray(WORDS)
                .zipWith(Flux.range(1,/* Integer.MAX_VALUE*/2),
                        ( word,index) ->String.format("%s.%s",word,index))

                                //StrUtil.format("{}. {}", index, word))
                .subscribe(System.out::println);

    }


    @Test
    public void reactorTest5(){
        // 创建 Flux 响应流
        Flux<String> source = Flux.just("foo", "bar");
// 使用 concatWith 操作符连接 2 个响应流
        Flux<String> boom = source.concatWith(Mono.error(new IllegalArgumentException("boom")));
// 创建一个 StepVerifier 构造器来包装和校验一个 Flux。
        StepVerifier.create(boom)
                .expectNext("foo")          // 第一个我们期望的信号是 onNext，它的值为 foo
                .expectNext("bar")          // 第二个我们期望的信号是 onNext，它的值为 bar
                .expectErrorMessage("boom") // 最后我们期望的是一个终止信号 onError，异常内容应该为 boom
                .verify();                  // 使用 verify() 触发测试。

    }

    @Test
    public void reactorTest6(){

        Hooks.onOperatorDebug();
        Flux.range(1, 3)
                .flatMap(n -> Mono.just(n + 100))
                .single()
                .map(n -> n * 2)
                .subscribe(System.out::println);

    }











}
