package Service;

import Repository.Repository;

import java.util.List;

public abstract class AbstractService<T> {

    protected final Repository<T> repository;

    public AbstractService(T t) {
        repository = new Repository<T>(t);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T find(int id) {
        return repository.find(id);
    }

    public void save(T t) {
        repository.save(t);
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public void update(T t) {
        repository.update(t);
    }
}
