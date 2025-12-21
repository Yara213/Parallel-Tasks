/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package section_8;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Section_8 {

    private static Semaphore semaphore = new Semaphore(2);
    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    private static volatile boolean running = true;

    static class Task implements Runnable {
        private int id;

        Task(int id) {
            this.id = id;
        }

        public void run() {
            try {
                semaphore.acquire();
                int value = atomicCounter.incrementAndGet();
                System.out.println("Thread " + id + " AtomicCounter = " + value);
                Thread.sleep(500);
                semaphore.release();
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Task(1)).start();
        new Thread(new Task(2)).start();
        new Thread(new Task(3)).start();

        running = false;
        System.out.println("Running = " + running);
    }
}
