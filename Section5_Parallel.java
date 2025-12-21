package section5_parallel;

import java.util.LinkedList;
import java.util.Queue;

public class Section5_Parallel {
    public static void main(String[] args) {
        ProducerConsumerExample pc = new ProducerConsumerExample(5);

        Thread producer1 = new Thread(() -> pc.produce("Message from Producer1"));
        Thread producer2 = new Thread(() -> pc.produce("Message from Producer2"));

        Thread consumer1 = new Thread(() -> pc.consume());
        Thread consumer2 = new Thread(() -> pc.consume());

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }
}

class ProducerConsumerExample {
    private final Queue<String> buffer;
    private final int capacity;

    public ProducerConsumerExample(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void produce(String message) {
        while (buffer.size() == capacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        buffer.add(message);
        System.out.println("Produced: " + message);
        notifyAll();
    }

    public synchronized void consume() {
        while (buffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String message = buffer.poll();
        System.out.println("Consumed: " + message);
        notifyAll();
    }
}
