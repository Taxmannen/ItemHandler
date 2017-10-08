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

public class InventoryTab extends Tab {
	private DefaultTableModel model;
	private ArrayList<Item> itemList = new ArrayList<>();
	
	public InventoryTab() {
    	setTable(new JTable());
    	setPane(new JScrollPane(getTable()));
    	setAddBtn(new JButton("Add"));
    	setEditBtn(new JButton("Edit"));
    	setDelBtn(new JButton("Del"));
    	setPanel(new JPanel());
		tableInventory();
		buttonAction();
	}
	
	@SuppressWarnings("serial")
	public void tableInventory() {
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		setModel(model);
		Object[] columns = { "Item", "Price", "Discount" };
		model.setColumnIdentifiers(columns);
		getTable().getTableHeader().setReorderingAllowed(false);
		getTable().setModel(model);	
		getTable().setFillsViewportHeight(true);
		
		Item coffee = new Item("Coffee", 20, 0);
		Item milk = new Item("Milk", 10, 0);
		itemList.add(coffee);
		itemList.add(milk);
		for(Item i : itemList) {
			Object[] row = new Object[3];
			row[0] = i.getName();
			row[1] = i.getPrice() + ":-";
			row[2] = i.getDiscount() + "%";
			model.addRow(row);  
		}
	}
	
	public void buttonAction() {
  		getAddBtn().addActionListener(new ActionListener() { 
  			public void actionPerformed(ActionEvent e) {
  				if(GUI.getGUI().getName().length() > 0 && GUI.getGUI().getPrice().length() > 0 && GUI.getGUI().getDiscount().length() > 0 && containsOnlyNumbers(GUI.getGUI().getPrice()) && containsOnlyNumbers(GUI.getGUI().getDiscount()) && Integer.parseInt(GUI.getGUI().getDiscount()) <= 100) {
					Item i = new Item(GUI.getGUI().getName(),  Integer.parseInt(GUI.getGUI().getPrice()),  Integer.parseInt(GUI.getGUI().getDiscount()));
		  			itemList.add(i);
		  			Object[] row = new Object[3];
					row[0] = i.getName();
					row[1] = i.getPrice() + ":-";
					row[2] = i.getDiscount() + "%";
					model.addRow(row); 
					GUI.getGUI().cb.addItem(i.getName());	
				} else {
					checkError();
				}
			}
  		});
  		
  		getEditBtn().addActionListener(new ActionListener() { 
  			public void actionPerformed(ActionEvent e) {
  				if(GUI.getGUI().getName().length() > 0 && GUI.getGUI().getPrice().length() > 0 && GUI.getGUI().getDiscount().length() > 0 && containsOnlyNumbers(GUI.getGUI().getPrice()) && containsOnlyNumbers(GUI.getGUI().getDiscount()) && Integer.parseInt(GUI.getGUI().getDiscount()) <= 100) {
					if(getTable().getSelectedRow() > -1) {
						int row = getTable().getSelectedRow();
		  				Item i = new Item(GUI.getGUI().getName(),  Integer.parseInt(GUI.getGUI().getPrice()),  Integer.parseInt(GUI.getGUI().getDiscount()));
		  				itemList.set(row, i);
						model.setValueAt(i.getName(), row, 0);
						model.setValueAt(i.getPrice() + ":-", row, 1);
						model.setValueAt(i.getDiscount() + "%", row, 2);
					} 
  				} else {
					checkError();
				}
			}
  		});
 
  		getDelBtn().addActionListener(new ActionListener() { 
  			public void actionPerformed(ActionEvent e) {
  				if(getTable().getSelectedRow() > -1) {
	  				int row = getTable().getSelectedRow();
	  				itemList.remove(row);
	  				model.removeRow(getTable().getSelectedRow());
  				}
			}
  		});
  	}
	
	public void checkError() {
		if(GUI.getGUI().getName().length() == 0 ) {
			GUI.getGUI().getErrorMessage("You must enter a name!");
		} 
		else if(GUI.getGUI().getPrice().length() == 0) {
			GUI.getGUI().getErrorMessage("You must enter a price!");
		}
		else if(GUI.getGUI().getDiscount().length() == 0) {
			GUI.getGUI().getErrorMessage("You must enter a discount!");
		}
		else if(!containsOnlyNumbers(GUI.getGUI().getPrice())) {
			GUI.getGUI().getErrorMessage("Only numbers in the pricefield!");
		}
		else if(!containsOnlyNumbers(GUI.getGUI().getDiscount())) {
			GUI.getGUI().getErrorMessage("Only numbers in the discountfield");
		}
		else if(Integer.parseInt(GUI.getGUI().getDiscount()) >= 100) {
			GUI.getGUI().getErrorMessage("Discount too high!");
		}
	}
	public ArrayList<Item> getItemList(){ return itemList; }
}