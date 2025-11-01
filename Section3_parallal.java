/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package section3_parallal;
import java.util.List;
/**
 *
 * @author Yara
 */
public class Section3_parallal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
          MyThread t1 = new MyThread("Downloader");
          t1.start();
        // set name
          Thread t = new Thread(() -> {
          System.out.println("Running: " + Thread.currentThread().getName());
          });
          t.setName("Worker-1");
          t.start();
        // set using runnable
        Runnable task = () -> System.out.println("Task running in: " + Thread.currentThread().getName());
        Thread t2 = new Thread(task, "parallalTask");
        t2.start();
        // PRIORITY
        Thread t3 = new Thread(() -> System.out.println("T3"));
        Thread t4 = new Thread(() -> System.out.println("T4"));

        t3.setPriority(Thread.MAX_PRIORITY); // 10
        t4.setPriority(Thread.MIN_PRIORITY); // 1
        
        t4.start();
        t3.start();
        //ThreadGroup
        ThreadGroup group = new ThreadGroup("New_Group");

        Thread t5 = new Thread(group, () -> System.out.println("Task 1 running"));
        Thread t6 = new Thread(group, () -> System.out.println("Task 2 running"));

        t5.start();
        t6.start();
        group.list(); 
        //User thread
        Thread t7 = new Thread(() -> {
                   for (int i = 1; i <= 3; i++) {
                       System.out.println("User thread working...");
                       try { Thread.sleep(500); } catch (Exception e) {}
                   }
               });
               t7.start();
               System.out.println("Main finished");
        //Daemon
                Thread t8 = new Thread(() -> {
               while (true) {
                   System.out.println("Daemon running...");
                   try { Thread.sleep(500); } catch (Exception e) {}
               }
           });
           t8.setDaemon(true); 
           t8.start();

           System.out.println("Main finished"); 

    }
    
}
// rename
class MyThread extends Thread {
    public MyThread(String name) {
        super(name);  
    }
    public void run() {
        System.out.println("Thread running: " + getName());
    }
}
