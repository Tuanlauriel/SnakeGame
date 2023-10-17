package main;

public class Main {
	
	public static void main(String[] args) {
		GameScreen gameScreen = new GameScreen();
		Window window = new Window(gameScreen);
		window.setVisible(true);
		gameScreen.startGame();
	}
	
}
