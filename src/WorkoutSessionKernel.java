package components.workoutsession;

import components.standard.Standard;

/**
 * Workout session kernel component with primary methods.
 *
 * @author Tiyash Ghosh
 *
 * @mathmodel type WorkoutSessionKernel is modeled by a sequence of workout sets
 * @initially <pre>
 * default constructor:
 * ensures
 *   this = empty sequence
 * </pre>
 */
public interface WorkoutSessionKernel extends Standard<WorkoutSession> {

    /**
     * Adds a set to this workout session.
     *
     * @param exercise
     *            the exercise name
     * @param reps
     *            the number of reps
     * @param load
     *            the load used
     * @updates this
     * @requires exercise is not empty and reps > 0 and load >= 0
     * @ensures this has one more set with the given exercise, reps, and load
     */
    void addSet(String exercise, int reps, int load);

    /**
     * Removes one set for the given exercise from this workout session.
     *
     * @param exercise
     *            the exercise name
     * @updates this
     * @requires exercise is not empty and this has at least one set for exercise
     * @ensures this has one fewer set for exercise
     */
    void removeSet(String exercise);

    /**
     * Reports the total number of sets in this workout session.
     *
     * @return the total number of sets
     * @ensures size = the total number of sets in this
     */
    int size();

    /**
     * Reports whether this workout session has no sets.
     *
     * @return true if this has no sets, false otherwise
     * @ensures isEmpty = (this has no sets)
     */
    boolean isEmpty();
}