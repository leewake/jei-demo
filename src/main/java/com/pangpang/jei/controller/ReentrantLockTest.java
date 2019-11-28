package com.pangpang.jei.controller;

import com.sun.javafx.binding.StringFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 重入锁
 * @author: leewake
 * @create: 2018-11-06 15:23
 **/

public class ReentrantLockTest {
    private int value;

    static ReentrantLock firstLock = new ReentrantLock();
    static Condition condition = firstLock.newCondition();

    static ReentrantLock secondLock = new ReentrantLock();

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    static ThreadLocal<ReentrantLockTest> threadLocal = new ThreadLocal<>();

    public ReentrantLockTest(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        System.out.println(format);

        try {
            testThreadLocal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            testCountDownLatch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        firstLock.lock();
        System.out.println("主线程正在等待");
        new Thread(new ConditionThread()).start();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            firstLock.unlock();
        }
        /*Thread thread = new Thread(new LockThread(firstLock, secondLock));
        Thread thread1 = new Thread(new LockThread(secondLock, firstLock));
        thread.start();
        thread1.start();
        thread1.interrupt();*/
    }

   static class LockThread implements Runnable {
       Lock firstLock;
       Lock secondLock;

       public LockThread(Lock firstLock, Lock secondLock) {
           this.firstLock = firstLock;
           this.secondLock = secondLock;
       }

       @Override
       public void run() {
           try {
               firstLock.lockInterruptibly();
               TimeUnit.MILLISECONDS.sleep(10);
               secondLock.lock();
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               firstLock.unlock();
               secondLock.unlock();
               System.out.println(Thread.currentThread().getName() + "正常结束");
           }
       }
   }

   static class ConditionThread implements Runnable {

       @Override
       public void run() {
           firstLock.lock();
           try {
               condition.signal();
               System.out.println("子线程通知主线程");
           } finally {
               firstLock.unlock();
           }
       }
   }

   public static void testCountDownLatch() throws InterruptedException {
       Thread thread1 = new Thread(new Runnable() {
           @Override
           public void run() {
                countDownLatch.countDown();
               System.out.println(Thread.currentThread().getName() + ", parsing txt file1 success");
           }
       });

       Thread thread2 = new Thread(new Runnable() {
           @Override
           public void run() {
               countDownLatch.countDown();
               try {
                   TimeUnit.MILLISECONDS.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread().getName() + ", parsing txt file2 success");
           }
       });

       thread1.start();
       thread2.start();
       countDownLatch.await();
       System.out.println("parsing txt file success");
   }

    public static void testThreadLocal() throws InterruptedException {
        final ThreadLocal<ReentrantLockTest> threadLocal = new ThreadLocal<>();
        final ReentrantLockTest reentrantLockTest = new ReentrantLockTest(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("设置前" + reentrantLockTest.getValue());
                reentrantLockTest.setValue(1);
                threadLocal.set(reentrantLockTest);
                System.out.println("子线程" + threadLocal.get());
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程休眠两秒后" + threadLocal.get());
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(1);
        reentrantLockTest.setValue(1000);
        System.out.println("主线程" + reentrantLockTest.toString());
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ReentrantLockTest{" +
                "value=" + value +
                '}';
    }


}
