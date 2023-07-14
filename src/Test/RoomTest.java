package Test;

import static org.junit.Assert.assertEquals;

import java.awt.*;
import org.junit.jupiter.api.Test;
import Model.*;
import Model.Room.Direction;
import View.TriviaMazeMain;

public class RoomTest {
	
	TriviaMazeMain myTmm;
	String[] myAns = {"T", "F"};
	Question myQuestion = new Question("?", myAns, 1, "TF", myTmm);
	Room myRoom = new Room(0, 0,1, 1, new Point(0, 0));
	Door myDoor = new Door(0, 0, 1, 1, myQuestion);;
	

	@Test
	void testsetDoor() {
		myRoom.setDoor(Direction.UP, myDoor);
		assertEquals(myRoom.getDoor(Direction.UP), myDoor);
	}
	
	@Test
	void testSetHasChest() {
		myRoom.setHasChest(false);
		assertEquals(myRoom.getHasChest(), false);
	}
}
