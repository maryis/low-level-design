package foodorder.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class GenericDao<T> {
    Map<UUID, T> db;

    public GenericDao() {
        this.db = new HashMap<>();
    }

    public Optional<T> get(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    public T saveOrUpdate(UUID id, T entity) {
        db.put(id, entity);
        return entity;
    }

    public int delete(UUID id) {
        if(db.containsKey(id)) {
            db.remove(id);
            return 1;
        }
        return -1;
    }

}
