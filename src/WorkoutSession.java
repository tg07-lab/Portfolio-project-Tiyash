package components.workoutsession;

/**
 * Workout session component with secondary methods.
 *
 * @author Tiyash Ghosh
 */
public interface WorkoutSession extends WorkoutSessionKernel {

    /**
     * Reports whether this workout session contains the given exercise.
     *
     * @param exercise
     *            the exercise name
     * @return true if exercise appears in this, false otherwise
     * @requires exercise is not empty
     * @ensures hasExercise = true iff this contains at least one set for exercise
     */
    boolean hasExercise(String exercise);

    /**
     * Reports the total number of sets for the given exercise.
     *
     * @param exercise
     *            the exercise name
     * @return the number of sets for exercise
     * @requires exercise is not empty
     * @ensures totalSets = the number of sets for exercise in this
     */
    int totalSets(String exercise);

    /**
     * Reports the total volume for the given exercise.
     *
     * @param exercise
     *            the exercise name
     * @return the total volume for exercise
     * @requires exercise is not empty
     * @ensures totalVolume = the sum of reps times load for all sets of exercise
     */
    int totalVolume(String exercise);

    /**
     * Reports the heaviest load used for the given exercise.
     *
     * @param exercise
     *            the exercise name
     * @return the greatest load used for exercise
     * @requires exercise is not empty and this has at least one set for exercise
     * @ensures bestSetLoad = the largest load used for exercise in this
     */
    int bestSetLoad(String exercise);

    /**
     * Reports the average load used for the given exercise.
     *
     * @param exercise
     *            the exercise name
     * @return the average load used for exercise
     * @requires exercise is not empty and this has at least one set for exercise
     * @ensures averageLoad = the average of all loads used for exercise in this
     */
    double averageLoad(String exercise);

    /**
     * Reports whether this workout session shows progress compared to a previous
     * workout session.
     *
     * @param previous
     *            the previous workout session
     * @return true if this shows progress compared to previous
     * @requires previous is not null
     * @ensures progressedFrom = true iff this shows improvement over previous
     */
    boolean progressedFrom(WorkoutSession previous);
}