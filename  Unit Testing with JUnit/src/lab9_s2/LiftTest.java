package lab9_s2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class LiftTest {

	@Test
	void testConstructor() {
		Lift l = new Lift(5);
		assertFalse(l.isFull());
		assertEquals(5, l.getTopFloor());
	}

	@Test
	public void testConstructorWithMaxRiders() {
		int highestFloor = 3;
		int maxRiders = 5;
		Lift l = new Lift(highestFloor, maxRiders);
		assertEquals(highestFloor, l.getTopFloor());
		assertEquals(maxRiders, l.getCapacity());
		assertEquals(0, l.getCurrentFloor());
	}

	@Test
	public void testEmptyLift() {
		int highestFloor = 1;
		int maxRiders = 1;
		Lift l = new Lift(highestFloor, maxRiders);
		assertFalse(l.isFull());
	}

	@Test
	public void testFullCargoLift() {
		int highestFloor = 1;
		int maxRiders = 0; // cargo lift doesn't allow riders
		Lift l = new Lift(highestFloor, maxRiders);
		l.addRiders(1);
		assertTrue(l.isFull());
	}

	@Test
	public void testFullLift() {
		int highestFloor = 1;
		int maxRiders = 1;
		Lift l = new Lift(highestFloor, maxRiders);
		l.addRiders(1);
		assertTrue(l.isFull());
	}

	@Test
	public void testFullLiftOverCapacity() {
		int highestFloor = 1;
		int maxRiders = 5;

		Lift l = new Lift(highestFloor, maxRiders);
		l.addRiders(10);
		assertTrue(l.isFull());
	}

	@Test
	public void testAddRiders() {
		Lift l = new Lift(1, 5);
		l.addRiders(2);
		assertEquals(2, l.getNumRiders());

		l.addRiders(10);
		assertEquals(5, l.getNumRiders());
	}

	@Test
	public void testGoUp() {
		Lift l = new Lift(1, 5);
		l.goUp();
		assertEquals(1, l.getCurrentFloor());
	}

	@Test
	public void testGoUpTopFloor() {
		Lift l = new Lift(1, 5);
		l.goUp();
		l.goUp();
		assertEquals(1, l.getCurrentFloor());
	}

	@Test
	public void testGoDownGroundFloor() {
		Lift l = new Lift(1);
		l.goDown();
		assertEquals(0, l.getCurrentFloor());
	}

	@Test
	public void testGoDown() {
		Lift l = new Lift(2);
		l.goUp();
		l.goUp();
		l.goDown();
		assertEquals(1, l.getCurrentFloor());
	}

	@Test
	public void testCall() {
		Lift l = new Lift(2);
		assertEquals(0, l.getCurrentFloor());
		l.call(2);
		assertEquals(2, l.getCurrentFloor());
	}

	@Test
	public void testCallBelowGroundFloor() {
		assertThrows(IllegalArgumentException.class, () -> {
			Lift l = new Lift(2);
			l.call(-2);	
		});
	}

	@Test
	public void testCallAboveHighestFloor() {
		assertThrows(IllegalArgumentException.class, () -> {
			Lift l = new Lift(2);
			l.call(3);	
		});
	}

	@Test
	public void testCallLowerFloor() {
		Lift l = new Lift(3);
		l.goUp();
		l.goUp();
		l.call(0);
		assertEquals(0, l.getCurrentFloor());
	}

}
