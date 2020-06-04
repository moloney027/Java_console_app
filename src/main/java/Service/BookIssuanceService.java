package Service;

import Entity.BookIssuanceEntity;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookIssuanceService extends AbstractService<BookIssuanceEntity> {

    public BookIssuanceService() {
        super(new BookIssuanceEntity());
    }

    public List<BookIssuanceEntity> findByDateOfIssue(Date dateIss) {
        List<BookIssuanceEntity> listIssues = repository.findAll();
        return listIssues.stream().filter(issue -> issue.getDateOfIssue().equals(dateIss)).collect(Collectors.toList());
    }

    public List<BookIssuanceEntity> findByDateOfComp(Date dateComp) {
        List<BookIssuanceEntity> listIssues = repository.findAll();
        return listIssues.stream().filter(issue -> issue.getDateOfCompletion().equals(dateComp)).collect(Collectors.toList());
    }
}
