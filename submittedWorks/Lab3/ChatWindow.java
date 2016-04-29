package Lab3;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;


public class ChatWindow extends JFrame
{

	private static JPanel mainPanel;
	private static JTextPane messageArea;
	static String name;
	private static String message = "";
	static L3Client L3C;

	public ChatWindow(String name, L3Client L3)
	{
		this.name = name;
		this.setTitle("Client Side");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		L3C = L3;

		JPanel middlePanel = createMiddlePanel();
		mainPanel.add(middlePanel);

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Creates the content panel
	 * 
	 * @return JPanel - a panel with a button area and message area
	 */
	private static JPanel createMiddlePanel()
	{
		JPanel p = new JPanel();

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
					doc.insertString(doc.getLength(), L3C.userName + ": " + message + "\n", null);
				}
				catch (BadLocationException e1)
				{
					e1.printStackTrace();
				}
				chatbox.setText("");

				L3C.sendData(message);
			}
		});

		p2.add(chatbox);
		p2.add(sendText);
		p1.add(p2);
		p.add(p1);
		return p;
	}

	/**
	 * Displays the received text in the message area Sam call this with the
	 * message received from the other guy
	 * 
	 * @param text - the message to display
	 */
	protected void displayReceivedText(String text)
	{
		StyledDocument doc = messageArea.getStyledDocument();
		try
		{
			doc.insertString(doc.getLength(), text + "\n", null);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}

}
