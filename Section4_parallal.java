/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package section4_parallal;

/**
 *
 * @author Yara
 */
public class Section4_parallal {
 static int counter = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Worker t1 = new Worker(); 
        Worker t2 = new Worker(); 
      

        t1.start(); 
        t2.start(); 
     
        
        // UncaughtExceptionHandler
        WorkerThread t4 = new WorkerThread();
        t4.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Exception in " + thread.getName() + ": " + exception.getMessage());
        });
        t4.start();
        
       //DEFAULTUNCAUGHTEXCEPTIONHANDLER

       Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Global handler caught exception in: " + thread.getName());
            System.out.println("Error: " + exception.getMessage());
        });


        Thread t5 = new Thread(() -> {
            throw new RuntimeException("Thread crashed!");
        });

   
        Thread t6 = new Thread(() -> {
            throw new ArithmeticException("Division by zero!");
        });

        t5.start();
        t6.start();
    }
    //RaceCondition
     static class MyTask implements Runnable {
        public void run() {
            for (int i = 0; i < 5; i++) {
                int current = counter;
                try {
                    
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int updated = ++counter;
                System.out.println(Thread.currentThread().getName()
                        + " -> Current: " + current + ", Updated: " + updated);
            }
        }
    }

    
}
//HANDLING EXCEPTIONS WITH TRY-CATCH

class Worker extends Thread{
    @Override
    public void run() {
        try { 
            System.out.println(Thread.currentThread().getName() + " started."); 
            
            int result = 100 / 0; 
        } catch (Exception e) { 
            System.out.println(Thread.currentThread().getName()+" caught: " + e); 
        }
        System.out.println(Thread.currentThread().getName()+" finished."); 
    }
}
//UNCAUGHTEXCEPTIONHANDLER

class WorkerThread extends Thread {
    @Override
    public void run() {
      
        System.out.println("Thread started: " + getName());
        int x = 10 / 0; 
    }
}

