package Service;

import Entity.GenreEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GenreService extends AbstractService<GenreEntity> {

    public GenreService() { super(new GenreEntity()); }

    public List<GenreEntity> findByTitle(String title) {
        List<GenreEntity> listGenres = repository.findAll();
        return listGenres.stream().filter(genre -> genre.getTitle().equals(title)).collect(Collectors.toList());
    }
}
