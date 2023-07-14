package Model;

import java.sql.*;
import java.util.*;

import View.TriviaMazeMain;

public class SqliteDB {

	private Connection myConnection = null;
	private Statement myStatement = null;
	private ArrayList<Integer> myTrack = new ArrayList<>();

	/**
	 * Constructor that sets up the connect to the SQLite database, allowing for
	 * interacting with it from within the code
	 */
	public SqliteDB() {

		// try to connect to DB
		try {
			Class.forName("org.sqlite.JDBC");
			myConnection = DriverManager.getConnection("jdbc:sqlite:trivia.sqlite");
			System.out.println("connected to db");
		} catch (Exception e) {
			System.out.println("Expection: " + e.getMessage());
		}

	}

	/**
	 * Quick lil method that the user just calls to generate a random question from
	 * the database, using the method from which you can generate an question object
	 * from with the id of it makes sure that the question returned isn't one that
	 * has been generated before, using an array list that keeps track of what ids
	 * have been used to create a question object
	 * 
	 * @param tmm main class
	 * @return a random question
	 * @throws SQLException 
	 */
	public Question getRandomQuestion(TriviaMazeMain tmm) throws SQLException {

		this.myStatement = myConnection.createStatement();
		ResultSet rs2 = myStatement.executeQuery("SELECT COUNT(*) AS total FROM TriviaQuestions");
		int total = rs2.getInt("total");
		Random rand = new Random();
		int ran = rand.nextInt(total) + 1;

		while (myTrack.contains(ran)) {
			ran = rand.nextInt(total) + 1;
		}

		return getFromID(ran, tmm);
	}

	/**
	 * Method that creates a question object based on a certain id that is passed
	 * through from the user.
	 * 
	 * @param theId player id
	 * @param tmm main class
	 * @return related question object
	 */
	public Question getFromID(final int theId, TriviaMazeMain tmm) {

		try {
			this.myStatement = myConnection.createStatement();

			// gets all the information from the row with this id
			ResultSet rs = myStatement.executeQuery("SELECT * FROM TriviaQuestions WHERE id = " + theId);

			if (rs.next()) {
				String q = rs.getString("Question");
				String type = rs.getString("Type");
				String correct = rs.getString("Answer");
				String[] answers = new String[4];
				Random rand = new Random();
				int correctIndex = 0;

				// if statement that will randomize where in the array of answers the correct
				// one is if it's multiple choice, and will then fill the array with the
				// preprovided wrong answers
				if (type.equals("MC")) {
					correctIndex = rand.nextInt(4);
					answers[correctIndex] = correct;
					for (int i = 0; i < 3; i++) {
						if (correctIndex == i) {
							answers[3] = rs.getString("Wrong" + (i + 1));
						} else {
							answers[i] = rs.getString("Wrong" + (i + 1));
						}
					}
				}

				// check if the question is a true false one, and depending on the answer, sets
				// the correct index to 0 or 1, true or false
				else if (type.equals("TF")) {
					if (correct.equals("True")) {
						correctIndex = 0;
					} else {
						correctIndex = 1;
					}
					answers[0] = "True";
					answers[1] = "False";
				}

				// now if it's not a mc or tf, then it has to be short answer, which will store
				// the correct answer in the 0 index
				else {
					answers[0] = correct;
				}

				myTrack.add(theId);
				return new Question(q, answers, correctIndex, type, tmm);
			}

		} catch (Exception e) {
			System.out.print("Expection: " + e);
		}

		return null;
	}

	/**
	 * Method to close the connect with the database.
	 */
	public void closeConnection() {

		try {
			myConnection.close();
		} catch (Exception e) {
			// error
		}

	}
}
