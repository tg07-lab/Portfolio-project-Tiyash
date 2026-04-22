/**
 * Workout session component with secondary methods.
 *
 * @author Tiyash Ghosh
 */
public interface WorkoutSession extends WorkoutSessionKernel {

    boolean hasExercise(String exercise);

    int totalSets(String exercise);

    int totalVolume(String exercise);

    int bestSetLoad(String exercise);

    double averageLoad(String exercise);

    boolean progressedFrom(WorkoutSession previous);
}