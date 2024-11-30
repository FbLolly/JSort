package menuPkg;

import java.awt.Graphics;

import java.awt.Point;

public class Menu {
	public Buttons buttons;
	private int choice;
	
	
	public Menu() {
		buttons = new Buttons();
		choice = 0;
	}
	
	public void tick(boolean clicked, Point mousePos) {
		buttons.tick(clicked, mousePos);
		
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
