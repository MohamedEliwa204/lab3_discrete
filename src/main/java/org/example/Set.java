package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Set {
    private Map<String, Integer> index;
    private BitOps bitOps;
    private List<String> universe;
    private int set;

    public Set(List<String> universe) {
        this.universe = new ArrayList<>(universe); // to be safe
        this.set = 0;
        this.bitOps = new BitOps();
        index = new HashMap<>();

        for (int i = 0; i < universe.size(); i++) {
            index.put(universe.get(i), i);
        }

    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getSet() {
        return this.set;
    }

//    // zero-based
//    private int getIdx(String element) {
//        int idx = universe.indexOf(element);
//        if (idx == -1) {
//            throw new IllegalArgumentException("Element '" + element + "' is not in the defined Universe.");
//        }
//        return idx;
//    }

    public void addString(String element) {
        int idx = index.get(element);
        set = bitOps.setBit(set, idx);
    }

    public Set union(Set anotehrSet) {
        Set result = new Set(universe);
        result.setSet(this.set | anotehrSet.getSet());
        return result;
    }

    public Set intersiction(Set anotherSet) {
        Set result = new Set(universe);
        result.setSet(this.set & anotherSet.getSet());
        return result;
    }

    public Set complement() {
        Set result = new Set(universe);
        int mask = (1 << universe.size()) - 1;
        result.setSet(~this.set & mask);
        return result;
    }

    public Set difference(Set anotherSet) {
        Set result = new Set(universe);
        result.setSet(this.set & ~anotherSet.getSet());
        return result;
    }

    public int cardinality() {
        return Integer.bitCount(this.set);
    }

    public List<String> getElements() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < universe.size(); i++) {
            if ((set & (1 << i)) != 0) {
                result.add(universe.get(i));
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return getElements().toString();
    }

}
