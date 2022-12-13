package test;

public class Student extends Person{

    private String family;
    //private int name;

    public Student(String family) {
        super(3);

        this.family = family;
        this.name=45;
    }

    public void m(){
        System.out.println(name);
        System.out.println(super.name);
    }
}
