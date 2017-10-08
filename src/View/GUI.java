package View;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controller.MouseManager;
import Model.InventoryTab;
import Model.Item;
import Model.ReceiptTab;
import Model.Start;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private static GUI g = new GUI();
	private InventoryTab i = new InventoryTab();
	private ReceiptTab r = new ReceiptTab(i);
	private JFrame frame = new JFrame();
	private int screenWidth = 610;
	private int screenHeight = 500;
	private JTabbedPane pane = new JTabbedPane();
	private JLabel headerR = new JLabel("Quantity");
	private JLabel headerR2 = new JLabel("Item");
	private JLabel headerI = new JLabel("Item");
	private JLabel headerI2 = new JLabel("Price");
	private JLabel headerI3 = new JLabel("Discount");
    public JTextField quantity = new JTextField(15);
    public JComboBox<String> cb = new JComboBox <String>();
    public JTextField price = new JTextField(15);
    public JTextField name = new JTextField(15);
    public JTextField discount = new JTextField(15);
    
	private GUI() {
		for(Item i : i.getItemList()) { cb.addItem(i.getName()); }
		setLayout();
		new Start(this);
		frame.setVisible(true);
	}
	 	
	private void setLayout() {
		frame.setSize(screenWidth, screenHeight);
		frame.setBackground(Color.black);
		frame.setResizable(false);
		frame.setTitle("HemCoop");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
		headerR.setBounds(160, 285, 200, 100);
		headerR2.setBounds(393, 284, 200, 100);
		headerI.setBounds(135, 284, 200, 100);
		headerI2.setBounds(284, 284, 200, 100);
		headerI3.setBounds(430, 284, 200, 100);
		
		r.getPanel().setLayout(null);
		r.getPane().setBounds(50, 20, 500, 300);
		r.getAddBtn().setBounds(95, 385, 125, 25);
		r.getEditBtn().setBounds(237, 385, 125, 25);
		r.getDelBtn().setBounds(380, 385, 125, 25);
		r.getAddBtn().setBackground(Color.LIGHT_GRAY);
		r.getEditBtn().setBackground(Color.LIGHT_GRAY);
		r.getDelBtn().setBackground(Color.LIGHT_GRAY);
	
		i.getPanel().setLayout(null);
		i.getPane().setBounds(50, 20, 500, 300);
		i.getAddBtn().setBounds(95, 385, 125, 25);
		i.getEditBtn().setBounds(237, 385, 125, 25);
		i.getDelBtn().setBounds(380, 385, 125, 25);
		i.getAddBtn().setBackground(Color.LIGHT_GRAY);
		i.getEditBtn().setBackground(Color.LIGHT_GRAY);
		i.getDelBtn().setBackground(Color.LIGHT_GRAY);
	   	
	   	cb.setBackground(Color.WHITE);
		name.setBounds(75, 350, 140, 20);
		price.setBounds(230, 350, 140, 20);
		discount.setBounds(385, 350, 140, 20);
		quantity.setBounds(85, 350, 200, 20);
		cb.setBounds(315, 350, 200, 20);
		((JLabel) cb.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void addReceipt() {
		frame.add(getPane());
		getPane().add("Create Receipt", r.getPanel());
		r.getTable().addMouseListener(new MouseManager(this, r, i));
		r.getPanel().add(headerR);
		r.getPanel().add(headerR2);
		r.getPanel().add(quantity);
		r.getPanel().add(cb);
		r.getPanel().add(r.getAddBtn());
		r.getPanel().add(r.getEditBtn());
		r.getPanel().add(r.getDelBtn());
		r.getPanel().add(r.getPane());
	}
	
	public void addInventory() {
		addReceipt();
		getPane().add("Add To Inventory", i.getPanel());
		i.getTable().addMouseListener(new MouseManager(this, r, i));
		i.getPanel().add(headerI);
		i.getPanel().add(headerI2);
		i.getPanel().add(headerI3);
		i.getPanel().add(name);
		i.getPanel().add(price);
		i.getPanel().add(discount);
		i.getPanel().add(i.getAddBtn());
		i.getPanel().add(i.getEditBtn());
		i.getPanel().add(i.getDelBtn());
		i.getPanel().add(i.getPane());
	}
	
	public static GUI getGUI()   { return g;                  }
	public JTabbedPane getPane() { return pane;               }
	public String getName()      { return name.getText();     }
	public String getPrice()     { return price.getText();    }
	public String getDiscount()  { return discount.getText(); }
	public String getQuantity()  { return quantity.getText(); }
	public void getErrorMessage(String error) {  JOptionPane.showMessageDialog(new JFrame(), error, "ERROR!!", JOptionPane.ERROR_MESSAGE); }
}