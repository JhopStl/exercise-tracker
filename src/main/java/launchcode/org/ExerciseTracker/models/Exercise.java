package launchcode.org.ExerciseTracker.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import launchcode.org.ExerciseTracker.dto.ExerciseDTO;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "exercise")
public class Exercise implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "exerciseId")
    private Long exerciseId;

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true)
    @NotEmpty
    private String name;


    //for the wSession and Exercise relationship
    @ManyToOne //Many exercises to one session
    @JsonBackReference
    private wSession sesh;

    //for the Sets and Exercise relationship
    @OneToMany //One exercise to many sets
    @JoinColumn (name="exerciseId") //if it's different from the exercise instance declared in "sets" then it will create separate fields
    private List<Sets> setsList = new ArrayList<>();

    private static final long serialVersionUID = 954844545;

    //default constructor
    public Exercise (){}

    //constructor
    public Exercise (ExerciseDTO exerciseDTO) {
        this.name = exerciseDTO.getName();
    }

    //constructor
    public Exercise (String name) {
        this.name = name;
    }

    //method to check if is Exercise is new (checks if ID value is null)
    @Transient
    public boolean isNew() {return (this.exerciseId == null);}

    //getters and setters

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {this.exerciseId = exerciseId;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public wSession getwSession() {
        return sesh;
    }

    public void setwSession(wSession sesh) {
            this.sesh = sesh;
    }

    public List<Sets> getSetsList() {
        return setsList;
    }

    public void setSetsList(List<Sets> setsList) {
        this.setsList = setsList;
    }

    //adds a set to the List of sets
    public void addSets(Sets sets) {
        sets.setExercise(this);
        getSetsList().add(sets);
    }


    @Override
    public String toString() {
    return new ToStringCreator(this)
    .append("exerciseId", this.getExerciseId())
    .append("new", this.isNew())
    .append("name", this.getName())
    .toString();
    }


    public static Builder getBuilder(String name, wSession sesh) {
        return new Builder (name, sesh);
    }

    public static class Builder {

        private Exercise built;

        public Builder(String name, wSession sesh) {
            built = new Exercise();
            built.name = name;
            built.sesh = sesh;
        }

        public Exercise build() {return built;}
    }
}
