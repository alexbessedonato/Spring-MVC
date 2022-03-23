package lab9_s2;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class HashSetTest {

	@Test
	public void testAdd() {
		HashSet<String> set = new HashSet<>();

		set.add("a");

		int expectedSize = 1;
		int actualSize = set.size();
		assertEquals(expectedSize, actualSize);
		// or shorter: assertEquals(1, set.size())
	}

	@Test
	public void testAddSameElement() {
		HashSet<String> set = new HashSet<>();

		set.add("a");
		set.add("a");

		int expectedSize = 1;
		int actualSize = set.size();
		assertEquals(expectedSize, actualSize);
		// or shorter: assertEquals(1, set.size());
	}

	@Test
	public void testAddDifferentElement() {
		HashSet<String> set = new HashSet<>();

		set.add("a");
		set.add("b");

		int expectedSize = 2;
		int actualSize = set.size();
		assertEquals(expectedSize, actualSize);
	}

	@Test
	public void testClear() {
		HashSet<String> set = new HashSet<>();

		set.add("a");
		set.add("b");
		set.clear();

		assertTrue(set.isEmpty());
	}

	@SuppressWarnings (value="unchecked")
	@Test
	public void testClone() {
		HashSet<String> set = new HashSet<String>();
		set.add("first");
		set.add("second");

		HashSet<String> clone;
		clone = (HashSet<String>) set.clone();
		assertEquals(set, clone);
		assertNotSame(set, clone);
	}

	@SuppressWarnings (value="unchecked")
	@Test
	public void testCloneChange() {
		HashSet<String> set = new HashSet<>();
		set.add("first");
		set.add("second");

		HashSet<String> clone;
		clone = (HashSet<String>) set.clone();
		clone.add("third");
		assertNotEquals(set, clone);
		assertEquals(2, set.size());
		assertEquals(3, clone.size());
	}

	@Test
	public void testEmpty() {
		HashSet<String> set = new HashSet<>();
		assertTrue(set.isEmpty());
	}

	@Test
	public void testNonEmpty() {
		HashSet<String> set = new HashSet<>();
		set.add("jose");
		assertFalse(set.isEmpty());
	}

	@Test
	public void testContains() {
		HashSet<String> set = new HashSet<>();
		set.add("1");
		set.add("2");
		set.add("3");
		assertTrue(set.contains("3"));
		assertTrue(set.contains("2"));
		assertTrue(set.contains("1"));
		assertFalse(set.contains("0"));
	}

	@Test
	public void testRemove() {
		HashSet<String> set = new HashSet<>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.remove("2");
		assertFalse(set.contains("2"));
		assertEquals(2, set.size());
	}

	@Test
	public void testRemoveNothing() {
		HashSet<String> set = new HashSet<>();
		set.add("1");
		set.add("3");
		assertEquals(2, set.size());
		set.remove("2");
		assertEquals(2, set.size());
	}

}
