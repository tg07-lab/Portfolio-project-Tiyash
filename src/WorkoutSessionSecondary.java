package components.workoutsession;

/**
 * Secondary methods for WorkoutSession.
 *
 * @author Tiyash Ghosh
 */
public abstract class WorkoutSessionSecondary implements WorkoutSession {

    @Override
    public boolean hasExercise(String exercise) {
        assert exercise.length() > 0;

        boolean found = false;
        int n = this.size();

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            if (current.exercise.equals(exercise)) {
                found = true;
            }

            this.addSet(current.exercise, current.reps, current.load);
        }

        return found;
    }

    @Override
    public int totalSets(String exercise) {
        assert exercise.length() > 0;

        int count = 0;
        int n = this.size();

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            if (current.exercise.equals(exercise)) {
                count++;
            }

            this.addSet(current.exercise, current.reps, current.load);
        }

        return count;
    }

    @Override
    public int totalVolume(String exercise) {
        assert exercise.length() > 0;

        int total = 0;
        int n = this.size();

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            if (current.exercise.equals(exercise)) {
                total += current.reps * current.load;
            }

            this.addSet(current.exercise, current.reps, current.load);
        }

        return total;
    }

    @Override
    public int bestSetLoad(String exercise) {
        assert exercise.length() > 0;

        int best = 0;
        int n = this.size();

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            if (current.exercise.equals(exercise) && current.load > best) {
                best = current.load;
            }

            this.addSet(current.exercise, current.reps, current.load);
        }

        return best;
    }

    @Override
    public double averageLoad(String exercise) {
        assert exercise.length() > 0;

        int totalLoad = 0;
        int count = 0;
        int n = this.size();

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            if (current.exercise.equals(exercise)) {
                totalLoad += current.load;
                count++;
            }

            this.addSet(current.exercise, current.reps, current.load);
        }

        return (double) totalLoad / count;
    }

    @Override
    public boolean progressedFrom(WorkoutSession previous) {
        assert previous != null;

        int n = this.size();
        boolean improved = false;

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            this.addSet(current.exercise, current.reps, current.load);

            if (previous.hasExercise(current.exercise)) {
                if (this.bestSetLoad(current.exercise) > previous.bestSetLoad(current.exercise)
                        || this.totalVolume(current.exercise) > previous.totalVolume(current.exercise)) {
                    improved = true;
                }
            }
        }

        return improved;
    }

    @Override
    public String toString() {
        String result = "[";
        int n = this.size();

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            result += "(" + current.exercise + ", " + current.reps + ", " + current.load + ")";

            if (i < n - 1) {
                result += ", ";
            }

            this.addSet(current.exercise, current.reps, current.load);
        }

        result += "]";
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WorkoutSession)) {
            return false;
        }

        WorkoutSession other = (WorkoutSession) obj;

        if (this.size() != other.size()) {
            return false;
        }

        int n = this.size();

        for (int i = 0; i < n; i++) {
            WorkoutSet current = this.removeAnySet();

            if (!other.hasExercise(current.exercise)
                    || this.totalSets(current.exercise) != other.totalSets(current.exercise)
                    || this.totalVolume(current.exercise) != other.totalVolume(current.exercise)
                    || this.bestSetLoad(current.exercise) != other.bestSetLoad(current.exercise)) {

                this.addSet(current.exercise, current.reps, current.load);
                return false;
            }

            this.addSet(current.exercise, current.reps, current.load);
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.size();
    }
}