/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab_section_5;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * @author Yara
 */
public class Lab_Section_5 {

     private static final Queue<String> buffer = new LinkedList<>();
    private static final int CAPACITY = 5;

    static class Producer extends Thread {
        public void run() {
            int count = 1;
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() == CAPACITY) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {}
                    }
                    buffer.add("Message " + count++);
                    System.out.println("Produced");
                    buffer.notifyAll();
                }
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {}
                    }
                    buffer.poll();
                    System.out.println("Consumed");
                    buffer.notifyAll();
                }
            }
        }
    }
    public static void main(String[] args) {
         new Producer().start();
         new Consumer().start();
    }
    
}
