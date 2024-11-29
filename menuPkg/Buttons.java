package menuPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import mainPkg.Defines;
import utilsPkg.Button;

public class Buttons {
	public Button[] btns;
	public int choice;
	
	public Buttons () {
		btns = new Button[Defines.buttons];
		choice = 0;
		
		int tempx = Defines.width/4-Defines.buttonWidth/2, tempy = Defines.buttonHeight*2;
		
		for (int i = 0; i < Defines.buttons; i++) {
			btns[i] = new Button(tempx, tempy, Defines.buttonWidth, Defines.buttonHeight, Defines.rosePurple, Color.white, Defines.btns[i]);
		
			tempy += Defines.buttonHeight*2;
			
			if (tempy > Defines.buttonHeight*18) {
				tempy = Defines.buttonHeight*2;
				tempx += Defines.width/4;
			}
		}
	}
	
	public void tick(boolean clicked, Point mousePos) {
		for (int i = 0; i < Defines.buttons; i++) {
			btns[i].tick(clicked, mousePos);
			
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
