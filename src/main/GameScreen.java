package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameScreen extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private final int originTile = 16;
	private final int scale = 2;
	private final int tileSize = originTile * scale;
	private final int colsScreen = 24;
	private final int rowsScreen = 18;
	private final int widthScreen = tileSize * colsScreen;
	private final int heightScreen = tileSize * rowsScreen;
	private int[] snakeX;
	private int[] snakeY;
	private Random random;
	private int foodX;
	private int foodY;
	private KeyHandler keyHandler;
	private int snakeLength = 2;
	private int score = 0;
	private boolean isGamePlaying;
	private final int delay = 75;
	private Timer timer;
	
	public GameScreen() {
		setPreferredSize(new Dimension(widthScreen, heightScreen));
		snakeX = new int[widthScreen * heightScreen / tileSize];
		snakeY = new int[widthScreen * heightScreen / tileSize];
		random = new Random();
		keyHandler = new KeyHandler();
		addKeyListener(keyHandler);
		setFocusable(true);
		timer = new Timer(delay, this);
	}

	public void startGame() {
		isGamePlaying = true;
		init();
		timer.start();
	}
	private void init() {
		spawnFood();
		for (int i = 0; i < snakeLength; i++) {
			snakeX[i] = i * tileSize;
			snakeY[i] = 0;
		}
	}

	private void update() {
		moveSnake();
		checkFoodCollision();
		checkCollision();
	}
	
	private void checkFoodCollision() {
		if (snakeX[0] == foodX && snakeY[0] == foodY ) {
			snakeLength++;
			score++;
			spawnFood();
		}
	}

	private void checkCollision() {
		if (snakeX[0] == widthScreen || snakeY[0] == heightScreen || snakeX[0] < 0 || snakeY[0] < 0) {
			isGamePlaying = false;
		}
	}

	private void moveSnake() {
		for (int i = snakeLength; i > 0; i--) {
			snakeX[i] = snakeX[i - 1];
			snakeY[i] = snakeY[i - 1];
		}
		
		if (keyHandler.isMovingLeft) {
			snakeX[0] -= tileSize;
		} else
		if (keyHandler.isMovingRight) {
			snakeX[0] += tileSize;
		} else
		if (keyHandler.isMovingUp) {
			snakeY[0] -= tileSize;
		} else
		if (keyHandler.isMovingDown) {
			snakeY[0] += tileSize;
		}
	}
	
	private void spawnFood() {
		foodX = random.nextInt(colsScreen) * tileSize;
		foodY = random.nextInt(rowsScreen) * tileSize;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		drawBackground(g2);
		drawFood(g2);
		drawSnake(g2);
		drawScore(g2);
		if (!isGamePlaying) {
			drawGameOver(g2);
		}
	}
	
	private void drawBackground(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, widthScreen, heightScreen);
	}
	
	private void drawFood(Graphics2D g2) {
		g2.setColor(Color.GREEN);
		g2.fillRect(foodX, foodY, tileSize, tileSize);
	}
	
	private void drawSnake(Graphics2D g2) {
		for (int i = 0; i < snakeLength; i++) {
			g2.setColor(Color.RED);
			if (i == 0) {
				g2.setColor(Color.ORANGE);
			}
			g2.fillRect(snakeX[i], snakeY[i], tileSize, tileSize);
		}
	}
	
	private void drawGameOver(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.setFont(new Font("Arial", Font.BOLD, 35));
		g2.drawString("Game Over", widthScreen / 2 - 50, heightScreen / 2 -25);
	}
	
	private void drawScore(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Arial", Font.PLAIN, 16));
		g2.drawString("Score " + score, widthScreen / 2 - 20, 15);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (isGamePlaying) {
			update();
			repaint();
		}
	}
}
