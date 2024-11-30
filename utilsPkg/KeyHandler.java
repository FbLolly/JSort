package utilsPkg;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import mainPkg.Defines;
import mainPkg.JSort;

public class KeyHandler{
	public boolean pressedEscape;
	private PressedEsc pe;
	
	public KeyHandler() {
		this.pressedEscape = false;
	}
	
	public void handleKeys(JSort jsort) {
		pe = new PressedEsc();
		
		jsort.getInputMap(Defines.IFW).put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
		jsort.getInputMap(Defines.IFW).put(KeyStroke.getKeyStroke("ENTER"), "escape");
		
		jsort.getActionMap().put("escape", pe);
	}
	
	public class PressedEsc extends AbstractAction{
		@Override
		public void actionPerformed(ActionEvent e) {
			pressedEscape = true;
		}
	}
}
