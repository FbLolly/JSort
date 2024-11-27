package menuPkg;

import java.awt.Graphics;
import java.awt.Point;

import mainPkg.Defines;

public class Menu {
	public Buttons buttons;
	private int choice;
	
	public Menu() { choice = 0; }
	public Menu(Defines defs) {
		buttons = new Buttons(defs);
		choice = 0;
	}
	
	public void tick(boolean clicked, Point mousePos, Defines defs) {
		buttons.tick(clicked, mousePos, defs);
		
		this.choice = buttons.choice;
	}
	
	public void paintComponent(Graphics g) {
		buttons.paintComponent(g);
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}
}
