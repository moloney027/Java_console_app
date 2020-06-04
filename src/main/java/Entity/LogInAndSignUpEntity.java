package Entity;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "LogInAndSignUp", schema = "dbo", catalog = "LibrarySystem")
public class LogInAndSignUpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "Login_", nullable = false, length = 800)
    private String Login_;
    @Basic
    @Column(name = "Password_", nullable = false, length = 800)
    private String Password_;

    public LogInAndSignUpEntity() {
    }

    public LogInAndSignUpEntity(int loginId, String login_, String password_) {
        this.id = loginId;
        Login_ = login_;
        Password_ = password_;
    }

    public int getId() {
        return id;
    }

    public void setId(int loginId) {
        this.id = loginId;
    }


    public String getLogin_() {
        return Login_;
    }

    public void setLogin_(String login_) {
        Login_ = login_;
    }


    public String getPassword_() {
        return Password_;
    }

    public void setPassword_(String password_) {
        Password_ = DigestUtils.md5Hex(password_);
    }


    @Override
    public String toString() {
        return "LogInAndSignUpEntity{" +
                "loginId=" + id +
                ", Login_='" + Login_ + '\'' +
                ", Password_='" + Password_ + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogInAndSignUpEntity)) return false;

        LogInAndSignUpEntity that = (LogInAndSignUpEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(Login_, that.Login_)) return false;
        return Objects.equals(Password_, that.Password_);
    }


    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (Login_ != null ? Login_.hashCode() : 0);
        result = 31 * result + (Password_ != null ? Password_.hashCode() : 0);
        return result;
    }
}
