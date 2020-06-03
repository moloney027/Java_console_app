package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Set;

@JsonAutoDetect
@Entity
@Table(name = "Book", schema = "dbo", catalog = "LibrarySystem")
public class BookEntity {

    public BookEntity() {
    }

    public BookEntity(int bookId, String title, int yearOfWriting, String languageBook) {
        this.id = bookId;
        this.title = title;
        this.yearOfWriting = yearOfWriting;
        this.languageBook = languageBook;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "Title", nullable = false, length = 800)
    private String title;

    @Basic
    @Column(name = "YearOfWriting", nullable = false)
    private int yearOfWriting;

    @Basic
    @Column(name = "LanguageBook", nullable = false, length = 800)
    private String languageBook;

    @JsonBackReference
    @OneToMany(mappedBy = "bookForAdaptation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdaptationsEntity> adaptationsForBook;

    @JsonBackReference
    @OneToMany(mappedBy = "bookForCopy", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookCopyEntity> copiesForBook;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<GenreEntity> genresForBook;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AuthorEntity> authorsForBook;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PublishingHouseID")
    private PublishingHouseEntity publishingHouseForBook;


    public int getId() {
        return id;
    }

    public void setId(int bookId) {
        this.id = bookId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }


    public String getLanguageBook() {
        return languageBook;
    }

    public void setLanguageBook(String languageBook) {
        this.languageBook = languageBook;
    }


    public Set<AdaptationsEntity> getAdaptationsForBook() {
        return adaptationsForBook;
    }

    public void setAdaptationsForBook(Set<AdaptationsEntity> adaptationsForBook) {
        this.adaptationsForBook = adaptationsForBook;
    }


    public Set<BookCopyEntity> getCopiesForBook() {
        return copiesForBook;
    }

    public void setCopiesForBook(Set<BookCopyEntity> copiesForBook) {
        this.copiesForBook = copiesForBook;
    }


    public Set<GenreEntity> getGenresForBook() {
        return genresForBook;
    }

    public void setGenresForBook(Set<GenreEntity> genresForBook) {
        this.genresForBook = genresForBook;
    }


    public Set<AuthorEntity> getAuthorsForBook() {
        return authorsForBook;
    }

    public void setAuthorsForBook(Set<AuthorEntity> authorsForBook) {
        this.authorsForBook = authorsForBook;
    }


    public PublishingHouseEntity getPublishingHouseForBook() {
        return publishingHouseForBook;
    }

    public void setPublishingHouseForBook(PublishingHouseEntity publishingHouseForBook) {
        this.publishingHouseForBook = publishingHouseForBook;
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

        BookEntity that = (BookEntity) o;

        if (id != that.id) return false;
        if (yearOfWriting != that.yearOfWriting) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(languageBook, that.languageBook)) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + yearOfWriting;
        result = 31 * result + (languageBook != null ? languageBook.hashCode() : 0);
        return result;
    }
}
