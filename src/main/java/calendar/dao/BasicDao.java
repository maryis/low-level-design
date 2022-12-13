package calendar.dao;

import java.util.*;

public class BasicDao<T> {
    private Map<String, T> db;

    public BasicDao() {
        this.db = new HashMap<>();
    }

    public Optional<T> get(String name) {
        return Optional.ofNullable(db.get(name));
    }

    public T saveOrUpdate(String name, T entity) {
        db.put(name, entity);
        return entity;
    }

    public void deleteByName(String name) {
        db.remove(name);
    }

    public List<T> getAll() {
        return new ArrayList<>(db.values());
    }
}
