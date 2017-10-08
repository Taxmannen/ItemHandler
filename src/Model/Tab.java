package Model;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tab {
    private JTable table;
    private JScrollPane pane;
    private DefaultTableModel model;
    private JButton addBtn;
	private JButton editBtn;  
	private JButton delBtn;
    private JPanel panel;
    
	public boolean containsOnlyNumbers(String str) {
		for(int i = 0; i < str.length(); i++) { 
			if(!Character.isDigit(str.charAt(i))) { return false; }
		} return true;
	}
	
	public JPanel getPanel() { return panel; }
	public JTable getTable() { return table; }
	public JScrollPane getPane() { return pane; }
	public DefaultTableModel getModel() { return model; }
	public JButton getAddBtn() { return addBtn; }
	public JButton getEditBtn() { return editBtn; }
	public JButton getDelBtn() { return delBtn; }
	
	public void setPanel(JPanel panel) { this.panel = panel; }
	public void setTable(JTable table) { this.table = table; }
	public void setPane(JScrollPane pane) { this.pane = pane; }
	public void setModel(DefaultTableModel model) { this.model = model; }
	public void setAddBtn(JButton addBtn) { this.addBtn = addBtn; }
	public void setEditBtn(JButton editBtn) { this.editBtn = editBtn; }
	public void setDelBtn(JButton delBtn) { this.delBtn = delBtn; }
}