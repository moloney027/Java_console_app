package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Set;

@JsonAutoDetect
@Entity
@Table(name = "Author", schema = "dbo", catalog = "LibrarySystem")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "FullName", nullable = false, length = 800)
    private String fullName;
    @Basic
    @Column(name = "DateOfBirth", nullable = true)
    private Date dateOfBirth;
    @Basic
    @Column(name = "PlaceOfBirth", nullable = true, length = 800)
    private String placeOfBirth;
    @JsonBackReference
    @ManyToMany(mappedBy = "authorsForBook")
    private Set<BookEntity> booksForAuthor;

    public AuthorEntity() {
    }

    public AuthorEntity(int authorId, String fullName, Date dateOfBirth, String placeOfBirth) {
        this.id = authorId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int authorId) {
        this.id = authorId;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }


    public Set<BookEntity> getBooksForAuthor() {
        return booksForAuthor;
    }

    public void setBooksForAuthor(Set<BookEntity> booksForAuthor) {
        this.booksForAuthor = booksForAuthor;
    }


    public String toJSON() throws JsonProcessingException {
        return JsonSerializer.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
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

        AuthorEntity that = (AuthorEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(fullName, that.fullName)) return false;
        if (!Objects.equals(dateOfBirth, that.dateOfBirth)) return false;
        if (!Objects.equals(placeOfBirth, that.placeOfBirth)) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (placeOfBirth != null ? placeOfBirth.hashCode() : 0);
        return result;
    }
}
