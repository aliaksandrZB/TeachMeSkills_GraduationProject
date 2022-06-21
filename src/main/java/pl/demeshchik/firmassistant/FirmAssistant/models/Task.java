package pl.demeshchik.firmassistant.FirmAssistant.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Size(max = 1000, message = "Количество символов не может првышать 1000")
    @Column(name = "text")
    private String text;

    @Column(name = "is_done")
    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "facility_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "premise_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Premise premise;

    public Task() {
        isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Premise getPremise() {
        return premise;
    }

    public void setPremise(Premise premise) {
        this.premise = premise;
    }
}
