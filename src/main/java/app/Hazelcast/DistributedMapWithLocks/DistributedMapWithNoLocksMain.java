package app.Hazelcast.DistributedMapWithLocks;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Objects;
import java.util.Scanner;

public class DistributedMapWithNoLocksMain {
    public static void main(String[] args) {
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("Distributed-map");
        helloWorldConfig.getNetworkConfig().setPort(5701);
        helloWorldConfig.getNetworkConfig().getRestApiConfig().setEnabled(true);
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);
        IMap<String, Integer> map = hz.getMap("distributed-map");
        map.putIfAbsent("key", 0);
        for ( int k = 0; k < 10_000; k++ ) {
            var value = map.get( "key" );
            value++;
            map.put( "key", value );
        }

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Get key: ");
            String userInput = scanner.next();
            if (Objects.equals(userInput, "exit")){
                break;
            }
            System.out.println("Value: " + map.get("key"));
        }
        scanner.close();
    }
}

