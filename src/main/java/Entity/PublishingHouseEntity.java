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
@Table(name = "PublishingHouse", schema = "dbo", catalog = "LibrarySystem")
public class PublishingHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "Title", nullable = false, length = 800)
    private String title;
    @Basic
    @Column(name = "DateOfEstablishment", nullable = true)
    private Integer dateOfEstablishment;
    @JsonBackReference
    @OneToMany(mappedBy = "publishingHouseForBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookEntity> booksForPublishingHouse;

    public PublishingHouseEntity() {
    }

    public PublishingHouseEntity(int publishingHouseId, String title, int dateOfEstablishment) {
        this.id = publishingHouseId;
        this.title = title;
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public int getId() {
        return id;
    }

    public void setId(int publishingHouseId) {
        this.id = publishingHouseId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(int dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }


    public Set<BookEntity> getBooksForPublishingHouse() {
        return booksForPublishingHouse;
    }

    public void setBooksForPublishingHouse(Set<BookEntity> booksForPublishingHouse) {
        this.booksForPublishingHouse = booksForPublishingHouse;
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

        PublishingHouseEntity that = (PublishingHouseEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(dateOfEstablishment, that.dateOfEstablishment))
            return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dateOfEstablishment != null ? dateOfEstablishment.hashCode() : 0);
        return result;
    }
}
