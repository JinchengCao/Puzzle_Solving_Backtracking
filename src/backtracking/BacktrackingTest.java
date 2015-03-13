package backtracking;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

/**
 * @author <Jincheng Cao>
 */
public class BacktrackingTest {

	int[] puzzle1 = { 3, 6, 4, 1, 3, 4, 2, 5, 3, 0 };
	int[] puzzle2 = { 3, 1, 1, 1 };
	int[] puzzle3 = { 2, 0, 1, 0, 5 };
	int[] puzzle4 = { 3 };
	int[] puzzle5 = { 3, 0 };

	/**
	 * Test method for {@link backtracking.Backtracking#solve(int[])}.
	 */
	@Test
	public void testSolve() {
		Stack<Character> solutionStack1 = new Stack<Character>();
		Stack<Character> solutionStack2 = new Stack<Character>();
		Stack<Character> solutionStack3 = new Stack<Character>();
		Stack<Character> solutionStack4 = new Stack<Character>();
		Stack<Character> solutionStack5 = new Stack<Character>();
		solutionStack1.push('R');
		solutionStack1.push('L');
		solutionStack1.push('R');
		solutionStack1.push('R');
		solutionStack1.push('L');
		solutionStack1.push('R');
		solutionStack1.push('R');
		solutionStack1.push('R');
		assertTrue(solutionStack1.toString().compareTo(
				Backtracking.solve(puzzle1).toString()) == 0);
		solutionStack2.push('R');
		assertTrue(solutionStack2.toString().compareTo(
				Backtracking.solve(puzzle2).toString()) == 0);
		solutionStack3 = null;
		assertEquals(solutionStack3, Backtracking.solve(puzzle3));
		assertEquals(solutionStack4, Backtracking.solve(puzzle4));
		solutionStack5 = null;
		assertEquals(solutionStack5, Backtracking.solve(puzzle5));
	}

	/**
	 * Test method for {@link backtracking.Backtracking#findAllSolutions(int[])}
	 * .
	 */
	@Test
	public void testFindAllSolutions() {
		Stack<Character> solutionStack1a = new Stack<Character>();
		Stack<Character> solutionStack1b = new Stack<Character>();
		Stack<Character> solutionStack1c = new Stack<Character>();
		Stack<Character> solutionStack2 = new Stack<Character>();
		Stack<Character> solutionStack3 = new Stack<Character>();
		Stack<Character> solutionStack4 = new Stack<Character>();
		Stack<Character> solutionStack5 = new Stack<Character>();

		solutionStack1a.push('R');
		solutionStack1a.push('L');
		solutionStack1a.push('R');
		solutionStack1a.push('R');
		solutionStack1a.push('L');
		solutionStack1a.push('R');
		solutionStack1a.push('R');
		solutionStack1a.push('R');

		solutionStack1b.push('R');
		solutionStack1b.push('L');
		solutionStack1b.push('R');
		solutionStack1b.push('R');
		solutionStack1b.push('L');
		solutionStack1b.push('R');
		solutionStack1b.push('L');
		solutionStack1b.push('R');
		solutionStack1b.push('R');

		solutionStack1c.push('R');
		solutionStack1c.push('L');
		solutionStack1c.push('R');
		solutionStack1c.push('R');
		solutionStack1c.push('L');
		solutionStack1c.push('R');

		Set<Stack<Character>> set1 = new HashSet<Stack<Character>>();
		Set<Stack<Character>> set2 = new HashSet<Stack<Character>>();
		Set<Stack<Character>> set3 = new HashSet<Stack<Character>>();
		Set<Stack<Character>> set4 = new HashSet<Stack<Character>>();
		Set<Stack<Character>> set5 = new HashSet<Stack<Character>>();
		set1.add(solutionStack1a);
		set1.add(solutionStack1b);
		set1.add(solutionStack1c);
		assertTrue(set1.containsAll(Backtracking.findAllSolutions(puzzle1)));
		solutionStack2.push('R');
		set2.add(solutionStack2);
		assertTrue(set2.containsAll(Backtracking.findAllSolutions(puzzle2)));
		assertEquals(set3, Backtracking.findAllSolutions(puzzle3));
		set4.add(solutionStack4);
		assertEquals(set4, Backtracking.findAllSolutions(puzzle4));
		assertEquals(set5, Backtracking.findAllSolutions(puzzle5));
	}

}
