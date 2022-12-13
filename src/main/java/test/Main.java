package test;

import java.util.ArrayList;
import java.util.List;

public class Main {

    static final List<Person> l=new ArrayList<>();
    static final Person p=new Person(333);
    public static void main(String[] args) {

        Student student=new Student("ddddd");
        student.m();

        Person p=new Person(4);
        System.out.println(p.name);

        l.add(new Person(3));
        l.add(new Person(23));
        l.forEach(a-> System.out.print(a.name));

        System.out.println(p.name);
        p.name=245;
        System.out.println(p.name);

    }
}
