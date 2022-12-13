package jsonparser;

public interface Parser<T> {
    T read(String json, Class<T> clazz);
}
