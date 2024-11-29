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
	
	public Settings(int elementNum) {
		super(elementNum);
		
		stopped = false;
		slider = new Slider();
		
		slider.value = Defines.divisor;
		slider.setBallY(((slider.value-1)*10)+20);
	}
	
	private boolean settingUtils(JSort jsort, int special) {
		try {
			jsort.setMouse(jsort.getMousePoint(MouseInfo.getPointerInfo().getLocation()));
		}catch (Exception e) {
			
		}
		
		this.slider.tick(jsort.getMh().clicked, jsort.getMouse(), this);
		this.slider.settingSlider(this);
		
		return super.sortingUtils(jsort, special);
	}
	
	public void tick(JSort jsort) {
		//do stuff

		this.startingOperations(5, 10);
		
		while (!stopped) {
			for (i = 0; i < this.size; i++) {
				for (ii = 0; ii < this.size-1; ii++) {
					if (array[ii] > array[ii+1]) {
						this.swap(ii, ii+1);
					}
					
					if (this.settingUtils(jsort, ii)) {
						stopped = true;
						super.exitOperations(jsort);
						return;
					}
				}
			}
			this.startingOperations(5, 10);
		}
		super.exitOperations(jsort);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		slider.paintComponent(g);
		g.setColor(Color.lightGray);
		g.drawString("example algorithm: bubbleSort", 20+Defines.defaultSliderWidth+50, 70);
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
