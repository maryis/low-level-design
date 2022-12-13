package jsonparser;

import java.util.Arrays;

public class WriterFactory {

    public static Writer getWriter(ReadContext readContext, Class clazz) {
        if (clazz.getName().contains("int")) {
            return new NumWriter();
        }
        if (clazz.getName().contains("String")) {
            readContext.goNext();//"
            return new StringWriter();
        }
        if (clazz == Arrays.class) {
            readContext.goNext();//"["
            return new HappyParser();
        }
        return new HappyParser(clazz);
    }
}
