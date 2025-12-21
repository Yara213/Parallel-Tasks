/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package section_6;
import java.util.concurrent.locks.ReentrantLock;

public class Section_6 {

    private static ReentrantLock lock = new ReentrantLock();
    private static int counter = 0;

    static class Task implements Runnable {
        private int id;

        Task(int id) {
            this.id = id;
        }

        public void run() {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    counter++;
                    System.out.println("Thread " + id + " Counter = " + counter);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Task(1));
        Thread t2 = new Thread(new Task(2));
        Thread t3 = new Thread(new Task(3));

        t1.start();
        t2.start();
        t3.start();
    }
}
