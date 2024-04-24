package app.Hazelcast.BoundedQueue;

import com.hazelcast.collection.IQueue;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class BoundedQueueWriter {
    public static void main(String[] args) {
        Config config = new Config();
        config.getQueueConfig("boundedQueue")
                .setBackupCount(1)
                .setMaxSize(10);
        config.getNetworkConfig().setPort(5701);
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        IQueue<Integer> queue = hazelcastInstance.getQueue("boundedQueue");
        for (int i = 1; i <= 100; i++) {
            queue.offer(i);
            System.out.println("Produced: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
