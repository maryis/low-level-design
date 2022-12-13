package jsonparser;

public class StringWriter extends Writer {

    @Override
    public WriteContext read(ReadContext readContext) {
        StringBuilder str = new StringBuilder();
        while (readContext.readNext() != SpecialCharType.QUOT.getValue()) {
            str.append(readContext.readNext());
            readContext.goNext();
        }
        return new WriteContext(readContext.getIdx(), str.toString());
    }
}
