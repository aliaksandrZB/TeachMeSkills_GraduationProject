package pl.demeshchik.firmassistant.FirmAssistant.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Facility")
public class Facility {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Страна должна быть от 2 до 100 символов")
    @Column(name = "country")
    private String country;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Город должен быть от 2 до 100 символов")
    @Column(name = "city")
    private String city;

    @Size(max = 15, message = "Почтовй индекс не должен превышать 15 символов")
    @Column(name = "postcode")
    private String postcode;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Улица должна быть от 2 до 100 символов")
    @Column(name = "street")
    private String street;

    @Size(max = 10, message = "Номер дома не должен перавышать 10 символов")
    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "notes")
    @Size(max = 10000)
    private String notes;

    @OneToMany(mappedBy = "facility")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Premise> premises;

    @ManyToMany(mappedBy = "facilities", fetch = FetchType.EAGER)
    private List<Client> clients;

    @OneToMany(mappedBy = "facility")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Task> tasks;


    public Facility() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Premise> getPremises() {
        return premises;
    }

    public void setPremises(List<Premise> premises) {
        this.premises = premises;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return id == facility.id && Objects.equals(country, facility.country) && Objects.equals(city, facility.city) && Objects.equals(postcode, facility.postcode) && Objects.equals(street, facility.street) && Objects.equals(houseNumber, facility.houseNumber) && Objects.equals(notes, facility.notes) && Objects.equals(premises, facility.premises);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, postcode, street, houseNumber, notes, premises);
    }
}
