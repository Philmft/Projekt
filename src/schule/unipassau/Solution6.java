package schule.unipassau;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Solution6 {

    interface Cache {
        void insert(int key, int value);

        int get(int key);

        int getHits();

        int getMisses();
    }

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
            if (!map.containsKey(key) && queue.size() >= capacity) {
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
            if (!map.containsKey(key) && map.size() >= capacity) {
                map.remove(mostRecentKey);
            }
            map.put(key, value);
            mostRecentKey = key;
        }

        @Override
        public int get(int key) {
            if (map.containsKey(key)) {
                hits++;
                mostRecentKey = key;
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


        class LFUCache implements Cache {
            private Map<Integer, Integer> map;
            private Map<Integer, Integer> freqMap;
            private Map<Integer, Long> timeMap;
            private int capacity;
            private int minFreq;
            private int hits = 0;
            private int misses = 0;

            public LFUCache(int capacity) {
                this.capacity = capacity;
                this.map = new HashMap<>();
                this.freqMap = new HashMap<>();
                this.timeMap = new HashMap<>();
            }

            @Override
            public void insert(int key, int value) {
                if (!map.containsKey(key) && map.size() >= capacity) {
                    int minKey = -1;
                    long minTime = Long.MAX_VALUE;
                    for (int k : map.keySet()) {
                        if (freqMap.get(k) == minFreq && timeMap.get(k) < minTime) {
                            minTime = timeMap.get(k);
                            minKey = k;
                        }
                    }
                    map.remove(minKey);
                    freqMap.remove(minKey);
                    timeMap.remove(minKey);
                }
                map.put(key, value);
                freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
                timeMap.put(key, System.currentTimeMillis());
                minFreq = Math.min(minFreq, freqMap.get(key));
            }

            @Override
            public int get(int key) {
                if (map.containsKey(key)) {
                    hits++;
                    freqMap.put(key, freqMap.get(key) + 1);
                    timeMap.put(key, System.currentTimeMillis());
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


        public void execute() {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());

        for (int t = 0; t < testCases; t++) {
            String[] scqDefinitions = scanner.nextLine().split(" ");
            int cacheSize = Integer.parseInt(scqDefinitions[0]);
            int numCaches = Integer.parseInt(scqDefinitions[1]);
            int numOperations = Integer.parseInt(scqDefinitions[2]);

            Cache[] caches = new Cache[numCaches];

            String[] cacheTypes = scanner.nextLine().split(" ");

            for (int c = 0; c < numCaches; c++) {
                int cacheType = Integer.parseInt(cacheTypes[c]);

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
                String[] operationLine = scanner.nextLine().split(" ");
                int operationType = Integer.parseInt(operationLine[0]);

                if (operationType == 0) {
                    int key = Integer.parseInt(operationLine[1]);
                    int value = Integer.parseInt(operationLine[2]);

                    for (int c = 0; c < caches.length; c++) {
                        caches[c].insert(key, value);
                    }

                } else if (operationType == 1) {
                    int key = Integer.parseInt(operationLine[1]);

                    for (int c = 0; c < caches.length; c++) {
                        int value = caches[c].get(key);
                        System.out.println(value == -1 ? "miss" : value);
                    }
                }
            }

            int bestCacheIndex = 0;

            for (int c = 1; c < caches.length; c++) {
                int scoreBest = caches[bestCacheIndex].getHits() - caches[bestCacheIndex].getMisses();
                int scoreCurrent = caches[c].getHits() - caches[c].getMisses();

                if (scoreCurrent > scoreBest) {
                    bestCacheIndex = c;
                }
            }

            System.out.println(getCacheType(caches[bestCacheIndex]) + ": H:" + caches[bestCacheIndex].getHits() + " M:" + caches[bestCacheIndex].getMisses());

        }
        scanner.close();
    }

    public String getCacheType(Cache cache) {
        if (cache instanceof FIFOCache) {
            return "FIFO";
        } else if (cache instanceof LRUCache) {
            return "LRU";
        } else if (cache instanceof MRUCache) {
            return "MRU";
        } else if (cache instanceof LFUCache) {
            return "LFU";
        } else {
            return "Unknown";
        }
    }

    public static void main(String[] args) {
        new Solution6().execute();
    }
}
