import java.util.ArrayList;
import java.util.List;

/**
 * Kernel implementation for WorkoutSession.
 *
 * @author Tiyash Ghosh
 *
 * @convention
 *   list is not null and all WorkoutSet objects in list have valid values
 *   (non-null and non-empty exercise name, reps > 0, load >= 0)
 *
 * @correspondence
 *   this = the sequence of WorkoutSet objects stored in list
 */
public class WorkoutSession1L extends WorkoutSessionSecondary {

    private List<WorkoutSet> list;

    private void createNewRep() {
        this.list = new ArrayList<>();
    }

    public WorkoutSession1L() {
        this.createNewRep();
    }

    @Override
    public void addSet(String exercise, int reps, int load) {
        assert exercise != null && !exercise.isEmpty() : "Violation of: exercise is not null or empty";
        assert reps > 0 : "Violation of: reps > 0";
        assert load >= 0 : "Violation of: load >= 0";
        this.list.add(new WorkoutSet(exercise, reps, load));
    }

    @Override
    public WorkoutSet removeAnySet() {
        assert this.list.size() > 0 : "Violation of: this is not empty";
        return this.list.remove(this.list.size() - 1);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public WorkoutSession newInstance() {
        return new WorkoutSession1L();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public void transferFrom(WorkoutSession source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof WorkoutSession1L : "Violation of: source is of dynamic type WorkoutSession1L";
        WorkoutSession1L localSource = (WorkoutSession1L) source;
        this.list = localSource.list;
        localSource.createNewRep();
    }
}