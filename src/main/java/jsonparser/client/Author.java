package jsonparser.client;

public class Author {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Author() {
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
