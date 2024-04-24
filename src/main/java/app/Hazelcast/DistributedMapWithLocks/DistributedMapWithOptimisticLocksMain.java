package app.Hazelcast.DistributedMapWithLocks;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Objects;
import java.util.Scanner;

public class DistributedMapWithOptimisticLocksMain {
    public static void main(String[] args) {
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("Distributed-map");
        helloWorldConfig.getNetworkConfig().setPort(5701);
        helloWorldConfig.getNetworkConfig().getRestApiConfig().setEnabled(true);
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);
        IMap<String, Integer> map = hz.getMap("distributed-map");
        String key = "key";
        map.putIfAbsent(key, 0);
        for ( int k = 0; k < 10_000; k++ ) {
            for (; ; ) {
                Integer oldValue = map.get( key );
                Integer newValue = oldValue;
                newValue++;
                if ( map.replace( key, oldValue, newValue ) )
                    break;
            }
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
