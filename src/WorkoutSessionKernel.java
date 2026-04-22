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
     * Workout set record type.
     */
    public static final class WorkoutSet {

        public final String exercise;
        public final int reps;
        public final int load;

        public WorkoutSet(String exerciseName, int repsCount, int loadAmount) {
            this.exercise = exerciseName;
            this.reps = repsCount;
            this.load = loadAmount;
        }
    }

    void addSet(String exercise, int reps, int load);

    WorkoutSet removeAnySet();

    int size();

    boolean isEmpty();
}