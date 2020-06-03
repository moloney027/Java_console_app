package Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Set;

@JsonAutoDetect
@Entity
@Table(name = "Readers", schema = "dbo", catalog = "LibrarySystem")
public class ReadersEntity {

    public ReadersEntity() {
    }

    public ReadersEntity(int libraryCard, String fullName, int age, String addressReader) {
        this.id = libraryCard;
        this.fullName = fullName;
        this.age = age;
        this.addressReader = addressReader;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Basic
    @Column(name = "FullName", nullable = false, length = 800)
    private String fullName;

    @Basic
    @Column(name = "Age", nullable = false)
    private int age;

    @Basic
    @Column(name = "AddressReader", nullable = false, length = 800)
    private String addressReader;

    @JsonIgnore
    @OneToMany(mappedBy = "readerForIssuance", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookIssuanceEntity> allIssuanceForReader;


    public int getId() { return id; }

    public void setId(int libraryCard) { this.id = libraryCard; }


    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }


    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }


    public String getAddressReader() { return addressReader; }

    public void setAddressReader(String addressReader) { this.addressReader = addressReader; }


    public Set<BookIssuanceEntity> getAllIssuanceForReader() { return allIssuanceForReader; }

    public void setAllIssuanceForReader(Set<BookIssuanceEntity> allIssuanceForReader) {
        this.allIssuanceForReader = allIssuanceForReader;
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

        ReadersEntity that = (ReadersEntity) o;

        if (id != that.id) return false;
        if (age != that.age) return false;
        if (!Objects.equals(fullName, that.fullName)) return false;
        if (!Objects.equals(addressReader, that.addressReader))
            return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (addressReader != null ? addressReader.hashCode() : 0);
        return result;
    }
}
