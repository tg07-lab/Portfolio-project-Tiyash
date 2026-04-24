package components.workoutsession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test fixture for {@code WorkoutSessionSecondary} abstract class methods.
 *
 * <p>Tests cover: hasExercise, totalSets, totalVolume, bestSetLoad,
 * averageLoad, and progressedFrom. All tests are run via the concrete
 * {@code WorkoutSession1L} implementation. Each test verifies both the
 * return value and that the session state is restored after the call.
 *
 * @author Tiyash Ghosh
 */
public class WorkoutSessionTest {

    /*
     * -------------------------------------------------------------------------
     * hasExercise
     * -------------------------------------------------------------------------
     */

    /**
     * Tests hasExercise returns false on an empty session.
     */
    @Test
    public void testHasExerciseEmptySession() {
        WorkoutSession s = new WorkoutSession1L();

        assertFalse(s.hasExercise("Squat"));
        assertEquals(0, s.size()); // state unchanged
    }

    /**
     * Tests hasExercise returns true when exercise is present.
     */
    @Test
    public void testHasExerciseFound() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Bench", 8, 60);

        boolean result = s.hasExercise("Bench");

        assertTrue(result);
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests hasExercise returns false when exercise is not in session.
     */
    @Test
    public void testHasExerciseNotFound() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);

        assertFalse(s.hasExercise("Deadlift"));
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests hasExercise with multiple exercises only finds correct one.
     */
    @Test
    public void testHasExerciseMultipleExercises() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);
        s.addSet("Bench", 8, 60);
        s.addSet("Row", 10, 50);

        assertTrue(s.hasExercise("Bench"));
        assertFalse(s.hasExercise("Deadlift"));
        assertEquals(3, s.size()); // state unchanged
    }

    /*
     * -------------------------------------------------------------------------
     * totalSets
     * -------------------------------------------------------------------------
     */

    /**
     * Tests totalSets returns zero on empty session.
     */
    @Test
    public void testTotalSetsEmptySession() {
        WorkoutSession s = new WorkoutSession1L();

        assertEquals(0, s.totalSets("Squat"));
        assertEquals(0, s.size()); // state unchanged
    }

    /**
     * Tests totalSets returns zero when exercise is not in session.
     */
    @Test
    public void testTotalSetsExerciseNotPresent() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Bench", 8, 60);

        assertEquals(0, s.totalSets("Squat"));
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests totalSets returns one when exercise appears once.
     */
    @Test
    public void testTotalSetsOneSet() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Deadlift", 5, 140);

        assertEquals(1, s.totalSets("Deadlift"));
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests totalSets counts correctly when exercise appears multiple times.
     */
    @Test
    public void testTotalSetsMultipleSets() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);
        s.addSet("Squat", 5, 110);
        s.addSet("Squat", 3, 120);
        s.addSet("Bench", 8, 60);

        assertEquals(3, s.totalSets("Squat"));
        assertEquals(1, s.totalSets("Bench"));
        assertEquals(4, s.size()); // state unchanged
    }

    /*
     * -------------------------------------------------------------------------
     * totalVolume
     * -------------------------------------------------------------------------
     */

    /**
     * Tests totalVolume returns zero on empty session.
     */
    @Test
    public void testTotalVolumeEmptySession() {
        WorkoutSession s = new WorkoutSession1L();

        assertEquals(0, s.totalVolume("Squat"));
        assertEquals(0, s.size()); // state unchanged
    }

    /**
     * Tests totalVolume for one set is reps * load.
     */
    @Test
    public void testTotalVolumeOneSet() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Bench", 8, 60);

        // volume = 8 * 60 = 480
        assertEquals(480, s.totalVolume("Bench"));
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests totalVolume sums correctly across multiple sets.
     */
    @Test
    public void testTotalVolumeMultipleSets() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100); // 500
        s.addSet("Squat", 5, 110); // 550
        s.addSet("Squat", 3, 120); // 360

        // total = 1410
        assertEquals(1410, s.totalVolume("Squat"));
        assertEquals(3, s.size()); // state unchanged
    }

    /**
     * Tests totalVolume with zero load (bodyweight exercise).
     */
    @Test
    public void testTotalVolumeZeroLoad() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("PullUp", 10, 0);

        assertEquals(0, s.totalVolume("PullUp"));
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests totalVolume ignores sets of other exercises.
     */
    @Test
    public void testTotalVolumeIgnoresOtherExercises() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);
        s.addSet("Bench", 8, 60); // should not count toward Squat volume

        assertEquals(500, s.totalVolume("Squat"));
        assertEquals(2, s.size()); // state unchanged
    }

    /*
     * -------------------------------------------------------------------------
     * bestSetLoad
     * -------------------------------------------------------------------------
     */

    /**
     * Tests bestSetLoad returns correct load for single set.
     */
    @Test
    public void testBestSetLoadOneSet() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Deadlift", 3, 140);

        assertEquals(140, s.bestSetLoad("Deadlift"));
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests bestSetLoad returns heaviest load among multiple sets.
     */
    @Test
    public void testBestSetLoadMultipleSets() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);
        s.addSet("Squat", 3, 120);
        s.addSet("Squat", 1, 140);

        assertEquals(140, s.bestSetLoad("Squat"));
        assertEquals(3, s.size()); // state unchanged
    }

    /**
     * Tests bestSetLoad when all sets have the same load.
     */
    @Test
    public void testBestSetLoadAllSameLoad() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("OHP", 8, 40);
        s.addSet("OHP", 8, 40);
        s.addSet("OHP", 8, 40);

        assertEquals(40, s.bestSetLoad("OHP"));
        assertEquals(3, s.size()); // state unchanged
    }

    /**
     * Tests bestSetLoad ignores loads from other exercises.
     */
    @Test
    public void testBestSetLoadIgnoresOtherExercises() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Bench", 8, 60);
        s.addSet("Squat", 5, 200); // much heavier, should not affect Bench

        assertEquals(60, s.bestSetLoad("Bench"));
        assertEquals(2, s.size()); // state unchanged
    }

    /*
     * -------------------------------------------------------------------------
     * averageLoad
     * -------------------------------------------------------------------------
     */

    /**
     * Tests averageLoad for a single set equals its load exactly.
     */
    @Test
    public void testAverageLoadOneSet() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Row", 10, 50);

        assertEquals(50.0, s.averageLoad("Row"), 0.001);
        assertEquals(1, s.size()); // state unchanged
    }

    /**
     * Tests averageLoad with uniform loads returns that load.
     */
    @Test
    public void testAverageLoadUniform() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Curl", 12, 20);
        s.addSet("Curl", 12, 20);
        s.addSet("Curl", 12, 20);

        assertEquals(20.0, s.averageLoad("Curl"), 0.001);
        assertEquals(3, s.size()); // state unchanged
    }

    /**
     * Tests averageLoad with varying loads computes correctly.
     */
    @Test
    public void testAverageLoadVarying() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Bench", 5, 60);
        s.addSet("Bench", 5, 80);
        s.addSet("Bench", 5, 100);

        // average = (60 + 80 + 100) / 3 = 80.0
        assertEquals(80.0, s.averageLoad("Bench"), 0.001);
        assertEquals(3, s.size()); // state unchanged
    }

    /**
     * Tests averageLoad ignores sets of other exercises.
     */
    @Test
    public void testAverageLoadIgnoresOtherExercises() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);
        s.addSet("Deadlift", 3, 200); // should not affect Squat average

        assertEquals(100.0, s.averageLoad("Squat"), 0.001);
        assertEquals(2, s.size()); // state unchanged
    }

    /*
     * -------------------------------------------------------------------------
     * progressedFrom
     * -------------------------------------------------------------------------
     */

    /**
     * Tests progressedFrom returns false when compared to an identical session.
     */
    @Test
    public void testProgressedFromIdenticalSession() {
        WorkoutSession current = new WorkoutSession1L();
        WorkoutSession previous = new WorkoutSession1L();

        current.addSet("Squat", 5, 100);
        previous.addSet("Squat", 5, 100);

        assertFalse(current.progressedFrom(previous));
    }

    /**
     * Tests progressedFrom returns true when current has higher load.
     */
    @Test
    public void testProgressedFromHigherLoad() {
        WorkoutSession current = new WorkoutSession1L();
        WorkoutSession previous = new WorkoutSession1L();

        current.addSet("Bench", 5, 80);
        previous.addSet("Bench", 5, 60);

        assertTrue(current.progressedFrom(previous));
    }

    /**
     * Tests progressedFrom returns true when current has more volume.
     */
    @Test
    public void testProgressedFromHigherVolume() {
        WorkoutSession current = new WorkoutSession1L();
        WorkoutSession previous = new WorkoutSession1L();

        current.addSet("Deadlift", 6, 100); // 600
        previous.addSet("Deadlift", 5, 100); // 500

        assertTrue(current.progressedFrom(previous));
    }

    /**
     * Tests progressedFrom returns false when previous was entirely different
     * exercises (no shared exercises to compare).
     */
    @Test
    public void testProgressedFromNoSharedExercises() {
        WorkoutSession current = new WorkoutSession1L();
        WorkoutSession previous = new WorkoutSession1L();

        current.addSet("Squat", 5, 100);
        previous.addSet("Bench", 8, 60);

        assertFalse(current.progressedFrom(previous));
    }

    /**
     * Tests progressedFrom does not modify either session's state.
     */
    @Test
    public void testProgressedFromDoesNotModifyState() {
        WorkoutSession current = new WorkoutSession1L();
        WorkoutSession previous = new WorkoutSession1L();

        current.addSet("Squat", 5, 100);
        previous.addSet("Squat", 5, 90);

        current.progressedFrom(previous);

        assertEquals(1, current.size());
        assertEquals(1, previous.size());
    }
}