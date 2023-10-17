package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean isMovingLeft = false;
	public boolean isMovingRight = true;
	public boolean isMovingUp = false;
	public boolean isMovingDown = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_LEFT && !isMovingRight) {
			isMovingUp = false;
			isMovingDown = false;
			isMovingLeft = true;
		} else
		if (code == KeyEvent.VK_RIGHT && !isMovingLeft) {
			isMovingUp = false;
			isMovingDown = false;
			isMovingRight = true;
		} else
		if (code == KeyEvent.VK_UP && !isMovingDown) {
			isMovingLeft = false;
			isMovingRight = false;
			isMovingUp = true;
		} else
		if (code == KeyEvent.VK_DOWN && !isMovingUp) {
			isMovingLeft = false;
			isMovingRight = false;
			isMovingDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
