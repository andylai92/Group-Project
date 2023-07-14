package Test;

import static org.junit.Assert.assertEquals;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import Model.Maze;

public class MazeTest {

	Maze myMazeMap = new Maze(new BorderLayout());
	
	@Test
	void testMovePlayer() {
		
		myMazeMap.movePlayer(2, 2);
		assertEquals(myMazeMap.getPlayer().getLocation(), new Point(2, 2));
	}
	
	@Test
	void testClear() {
		
		myMazeMap.addRoom(0, 0, 1, 1);
		myMazeMap.addDoor(0, 1, 1, 1);
		myMazeMap.addOpen(1, 0, 1, 1);
		myMazeMap.addLock(1, 1, 1, 1);
		myMazeMap.clear();
		
		assertEquals(new LinkedList<Rectangle>(), myMazeMap.getMyDoors());
		assertEquals(new LinkedList<Rectangle>(), myMazeMap.getMyRooms());
		assertEquals(new LinkedList<Rectangle>(), myMazeMap.getMyOpenSymbols());
		assertEquals(new LinkedList<Rectangle>(), myMazeMap.getMyLockedSymbols());
		assertEquals(new Rectangle(0,0,2,2), myMazeMap.getPlayer());
	}
}
