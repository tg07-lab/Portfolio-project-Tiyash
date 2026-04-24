package components.workoutsession;

/**
 * Demonstration of WorkoutSession as a workout logger. This use case shows
 * how a user might log a full lifting session and then print a per-exercise
 * summary including total volume, best load, and average load.
 *
 * <p>This maps to a real-world scenario: an athlete finishes a session and
 * wants a quick breakdown of performance for each exercise they performed.
 *
 * @author Tiyash Ghosh
 */
public final class WorkoutLogger {

    /**
     * Private constructor to prevent instantiation.
     */
    private WorkoutLogger() {
    }

    /**
     * Main method demonstrating workout logging and per-exercise reporting.
     *
     * @param args
     *            command-line arguments (not used)
     */
    public static void main(String[] args) {

        // --- Log the session ---
        WorkoutSession session = new WorkoutSession1L();

        // Squat: progressive loading (pyramid up)
        session.addSet("Squat", 5, 100);
        session.addSet("Squat", 5, 110);
        session.addSet("Squat", 3, 120);
        session.addSet("Squat", 1, 130);

        // Bench press: moderate volume
        session.addSet("Bench", 8, 60);
        session.addSet("Bench", 8, 65);
        session.addSet("Bench", 6, 70);

        // Barbell row: accessory work
        session.addSet("Row", 10, 50);
        session.addSet("Row", 10, 55);

        // --- Print summary ---
        System.out.println("=== Workout Summary ===");
        System.out.println("Total sets logged: " + session.size());
        System.out.println();

        printExerciseSummary(session, "Squat");
        printExerciseSummary(session, "Bench");
        printExerciseSummary(session, "Row");
    }

    /**
     * Prints a formatted performance summary for one exercise in the given
     * workout session.
     *
     * @param session
     *            the workout session to query
     * @param exercise
     *            the exercise name to report on
     * @requires session is not null and exercise is not empty
     */
    private static void printExerciseSummary(WorkoutSession session,
            String exercise) {
        if (!session.hasExercise(exercise)) {
            System.out.println(exercise + ": not performed this session.");
            return;
        }

        int sets = session.totalSets(exercise);
        int volume = session.totalVolume(exercise);
        int best = session.bestSetLoad(exercise);
        double avg = session.averageLoad(exercise);

        System.out.println("--- " + exercise + " ---");
        System.out.println("  Sets:         " + sets);
        System.out.println("  Total volume: " + volume + " kg");
        System.out.println("  Best load:    " + best + " kg");
        System.out.printf("  Avg load:     %.1f kg%n", avg);
        System.out.println();
    }
}