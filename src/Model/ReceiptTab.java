package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.GUI;

public class ReceiptTab extends Tab {
	private DefaultTableModel model;
	private InventoryTab i;
	private ArrayList<Receipt> itemList = new ArrayList<>();
	private ArrayList<Integer> cbList = new ArrayList<>();
	
    public ReceiptTab(InventoryTab i) {
    	this.i = i;
    	setTable(new JTable());
    	setPane(new JScrollPane(getTable()));
    	setAddBtn(new JButton("Add"));
    	setEditBtn(new JButton("Edit"));
    	setDelBtn(new JButton("Del"));
    	setPanel(new JPanel());
    	tableReceipt();
    	buttonAction();
    }
    
  @SuppressWarnings("serial")
	public void tableReceipt() {
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) { return false; }
		};
		setModel(model);
		Object[] columns = { "Quantity", "Item", "Price" };
		model.setColumnIdentifiers(columns);
		getTable().getTableHeader().setReorderingAllowed(false);
		getTable().setModel(model);
		getTable().setFillsViewportHeight(true);
		
		Object[] row = new Object[3];
		row[0] = "";
		row[1] = "";
		row[2] = " Total " + "0" + " :-";
		model.addRow(row);
	}
  
  	public void buttonAction() {
  		getAddBtn().addActionListener(new ActionListener() { 
  			public void actionPerformed(ActionEvent e) {
				if(containsOnlyNumbers(GUI.getGUI().getQuantity()) && GUI.getGUI().getQuantity().length() > 0 && Integer.parseInt(GUI.getGUI().getQuantity()) > 0) { 
					Receipt receipt = new Receipt(i.getItemList().get(GUI.getGUI().cb.getSelectedIndex()), Integer.parseInt(GUI.getGUI().getQuantity()));
		  			itemList.add(receipt);
		  			cbList.add(GUI.getGUI().cb.getSelectedIndex());
		  			if(model.getRowCount() >= 1) {
						model.removeRow(model.getRowCount()-1);
					}
		  			Object[] row = new Object[3];
		  			row[0] = receipt.getQuantity();
					row[1] = receipt.getName();
					row[2] = receipt.getTotalPrice() + ":-";
					model.addRow(row);
				
					row[0] = "";
					row[1] = "";
					row[2] = "Total " + "0" + ":-";
					model.addRow(row);
					totalCounter();
				} 
				else {
					checkError();
				}
  			}
  		});
  		
  		getEditBtn().addActionListener(new ActionListener() { 
  			public void actionPerformed(ActionEvent e) {
				if(containsOnlyNumbers(GUI.getGUI().getQuantity()) && GUI.getGUI().getQuantity().length() > 0 && Integer.parseInt(GUI.getGUI().getQuantity()) > 0 && getTable().getSelectedRow() != model.getRowCount()-1) { 
					int row = getTable().getSelectedRow();
			  		Receipt receipt = new Receipt(i.getItemList().get(GUI.getGUI().cb.getSelectedIndex()), Integer.parseInt(GUI.getGUI().getQuantity()));
			  		itemList.set(row, receipt);
			  		cbList.set(row, GUI.getGUI().cb.getSelectedIndex());
					model.setValueAt(receipt.getQuantity(), row, 0);
					model.setValueAt(receipt.getName(), row, 1);
					model.setValueAt(receipt.getTotalPrice() + ":-", row, 2);
					totalCounter();
				}  else {
					checkError();
				}
  			}
  		});
  		
  		getDelBtn().addActionListener(new ActionListener() { 
  			public void actionPerformed(ActionEvent e) {
  				if(getTable().getSelectedRow() > -1 && getTable().getSelectedRow() != model.getRowCount()-1) {
  					int row = getTable().getSelectedRow();
  	  				itemList.remove(row);
  	  				cbList.remove(row);
  	  				model.removeRow(getTable().getSelectedRow());
  	  				totalCounter();
  				}
			}
  		});
  	}
  	
	public void totalCounter() {
		double totalPrice = 0;
		if(model.getRowCount() != 1) {
			for(Receipt a : itemList) {
				totalPrice += a.getTotalPrice();
			}
		}
		model.setValueAt("Total "+ totalPrice +":-", model.getRowCount()-1, 2);
	}
	
	public void checkError() {
		
		if(!containsOnlyNumbers(GUI.getGUI().getQuantity())) {
			GUI.getGUI().getErrorMessage("Only enter numbers!");
		}
		else if(GUI.getGUI().getQuantity().length() == 0 || Integer.parseInt(GUI.getGUI().getQuantity()) == 0) {
			GUI.getGUI().getErrorMessage("Enter a minimum of 1 quantity!");
		}
		else if(getTable().getSelectedRow() == model.getRowCount()-1) {
			GUI.getGUI().getErrorMessage("You cant edit the totalprice!");
		}
	}
	public ArrayList<Integer> getCbList(){ return cbList; }
}