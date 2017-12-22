/* 
 * Name: Ryan Russell
 * ID: V00873387
 * Date: 03/10/2017
 * Filename: MazeSolver.java
 * Details: CSC 115 Assignment 3 Part 2
*/


/*
 * MazeSolver.java
 *
 * UVic CSC 115, Spring 2017
 *
 * Purpose:
 *   class that contains a single public static method called
 *   "findPath". To involve the method one must have already created
 *   an instance of Maze. The method must return a path through the
 *   maze (if it exists) in the format shown within the Assignment #3
 *   description.
 *
 * Note: You are free to add to this class whatever other methods will
 * help you in writing a solution to A#3 part 2.
 */
public class MazeSolver {
	
	/* 
	 * Finds the path through the maze.
	 * @param the given maze parameter.
	 */
	public static String findPath(Maze maze) {

		String result = "";
		boolean[][] visitedLocations = new boolean[maze.getNumRows()][maze.getNumCols()];
		StackRefBased<MazeLocation> locationStack = new StackRefBased();

		try {
			
			locationStack.push(maze.getEntry()); // The entry location of the maze is pushed onto the location stack.
			
			/*
			 * While loop to set the current location, check all locations around 
			 * that location, and find the proper path through the maze.
			 */
			while (!locationStack.isEmpty() && !locationStack.peek().equals(maze.getExit())) {
				
				MazeLocation currentLocation = locationStack.peek(); // Sets the current location to what's on the top of the stack.
				int row = currentLocation.getRow();
				int col = currentLocation.getCol();

				visitedLocations[currentLocation.getRow()][currentLocation.getCol()] = true; // Sets the current location to visited.
				
				/* 
				 * Checks all locations surrounding the current location to find the next location required for the proper path.
				 */
				if ((!maze.isWall(row + 1, col)) && (visitedLocations[row + 1][col] == false)) {
					locationStack.push(new MazeLocation(row + 1, col));
				} 
				else if ((!maze.isWall(row - 1, col)) && (visitedLocations[row - 1][col] == false)) {
					locationStack.push(new MazeLocation(row - 1, col));
				} 
				else if ((!maze.isWall(row, col + 1)) && (visitedLocations[row][col + 1] == false)) {
					locationStack.push(new MazeLocation(row, col + 1));
				} 
				else if ((!maze.isWall(row, col - 1)) && (visitedLocations[row][col - 1] == false)) {
					locationStack.push(new MazeLocation(row, col - 1));
				} else {
					locationStack.pop(); // If there are no unvisited locations then pop the current location off the stack.
				}
			}
			if (locationStack.isEmpty()) {
				return result;
			} else {
				result = locationStack.toString(); // Sets result to the string that describes the proper path through the maze.
				return result;
			}
			/*
			 * Catches the StackEmptyException if it is thrown in the try block above.
			 */
		} catch (StackEmptyException locationStackFailure) { 
			System.out.println("There's nothing here.");
		}
		return result; 
	}
}
