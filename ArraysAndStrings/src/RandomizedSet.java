import java.util.*;

class RandomizedSet {
    Map<Integer,Integer> mapping;
    List<Integer> store;

    public static void main(String[] args) {

    }

    public RandomizedSet() {
        mapping = new HashMap<>();
        store = new ArrayList<>();
    }

    public boolean insert(int val) {
        if(mapping.containsKey(val))
            return false;
        else {
            store.add(val);
            mapping.put(val,store.size() - 1);
            return true;
        }
    }

    public boolean remove(int val) {
        if(!mapping.containsKey(val))
            return false;
        else {
            int index = mapping.get(val);
            store.set(index,store.get(store.size()-1));
            mapping.put(store.get(index),index);
            store.remove(store.size() - 1);
            mapping.remove(val);
            return true;
        }
    }

    public int getRandom() {
        Random rand = new Random();
        return store.get(rand.nextInt(store.size()));
    }


}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */