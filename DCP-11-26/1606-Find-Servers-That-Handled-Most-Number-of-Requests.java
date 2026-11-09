import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        int n = arrival.length;
        int[] requestCount = new int[k];
        
        // TreeSet to store indices of available servers
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        // Min-heap to store busy servers: stores int[] {freeTime, serverIndex}
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        int maxRequests = 0;
        
        for (int i = 0; i < n; i++) {
            int currTime = arrival[i];
            int duration = load[i];
            
            // Free up servers that have completed their tasks by the current arrival time
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                availableServers.add(busyServers.poll()[1]);
            }
            
            // If all servers are busy, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Find the preferred server using (i % k)
            Integer target = availableServers.ceiling(i % k);
            
            // If no server >= (i % k) is available, wrap around to the first available server
            if (target == null) {
                target = availableServers.first();
            }
            
            // Assign the request to the target server
            availableServers.remove(target);
            busyServers.offer(new int[] { currTime + duration, target });
            
            requestCount[target]++;
            maxRequests = Math.max(maxRequests, requestCount[target]);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}