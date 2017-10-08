package Model;

import View.GUI;

public class User implements Chain {
	@SuppressWarnings("unused")
	private Chain nextInChain;

	@Override
	public void setNextChain(Chain nextChain) {
		this.nextInChain = nextChain;
	}

	@Override
	public void checkUser(Login req, GUI g, Start t) {
		if(req.getUsername().equals("User") && req.getPassword().equals("user")) {
			g.addReceipt();
		} else {
			g.getErrorMessage("Wrong username or password!");
    		t.login();
		}
	}
}
