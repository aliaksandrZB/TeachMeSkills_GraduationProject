package pl.demeshchik.firmassistant.FirmAssistant.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 100, message = "Имя дожно быть от 2 до 100 символов длиной")
    private String name;

    @Column(name = "lastname")
    @Size(max = 100, message = "Фамилия должна быть до 100 символов длиной")
    private String lastname;

    @Column(name = "add_info")
    @Size(max = 100, message = "Дополнительная информация не должна превышать 100 символов")
    private String addInfo;

    @Column(name = "phone_number")
    @NotEmpty
    @Size(max = 15, message = "Телефон не должен превышать 15 символов")
    private String phoneNumber;

    @Column(name = "email")
    @Email
    @Size(max = 15, message = "Телефон не должен превышать 15 символов")
    private String email;

    @ManyToMany
    @JoinTable(
            name = "Facility_Client",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id")
    )
    private List<Facility> facilities;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(name, client.name) && Objects.equals(lastname, client.lastname) && Objects.equals(addInfo, client.addInfo) && Objects.equals(phoneNumber, client.phoneNumber) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, addInfo, phoneNumber, email);
    }
}
