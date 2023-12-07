package schule.unipassau;


import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Solution {
    // Cache Interface
    interface Cache {
        void insert(int key, int value);

        int get(int key);

        int getHits();

        int getMisses();
    }

    public static void main(String[] args) {
        new Solution().execute();
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int t = 0; t < testCases; t++) {
            int cacheSize = scanner.nextInt();
            int numCaches = scanner.nextInt();
            int numOperations = scanner.nextInt();
            Cache[] caches = new Cache[numCaches];
            String[] cacheNames = {"FIFO", "LRU", "MRU", "LFU"};
            for (int c = 0; c < numCaches; c++) {
                int cacheType = scanner.nextInt();
                switch (cacheType) {
                    case 0:
                        caches[c] = new FIFOCache(cacheSize);
                        break;
                    case 1:
                        caches[c] = new LRUCache(cacheSize);
                        break;
                    case 2:
                        caches[c] = new MRUCache(cacheSize);
                        break;
                    case 3:
                        caches[c] = new LFUCache(cacheSize);
                        break;
                }
            }
            for (int q = 0; q < numOperations; q++) {
                int operationType = scanner.nextInt();
                if (operationType == 0) {
                    int key = scanner.nextInt();
                    int value = scanner.nextInt();
                    for (Cache cache : caches) {
                        cache.insert(key, value);
                    }
                } else if (operationType == 1) {
                    int key = scanner.nextInt();

                    for (Cache cache : caches) {
                        int value = cache.get(key);
                        System.out.println(value == -1 ? "miss" : value);

                    }
                }

            }
            int bestCacheIndex = 0;
            for (int i = 0; i < caches.length; i++) {
                if (caches[i].getHits() > caches[bestCacheIndex].getHits()) {
                    bestCacheIndex = i;
                }
            }
            System.out.println(cacheNames[bestCacheIndex] + ": H:" + caches[bestCacheIndex].getHits() + " M:" + caches[bestCacheIndex].getMisses());
        }
        scanner.close();
    }


    // FIFO Cache
    class FIFOCache implements Cache {
        private Queue<Integer> queue;
        private Map<Integer, Integer> map;
        private int capacity;
        private int hits = 0;
        private int misses = 0;

        public FIFOCache(int capacity) {
            this.capacity = capacity;
            this.queue = new LinkedList<>();
            this.map = new HashMap<>();

        }

        @Override
        public void insert(int key, int value) {
            if (map.size() >= capacity) {
                int oldestKey = queue.poll();
                map.remove(oldestKey);
            }
            queue.add(key);
            map.put(key, value);
        }

        @Override
        public int get(int key) {
            if (map.containsKey(key)) {
                hits++;
                return map.get(key);
            } else {
                misses++;
                return -1;
            }
        }

        @Override
        public int getHits() {
            return hits;
        }

        @Override
        public int getMisses() {
            return misses;
        }
    }

    // LRU Cache
    class LRUCache implements Cache {
        private LinkedHashMap<Integer, Integer> map;
        private int capacity;
        private int hits = 0;
        private int misses = 0;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        @Override
        public void insert(int key, int value) {
            map.put(key, value);
        }

        @Override
        public int get(int key) {
            if (map.containsKey(key)) {
                hits++;
                return map.get(key);
            } else {
                misses++;
                return -1;
            }
        }

        @Override
        public int getHits() {
            return hits;
        }

        @Override
        public int getMisses() {
            return misses;
        }
    }

    // MRU Cache
    class MRUCache implements Cache {
        private Map<Integer, Integer> map;
        private int capacity;
        private int mostRecentKey;
        private int hits = 0;
        private int misses = 0;

        public MRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
        }

        @Override
        public void insert(int key, int value) {
            if (map.size() >= capacity) {
                map.remove(mostRecentKey);
            }
            map.put(key, value);
            mostRecentKey = key;
        }

        @Override
        public int get(int key) {
            if (map.containsKey(key)) {
                hits++;
                return map.get(key);
            } else {
                misses++;
                return -1;
            }
        }

        @Override
        public int getHits() {
            return hits;
        }

        @Override
        public int getMisses() {
            return misses;
        }
    }


    // LFU Cache
    class LFUCache implements Cache {
        private Map<Integer, Integer> map;
        private Map<Integer, Integer> freqMap;
        private int capacity;
        private int minFreq;
        private int hits = 0;
        private int misses = 0;


        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            this.freqMap = new HashMap<>();
        }

        @Override
        public void insert(int key, int value) {
            if (map.size() >= capacity) {
                for (int k : map.keySet()) {
                    if (freqMap.get(k) == minFreq) {
                        map.remove(k);
                        freqMap.remove(k);
                        break;
                    }
                }
            }
            map.put(key, value);
            freqMap.put(key, 1);
            minFreq = 1;
        }

        @Override
        public int get(int key) {
            if (map.containsKey(key)) {
                hits++;
                return map.get(key);
            } else {
                misses++;
                return -1;
            }
        }

        @Override
        public int getHits() {
            return hits;
        }

        @Override
        public int getMisses() {
            return misses;
        }
    }
}




