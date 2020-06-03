package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Set;

@JsonAutoDetect
@Entity
@Table(name = "Genre", schema = "dbo", catalog = "LibrarySystem")
public class GenreEntity {

    public GenreEntity() {
    }

    public GenreEntity(int genreId, String title) {
        this.id = genreId;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "Title", nullable = false, length = 800)
    private String title;

    @JsonBackReference
    @ManyToMany(mappedBy = "genresForBook")
    private Set<BookEntity> booksForGenre;


    public int getId() {
        return id;
    }

    public void setId(int genreId) {
        this.id = genreId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Set<BookEntity> getBooksForGenre() {
        return booksForGenre;
    }

    public void setBooksForGenre(Set<BookEntity> booksForGenre) {
        this.booksForGenre = booksForGenre;
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

        GenreEntity that = (GenreEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(title, that.title)) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
