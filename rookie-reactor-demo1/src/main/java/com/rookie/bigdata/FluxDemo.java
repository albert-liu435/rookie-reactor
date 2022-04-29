package com.rookie.bigdata;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * @Classname FluxDemo
 * @Description TODO
 * @Author rookie
 * @Date 2022/4/26 11:05
 * @Version 1.0
 */
public class FluxDemo {

    @Test
    public void JustTest() {
        String str = "";
        Optional<String> optional = Optional.of("hello, java");

        Mono.just("hello,world")
                .subscribe(System.out::println);

        Mono.justOrEmpty(str)
                .subscribe(System.out::println);

        Mono.justOrEmpty(optional)
                .subscribe(System.out::println);

        Flux.just("hello", "world")
                .subscribe(System.out::println);

        Flux.just("hello")
                .subscribe(System.out::println);

    }


    @Test
    public void fromTest(){
        String[] array = new String[]{"hello", "reactor", "flux"};
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");

        Flux.fromArray(array)
                .subscribe(System.out::println);
        Flux.fromIterable(iterable)
                .subscribe(System.out::println);
        Flux.fromStream(Arrays.stream(array))
                .subscribe(System.out::println);
    }

    @Test
    public void RangeTest(){
        Flux.range(3, 5)
                .subscribe(System.out::println);
    }

    @Test
    public void IntervalTest() throws Exception{
        Flux.interval(Duration.ofMillis(30), Duration.ofMillis(500))
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }



    @Test
    public void emptyTest() {
        Flux.empty()
                .subscribe(System.out::println, System.out::println, () -> System.out.println("结束"));
    }


    @Test
    public void neverTest() {
        Flux.never()
                .subscribe(System.out::println, System.out::println, () -> System.out.println("结束"));
    }

    /**
     * 测试错误
     */
    @Test
    public void errorTest() {
        Flux.error(new IllegalStateException(), true)
                .log()
                .subscribe(System.out::println, System.err::println);
    }

    /**
     * 测试 Subscribe
     */
    @Test
    public void subscribeTest() {
        Flux.range(1, 4)
                .log()
                .subscribe(System.out::println,
                        error -> System.err.println("发生错误：" + error),
                        () -> System.out.println("完成"),
                        sub -> {
                            System.out.println("已订阅");
                            // 理解背压
                            // 尝试修改 request 中成小于4的值，看看有啥变化
                            sub.request(10);
                            // sub.cancel();
                        });
    }

    /**
     * 测试 log
     */
    @Test
    public void logTest() {
        // 尝试交换下 take 和 log 的顺序，看看有啥变化
        Flux.range(1, 10)
                // .log()
                .take(3)
                .log()
                .subscribe();
    }



}
