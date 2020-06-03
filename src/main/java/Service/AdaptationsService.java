package Service;

import Entity.AdaptationsEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AdaptationsService extends AbstractService<AdaptationsEntity> {

    public AdaptationsService() {
        super(new AdaptationsEntity());
    }

    public List<AdaptationsEntity> findByType(String type) {
        List<AdaptationsEntity> listAdapt = repository.findAll();
        return listAdapt.stream().filter(adapt -> adapt.getTypeAdaptation().equals(type)).collect(Collectors.toList());
    }

    public List<AdaptationsEntity> findByYear(int year) {
        List<AdaptationsEntity> listAdapt = repository.findAll();
        return listAdapt.stream().filter(adapt -> adapt.getYear() == year).collect(Collectors.toList());
    }

    public List<AdaptationsEntity> findByCountry(String country) {
        List<AdaptationsEntity> listAdapt = repository.findAll();
        return listAdapt.stream().filter(adapt -> country.equals(adapt.getCountry())).collect(Collectors.toList());
    }
}
