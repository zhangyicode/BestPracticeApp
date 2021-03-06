package com.liyafeng.event.rxjava;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liyafeng on 2017/12/27.
 * a library for composing asynchronous and event-based programs by using observable sequences.
 * 一个组成异步和基于事件的程序 ，通过使用被观察者的队列
 * 实际上就是一个异步的处理库，而且异步处理完还能通知观察者 进行下一步处理
 * <p>
 * 我们可以手写 Thread然后使用Handler来实现相同的逻辑，但是rxjava它简洁，链式调用，
 * 可维护性高
 * <p>
 * 订阅者-发布者
 * 观察者-被观察者
 * <p>
 * 订阅者 订阅 发布者发布的消息
 * Publisher<T>-发布者
 * Subscriber<T> -订阅者
 * T代表发布的消息类型
 * <p>
 * =============================
 * 可以消除回调地狱，我们之前在model层请求网络或者io操作，
 * 可能还有localmodel和remotelocal ，还要加回调到model
 * 然后要回调到presenter，这就要很多回调
 * 但是用Rxjava就可以消除这种，将线程操作或者网络请求放到rxjava中的子线程
 * 然后返回直接return，不用回调，然后我们切换到主线程做更新UI的操作
 * 这样就省去了回调，和handler切换线程。代码就会清晰很多。
 *
 *
 * ==============================================
 * 扔物线 朱凯写的rxjava详解
 * http://gank.io/post/560e15be2dca930e00da1083#toc_8
 */

public class RxJavaSample {

    public RxJavaSample() {

        do1();
        do2();
        do3();


    }

    private void do3() {
        Flowable<Long> timer = Flowable.timer(3000, TimeUnit.MILLISECONDS);
        timer.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i("test", "打印" + aLong);
            }
        });
    }

    private void do2() {
        Flowable.fromCallable(new Callable<String>() {//发布者
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "完成";
            }
        })
                .subscribeOn(Schedulers.io())//订阅的事件（被观察者）执行在哪个线程
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {//订阅者
            @Override
            public void accept(String s) throws Exception {

                Log.i("test", "执行：" + s);
            }
        });
    }

    private void do1() {
        //创造事件队列,然后加入
        Flowable<String> just = Flowable.just("123");//这个是被观察的事件队列，123是内容
        just.subscribe(new Consumer<String>() {//这个是观察者
            @Override
            public void accept(String s) throws Exception {
                Log.i("test", "打印" + s);
            }
        });

    }

    private void do4(){
        Flowable.range(1,10).flatMap(new Function<Integer, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Integer integer) throws Exception {
                return null;
            }
        }).filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                return false;
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });
    }
}
