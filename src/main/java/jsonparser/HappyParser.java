package jsonparser;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class HappyParser extends Writer implements Parser {
    private Class clazz;

    public HappyParser() {
    }

    public HappyParser(Class clazz) {
        this.clazz = clazz;
    }

    public Object read(String json, Class c) {
        if (c == null)
            throw new RuntimeException("Class is null!");
        this.clazz = c;
        ReadContext readContext = new ReadContext(0, trimInput(json));
        WriteContext result = read(readContext);
        return result.getValue();
    }

    private String trimInput(String json) {
        json = json.replaceAll("\\s+", "");
        String trim = json.trim()
                .replace("\n", "")
                .replace("\r", "");
        return trim;
    }

    @Override
    public WriteContext read(ReadContext readContext) {
        Object instance = null;
        Constructor constructor = getNoArgConstructor(this.clazz);
        moveToStart(readContext);
        try {
            instance = constructor.newInstance();
            while (!readContext.ended()) {
                WriteContext keyOutput = readKey(readContext);
                Field field = this.clazz.getDeclaredField(keyOutput.getValue().toString());
                Class childClass = field.getType();

                moveToValue(readContext);
                Writer writer = WriterFactory.getWriter(readContext, childClass);
                WriteContext valueOutput = writer.read(readContext);

                field.setAccessible(true);
                field.set(instance, valueOutput.getValue());
                moveToKey(readContext);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Make sure class properties/constructor is accessible");
        } catch (InstantiationException e) {
            throw new RuntimeException("Make sure class properties/constructor is accessible");
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Make sure class properties/constructor is accessible");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("input json doesn't match class declaration");
        }
        return new WriteContext(readContext.idx, instance);
    }

    private Constructor getNoArgConstructor(Class clazz) {
        Constructor[] constructors = clazz.getConstructors();
        Constructor cons = null;
        for (int i = 0; i < constructors.length; i++)
            if (constructors[i].getGenericParameterTypes().length == 0)
                cons = constructors[i];
        if (cons == null)
            throw new RuntimeException(String.format("%s class should have no arg constructor", clazz.getName()));
        cons.setAccessible(true);
        return cons;
    }

    public WriteContext readKey(ReadContext readContext) {
        StringBuilder key = new StringBuilder();
        while (readContext.readNext() != SpecialCharType.QUOT.getValue()) {
            key.append(readContext.readNext());
            readContext.goNext();
        }
        return new WriteContext(readContext.getIdx(), key);
    }

    private void moveToStart(ReadContext readContext) {
        if (readContext.readNext() == SpecialCharType.OBJECT.getValue())
            readContext.idx++;
        if (readContext.readNext() == SpecialCharType.QUOT.getValue())
            readContext.idx++;
    }

    private void moveToKey(ReadContext readContext) {
        if (readContext.readNext() == SpecialCharType.QUOT.getValue())
            readContext.goNext();
        if (readContext.readNext() == SpecialCharType.END_ARRAY.getValue())
            readContext.goNext();
        if (readContext.readNext() == SpecialCharType.END_OBJECT.getValue())
            readContext.goNext();
        if (!readContext.ended()) {
            if (readContext.readNext() == SpecialCharType.COMMA.getValue())
                readContext.goNext();
            if (readContext.readNext() == SpecialCharType.QUOT.getValue())
                readContext.goNext();
        }
    }

    private void moveToValue(ReadContext readContext) {
        if (readContext.readNext() == SpecialCharType.QUOT.getValue())
            readContext.goNext();
        if (readContext.readNext() == SpecialCharType.COLON.getValue())
            readContext.goNext();
    }
}
