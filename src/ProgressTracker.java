package components.workoutsession;

/**
 * Demonstration of WorkoutSession as a week-over-week progress tracker.
 * This use case shows how a coach or app might compare two sessions for the
 * same athlete and give feedback on which exercises improved, regressed, or
 * stayed the same.
 *
 * <p>This maps to a real-world scenario: an athlete wants to know whether
 * they are making measurable progress across training weeks.
 *
 * @author Tiyash Ghosh
 */
public final class ProgressTracker {

    /**
     * Private constructor to prevent instantiation.
     */
    private ProgressTracker() {
    }

    /**
     * Main method demonstrating session-to-session progress comparison.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {

        // --- Week 1 session ---
        WorkoutSession week1 = new WorkoutSession1L();
        week1.addSet("Squat", 5, 100);
        week1.addSet("Squat", 5, 100);
        week1.addSet("Squat", 5, 100);
        week1.addSet("Bench", 8, 60);
        week1.addSet("Bench", 8, 60);
        week1.addSet("Deadlift", 5, 120);

        // --- Week 2 session ---
        WorkoutSession week2 = new WorkoutSession1L();
        week2.addSet("Squat", 5, 100);
        week2.addSet("Squat", 5, 105); // slight load increase
        week2.addSet("Squat", 5, 105);
        week2.addSet("Bench", 8, 60);  // same load
        week2.addSet("Bench", 6, 60);  // fewer reps - regression in volume
        week2.addSet("Deadlift", 5, 130); // heavier - clear PR

        // --- Comparison header ---
        System.out.println("=== Week-Over-Week Progress Report ===");
        System.out.println();
        System.out.println("Week 1 total sets: " + week1.size());
        System.out.println("Week 2 total sets: " + week2.size());
        System.out.println();

        // --- Per-exercise breakdown ---
        String[] exercises = {"Squat", "Bench", "Deadlift"};

        for (String exercise : exercises) {
            compareExercise(week1, week2, exercise);
        }

        // --- Overall verdict ---
        System.out.println("=== Overall Verdict ===");
        if (week2.progressedFrom(week1)) {
            System.out.println("Great work! Week 2 shows improvement over Week 1.");
        } else {
            System.out.println("Week 2 did not show clear progress over Week 1. "
                    + "Consider adjusting your programming.");
        }
    }

    /**
     * Compares performance on one exercise across two workout sessions and
     * prints a detailed report with coaching feedback.
     *
     * @param previous
     *            the earlier workout session
     * @param current
     *            the more recent workout session
     * @param exercise
     *            the exercise name to compare
     * @requires previous is not null and current is not null and exercise is not empty
     */
    private static void compareExercise(WorkoutSession previous,
            WorkoutSession current, String exercise) {

        System.out.println("--- " + exercise + " ---");

        boolean inPrevious = previous.hasExercise(exercise);
        boolean inCurrent = current.hasExercise(exercise);

        if (!inPrevious && !inCurrent) {
            System.out.println("  Not performed in either week.");
            System.out.println();
            return;
        }
        if (!inPrevious) {
            System.out.println("  New exercise this week!");
            System.out.println();
            return;
        }
        if (!inCurrent) {
            System.out.println("  Skipped this week.");
            System.out.println();
            return;
        }

        int prevVolume = previous.totalVolume(exercise);
        int currVolume = current.totalVolume(exercise);
        int prevBest = previous.bestSetLoad(exercise);
        int currBest = current.bestSetLoad(exercise);

        System.out.printf("  Volume:    %d -> %d kg%n", prevVolume, currVolume);
        System.out.printf("  Best load: %d -> %d kg%n", prevBest, currBest);

        if (currBest > prevBest) {
            System.out.println("  ✓ New load PR!");
        } else if (currVolume > prevVolume) {
            System.out.println("  ✓ Volume increased.");
        } else if (currVolume < prevVolume) {
            System.out.println("  ✗ Volume dropped — monitor for fatigue.");
        } else {
            System.out.println("  = Maintained. Consider adding load next week.");
        }

        System.out.println();
    }
}