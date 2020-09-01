package team.weacsoft;

import java.util.ArrayList;

public class MyTest {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        System.out.println(strings.stream().anyMatch(s -> s.equals("5")));

    }
}
