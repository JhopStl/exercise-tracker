package launchcode.org.ExerciseTracker.models;

import launchcode.org.ExerciseTracker.dto.SetsDTO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Sets implements Serializable {

    @Id
    @GeneratedValue
    @Column(name ="sets_id", nullable = false, insertable = true, updatable = true) //defining the column name and details
    private Long setsId;

    @Basic
    @Column(name="rep", nullable = false, insertable = true, updatable = true)
    @NotEmpty
    private int rep;

    @Basic
    @Column(name="weight", nullable = false, insertable = true, updatable = true)
    @NotEmpty
    private double weight;

    private static final long serialVersionUID = 445689846;


    @ManyToOne //many sets to one exercise
    @JoinColumn(name = "exercise_id", referencedColumnName = "id", nullable = false, insertable = true, updatable = true)
    private Exercise exercise;

    //constructor
    public Sets (SetsDTO setsDTO) {
        this.rep = setsDTO.getRep();
        this.weight = setsDTO.getWeight();
    }

    //default constructor
    public Sets () {}

    //method to check if Sets is new (checks if ID value is null)
    @Transient
    public boolean isNew() {return (this.setsId == null);}

    //getters and setters

    public Long getSetsId() { return setsId; }

    public void setSetsId(Long setsId) { this.setsId = setsId; }


    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
