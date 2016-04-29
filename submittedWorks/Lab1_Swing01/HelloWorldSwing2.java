import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HelloWorldSwing2 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelloWorldSwing2 frame = new HelloWorldSwing2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private String text = "";
	private int atribute = Font.PLAIN;
	private String font = "Arial";
	private int size = 12;
	/**
	 * Create the frame.
	 */
	public HelloWorldSwing2() {
		
		//setSize(370, 220);
		setTitle("HelloWorldSwing!");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 220);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(370, 220));
		contentPane.setSize(new Dimension(370, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblMain = new JLabel(text);
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setBounds(113, 49, 190, 89);
		lblMain.setVisible(true);
		contentPane.add(lblMain);
		
		
		JLabel lblText = new JLabel("Text:");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblText.setBounds(10, 11, 68, 22);
		contentPane.add(lblText);
		
		textField = new JTextField();
		textField.setBounds(88, 16, 86, 18);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnPlain = new JRadioButton("Plain");
		rdbtnPlain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atribute = Font.PLAIN;
				lblMain.setFont(new Font(font, atribute, size));
			}
		});
		buttonGroup.add(rdbtnPlain);
		rdbtnPlain.setBounds(10, 41, 109, 23);
		contentPane.add(rdbtnPlain);
		
		JRadioButton rdbtnBold = new JRadioButton("Bold");
		rdbtnBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atribute = Font.BOLD;
				lblMain.setFont(new Font(font, atribute, size));
			}
		});
		buttonGroup.add(rdbtnBold);
		rdbtnBold.setBounds(10, 60, 109, 23);
		contentPane.add(rdbtnBold);
		
		JRadioButton rdbtnItalic = new JRadioButton("Italic");
		rdbtnItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atribute = Font.ITALIC;
				lblMain.setFont(new Font(font, atribute, size));
			}
		});
		buttonGroup.add(rdbtnItalic);
		rdbtnItalic.setBounds(10, 80, 109, 23);
		contentPane.add(rdbtnItalic);
		
		JRadioButton rdbtnBolditalic = new JRadioButton("Bold Italic");
		rdbtnBolditalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atribute = Font.BOLD + Font.ITALIC;
				lblMain.setFont(new Font(font, atribute, size));
			}
		});
		buttonGroup.add(rdbtnBolditalic);
		rdbtnBolditalic.setBounds(10, 99, 109, 23);
		contentPane.add(rdbtnBolditalic);
		
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cb = (JComboBox) e.getSource();
				String s = (String) cb.getSelectedItem();
				if (s.equals("Tiny"))
					size = 8;
				else if (s.equals("Small"))
					size = 12;
				else if (s.equals("Medium"))
					size = 20;
				else if (s.equals("Large"))
					size = 28;
				
				lblMain.setFont(new Font(font, atribute, size));
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tiny", "Small", "Medium", "Large"}));
		comboBox.setMaximumRowCount(4);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBox.setBounds(230, 11, 73, 25);
		comboBox.setSelectedIndex(1);
		contentPane.add(comboBox);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(253, 147, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnShow = new JButton("Show!");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				text = textField.getText();
				lblMain.setText(text);
				
			}
		});
		btnShow.setBounds(10, 147, 89, 23);
		contentPane.add(btnShow);
	}
}
