package com;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JScrollPane;
public class ViewDataset extends JFrame{
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
public ViewDataset(){
	super("View Dataset");
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
	dtm.addColumn("Year");
	dtm.addColumn("Month");
	dtm.addColumn("Day");
	dtm.addColumn("Hour");
	dtm.addColumn("Minutes");
	dtm.addColumn("Seconds");
	dtm.addColumn("Session Duration");
	dtm.addColumn("Latitude");
	dtm.addColumn("Longitude");
	getContentPane().add(jsp);
}
}
