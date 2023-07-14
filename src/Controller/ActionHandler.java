package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import View.TriviaMazeMain;

public class ActionHandler implements ActionListener , Serializable{

	private static final long serialVersionUID = -5918381906718494150L;

	private TriviaMazeMain myTmm;

	public ActionHandler(TriviaMazeMain theTmm) {

		this.myTmm = theTmm;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String yourChoice = e.getActionCommand();

		switch (yourChoice) {

		case "OPEN CHEST": myTmm.event.openChest();
		break;

		case "GO UP": myTmm.event.goUp();
		break;

		case "GO DOWN": myTmm.event.goDown();
		break;

		case "GO LEFT": myTmm.event.goLeft();
		break;

		case "GO RIGHT": myTmm.event.goRight();
		break;

		case "ENTER ANSWER": myTmm.event.submit();
		break;

		case "START": myTmm.event.start();
		break;

		case "LOAD": myTmm.event.load();
		break;

		}
	}

}
