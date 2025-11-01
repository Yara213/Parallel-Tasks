/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package task1_section3_parallal;
import java.util.List;

public class Task1_section3_parallal 
 {
    private final List<Runnable> tasks;

   
    public Task1_section3_parallal(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    
    public void executeAll() {
        for (Runnable task : tasks) {
            Thread t = new Thread(task);  
            t.start();                    
        }
    }

    
    public static void main(String[] args) {
        Runnable task1 = () -> System.out.println("Task 1 running by " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Task 2 running by " + Thread.currentThread().getName());
        Runnable task3 = () -> System.out.println("Task 3 running by " + Thread.currentThread().getName());

        Task1_section3_parallal executor = new Task1_section3_parallal(List.of(task1, task2, task3));
        executor.executeAll();
    }
}
