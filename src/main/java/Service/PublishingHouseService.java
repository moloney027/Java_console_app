package Service;

import Entity.PublishingHouseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PublishingHouseService extends AbstractService<PublishingHouseEntity> {

    public PublishingHouseService() { super(new PublishingHouseEntity()); }

    public List<PublishingHouseEntity> findByTitle(String title) {
        List<PublishingHouseEntity> listpH = repository.findAll();
        return listpH.stream().filter(pH -> pH.getTitle().equals(title)).collect(Collectors.toList());
    }

    public List<PublishingHouseEntity> findByDate(int date) {
        List<PublishingHouseEntity> listpH = repository.findAll();
        return listpH.stream().filter(pH -> pH.getDateOfEstablishment() == date).collect(Collectors.toList());
    }
}
