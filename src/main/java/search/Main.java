package search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

//Question:
//You are given an information on Cat object such as name,height and weight. For eg following:
//
//String[] names = {"a","b","c","d","e","f","g","h"};
//Integer[] height = {31,24,67,12,45,21,31,12};
//Integer[] weight = {120,124,160,130,175,120,124,142};
//
//You have to write a method which takes the following input and return the list of Cat objects which satisfies the input criteria. For eg
//
//searchCriteria could be : HEIGHT or WEIGHT
//searchValue could be : Integer value of either HEIGHT or WEIGHT
//symbol could be : "<" , ">", or "="
//
//public List<Cat> getCatNames(List<Cat> cats, String searchCriteria, Integer searchValue, String symbol) {
//}
public class Main {

    private static final Map<String, Map<String, BiPredicate<Cat, Integer>>> map = new HashMap<>();

    public static void main(String[] args) {
        List<Cat> data = generateSample(10);
        initialPredicates();

        data.stream().filter(cat ->
                getPredicate("HEIGHT", ">").test(cat, 10))
                .forEach(System.out::println);
        System.out.println("------------------------------");
        data.stream().filter(cat ->
                getPredicate("WEIGHT", ">").test(cat, 50))
                .forEach(System.out::println);
    }

    private static void initialPredicates() {
        BiPredicate<Cat, Integer> highBT = (cat, value) -> cat.h > value;
        BiPredicate<Cat, Integer> weightBT = (cat, value) -> cat.w > value;
        BiPredicate<Cat, Integer> highLT = (cat, value) -> cat.h < value;
        BiPredicate<Cat, Integer> weightLT = (cat, value) -> cat.w < value;
        BiPredicate<Cat, Integer> weightE = (cat, value) -> cat.w == value;
        BiPredicate<Cat, Integer> highE = (cat, value) -> cat.h == value;
        Map<String, BiPredicate<Cat, Integer>> h = new HashMap<>();
        h.put(">", highBT);
        h.put("<", highLT);
        h.put("=", highE);
        map.put("HEIGHT", h);
        Map<String, BiPredicate<Cat, Integer>> w = new HashMap<>();
        w.put(">", weightBT);
        w.put("<", weightLT);
        w.put("=", weightE);
        map.put("WEIGHT", w);
    }

    private static BiPredicate<Cat, Integer> getPredicate(String criteria, String symbol) {
        return map.get(criteria).get(symbol);
    }

    private static List<Cat> generateSample(int n) {
        List<Cat> cats = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            cats.add(new Cat(i + 2, (i + 1) * 10, "name" + i));
        return cats;
    }
}

class Cat {
    int h;
    int w;
    String name;

    public Cat(int h, int w, String name) {
        this.h = h;
        this.w = w;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "h=" + h +
                ", w=" + w +
                ", name='" + name + '\'' +
                '}';
    }
}
