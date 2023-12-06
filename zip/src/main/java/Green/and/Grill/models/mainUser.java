package Green.and.Grill.models;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@MappedSuperclass
public abstract class mainUser {
    @Id
    @GeneratedValue
    private int id;
public mainUser(){}
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email. Try again.")
    @Size(min = 3, max = 45, message = "Email must be between 3 and 45 characters")
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 5, max = 20, message = "Invalid password. Must be between 5 and 30 characters.")
    private String password;
    @NotNull
    @NotBlank(message = "phone is required")
    private String phone;
    @NotNull
    @NotBlank(message = "address is required")
    private String address;
    @NotNull
    @NotBlank(message = "zip is required")
    private String zip ;
    @NotBlank(message = "City name is required")
    private String city;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public mainUser(String email, String password, String phone, String address, String zip, String city) {

        this.email = email;
        this.password = encoder.encode(password);
        this.phone = phone;
        this.address = address;
        this.zip=zip;
        this.city=city;
    }
    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, password);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        mainUser mainUser = (mainUser) o;
        return id == mainUser.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
