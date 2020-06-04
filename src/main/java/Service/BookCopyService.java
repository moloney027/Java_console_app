package Service;

import Entity.BookCopyEntity;

public class BookCopyService extends AbstractService<BookCopyEntity> {

    public BookCopyService() {
        super(new BookCopyEntity());
    }
}
