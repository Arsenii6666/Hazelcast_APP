package app.Hazelcast.BoundedQueue;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class BoundedQueueReader {
    public static void main(String[] args) {
        Config config = new Config();
        config.getQueueConfig("boundedQueue")
                .setBackupCount(1)
                .setMaxSize(10);

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        IQueue<Integer> queue = hazelcastInstance.getQueue("boundedQueue");
        while (true) {
            Integer value = queue.poll();
            if (value != null) {
                System.out.println("Consumer consumed: " + value);
            } else {
                System.out.println("Queue is empty");
                try {
                    Thread.sleep(500);
                    System.out.println("No data to read");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
