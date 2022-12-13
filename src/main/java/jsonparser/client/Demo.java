package jsonparser.client;

import jsonparser.HappyParser;
import jsonparser.Parser;

public class Demo {

    public static void main(String[] args) {
        String json = "{ \"id\" : 123,\n" +
                "\"name\":   \"good book\",\n" +
                "\"author\" : { \"name\": \"ali\"}}";

        Parser parser = new HappyParser();
        Book book = (Book) parser.read(json, Book.class);
        System.out.println(book);
    }
}
