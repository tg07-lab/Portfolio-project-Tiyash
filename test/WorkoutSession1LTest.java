package components.workoutsession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test fixture for {@code WorkoutSession1L} kernel methods.
 *
 * <p>Tests cover: addSet, removeAnySet, size, isEmpty, clear, newInstance,
 * and transferFrom. Each test verifies both the return value and the state
 * of the session after the operation using only kernel methods.
 *
 * @author Tiyash Ghosh
 */
public class WorkoutSession1LTest {

    /*
     * -------------------------------------------------------------------------
     * isEmpty
     * -------------------------------------------------------------------------
     */

    /**
     * Tests isEmpty on a newly constructed (empty) session.
     */
    @Test
    public void testIsEmptyOnNewSession() {
        WorkoutSession s = new WorkoutSession1L();
        WorkoutSession sCopy = new WorkoutSession1L();

        assertTrue(s.isEmpty());
        assertEquals(sCopy, s); // state unchanged
    }

    /**
     * Tests isEmpty returns false after one set is added.
     */
    @Test
    public void testIsEmptyAfterAddSet() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Squat", 5, 100);
        assertFalse(s.isEmpty());
    }

    /**
     * Tests isEmpty returns true after all sets are removed.
     */
    @Test
    public void testIsEmptyAfterRemovingAllSets() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Bench", 8, 60);
        s.removeAnySet();

        assertTrue(s.isEmpty());
    }

    /*
     * -------------------------------------------------------------------------
     * size
     * -------------------------------------------------------------------------
     */

    /**
     * Tests size on empty session is zero.
     */
    @Test
    public void testSizeEmpty() {
        WorkoutSession s = new WorkoutSession1L();
        WorkoutSession sCopy = new WorkoutSession1L();

        assertEquals(0, s.size());
        assertEquals(sCopy, s);
    }

    /**
     * Tests size after adding one set.
     */
    @Test
    public void testSizeAfterOneAdd() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Deadlift", 3, 140);

        assertEquals(1, s.size());
    }

    /**
     * Tests size after adding multiple sets.
     */
    @Test
    public void testSizeAfterMultipleAdds() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Squat", 5, 100);
        s.addSet("Squat", 5, 110);
        s.addSet("Bench", 8, 60);

        assertEquals(3, s.size());
    }

    /**
     * Tests size decreases after removeAnySet.
     */
    @Test
    public void testSizeAfterRemove() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("OHP", 6, 40);
        s.addSet("OHP", 6, 45);
        s.removeAnySet();

        assertEquals(1, s.size());
    }

    /*
     * -------------------------------------------------------------------------
     * addSet
     * -------------------------------------------------------------------------
     */

    /**
     * Tests addSet increases size by one and session is no longer empty.
     */
    @Test
    public void testAddSetSingleSet() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Curl", 10, 20);

        assertEquals(1, s.size());
        assertFalse(s.isEmpty());
    }

    /**
     * Tests addSet with zero load (bodyweight exercise).
     */
    @Test
    public void testAddSetZeroLoad() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("PullUp", 8, 0);

        assertEquals(1, s.size());
    }

    /**
     * Tests addSet with multiple different exercises.
     */
    @Test
    public void testAddSetMultipleExercises() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Squat", 5, 100);
        s.addSet("Bench", 8, 60);
        s.addSet("Row", 10, 50);

        assertEquals(3, s.size());
    }

    /**
     * Tests addSet with same exercise multiple times.
     */
    @Test
    public void testAddSetSameExerciseMultipleTimes() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Deadlift", 5, 140);
        s.addSet("Deadlift", 3, 160);
        s.addSet("Deadlift", 1, 180);

        assertEquals(3, s.size());
    }

    /*
     * -------------------------------------------------------------------------
     * removeAnySet
     * -------------------------------------------------------------------------
     */

    /**
     * Tests removeAnySet on a session with one set returns a valid WorkoutSet
     * and leaves the session empty.
     */
    @Test
    public void testRemoveAnySetSingleSet() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Squat", 5, 100);
        WorkoutSet removed = s.removeAnySet();

        assertEquals("Squat", removed.exercise);
        assertEquals(5, removed.reps);
        assertEquals(100, removed.load);
        assertTrue(s.isEmpty());
    }

    /**
     * Tests removeAnySet reduces size by one when multiple sets exist.
     */
    @Test
    public void testRemoveAnySetReducesSize() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Bench", 10, 60);
        s.addSet("Bench", 8, 70);
        s.removeAnySet();

        assertEquals(1, s.size());
    }

    /**
     * Tests removeAnySet returns a set that was actually added.
     */
    @Test
    public void testRemoveAnySetReturnsValidSet() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Row", 12, 40);
        WorkoutSet removed = s.removeAnySet();

        assertTrue(removed.reps > 0);
        assertTrue(removed.load >= 0);
        assertFalse(removed.exercise.isEmpty());
    }

    /*
     * -------------------------------------------------------------------------
     * clear
     * -------------------------------------------------------------------------
     */

    /**
     * Tests clear on empty session leaves it empty.
     */
    @Test
    public void testClearOnEmptySession() {
        WorkoutSession s = new WorkoutSession1L();

        s.clear();

        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
    }

    /**
     * Tests clear removes all sets from a populated session.
     */
    @Test
    public void testClearOnPopulatedSession() {
        WorkoutSession s = new WorkoutSession1L();

        s.addSet("Squat", 5, 100);
        s.addSet("Bench", 8, 60);
        s.addSet("Deadlift", 3, 140);
        s.clear();

        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
    }

    /*
     * -------------------------------------------------------------------------
     * newInstance
     * -------------------------------------------------------------------------
     */

    /**
     * Tests newInstance returns an empty session.
     */
    @Test
    public void testNewInstanceIsEmpty() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);

        WorkoutSession fresh = s.newInstance();

        assertTrue(fresh.isEmpty());
        assertEquals(0, fresh.size());
    }

    /**
     * Tests newInstance does not affect the original session.
     */
    @Test
    public void testNewInstanceDoesNotAffectOriginal() {
        WorkoutSession s = new WorkoutSession1L();
        s.addSet("Squat", 5, 100);

        s.newInstance();

        assertEquals(1, s.size());
    }

    /*
     * -------------------------------------------------------------------------
     * transferFrom
     * -------------------------------------------------------------------------
     */

    /**
     * Tests transferFrom moves all sets to destination and clears source.
     */
    @Test
    public void testTransferFromBasic() {
        WorkoutSession source = new WorkoutSession1L();
        WorkoutSession dest = new WorkoutSession1L();

        source.addSet("Squat", 5, 100);
        source.addSet("Bench", 8, 60);
        dest.transferFrom(source);

        assertEquals(2, dest.size());
        assertTrue(source.isEmpty());
    }

    /**
     * Tests transferFrom into a non-empty destination replaces its contents.
     */
    @Test
    public void testTransferFromIntoNonEmptyDest() {
        WorkoutSession source = new WorkoutSession1L();
        WorkoutSession dest = new WorkoutSession1L();

        source.addSet("Deadlift", 3, 140);
        dest.addSet("OHP", 6, 40);
        dest.transferFrom(source);

        assertEquals(1, dest.size());
        assertTrue(source.isEmpty());
    }

    /**
     * Tests transferFrom with an empty source results in empty destination.
     */
    @Test
    public void testTransferFromEmptySource() {
        WorkoutSession source = new WorkoutSession1L();
        WorkoutSession dest = new WorkoutSession1L();

        dest.addSet("Row", 10, 50);
        dest.transferFrom(source);

        assertTrue(dest.isEmpty());
        assertTrue(source.isEmpty());
    }
}