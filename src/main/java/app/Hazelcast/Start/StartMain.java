package app.Hazelcast.Start;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;

public class StartMain {
    public static void main(String[] args) {
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("hello-world");
        HazelcastInstance hz1 = Hazelcast.newHazelcastInstance(helloWorldConfig);
        HazelcastInstance hz2 = Hazelcast.newHazelcastInstance(helloWorldConfig);
        HazelcastInstance hz3 = Hazelcast.newHazelcastInstance(helloWorldConfig);
        Map<String, String> map1 = hz1.getMap("my-distributed-map");
        Map<String, String> map2 = hz2.getMap("my-distributed-map");
        Map<String, String> map3 = hz3.getMap("my-distributed-map");
        map1.put("1", "John");
        map2.put("2", "Mary");
        map3.put("3", "Jane");
        System.out.println(map1.get("1"));
        System.out.println(map1.get("2"));
        System.out.println(map1.get("3"));
    }
}