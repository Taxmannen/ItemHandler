package Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Model.InventoryTab;
import Model.ReceiptTab;
import View.GUI;

public class MouseManager extends MouseAdapter {
	private GUI g;
	private ReceiptTab rTab;
	private InventoryTab iTab;
	
	public MouseManager(GUI g, ReceiptTab rTab, InventoryTab iTab) {
		this.g = g;
		this.rTab = rTab;
		this.iTab = iTab;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(g.getPane().getSelectedIndex() == 0) {
			int row = rTab.getTable().getSelectedRow();
			if(row >= 0 && row != rTab.getTable().getRowCount()-1) {
		 		g.quantity.setText(rTab.getModel().getValueAt(row, 0).toString());
		 		g.cb.setSelectedIndex(rTab.getCbList().get(row));
			}
		} 
		
		else if(g.getPane().getSelectedIndex() == 1) {
			int row = iTab.getTable().getSelectedRow();
			if(row >= 0) {
				String n = iTab.getModel().getValueAt(row, 0).toString();
				String p = iTab.getModel().getValueAt(row, 1).toString();
				String d = iTab.getModel().getValueAt(row, 2).toString();
				g.name.setText(n);
				g.price.setText(p.substring(0, p.length() - 2));
				g.discount.setText(d.substring(0, d.length() - 1));
			}
		}
	}
}
