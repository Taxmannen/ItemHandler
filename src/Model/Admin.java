package Model;

import View.GUI;

public class Admin extends User implements Chain {
	private Chain nextInChain;
	
	@Override
	public void setNextChain(Chain nextChain) {
		this.nextInChain = nextChain;
	}

	@Override
	public void checkUser(Login req, GUI g, Start t) {
		if(req.getUsername().equals("Admin") && req.getPassword().equals("admin")) {
			g.addInventory();
		} else {
			nextInChain.checkUser(req, g, t);
		}
	}
}
