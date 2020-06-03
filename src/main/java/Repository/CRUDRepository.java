package Repository;

import java.util.List;

public interface CRUDRepository <T> {
    List<T> findAll();
    T find(int id);
    void save(T t);
    void delete(T t);
    void delete(int id);
    void update(T t);
}
