package Service;

import Entity.ReadersEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ReadersService extends AbstractService<ReadersEntity> {

    public ReadersService() { super(new ReadersEntity()); }

    public List<ReadersEntity> findByName(String name) {
        List<ReadersEntity> listReaders = repository.findAll();
        return listReaders.stream().filter(reader -> reader.getFullName().equals(name)).collect(Collectors.toList());
    }

    public List<ReadersEntity> findByAge(int age) {
        List<ReadersEntity> listReaders = repository.findAll();
        return listReaders.stream().filter(reader -> reader.getAge() == age).collect(Collectors.toList());
    }
}
