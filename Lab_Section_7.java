/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab_section_7;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
/**
 *
 * @author Yara
 */
public class Lab_Section_7 {
 private static final int CAPACITY = 5;
    private static final Queue<String> buffer = new LinkedList<>();

   
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition notFull = lock.newCondition();
    private static final Condition notEmpty = lock.newCondition();

   
    static class Producer implements Runnable {
        private final String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int count = 1;
            try {
                while (true) {
                    lock.lock();
                    try {
                        
                        while (buffer.size() == CAPACITY) {
                            System.out.println(name + " waiting (Buffer Full)");
                            notFull.await();
                        }

                        String message = name + " Message " + count++;
                        buffer.add(message);
                        System.out.println(name + " produced: " + message);

                        
                        notEmpty.signalAll();
                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    
    static class Consumer implements Runnable {
        private final String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    lock.lock();
                    try {
                        
                        while (buffer.isEmpty()) {
                            System.out.println(name + " waiting (Buffer Empty)");
                            notEmpty.await();
                        }

                        String message = buffer.poll();
                        System.out.println(name + " consumed: " + message);

                        
                        notFull.signalAll();
                    } finally {
                        lock.unlock();
                    }

                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void main(String[] args) {
        Thread p1 = new Thread(new Producer("Producer-1"));
        Thread p2 = new Thread(new Producer("Producer-2"));

        Thread c1 = new Thread(new Consumer("Consumer-1"));
        Thread c2 = new Thread(new Consumer("Consumer-2"));

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
    
}
