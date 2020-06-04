package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

@JsonAutoDetect
@Entity
@Table(name = "BookCopy", schema = "dbo", catalog = "LibrarySystem")
public class BookCopyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BookID")
    private BookEntity bookForCopy;
    @JsonBackReference
    @OneToMany(mappedBy = "copyForIssuance", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookIssuanceEntity> allIssuanceForCopy;

    public BookCopyEntity() {
    }

    public BookCopyEntity(int bookCopyId) {
        this.id = bookCopyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int bookCopyId) {
        this.id = bookCopyId;
    }


    public BookEntity getBookForCopy() {
        return bookForCopy;
    }

    public void setBookForCopy(BookEntity bookForCopy) {
        this.bookForCopy = bookForCopy;
    }


    public Set<BookIssuanceEntity> getAllIssuanceForCopy() {
        return allIssuanceForCopy;
    }

    public void setAllIssuanceForCopy(Set<BookIssuanceEntity> allIssuanceForCopy) {
        this.allIssuanceForCopy = allIssuanceForCopy;
    }


    public String toJSON() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(df);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }


    @Override
    public String toString() {
        try {
            return toJSON();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Не удалось преобразовать к JSON";
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCopyEntity that = (BookCopyEntity) o;

        if (id != that.id) return false;

        return true;
    }


    @Override
    public int hashCode() {
        return id;
    }
}
