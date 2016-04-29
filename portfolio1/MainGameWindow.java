package portfolio;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


public class MainGameWindow extends JFrame
{

	private static JPanel mainPanel;
	private static JTextPane messageArea;
	private static Map<Integer, JButton> gameBoard = new HashMap<Integer, JButton>();

	private static String opponentsName;
	private static String playersMark;
	private static String opponentsMark;
	private static String message = "";
	private static boolean turn;
	private static ConnectWindow CW;

	public MainGameWindow(String opponentsName, String playersMark, String opponentsMark, boolean turn,
			ConnectWindow cw)
	{
		this.opponentsName = opponentsName;
		this.playersMark = playersMark;
		this.opponentsMark = opponentsMark;
		this.turn = turn;
		CW = cw;

		this.setTitle("Tic Tac Toe vs. " + opponentsName);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		JPanel contentPanel = createContentPanel();
		mainPanel.add(contentPanel);

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Creates the content panel
	 * 
	 * @return JPanel - a panel with a button area and message area
	 */
	private static JPanel createContentPanel()
	{
		JPanel p = new JPanel();
		JPanel board = createButtonTable();
		p.add(board);

		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		messageArea = new JTextPane();
		messageArea.setPreferredSize(new Dimension(100, 200));
		messageArea.setMinimumSize(new Dimension(100, 200));
		messageArea.setEditable(false);
		messageArea.setVisible(true);
		messageArea.setContentType("text/html");
		JScrollPane scroll = new JScrollPane(messageArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p1.add(scroll);

		JPanel p2 = new JPanel();
		JTextField chatbox = new JTextField(10);
		JButton sendText = new JButton("Send");
		sendText.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				message = chatbox.getText();
				StyledDocument doc = messageArea.getStyledDocument();
				try
				{
					doc.insertString(doc.getLength(), "You: " + message + "\n", null);
				}
				catch (BadLocationException e1)
				{
				}
				chatbox.setText("");

				CW.sendData(-1, message);
			}

		});

		p2.add(chatbox);
		p2.add(sendText);
		p1.add(p2);
		p.add(p1);
		return p;
	}

	/**
	 * Creates the game board button panel
	 * 
	 * @return JPanel - the initialized button panel
	 */
	private static JPanel createButtonTable()
	{
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++)
		{
			JButton b = new JButton();
			b.setPreferredSize(new Dimension(50, 50));
			b.setText("" + i);
			b.setFont(new Font("Dialog", 1, 0));

			b.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					if (turn)
					{
						CW.sendData(Integer.parseInt(b.getText()), "Your move.");
						b.setText(playersMark);
						b.setFont(new Font("Dialog", 1, 12));
						b.setEnabled(false);
						int win = winnerFound();
						if (win != 0)
						{
							nullGameBoard(win);
						}
						turn = false;
					}
				}
			});
			p.add(b);
			gameBoard.put(i, b);
		}

		return p;
	}

	/**
	 * Displays the received text in the message area
	 * 
	 * @param text - the message to display
	 */
	protected void displayReceivedText(String text)
	{
		StyledDocument doc = messageArea.getStyledDocument();
		try
		{
			doc.insertString(doc.getLength(), opponentsName + ": " + text + "\n", null);
		}
		catch (BadLocationException e)
		{
		}
	}

	/**
	 * Marks the given button with the opponents mark
	 * 
	 * @param location - the id of the button to mark
	 */
	protected void markBoard(int location)
	{
		gameBoard.get(location).setFont(new Font("Dialog", 1, 12));
		gameBoard.get(location).setText(opponentsMark);
		gameBoard.get(location).setEnabled(false);
		int win = winnerFound();
		if (win != 0)
		{
			nullGameBoard(win);
		}
		turn = true;
	}

	/**
	 * Checks if someone has won
	 * 
	 * @return int - 0 for no winner, 1 for player, 2 for opponent
	 */
	private static int winnerFound()
	{
		String zero = "";
		if (!gameBoard.get(0).isEnabled())
			zero = gameBoard.get(0).getText();
		if ((!gameBoard.get(1).isEnabled()) && zero.equals(gameBoard.get(1).getText()))
		{
			if ((!gameBoard.get(2).isEnabled()) && zero.equals(gameBoard.get(2).getText()))
			{
				if (zero.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}
		if ((!gameBoard.get(4).isEnabled()) && zero.equals(gameBoard.get(4).getText()))
		{
			if ((!gameBoard.get(8).isEnabled()) && zero.equals(gameBoard.get(8).getText()))
			{
				if (zero.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}
		if ((!gameBoard.get(3).isEnabled()) && zero.equals(gameBoard.get(3).getText()))
		{
			if ((!gameBoard.get(6).isEnabled()) && zero.equals(gameBoard.get(6).getText()))
			{
				if (zero.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}

		String one = "";
		if (!gameBoard.get(1).isEnabled())
			one = gameBoard.get(1).getText();
		if ((!gameBoard.get(4).isEnabled()) && one.equals(gameBoard.get(4).getText()))
		{
			if ((!gameBoard.get(7).isEnabled()) && one.equals(gameBoard.get(7).getText()))
			{
				if (one.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}

		String two = "";
		if (!gameBoard.get(2).isEnabled())
			two = gameBoard.get(2).getText();
		if ((!gameBoard.get(5).isEnabled()) && two.equals(gameBoard.get(5).getText()))
		{
			if ((!gameBoard.get(8).isEnabled()) && two.equals(gameBoard.get(8).getText()))
			{
				if (two.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}
		if ((!gameBoard.get(4).isEnabled()) && two.equals(gameBoard.get(4).getText()))
		{
			if ((!gameBoard.get(6).isEnabled()) && two.equals(gameBoard.get(6).getText()))
			{
				if (two.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}

		String three = "";
		if (!gameBoard.get(3).isEnabled())
			three = gameBoard.get(3).getText();
		if ((!gameBoard.get(4).isEnabled()) && three.equals(gameBoard.get(4).getText()))
		{
			if ((!gameBoard.get(5).isEnabled()) && three.equals(gameBoard.get(5).getText()))
			{
				if (three.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}

		String four = "";
		if (!gameBoard.get(6).isEnabled())
			four = gameBoard.get(6).getText();
		if ((!gameBoard.get(7).isEnabled()) && four.equals(gameBoard.get(7).getText()))
		{
			if ((!gameBoard.get(8).isEnabled()) && four.equals(gameBoard.get(8).getText()))
			{
				if (four.equals(playersMark))
					return 1;
				else
					return 2;
			}
		}

		return 0;
	}

	/**
	 * Disables all buttons on the game board and displays a message to each
	 * player telling them if they won or lost
	 * 
	 * @param winner - an integer representing the player who won, 1 for player,
	 *            2 for opponent
	 */
	private static void nullGameBoard(int winner)
	{
		Object keys[] = gameBoard.keySet().toArray();
		for (Object o : keys)
		{
			gameBoard.get((Integer) o).setEnabled(false);
		}
		if (winner == 1)
		{
			StyledDocument doc = messageArea.getStyledDocument();
			try
			{
				doc.insertString(doc.getLength(), "You win!\n", null);
			}
			catch (BadLocationException be)
			{
			}
		}
		else
		{
			StyledDocument doc = messageArea.getStyledDocument();
			try
			{
				doc.insertString(doc.getLength(), "You lose!\n", null);
			}
			catch (BadLocationException be)
			{
			}
		}
	}

}
