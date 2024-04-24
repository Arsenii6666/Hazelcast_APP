package app.Hazelcast.DistributedMap;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Scanner;

public class DistributedMapNoData {
    public static void main(String[] args) {
        Config helloWorldConfig = new Config();
        helloWorldConfig.setClusterName("Distributed-map");
        helloWorldConfig.getNetworkConfig().setPort(5701);
        helloWorldConfig.getNetworkConfig().getRestApiConfig().setEnabled(true);
        HazelcastInstance hz = Hazelcast.newHazelcastInstance(helloWorldConfig);
        IMap<Integer, String> map = hz.getMap("distributed-map");
        for (int i=0; i<0; i++){
            map.put(i, Integer.toString(i));
        }
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Enter key: ");
            Integer userInput = scanner.nextInt();
            if (userInput==0){
                map.destroy();
                break;
            }
            System.out.println("Key: " + userInput + ", Value: " + map.get(userInput));
        }
        scanner.close();

    }
}
