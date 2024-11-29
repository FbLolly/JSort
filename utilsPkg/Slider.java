package utilsPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import algPkg.Settings;
import algPkg.Sort;
import mainPkg.Defines;
import mainPkg.JSort;

public class Slider{
	private int x, y, width, height;
	private int ballX, ballY;
	public int value;
	private int lastValue;
	
	public Slider() {
		value = 0;
		lastValue = 0;
		x = 20; y = 20;
		width = Defines.defaultSliderWidth;
		height = Defines.height-40;
		ballX = x;
		ballY = y;
	}
	
	public void settingSlider(Settings set) {
		if (this.lastValue != this.value) {
			Defines.divisor = this.value;
			Defines.sortingFPS = (100/Defines.divisor)*100;
			Defines.FPS = Defines.sortingFPS;
			
			set.size = Defines.width/Defines.divisor;

			set.setI(0);
			set.setIi(0);
			
			set.singleWidth = value;
			set.array = new int[set.size];
			set.setArray();
		}
		
		this.lastValue = this.value;
	}
	
	public void tick(boolean mouseClicked, Point mousePos, Sort set) {
		if ( mousePos.x < this.x+this.width &&
				mousePos.x > this.x &&
				mousePos.y < this.y+this.height &&
				mousePos.y > this.y) {
			if (mouseClicked) {
				ballY = mousePos.y-Defines.defaultSliderWidth/2;
				
				if (ballY < 20)
					ballY = 20;
				if (ballY > Defines.height-20-Defines.defaultSliderWidth)
					ballY = Defines.height-20-Defines.defaultSliderWidth;
			}
		}
		
		this.value = ((ballY-20)/10)+1;
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRoundRect(x, y, width, height, 10, 10);
		
		g.setColor(Color.lightGray);
		g.fillRoundRect(ballX, ballY, Defines.defaultSliderWidth, Defines.defaultSliderWidth, 10, 10);
	}
	
	public void setBallY(int ballY) {
		this.ballY = ballY;
	}
}
