package main;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	public Window(GameScreen gameScreen) {
		setTitle("Snake");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(150, 150);
		setResizable(false);
		add(gameScreen);
		pack();
	}
}
