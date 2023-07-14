package Model;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Maze extends JPanel implements Serializable{

	private static final long serialVersionUID = 153585957946307993L;

	private final List<Rectangle> myRooms = new LinkedList<Rectangle>();
	private final List<Rectangle> myDoors = new LinkedList<Rectangle>();
	private final List<Rectangle> myOpenSymbols = new LinkedList<Rectangle>();
	private final List<Rectangle> myLockedSymbols = new LinkedList<Rectangle>();
	private Rectangle myPlayer;

	public Maze(BorderLayout theBorderLayout) {

		super(theBorderLayout);
		myPlayer = new Rectangle(0,0,2,2);
	}

	/**
	 * Add room into min map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addRoom(int theX, int theY, int theWidth, int theHeight) {
		myRooms.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add closed door into min map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addDoor(int theX, int theY, int theWidth, int theHeight) {
		myDoors.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add opened door into min map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addOpen(int theX, int theY, int theWidth, int theHeight) {
		myOpenSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add locked door into min map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addLock(int theX, int theY, int theWidth, int theHeight) {
		myLockedSymbols.add(new Rectangle(theX,theY,theWidth,theHeight));
	}

	/**
	 * Add player token into min map
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 * @param theWidth item width
	 * @param theHeight item height
	 */
	public void addPlayer(int theX, int theY, int theWidth, int theHeight) {
		myPlayer = new Rectangle(theX, theY, theWidth, theHeight);
	}

	/**
	 * Move player to a different position.
	 * 
	 * @param theX coordinate x 
	 * @param theY coordinate y
	 */
	public void movePlayer(int theX, int theY) {
		myPlayer.setLocation(theX, theY);
	}

	public Rectangle getPlayer() {
		return myPlayer;
	}

	public List<Rectangle> getMyLockedSymbols() {
		return myLockedSymbols;
	}

	public List<Rectangle> getMyRooms() {
		return myRooms;
	}

	public List<Rectangle> getMyDoors() {
		return myDoors;
	}

	public List<Rectangle> getMyOpenSymbols() {
		return myOpenSymbols;
	}

	/**
	 * To see if there is player token.
	 * 
	 * @return true if there is one
	 */
	public boolean hasPlayerToken() {

		boolean flag = true;

		if (myPlayer.contains(1, 1)) {
			flag = false;
		}

		return flag;
	}

	/**
	 * Clear everything.
	 */
	public void clear() {

		myRooms.clear();
		myDoors.clear();
		myOpenSymbols.clear();
		myLockedSymbols.clear();
		myPlayer = new Rectangle(0,0,2,2);	
		repaint();
	}

	/**
	 * Draw things in the min map.
	 */
	@Override
	public void paintComponent(final Graphics theGraphics) {

		super.paintComponent(theGraphics);
		Graphics2D tempGraphics = (Graphics2D) theGraphics;

		for (int i = 0; i < myRooms.size(); i++) {
			tempGraphics.setPaint(new Color(200,150,0));
			tempGraphics.fill(myRooms.get(i));
		}

		for (int i = 0; i < myDoors.size(); i++) {
			tempGraphics.setPaint(Color.LIGHT_GRAY);
			tempGraphics.fill(myDoors.get(i));
		}

		for (int i = 0; i < myOpenSymbols.size(); i++) {
			tempGraphics.setPaint(Color.green);
			tempGraphics.fill(myOpenSymbols.get(i));
		}

		for (int i = 0; i < myLockedSymbols.size(); i++) {
			tempGraphics.setPaint(Color.RED);
			tempGraphics.fill(myLockedSymbols.get(i));
		}

		tempGraphics.setPaint(Color.BLUE);

		if (myPlayer != null) {
			tempGraphics.fill(myPlayer);
		}
	}
}
