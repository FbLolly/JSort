package algPkg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;

import mainPkg.Defines;
import mainPkg.JSort;
import utilsPkg.Slider;

public abstract class Sort {
	public int[] array;
	public boolean[] checked;
	
	public int size;
	public int singleWidth;
	
	private Slider speedSlider;
	private int specialSort;
	private boolean checking;
	
	public void swap(int idx1, int idx2) {
		int temp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = temp;
	}
	
	public void setArray() {
		
		for (int i = 0; i < this.size; i++) {
			array[i] = singleWidth+((int)(Math.random()*(((Defines.height-singleWidth)/singleWidth)))*singleWidth);
		}
	}
	
	public void setArray(JSort jsort) {
		int currentIndex = this.size, randomIndex;
		int currentNum = 1;
		
		for (int i = 0; i < this.size; i++) {
			array[i] = currentNum;
			
			if (i % 2 == 0)
				currentNum += this.singleWidth;
		}
		
		while (currentIndex != 0) {
			randomIndex = (int)(Math.random()*currentIndex);
			currentIndex--;
			
			this.swap(currentIndex, randomIndex);
			this.sortingUtils(jsort, currentNum);
		}
		
	}
	
	public Sort(int elementNum) {
		checked = new boolean[elementNum];
		for (int i = 0; i < this.size; i++) this.checked[i] = false;
		
		size = elementNum;
		singleWidth = Defines.width/size;
		
		array = new int[elementNum];
		
		this.speedSlider = new Slider();
		this.speedSlider.value = 50;
		this.speedSlider.setBallY(((49)*10)+20);
		this.checking = false;
	}
	
	protected boolean sortingUtils(JSort jsort, int special) {
		if (jsort.kh.pressedEscapeOrEnter) {
			jsort.getMenu().setChoice(0);
			return true;
		}
		
		try {
			jsort.setMouse(jsort.getMousePoint(MouseInfo.getPointerInfo().getLocation()));
		}catch (Exception e) {}
		
		this.setSpecialSort(special);
		this.speedSlider.tick(jsort.getMh().clicked, jsort.getMouse(), this);
		Defines.FPS = (int)(Defines.sortingFPS*(2-((double)this.speedSlider.value/50.0)));
	
		if (Defines.FPS <= 5)
			Defines.FPS = 5;
		
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
	
	public abstract void tick(JSort jsort);
	
	protected void startingOperations(JSort jsort) {
		Defines.sortingFPS = (100/Defines.divisor)*5;
		Defines.FPS = Defines.sortingFPS;
		this.setArray(jsort);
		Defines.sortingFPS = (100/Defines.divisor)*100;
		Defines.FPS = Defines.sortingFPS;
	}
	
	protected void startingOperations(int mult1, int mult2) {
		Defines.sortingFPS = (100/Defines.divisor)*mult1;
		Defines.FPS = Defines.sortingFPS;
		this.setArray();
		Defines.sortingFPS = (100/Defines.divisor)*mult2;
		Defines.FPS = Defines.sortingFPS;
	}
	
	protected void exitOperations(JSort jsort) {
		//add actual sorting here
	
			Defines.sortingFPS = (100/Defines.divisor)*5;
			Defines.FPS = Defines.sortingFPS;
			
			this.checking = true;
			for (int i = 0; i < this.size; i++) {
				this.checked[i] = true;
				
				this.sortingUtils(jsort, i);
			}
			this.checking = false;

			jsort.getMenu().setChoice(0); //sets menu back to active
	}
	
	public void paintComponent(Graphics g) {
		if (this.array != null) {
			g.setColor(Color.lightGray);
			for (int i = 0; i < size; i++) {
				if (i != size-1 && this.checked[i] && array[i] <= array[i+1] && this.checking) {
					g.setColor(Color.GREEN);
				}else if (g.getColor() != Color.lightGray) {
					g.setColor(Color.lightGray);
				}
				
				g.fillRect(i*singleWidth, Defines.height-array[i], singleWidth, array[i]);
			}
		
			if (specialSort < this.size) {
				g.setColor(Defines.rosePurple);
				g.fillRect(specialSort*singleWidth, Defines.height-array[specialSort], singleWidth, array[specialSort]);
			}
		}

		speedSlider.paintComponent(g);
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
