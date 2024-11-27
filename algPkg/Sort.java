package algPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

import mainPkg.Defines;
import mainPkg.JSort;
import utilsPkg.Slider;

public class Sort {
	public int[] array;
	public int size;
	public int singleWidth;
	
	private Slider speedSlider;
	private int specialSort;
	private Defines defs;
	
	public void swap(int idx1, int idx2) {
		int temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
	}
	
	public void setArray() {
		for (int i = 0; i < size; i++) {
			array[i] = singleWidth+((int)(Math.random()*(((defs.height-singleWidth)/singleWidth)))*singleWidth);
		}
	}
	
	public Sort(int elementNum, Defines defs) {
		size = elementNum;
		singleWidth = defs.width/size;
		this.defs = defs;
		
		this.speedSlider = new Slider(defs);
		this.speedSlider.value = 50;
		this.speedSlider.setBallY(((49)*10)+20);
		
		array = new int[this.size];
		setArray();
	}
	
	protected boolean sortingUtils(JSort jsort, int special) {
		if (jsort.kh.pressedEscapeOrEnter) {
			jsort.getMenu().setChoice(0);
			return true;
		}
		
		try {
			jsort.setMouse(jsort.getMousePoint(MouseInfo.getPointerInfo().getLocation()));
		}catch (Exception e) {}
		
		defs.FPS += (defs.sortingFPS/32) * jsort.kh.direction;
		jsort.kh.direction = 0;
		
		this.setSpecialSort(special);
		this.speedSlider.tick(jsort.getMh().clicked, jsort.getMouse(), defs, this);
		defs.FPS = (int)(defs.sortingFPS*(2-((double)this.speedSlider.value/50.0)));
	
		if (defs.FPS <= 5)
			defs.FPS = 5;
		
		jsort.repaint();
		jsort.getNewTimeAndSleep();
		
		return false;
	}
	
	protected int getMax() {
		int max = array[0];
		
		for (int i = 0; i < this.size; i++) {
			if (array[i] > max)
				max = array[i];
		}
		
		return max;
	}
	
	public void tick(JSort jsort, Defines defs) {
		//add actual sorting here
		
		defs.sortingFPS = (100/defs.divisor)*25;
		defs.FPS = defs.sortingFPS;
		for (int i = 0; i < this.size; i++) {
			this.sortingUtils(jsort, i);
		}
		
		jsort.getMenu().setChoice(0); //sets menu back to active
	}
	
	public void paintComponent(Graphics g, Defines defs, Color c) {
		g.setColor(Color.white);
		
		this.speedSlider.paintComponent(g, defs);
		if (this.array != null) {
			g.setColor(Color.lightGray);
			for (int i = 0; i < size; i++) {
				g.fillRect(i*singleWidth, defs.height-array[i], singleWidth, array[i]);
			}
		
			if (specialSort < this.size) {
				g.setColor(c);
				g.fillRect(specialSort*singleWidth, defs.height-array[specialSort], singleWidth, array[specialSort]);
			}
		}
	}

	public int getSpecialSort() {
		return specialSort;
	}

	public void setSpecialSort(int specialSort) {
		this.specialSort = specialSort;
	}

	public Slider getSpeedSlider() {
		return speedSlider;
	}

	public void setSpeedSlider(Slider speedSlider) {
		this.speedSlider = speedSlider;
	}
}
