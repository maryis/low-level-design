package jsonparser;

import java.lang.reflect.InvocationTargetException;

public abstract class Writer {
    abstract WriteContext read(ReadContext readContext) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException;
}
