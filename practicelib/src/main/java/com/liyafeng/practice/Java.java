package com.liyafeng.practice;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class Java {


    interface a {
        public static int b = 1;

        public abstract void aa();
    }

    static abstract class b {
        int a = 1;

        public void a() {

        }

        protected abstract void b();
    }

    //region Java基础

    /**
    * =====================
    * ### Java基础
    * =====================
    * */

    /**
     * 1.接口和抽象类的区别
     * http://www.importnew.com/12399.html
     */
    void a1() {
        /*
        * 从微观上说
        * 一个类能实现多个接口，但只能继承一个抽象类
        * 接口中只能有公有的抽象方法,而抽象类中可以有抽象方法，也可以没有抽象方法，也可以有普通方法
        * 接口中只能定义静态成员变量，而抽象类中可以定义非静态成员变量
        *
        * 相同点是他们都不能被实例化
        *
        * 从宏观上说
        * 定义抽象类是为了捕捉子类的通用特性，通用的实现逻辑可以在抽象类中完成，而接口中不能有逻辑
        * 而接口定义的是一种约定，实现这个接口就代表实现了接口中方法提供的服务
        *
        *
        *
        * */
    }

    /**
     * 说说String ，StringBuilder ,StringBuffer的区别
     * */
    public void a1_1(){
        /*
        * String 是一个不可变的字符序列
        * StringBuilder是可变的字符序列，他在单线程中使用，比速度StringBuffer更快
        * StringBuffer是线程安全的可变的字符序列
        * 他们两个操作的是一个字符数组，而String操作的是常量池中的数据
        */
    }

    /**
     * 说说Java的三大特性？如何理解多态？
     * http://www.runoob.com/java/java-polymorphism.html
     * */
    public void a1_2(){
        /*
        * 多态，封装，继承
        * 封装意思是隐藏类中的实现细节，只暴露它的方法
        * 继承，子类继承父类中的特性，减少重复代码
        * 多态，就是父类引用指向子类对象，当父类引用调用方法的时候，调用的是子类重写的方法
        * 就是一个行为有多种实现方式
        * 多态的三个必要条件，继承，重写，父类引用指向子类对象
        * override
        */
    }
    //endregion

    //region Java线程

    /**
     * =====================
     * ### 线程
     * 推荐阅读书籍《Java并发编程实战》
     * =====================
     * 线程中的锁就是那个对象，对象就是锁，也叫内置锁，监视器，monitor ,监视器锁，互斥锁，可重入锁
     *
     * 一个线程获取锁的唯一方式就是进入这个锁的代码块
     *
     * race conditions 竞态条件，线程执行顺序的不确定
     *
     * java重排序 http://www.infoq.com/cn/articles/java-memory-model-2#anch94124
     *
     * */

    /**
     * sleep和wait有什么区别
     */
    public void a2() {
        /*
        * 首先线程有6种状态，new waiting runnable blocked ,timed waiting ,terminated
        * 见下图
        * 首先sleep是Thread中的方法，而wait是Object中的方法
        * 调用wait，线程必须在synchronized中调用，也就是说调用wait之前
        * 线程必须先获取锁，否则会抛出异常java.lang.IllegalMonitorStateException
        * 然后调用wait后，线程释放锁，进入阻塞队列，直到被同一对象notify
        *
        * sleep 和 wait都是让线程进入阻塞状态，但是sleep是指定时间后恢复运行状态
        * 而wait需要notify，而且操作和锁有关
        *
        *
        */

        Thread.yield();
        int thread_status = R.drawable.thread_status;
    }

    /**
     * 什么是线程安全？
     */
    public void a2_1() {
        /*
        * 多个线程访问某个类时，这个类始终都表现出正确的行为，那么我们就说这个类时线程安全的
        *
        * 无状态：不包含任何字段，也不包含对其他类中字段的引用（只包含局部变量）
        * 无状态对象一定是线程安全的
        */
    }

    /**
     * volatile关键字有什么作用
     * http://aleung.github.io/blog/2012/09/17/Java-volatile-/
     */
    public void a2_2() {
        /*
        * volatile只能保证变量的可见性，就是说保证每次读取变量的时候是最新的值
        * 而不是保存在寄存器中的值，它不能保证i++的原子性
        *
        * 标记为volatile的变量表明这个变量是共享的，所以编译器不会将
        * 这个该变量上的操作和其他内存操作一起重排序
        * 也不会将变量寄存在寄存器或对其他处理器不可见的地方
        *
        * 写入相当于退出同步代码块，读取的时候是进入同步代码块
        *
        * volatile的一个典型应用就是多线程中，作为while(flag)退出的标记
        * volatile boolean flag;
        * 因为jvm在server环境（应用环境）时，会进行优化，将在while内没有改变的变量提取到循环外部
        * 所以就成了 flag = true ; while(true)，这就导致了死循环，或者说循环没有及时退出
        *
        *
        *
        */
    }

//    private static boolean flag = true;
//    private static int number = 0;

//    public static void main(String[] args) {
//
//        try {
//            new Object().wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 10; i++) {
//            final int j = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (flag){
//                        System.out.println("循环"+j);
//                    }
//                    System.out.println(j+"数字"+number);
//                }
//            }).start();
//        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                number = 42;
//                System.out.println("执行=============="+(flag = false));
//            }
//        }).start();

//    }

    /**
     * 线程池的原理？
     * */
    public void a2_3(){
        /*
        * 
        */
    }
    //endregion

    //region Java集合
    /**
     * HashMap的实现原理
     * */
    public void a3_1(){
        /*
        * 数组加链表，拉链法实现的散列表
        */
    }
    //endregion

    //region Java网络

    /**
     * =====================
     * ### 网络
     * =====================
     * */

    /**
     * 说说断点下载
     * */
    public void a6(){
        /*
        * 在http请求头中加入 range字段， range:bytes=0-
        * 然后加入etag，If-Match:asdfasdfa
        * 第一次请求的时候可以不用传etag，服务器相应200
        * 返回数据和Etag,将Etag保存到本地
        * 下次请求获取本地以下载文件的字节数，然后加上
        * range:bytes=1024-
        * If-Match:asdfasdfa
        * 服务器会响应206，如果服务器返回206，就用RandomAccessFile .seekTo()
        * 到最后一个字节，然后再继续写入
        */
    }

    /**
     * 说说多线程断点下载
     * */
    public void a7(){
        /*
        * 和断点下载一样，开启多个线程，每个线程请求指定范围的数据，
        * 首先获取文件的总大小，用get方式请求文件，然后getContentLength获取文件长度
        * 用RandomAccessFile写入文件
        */
    }


    //endregion
}
