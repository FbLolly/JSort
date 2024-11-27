package algPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

import mainPkg.Defines;
import mainPkg.JSort;
import utilsPkg.Slider;

public class Settings extends Sort{
	private boolean stopped;
	private Slider slider;
	private int i, ii; //gotta modify them from to outside to avoid out of bounds exceptions
	
	public Settings(int elementNum, Defines defs) {
		super(elementNum, defs);
		
		stopped = false;
		slider = new Slider(defs);
		
		slider.value = defs.divisor;
		slider.setBallY(((slider.value-1)*10)+20);
	}
	
	private boolean settingUtils(JSort jsort, int special, Defines defs) {
		try {
			jsort.setMouse(jsort.getMousePoint(MouseInfo.getPointerInfo().getLocation()));
		}catch (Exception e) {
			
		}
		
		this.slider.tick(jsort.getMh().clicked, jsort.getMouse(), defs, this);
		this.slider.settingSlider(this, defs);
		
		return super.sortingUtils(jsort, special);
	}
	
	public void tick(JSort jsort, Defines defs) {
		//do stuff
		
		defs.sortingFPS = (100-defs.divisor)*10;
		defs.FPS = defs.sortingFPS;
		
		while (!stopped) {
			for (i = 0; i < this.size; i++) {
				for (ii = 0; ii < this.size-1; ii++) {
					if (array[ii] > array[ii+1]) {
						this.swap(ii, ii+1);
					}

					if (this.settingUtils(jsort, ii, defs)) {
						stopped = true;
						super.tick(jsort, defs);
						return;
					}
				}
			}
			
			this.setArray();
		}
		
		super.tick(jsort, defs);
	}
	
	@Override
	public void paintComponent(Graphics g, Defines defs, Color c) {
		super.paintComponent(g, defs, c);
		slider.paintComponent(g, defs);
		g.setColor(Color.lightGray);
		g.drawString("example algorithm: bubbleSort", 20+defs.defaultSliderWidth+50, 70);
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getIi() {
		return ii;
	}

	public void setIi(int ii) {
		this.ii = ii;
	}
}
