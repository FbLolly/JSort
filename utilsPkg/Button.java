package utilsPkg;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;

import mainPkg.Defines;

public class Button {
	private int x, y, width, height;
	private int gx, gy, gw, gh; //graphical variables, not accessible from the outside
	private String text;
	private Color c;
	private Color textColor;
	public boolean active;
	
	private int textX, textY, textWidth;
	
	private boolean resized;
	
	public Button(int x, int y, int width, int height, Color c, Color textColor, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.gx = this.x + this.width/2;
		this.gy = this.y + this.height/2;
		this.gw = 0;
		this.gh = 0;
		
		this.c = c;
		this.textColor = textColor;
		
		this.active = false;
		
		this.setText(text);
		this.resized = false;
		
		//resizes box to fit text just fine (looks awesome :))
		
		//FontMetrics metrics = g.getFontMetrics(g.getFont());
		
		//int textX = this.x + (this.width - metrics.stringWidth(text))/2;
		//int textY = this.y + ((this.height - metrics.getHeight())/2)+metrics.getAscent();
		
	}
	
	public void tick(boolean mouseClicked, Point mousePos) {
		if ( mousePos.x < this.x+this.width &&
				mousePos.x > this.x &&
				mousePos.y < this.y+this.height &&
				mousePos.y > this.y) { //simple implementation of "checkCollisionPointRect()" in raylib
			
			if (this.gw < this.width) {
				this.gx -= Defines.buttonAnimationSpeed*2;
				this.gw += Defines.buttonAnimationSpeed*4;
			}
			if (this.gh < this.height) {
				this.gy -= Defines.buttonAnimationSpeed;
				this.gh += Defines.buttonAnimationSpeed*2;
			}
			
			if (mouseClicked) {
				this.active = true;
			}
		}else {
			if (this.gw > 0) {
				this.gx += Defines.buttonAnimationSpeed*2;
				this.gw -= Defines.buttonAnimationSpeed*4;
			}
			if (this.gh > 0) {
				this.gy += Defines.buttonAnimationSpeed;
				this.gh -= Defines.buttonAnimationSpeed*2;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		if (!this.resized) {
			FontMetrics metrics = g.getFontMetrics(g.getFont());
			this.textWidth = metrics.stringWidth(text);
			
			this.textX = this.x + (this.width - textWidth)/2;
			this.textY = this.y + ((this.height - metrics.getHeight())/2)+metrics.getAscent();
		
			this.x += (this.width/2)-(this.textWidth/2)-40;
			this.width = this.textWidth + 40;
			
			this.resized = true;
		}
		
		g.setColor(this.c);
		g.fillRoundRect(gx, gy, gw, gh, this.width/10, this.width/10);
		g.setColor(this.textColor);
		g.drawString(text, textX, textY);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
