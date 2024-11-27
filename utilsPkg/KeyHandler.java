package utilsPkg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean pressedEscapeOrEnter;
	public int direction;
	
	private int code;

	public KeyHandler() {
		pressedEscapeOrEnter = false;
		direction = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
			this.pressedEscapeOrEnter = true;
		}else if (code == KeyEvent.VK_RIGHT) {
			this.direction = +1;
		}else if (code == KeyEvent.VK_LEFT) {
			this.direction = -1;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
			this.pressedEscapeOrEnter = true;
		}else if (code == KeyEvent.VK_RIGHT) {
			this.direction = +1;
		}else if (code == KeyEvent.VK_LEFT) {
			this.direction = -1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
			this.pressedEscapeOrEnter = false;
		}else if (code == KeyEvent.VK_RIGHT) {
			this.direction = 0;
		}else if (code == KeyEvent.VK_LEFT) {
			this.direction = 0;
		}
	}

}
