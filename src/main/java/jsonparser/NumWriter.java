package jsonparser;

import java.lang.reflect.InvocationTargetException;

public class NumWriter extends Writer {

    @Override
    WriteContext read(ReadContext readContext) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        StringBuilder num = new StringBuilder();
        while (readContext.readNext() != SpecialCharType.END_OBJECT.getValue()
                && readContext.readNext() != SpecialCharType.COMMA.getValue()) {
            num.append(readContext.readNext());
            readContext.goNext();
        }
        return new WriteContext(readContext.getIdx(), Integer.parseInt(num.toString()));
    }

}
