import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HelloWorldSwing3
{
	
	private static JPanel mainPanel;
	private static JTextField input;
	private static JComboBox size;
	private static JLabel displayedText;
	private static JCheckBox checkBox;
	private static JTextField clear;
	private static JLabel error;

	private static String text = "Hello World!";
	private static String[] dropdown = {"Tiny", "Small", "Medium", "Large"};
	private static int textSize = 12;
	private static int atribute = Font.PLAIN;
	private static String font = "Arial";
	private static int timeout = 5;
	
	public static void main(String[] args)
	{

		// CREATE A JFRAME
		JFrame f = createAndSetupFrame();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		f.setContentPane(mainPanel);

		// CREATE THE TOP PANEL
		JPanel topPanel = createTopPanel();
		mainPanel.add(topPanel);

		// CREATE THE MIDDLE PANEL
		JPanel middlePanel = createMiddlePanel();
		mainPanel.add(middlePanel);

		// CREATE THE BOTTOM PANEL
		JPanel bottomPanel = createBottomPanel();
		mainPanel.add(bottomPanel);

		// SHOW ME
		f.pack();
		f.setVisible(true);
	}
	
	private static JFrame createAndSetupFrame()
	{
		JFrame f = new JFrame("HelloWorldSwing!");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(370, 220);
		return f;
	}

	private static JPanel createTopPanel()
	{
		JPanel p = new JPanel();
		p.add(new JLabel("Text:"));
		input = new JTextField("Hello World!", 10);
		size = new JComboBox(dropdown);
		size.setSelectedIndex(1);
		size.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String s = (String) cb.getSelectedItem();
				if(s.equals("Tiny"))
					textSize = 8;
				else if(s.equals("Small"))
					textSize = 12;
				else if(s.equals("Medium"))
					textSize = 20;
				else if(s.equals("Large"))
					textSize = 28;
				displayedText.setFont(new Font(font, atribute, textSize));
			}
			
		});
		p.add(input);
		p.add(size);
		
		checkBox = new JCheckBox("clear after:");
		checkBox.setSelected(false);
		p.add(checkBox);
		
		clear = new JTextField("5", 5);
		clear.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();				
			}
			
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				warn();
			}
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
			}
			
			public void warn()
			{
				if(!clear.getText().equals(""))
				{	
					try
					{
						if(Integer.parseInt(clear.getText()) <=0 || Integer.parseInt(clear.getText()) >= 11)
						{
							error.setText("Enter int between 0 and 11 exclusive");
						}
						else
						{
							timeout = Integer.parseInt(clear.getText());
							error.setText(null);
						}
					}
					catch(Exception e)
					{
						error.setText("Enter int between 0 and 11 exclusive");
					}
				}
			}
			
		});
		p.add(clear);
		
		return p;
	}
	
	private static JPanel createMiddlePanel()
	{
		JPanel p = new JPanel();
		JPanel radio = createRadioButtons();
		p.add(radio);
		displayedText = new JLabel(text);
		displayedText.setFont(new Font(font, atribute, textSize));
		p.add(displayedText);
		return p;
	}
	
	private static JPanel createBottomPanel()
	{
		JPanel p = new JPanel();
		JButton submitButton = new JButton("Show!");
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				text = input.getText();
				displayedText.setText(text);
				if(checkBox.isSelected())
				{
					Timer t = new Timer((timeout * 1000), new ActionListener() {
						
						@Override
					    public void actionPerformed(ActionEvent e) {
							if(checkBox.isSelected())
								displayedText.setText(null);
					    }
						
					});
					t.setRepeats(false);
					t.start();
				}
			}

		});
		p.add(submitButton);
		
		error = new JLabel();
		error.setMinimumSize(new Dimension(20, 15));
		error.setPreferredSize(new Dimension(220, 15));
		error.setMaximumSize(new Dimension(220, 15));
		p.add(error);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			
			@Override
		    public void actionPerformed(ActionEvent e) {
		        System.exit(0);
		    }
			
		});
		p.add(exitButton);
		return p;
	}
	
	private static JPanel createRadioButtons()
	{
		JRadioButton plain = new JRadioButton("Plain", true);
		JRadioButton bold = new JRadioButton("Bold");
		JRadioButton italic = new JRadioButton("Italic");
		JRadioButton boldItalic = new JRadioButton("Bold Italic");
		
		plain.setActionCommand("Plain");
		bold.setActionCommand("Bold");
		italic.setActionCommand("Italic");
		boldItalic.setActionCommand("Bold Italic");
		
		ButtonGroup group = new ButtonGroup();
		group.add(plain);
		group.add(bold);
		group.add(italic);
		group.add(boldItalic);
		
		plain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atribute = Font.PLAIN;
				displayedText.setFont(new Font(font, atribute, textSize));
			}

		});
		
		bold.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atribute = Font.BOLD;
				displayedText.setFont(new Font(font, atribute, textSize));
			}

		});
		
		italic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				atribute = Font.ITALIC;
				displayedText.setFont(new Font(font, atribute, textSize));
			}

		});
		
		boldItalic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				atribute = Font.BOLD + Font.ITALIC;
				displayedText.setFont(new Font(font, atribute, textSize));
			}

		});
		
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.add(plain);
		radioPanel.add(bold);
		radioPanel.add(italic);
		radioPanel.add(boldItalic);
		
		return radioPanel;
	}
	
}
