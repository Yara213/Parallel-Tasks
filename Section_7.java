/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package section_7;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

public class Section_7 {

    private static ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static Lock writeLock = rwLock.writeLock();
    private static Lock readLock = rwLock.readLock();
    private static Condition condition = writeLock.newCondition();

    private static int data = 0;

    static class Writer implements Runnable {
        public void run() {
            try {
                writeLock.lock();
                data++;
                System.out.println("Write data = " + data);
                condition.signalAll();
            } finally {
                writeLock.unlock();
            }
        }
    }

    static class Reader implements Runnable {
        public void run() {
            readLock.lock();
            try {
                System.out.println("Read data = " + data);
            } finally {
                readLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Writer()).start();
        new Thread(new Reader()).start();
        new Thread(new Reader()).start();
    }
}
