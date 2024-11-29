package utilsPkg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	public boolean pressedEscapeOrEnter;
	private int code;

	public KeyHandler() {
		pressedEscapeOrEnter = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
			this.pressedEscapeOrEnter = true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
			this.pressedEscapeOrEnter = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		code = e.getKeyCode();

		if (code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_ENTER) {
			this.pressedEscapeOrEnter = false;
		}
	}

}
