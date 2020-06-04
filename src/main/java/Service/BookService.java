package Service;

import Entity.BookEntity;

import java.util.List;
import java.util.stream.Collectors;

public class BookService extends AbstractService<BookEntity> {

    public BookService() {
        super(new BookEntity());
    }

    public List<BookEntity> findByTitle(String title) {
        List<BookEntity> listBooks = repository.findAll();
        return listBooks.stream().filter(book -> book.getTitle().equals(title)).collect(Collectors.toList());
    }

    public List<BookEntity> findByYear(int year) {
        List<BookEntity> listBooks = repository.findAll();
        return listBooks.stream().filter(book -> book.getYearOfWriting() == year).collect(Collectors.toList());
    }

    public List<BookEntity> findByLanguage(String language) {
        List<BookEntity> listBooks = repository.findAll();
        return listBooks.stream().filter(book -> book.getLanguageBook().equals(language)).collect(Collectors.toList());
    }
}
