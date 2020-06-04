package Service;

import Entity.AuthorEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorService extends AbstractService<AuthorEntity> {

    public AuthorService() {
        super(new AuthorEntity());
    }

    public List<AuthorEntity> findByName(String fullName) {
        List<AuthorEntity> listAuth = repository.findAll();
        return listAuth.stream().filter(auth -> auth.getFullName().equals(fullName)).collect(Collectors.toList());
    }

    public List<AuthorEntity> findByDate(Date date) {
        List<AuthorEntity> listAuth = repository.findAll();
        return listAuth.stream().filter(auth -> auth.getDateOfBirth().equals(date)).collect(Collectors.toList());
    }

    public List<AuthorEntity> findByPlace(String place) {
        List<AuthorEntity> listAuth = repository.findAll();
        return listAuth.stream().filter(auth -> auth.getPlaceOfBirth().equals(place)).collect(Collectors.toList());
    }
}
