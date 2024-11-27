package utilsPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import algPkg.Settings;
import algPkg.Sort;
import mainPkg.Defines;

public class Slider{
	private int x, y, width, height;
	private int ballX, ballY;
	public int value;
	private int lastValue;
	
	private Color c, ballC;
	
	public Slider(Defines defs) {
		value = 0;
		lastValue = 0;
		x = 20; y = 20;
		width = defs.defaultSliderWidth;
		height = defs.height-40;
		ballX = x;
		ballY = y;
		c = Color.gray;
		ballC = Color.darkGray;
	}
	
	public void settingSlider(Settings set, Defines defs) {
		if (this.lastValue != this.value) {
			defs.divisor = this.value;
			defs.sortingFPS = (100/defs.divisor)*100;
			defs.FPS = defs.sortingFPS;
			
			set.size = defs.width/defs.divisor;

			set.setI(0);
			set.setIi(0);
			
			set.singleWidth = value;
			set.array = new int[set.size];
			set.setArray();
		}
		
		this.lastValue = this.value;
	}
	
	public void tick(boolean mouseClicked, Point mousePos, Defines defs, Sort set) {
		if ( mousePos.x < this.x+this.width &&
				mousePos.x > this.x &&
				mousePos.y < this.y+this.height &&
				mousePos.y > this.y) {
			if (mouseClicked) {
				ballY = mousePos.y-defs.defaultSliderWidth/2;
				
				if (ballY < 20)
					ballY = 20;
				if (ballY > defs.height-20-defs.defaultSliderWidth)
					ballY = defs.height-20-defs.defaultSliderWidth;
			}
		}
		
		this.value = ((ballY-20)/10)+1;
		
	}
	
	public void paintComponent(Graphics g, Defines defs) {
		g.setColor(c);
		g.fillRoundRect(x, y, width, height, 10, 10);
		
		g.setColor(ballC);
		g.fillRoundRect(ballX, ballY, defs.defaultSliderWidth, defs.defaultSliderWidth, 10, 10);
	}
	
	public void setBallY(int ballY) {
		this.ballY = ballY;
	}
}
