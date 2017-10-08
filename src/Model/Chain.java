package Model;

import View.GUI;

public interface Chain {
	public void setNextChain(Chain nextChain);
	public void checkUser(Login req, GUI g, Start t);
}
