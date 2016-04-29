package cs319;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Lab2Swing extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private static DataModel model;

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		model = new DataModel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab2Swing frame = new Lab2Swing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Lab2Swing() {
		
		//default frame operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//create tabbed Frame
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane.setBounds(0, 0, 343, 361);
		contentPane.add(tabbedPane);
		
		
		//container panel for List
		JPanel List = new JPanel();
		List.setName("List");
		tabbedPane.addTab("List", null, List, null);
		List.setLayout(null);
		JList list = new JList();
		//list
			JButton btnRemove = new JButton("Remove");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int index = list.getSelectedIndex();
					if(index >= 0)
						removeCompany(index);
				}
			});
			btnRemove.setBounds(169, 302, 129, 23);
			List.add(btnRemove);
		
			JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String company = JOptionPane.showInputDialog(null, "What is the new company", "Enter new company name", JOptionPane.PLAIN_MESSAGE);
					if(!company.equals(null))
						addCompany(company);
				}
			});
			btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
			btnAdd.setBounds(10, 302, 129, 23);
			List.add(btnAdd);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(0, 0, 308, 291);
			List.add(scrollPane);
			
			scrollPane.setViewportView(list);
			list.setModel(model);
			list.setSelectionBackground(Color.YELLOW);
			//List.add(list);
			
		//container panel for Tree
		JPanel Tree = new JPanel();
		Tree.setName("Tree");
		tabbedPane.addTab("Tree", null, Tree, null);
		Tree.setLayout(null);
		
		//contents of tree
			JButton button = new JButton("Remove");
			button.setBounds(169, 302, 129, 23);
			Tree.add(button);
			
			JButton button_1 = new JButton("Add");
			button_1.setBounds(10, 302, 129, 23);
			button_1.setAlignmentX(0.5f);
			Tree.add(button_1);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(0, 0, 308, 291);
			scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			Tree.add(scrollPane_1);
			
			JTree tree = new JTree();
			tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Animals") {
					{
						DefaultMutableTreeNode node_1;
						node_1 = new DefaultMutableTreeNode("Mammals");
							node_1.add(new DefaultMutableTreeNode("Human"));
							node_1.add(new DefaultMutableTreeNode("Simanta"));
							node_1.add(new DefaultMutableTreeNode("Kangaroo"));
							node_1.add(new DefaultMutableTreeNode("Elephant"));
							node_1.add(new DefaultMutableTreeNode("Goat"));
							add(node_1);
						node_1 = new DefaultMutableTreeNode("Reptiles");
							node_1.add(new DefaultMutableTreeNode("Lizard"));
							node_1.add(new DefaultMutableTreeNode("Boa"));
							node_1.add(new DefaultMutableTreeNode("Iguana"));
							add(node_1);
						node_1 = new DefaultMutableTreeNode("Birds");
							node_1.add(new DefaultMutableTreeNode("Duck"));
							node_1.add(new DefaultMutableTreeNode("Pigeon"));
							node_1.add(new DefaultMutableTreeNode("Turky"));
							node_1.add(new DefaultMutableTreeNode("Goose"));
							add(node_1);
						node_1 = new DefaultMutableTreeNode("Insects");
							node_1.add(new DefaultMutableTreeNode("Termite"));
							node_1.add(new DefaultMutableTreeNode("Ladybug"));
							node_1.add(new DefaultMutableTreeNode("Fly"));
							node_1.add(new DefaultMutableTreeNode("Ant"));
							add(node_1);
						node_1 = new DefaultMutableTreeNode("Fish");
							node_1.add(new DefaultMutableTreeNode("Swordfish"));
							node_1.add(new DefaultMutableTreeNode("Shark"));
							node_1.add(new DefaultMutableTreeNode("Eel"));
							add(node_1);
					}
				}
			));
			scrollPane_1.setViewportView(tree);
			tabbedPane.setSelectedIndex(0);
			String [] ColumnNames = {"First Name", "Last Name", "Age", "Gender", "Vegitatian"};
			Object dataValues[][] = {
						{"Kathy", "Smith", 25, 'F', false},
						{"John", "Doe", 43, 'M', false},
						{"Sue", "Black", 61, 'F', true},
						{"Jane", "White", 17, 'F', true},
						{"Joe", "Brown", 32, 'M', false},
						{"Abby", "Dawn", 41, 'F', false},
						{"Mila", "Manson", 26, 'F', true},
						{"Jack", "Schmitt", 32, 'M', true}
					};
			
			JScrollPane TabelPane = new JScrollPane();
			tabbedPane.addTab("Tabel", null, TabelPane, null);
			
			table = new JTable(dataValues, ColumnNames);
			TabelPane.setViewportView(table);
			tabbedPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{List, Tree}));
	
	}
	
	private void addCompany(String company)
	{
		try
		{
			model.addItem(company);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void removeCompany(int index)
	{
		try
		{
			model.removeItem(index);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
