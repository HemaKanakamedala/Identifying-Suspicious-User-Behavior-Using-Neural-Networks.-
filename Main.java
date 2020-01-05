package com;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.util.ArrayList;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
public class Main extends JFrame{
	JPanel p1;
	JPanel p2,p3;
	JLabel title,l1;
	JButton b1,b2,b3,b4,b5,b6,b7;
	Font f1;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
public Main(){
	super("Identifying Suspicious User Behavior");
	p1 = new JPanel();
	f1 = new Font("Courier New",Font.BOLD,14);
	p1.setBackground(new Color(204, 110, 155));
	title = new JLabel("<HTML><BODY><CENTER>Identifying Suspicious User Behavior with Neural Networks</CENTER></BODY></HTML>".toUpperCase());
	title.setForeground(Color.white);
	title.setFont(new Font("Times New Roman",Font.BOLD,16));
	p1.add(title);

	p2 = new JPanel();
	p2.setLayout(null);
	b1 = new JButton("Read Location");
	b1.setFont(f1);
	b1.setBounds(10,50,300,50);
	p2.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			GenerateRandomAccount.readLocation();
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
			JOptionPane.showMessageDialog(Main.this,"Location reading process completed");
			
		}
	});

	b2 = new JButton("Generate Accounts");
	b2.setFont(f1);
	b2.setBounds(10,130,300,50);
	p2.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			GenerateRandomAccount.generateAccount();
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
			JOptionPane.showMessageDialog(Main.this,"Account generated successfully");
		}
	});

	b3 = new JButton("Create Users");
	b3.setFont(f1);
	b3.setBounds(10,210,300,50);
	p2.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			clearTable();
			GenerateRandomAccount.createUsers(dtm);
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
			JOptionPane.showMessageDialog(Main.this,"Users created successfully");
		}
	});
	
	b4 = new JButton("Generate Simulation Data");
	b4.setFont(f1);
	b4.setBounds(10,290,300,50);
	p2.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			GenerateRandomAccount.createSimulationData();
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
		}
	});

	
	b5 = new JButton("Run Neural Network");
	b5.setFont(f1);
	b5.setBounds(10,370,300,50);
	p2.add(b5);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
			setCursor(hourglassCursor);
			try{
				Convert.convert("dataset.txt","dataset.arff");
				NeuralNetwork.nn("dataset.arff");
			}catch(Exception e){
				e.printStackTrace();
			}
			Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
			setCursor(normalCursor);
		}
	});

	b6 = new JButton("Prediction Chart");
	b6.setFont(f1);
	b6.setBounds(10,450,300,50);
	p2.add(b6);
	b6.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Chart chart1 = new Chart("Prediction Chart");
			chart1.pack();
			RefineryUtilities.centerFrameOnScreen(chart1);
			chart1.setVisible(true);
		}
	});

	b7 = new JButton("Exit");
	b7.setFont(f1);
	b7.setBounds(10,530,300,50);
	p2.add(b7);
	b7.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			System.exit(0);
		}
	});

	p3 = new JPanel();
	p3.setLayout(new BorderLayout());
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int r,int c){
			return false;
		}
	};
	table = new JTable(dtm);
	table.setRowHeight(30);
	table.setFont(new Font("Courier New",Font.BOLD,13));
	jsp = new JScrollPane(table);
	dtm.addColumn("User ID");
	dtm.addColumn("System ID");
	dtm.addColumn("Username");
	dtm.addColumn("Password");
	dtm.addColumn("Latitude");
	dtm.addColumn("Longitude");
	p3.add(jsp,BorderLayout.CENTER);
	p3.setBounds(350,50,600,530);
	p2.add(p3);

	getContentPane().add(p1,BorderLayout.NORTH);
	getContentPane().add(p2,BorderLayout.CENTER);
}
public static void main(String a[])throws Exception{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	Main main = new Main();
	main.setVisible(true);
	main.setExtendedState(JFrame.MAXIMIZED_BOTH);
}
public void clearTable(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
}
