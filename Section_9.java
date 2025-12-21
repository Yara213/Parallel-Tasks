/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package section_9;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Section_9 {

    static class Task implements Runnable {
        private int id;

        Task(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println("Executing Task " + id + " by " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            threadPool.execute(new Task(i));
        }

        threadPool.shutdown();

        ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(2);

        scheduledPool.scheduleAtFixedRate(() -> {
            System.out.println("Scheduled Task executed by " + Thread.currentThread().getName());
        }, 0, 1, TimeUnit.SECONDS);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scheduledPool.shutdown();
    }
}
