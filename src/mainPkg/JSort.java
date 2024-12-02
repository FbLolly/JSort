package mainPkg;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;

import algPkg.*;

import menuPkg.Menu;
import utilsPkg.Button;
import utilsPkg.KeyHandler;
import utilsPkg.MouseHandler;


public class JSort extends JPanel implements Runnable{
	private MouseHandler mh;
	private Menu menu;
	private Sort sort;
	private Point mouse;
	
	public KeyHandler kh;
	public Thread thread;
	
	double drawInterval;
	double nextDrawTime;
	double remainingTime;

    public JSort() {
    	mh = new MouseHandler();
    	menu = new Menu();
    	kh = new KeyHandler();
    	mouse = new Point(0, 0);
     	
    	try {
    		InputStream is = this.getClass().getResourceAsStream("CaskaydiaCove-Bold.ttf"); //im forced to put it in the same pkg
    		Font f = Font.createFont(Font.TRUETYPE_FONT, is);
    		Font sizedFont = f.deriveFont(30f);
    		this.setFont(sizedFont);
    	}catch (IOException | FontFormatException e) {
    		this.setFont(new Font("serif", Font.BOLD, 30));
    	}
    	
    	thread = new Thread(this);
    	
    	this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(Defines.width, Defines.height));
		this.setSize(new Dimension(Defines.width, Defines.height));
		this.addMouseListener(mh);
		this.setFocusable(true);
		this.setVisible(true);
		this.startApp();
    }
    
    public void startApp() {
    	thread = new Thread(this);
    	thread.start();
    }
    
    public long getNewTimeAndSleep() {
    	drawInterval = 1000000000/Defines.FPS; //recalculate drawInterval in case the fps changes
		
    	try {
			remainingTime = nextDrawTime - System.nanoTime();
			remainingTime /= 1000000;
    	
			if (remainingTime < 0)
				remainingTime = 0;
			
			Thread.sleep((long)remainingTime);
			nextDrawTime += drawInterval;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	return (long)remainingTime;
    }
    
    @Override
    public void run() {
    	drawInterval = 1000000000/Defines.FPS;
    	nextDrawTime = System.nanoTime() + drawInterval;
    	remainingTime = 0;
    	
    	while (thread != null) {
        	getNewTimeAndSleep();
        	
        	update();
    		repaint();
    		Toolkit.getDefaultToolkit().sync();
    	}
    }
    
    public Point getMousePoint(Point mousePos) {
    	Point ret = new Point();
    	
    	ret.x = mousePos.x - this.getLocationOnScreen().x;
    	ret.y = mousePos.y - this.getLocationOnScreen().y;
    	
    	return ret;
    }
    
    public void update() {
    	kh.handleKeys(this);
    	
    	try {
    		mouse = getMousePoint(MouseInfo.getPointerInfo().getLocation());
    	}catch (Exception e) {
    	}
    	
    	if (menu.getChoice() == 0) {
    		if (Defines.FPS != Defines.menuFPS)
    			Defines.FPS = Defines.menuFPS;
    		
    		menu.tick(mh.clicked, mouse);
    	}else {
    		menu.buttons.choice = 0;
    		for (Button b : menu.buttons.btns)
    			b.active = false;
    		
    		Sort[] array = {
    				new SelectionSort(Defines.width/Defines.divisor),
    				new BubbleSort(Defines.width/Defines.divisor),
    				new InsertionSort(Defines.width/Defines.divisor),
    				new CocktailShakerSort(Defines.width/Defines.divisor),
    				new OddEvenSort(Defines.width/Defines.divisor),
    				new ShellSort(Defines.width/Defines.divisor),
    				new RadixSort(Defines.width/Defines.divisor),
    				new CountingSort(Defines.width/Defines.divisor),
    				new DoubleSelectionSort(Defines.width/Defines.divisor),
    				new OptimizedBubbleSort(Defines.width/Defines.divisor),
    				new QuickSort(Defines.width/Defines.divisor),
    				new MergeSort(Defines.width/Defines.divisor),
    				new Settings(Defines.width)
    		};
    		
    		if (menu.getChoice() < Defines.buttons) {
    			sort = array[menu.getChoice()-1];
    			array = null;
    			sort.tick(this);
    			kh.pressedEscape = false;
    		}else {
    			System.exit(0);
    		}
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	g.setColor(Color.black);
    	g.fillRect(0, 0, Defines.width, Defines.height); //clear background
    	
    	
    	if (menu.getChoice() != Defines.buttons) {
	    	if (menu.getChoice() == 0)
	    		menu.paintComponent(g);
	    	else if (sort != null)
	    		sort.paintComponent(g);
    	}
    }

	public MouseHandler getMh() {
		return mh;
	}

	public void setMh(MouseHandler mh) {
		this.mh = mh;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
	public Point getMouse() {
		return mouse;
	}
	
	public void setMouse(Point mouse) {
		this.mouse = mouse;
	}
}
//Copyright 2024 FbLolly
