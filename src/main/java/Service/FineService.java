package Service;

import Entity.FineEntity;

import java.util.List;
import java.util.stream.Collectors;

public class FineService extends AbstractService<FineEntity> {

    public FineService() { super(new FineEntity()); }

    public List<FineEntity> findByAmount(int amount) {
        List<FineEntity> listFines = repository.findAll();
        return listFines.stream().filter(fine -> fine.getAmount() == amount).collect(Collectors.toList());
    }
}
