package backtracking;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * This is a CIT594 assignment to solve path-finding puzzles and practice
 * backtracking.
 * 
 * @author <Jincheng Cao>
 */
public class Backtracking {

	/**
	 * Takes an array of integers, and tries to find a path from the first
	 * location in the array (puzzle[0]) to the last location in the array
	 * (puzzle[puzzle.length - 1]).
	 * 
	 * @param puzzle
	 *            an array to store sequences of integers.
	 * @return a stack representing a path if it exists, <code>null</code>
	 *         otherwise.
	 */
	public static Stack<Character> solve(int[] puzzle) {
		Stack<Character> solutionStack = new Stack<Character>();
		Stack<Character> numberStack = new Stack<Character>();
		Stack<Character> returnStack = new Stack<Character>();
		numberStack.push((char) ('0' + 0));
		solutionStack.push((char) 'R');
		if (puzzle.length == 1) {
			numberStack.pop();
			solutionStack.pop();
			return solutionStack;
		} else {
			if (solvable(puzzle, puzzle[0], solutionStack, numberStack)) {
				while (!solutionStack.isEmpty()) {
					returnStack.push(solutionStack.pop());
				}
				return returnStack;
			} else {
				return null;
			}
		}

	}

	/**
	 * Takes an array of integers, and tries to find all paths from the first
	 * location in the array (puzzle[0]) to the last location in the array
	 * (puzzle[puzzle.length - 1]).
	 * 
	 * @param puzzle
	 *            an array to store sequences of integers.
	 * @return a stack representing all paths if they exist, or an empty
	 *         otherwise.
	 */
	public static Set<Stack<Character>> findAllSolutions(int[] puzzle) {
		Stack<Character> solutionStack = new Stack<Character>();
		Stack<Character> numberStack = new Stack<Character>();
		Set<Stack<Character>> set = new HashSet<Stack<Character>>();
		numberStack.push((char) ('0' + 0));
		solutionStack.push((char) 'R');
		if (puzzle.length == 1) {
			numberStack.pop();
			solutionStack.pop();
			set.add(solutionStack);
			return set;
		} else {
			if (solvable2(puzzle, puzzle[0], solutionStack, numberStack, set)) {
				return set;
			} else {
				Set<Stack<Character>> emptySet = new HashSet<Stack<Character>>();
				return emptySet;
			}
		}

	}

	/**
	 * Takes an array of integers, a integer representing current position of
	 * marker in the array, and checks if there's a path from the first location
	 * in the array (puzzle[0]) to the last location in the array
	 * (puzzle[puzzle.length - 1]), and puts the path on a stack.
	 * 
	 * @param puzzle
	 *            an array to store sequences of integers.
	 * @param position
	 *            an integer to represent current position in the array.
	 * @param solutionStack
	 *            a stack to store potential solution path
	 * @return <code>true</code> if such path exists; <code>false</code>
	 *         otherwise.
	 * 
	 */
	private static boolean solvable(int[] puzzle, int position,
			Stack<Character> solutionStack, Stack<Character> numberStack) {
		if (puzzleSolved(puzzle, position, solutionStack, numberStack)) {
			return true;
		}
		if (canMove(puzzle, position, "R", solutionStack, numberStack)) {
			int newPosition = makeMove(puzzle, position, "R", solutionStack,
					numberStack);
			if (solvable(puzzle, newPosition, solutionStack, numberStack)) {
				return true;
			}
		}
		if (canMove(puzzle, position, "L", solutionStack, numberStack)) {
			int newPosition = makeMove(puzzle, position, "L", solutionStack,
					numberStack);
			if (solvable(puzzle, newPosition, solutionStack, numberStack)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Takes an array of integers, a integer representing current position of
	 * marker in the array, to checks if there's a path from the first location
	 * in the array (puzzle[0]) to the last location in the array
	 * (puzzle[puzzle.length - 1]), and puts the path on a stack. Stores all
	 * paths on stacks into a set.
	 * 
	 * @param puzzle
	 *            an array to store sequences of integers.
	 * @param position
	 *            an integer to represent current position in the array.
	 * @param solutionStack
	 *            a stack to store potential solution path.
	 * @param set
	 *            a set to store all potential solution paths
	 * @return <code>true</code> if paths exist; <code>false</code> otherwise.
	 * 
	 */
	private static boolean solvable2(int[] puzzle, int position,
			Stack<Character> solutionStack, Stack<Character> numberStack,
			Set<Stack<Character>> set) {

		if (puzzleSolved(puzzle, position, solutionStack, numberStack)) {
			Stack<Character> finalStack = new Stack<Character>();
			Stack<Character> tempStack = new Stack<Character>();
			tempStack = solutionStack;
			while (!tempStack.isEmpty()) {
				finalStack.push(tempStack.pop());
			}
			set.add(finalStack);
			return true;
		}
		if ((!canMove(puzzle, position, "R", solutionStack, numberStack))
				&& !(canMove(puzzle, position, "L", solutionStack, numberStack)))
			return false;
		if (canMove(puzzle, position, "R", solutionStack, numberStack)) {
			Stack<Character> newStack = new Stack<Character>();
			Stack<Character> newNumberStack = new Stack<Character>();
			for (Character c : solutionStack) {
				newStack.add(c);
			}
			for (Character c : numberStack) {
				newNumberStack.add(c);
			}
			int newPosition = makeMove(puzzle, position, "R", newStack,
					newNumberStack);
			solvable2(puzzle, newPosition, newStack, newNumberStack, set);
		}
		if (canMove(puzzle, position, "L", solutionStack, numberStack)) {
			Stack<Character> newStack = new Stack<Character>();
			Stack<Character> newNumberStack = new Stack<Character>();
			for (Character c : solutionStack) {
				newStack.add(c);
			}
			for (Character c : numberStack) {
				newNumberStack.add(c);
			}
			int newPosition = makeMove(puzzle, position, "L", newStack,
					newNumberStack);
			solvable2(puzzle, newPosition, newStack, newNumberStack, set);
		}
		return true;

	}

	/**
	 * Takes an array of integers, a integer representing current position of
	 * marker in the array, a string of direction and a stack, and checks if the
	 * maker can move to designated direction.
	 * 
	 * @param puzzle
	 *            an array to store sequences of integers.
	 * @param position
	 *            an integer to represent current position in the array.
	 * @param solutionStack
	 *            a stack to store potential solution path.
	 * @return <code>true</code> if the marker can move; <code>false</code>
	 *         otherwise.
	 */

	private static boolean canMove(int[] puzzle, int position,
			String direction, Stack<Character> solutionStack,
			Stack<Character> numberStack) {
		if (position == 0 || position > puzzle.length - 1)
			return false;
		int number = puzzle[position];
		if (direction == "R") {
			if ((position + number <= puzzle.length - 1)
					&& (!numberStack.contains((char) ('0' + position + number))))
				return true;
		}
		if (direction == "L") {
			if ((position - number >= 0)
					&& (!numberStack.contains((char) ('0' + position - number)) || position
							- number == 0))
				return true;
		}
		return false;
	}

	/**
	 * Takes an array of integers, a integer representing current position of
	 * marker in the array, a string of direction and a stack, and checks if the
	 * maker is moved to the end of the array.
	 * 
	 * @param puzzle
	 *            an array to store sequences of integers.
	 * @param position
	 *            an integer to represent current position in the array.
	 * @param solutionStack
	 *            a stack to store potential solution path.
	 * @return <code>true</code> if the puzzle is solved, <code>false</code>
	 *         otherwise.
	 */

	private static boolean puzzleSolved(int[] puzzle, int position,
			Stack<Character> solutionStack, Stack<Character> numberStack) {
		if (position == puzzle.length - 1) {
			return true;
		} else
			return false;
	}

	/**
	 * Takes an array of integers, a integer representing current position of
	 * marker in the array, a string of direction and a stack, and returns
	 * 
	 * @param puzzle
	 *            an array to store sequences of integers.
	 * @param position
	 *            an integer to represent current position in the array.
	 * @param solutionStack
	 *            a stack to store potential solution path.
	 * @return an integer representing new position of the marker after moved.
	 */
	private static int makeMove(int[] puzzle, int position, String direction,
			Stack<Character> solutionStack, Stack<Character> numberStack) {
		if (direction == "R") {
			numberStack.push((char) ('0' + position));
			solutionStack.push((char) 'R');
			position = position + puzzle[position];
		}
		if (direction == "L") {
			numberStack.push((char) ('0' + position));
			solutionStack.push((char) 'L');
			position = position - puzzle[position];
		}

		return position;
	}
}
