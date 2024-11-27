package mainPkg;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import algPkg.BubbleSort;
import algPkg.CocktailShakerSort;
import algPkg.CountingSort;
import algPkg.DoubleSelectionSort;
import algPkg.InsertionSort;
import algPkg.OddEvenSort;
import algPkg.OptimizedBubbleSort;
import algPkg.RadixSort;
import algPkg.SelectionSort;
import algPkg.Settings;
import algPkg.ShellSort;
import algPkg.Sort;
import menuPkg.Menu;
import utilsPkg.Button;
import utilsPkg.KeyHandler;
import utilsPkg.MouseHandler;

public class JSort extends JPanel implements Runnable{
	private Defines defs;
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
    	defs = new Defines();
    	mh = new MouseHandler();
    	menu = new Menu(defs);
    	sort = new Sort(defs.width/10, defs);
    	kh = new KeyHandler();
    	mouse = new Point(0, 0);
     	
    	this.setFont( new Font("serif", Font.BOLD, defs.fontSize));
    	
    	thread = new Thread(this);
    	
    	this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(defs.width, defs.height));
		this.setSize(new Dimension(defs.width, defs.height));
		this.addMouseListener(mh);
		this.addKeyListener(kh);
		this.setFocusable(true);
		this.setVisible(true);
		this.startApp();
    }
    
    public void startApp() {
    	thread = new Thread(this);
    	thread.start();
    }
    
    public long getNewTimeAndSleep() {
    	drawInterval = 1000000000/defs.FPS; //recalculate drawInterval in case the fps changes
		
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
    	drawInterval = 1000000000/defs.FPS;
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
    	try {
    		mouse = getMousePoint(MouseInfo.getPointerInfo().getLocation());
    	}catch (Exception e) {
    	}
    	
    	if (menu.getChoice() == 0) {
    		if (defs.FPS != 60)
    			defs.FPS = 60;
    		
    		menu.tick(mh.clicked, mouse, defs);
    	}else {
    		menu.buttons.choice = 0;
    		for (Button b : menu.buttons.btns)
    			b.active = false;
    		
    		switch (menu.getChoice()) {
    			case 1:
    				sort = new SelectionSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 2:
    				sort = new BubbleSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 3:
    				sort = new InsertionSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 4:
    				sort = new CocktailShakerSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 5:
    				sort = new OddEvenSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 6:
    				sort = new ShellSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 7:
    				sort = new RadixSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 8:
    				sort = new CountingSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 9:
    				sort = new DoubleSelectionSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 10:
    				sort = new OptimizedBubbleSort(defs.width/this.defs.divisor, defs);
    			break;
    			case 11:
    				sort = new Settings(defs.width, defs);
    			break;
    			case 12:
    				System.exit(0);
    			break;
    		}
    		
    		if (menu.getChoice() != defs.buttons)
    			sort.tick(this, defs);
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
    	g.setColor(Color.black);
    	g.fillRect(0, 0, defs.width, defs.height); //clear background
    	
    	
    	if (menu.getChoice() != defs.buttons) {
	    	if (menu.getChoice() == 0)
	    		menu.paintComponent(g);
	    	else
	    		sort.paintComponent(g, defs, defs.rosePurple);
    	}
    }
	
	public Defines getDefs() {
		return defs;
	}

	public void setDefs(Defines defs) {
		this.defs = defs;
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
