package launchcode.org.ExerciseTracker.models.data;

import launchcode.org.ExerciseTracker.models.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//Data Access Object - Interface that allows us to access cheese classes and interact with the DB
//crud repository class specifies methods that will allow us to send and retrieve data from DB

@Repository
@Transactional
public interface WorkoutDao extends CrudRepository<Workout,Integer> {
}
