package menuPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import mainPkg.Defines;
import utilsPkg.Button;

public class Buttons {
	public Button[] btns;
	public int choice;
	
	public Buttons(Defines defs) {
		btns = new Button[defs.buttons];
		choice = 0;
		
		int tempx = defs.width/4-defs.buttonWidth/2, tempy = defs.buttonHeight*2;
		
		for (int i = 0; i < defs.buttons; i++) {
			btns[i] = new Button(tempx, tempy, defs.buttonWidth, defs.buttonHeight, defs.rosePurple, Color.white, defs.btns[i]);
		
			tempy += defs.buttonHeight*2;
			
			if (tempy > defs.buttonHeight*16) {
				tempy = defs.buttonHeight*2;
				tempx += defs.width/4;
			}
		}
	}
	
	public void tick(boolean clicked, Point mousePos, Defines defs) {
		for (int i = 0; i < defs.buttons; i++) {
			btns[i].tick(clicked, mousePos, defs);
			
			if (btns[i].active)
				choice = i+1;
		}
	}
	
	public void paintComponent(Graphics g) {
		for (Button b : btns) {
			b.paintComponent(g);
		}
	}
}
