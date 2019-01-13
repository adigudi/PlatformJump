package Game;
//HERE ARE SOME CHANGES!!!

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Level2FinalGame extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1950;
	public static final int HEIGHT = 600;
	JFrame window;
	static Timer timer;
	static int x = 20;
	static int y = 50;
	static int finishY = 525;
	static int y1 = 550;
	static int y2 = 550;
	static int y3 = 550;
	static int y4 = 550;
	static int lavaY;
	static int lavaIncrease = 10;
	static Graphics g;
	FinalGamePanel GP;
	static boolean scoreIsFive;
	static Player p1 = new Player(Player.x, y, 100, 100);
	static Random r = new Random();
	int rand = r.nextInt(500 - 400) + 400;
	int rand2 = r.nextInt(500 - 400) + 400;
	int rand3 = r.nextInt(500 - 400) + 400;
	int rand4 = r.nextInt(500 - 400) + 400;
	static int randX = r.nextInt(1820 - 0) + 0;
	static int randY = r.nextInt(275 - 0) + 0;
	static Platform platform1 = new Platform(1400, y1, 200, 50, 1);
	static Platform platform2 = new Platform(1050, y2, 200, 50, 0.750);
	static Platform platform3 = new Platform(700, y3, 200, 50, 0.5);
	static Platform platform4 = new Platform(350, y4, 200, 50, 0.25);
	static Platform start = new Platform(0, 525, 350, 75, 0);
	static Platform finish = new Platform(1600, finishY, 350, 75, 0);
	static Lava lava = new Lava(350, 550, 1250, 50);
	static PowerUp powerup = new PowerUp(randX, randY, 40, 80);
	static ArrayList<Player> players = new ArrayList<Player>();
	static ArrayList<Platform> platforms = new ArrayList<Platform>();
	static ArrayList<Lava> lavas = new ArrayList<Lava>();
	static ArrayList<PowerUp> powerups = new ArrayList<PowerUp>();
	final static int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	static int currentState = 0;
	Font titleFont;
	Font instructionsFont;
	Font endFont;
	public static BufferedImage faceImg;
	public static BufferedImage lavaImg;
	public static BufferedImage backgroundImg;
	static int score = 0;
	String song = "powerup.wav";
	String gameover = "gameover.wav";
	String point = "point.wav";
	String intro = "game.wav";
	static AudioClip sound;

	Level2FinalGame() {
		GP = new FinalGamePanel();
	}

	public static void main(String[] args) {
		Level2FinalGame game = new Level2FinalGame();
		game.run();
	}

	public static void setScore(int newScore) {
		score = newScore;
	}

	public static int getScore() {
		return score;
	}

	public static void increaseScore() {
		score++;
	}

	public void run() {
		window = new JFrame("Platform Jump");
		window.addKeyListener(this);
		window.add(this);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.pack();
		timer = new Timer(1000 / 60, this);
		titleFont = new Font("Helvetica Neue", Font.PLAIN, 96);
		instructionsFont = new Font("Helvetica Neue", Font.PLAIN, 48);
		endFont = new Font("Helvetica Neue", Font.PLAIN, 96);
		// platforms.add(new Platform(200, 600, 200, 50));
		// platforms.add(new Platform(200, 500, 200, 50));
		platforms.add(platform1);
		platforms.add(platform2);
		platforms.add(platform3);
		platforms.add(platform4);
		// platforms.add(start);
		// platforms.add(finish);
		lavas.add(lava);
		powerups.add(powerup);
		players.add(p1);
		finishY = 525;
		score = 0;
		// platforms.add(new Platform(1200, 600, 200, 50));
		timer.start();
		playIntro();
		// g1.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);
		lavaY = 550;
	}

	public void actionPerformed(ActionEvent e) {
		checkCollision();

		p1.update();

		for (Platform p : platforms) {
			p.update();
		}
		repaint();
		lava.lavaUpdate();
		for (PowerUp p : powerups) {
			p.powerUpUpdate();
		}
	}

	public void playerRemove(Player p) {
		players.remove(p);
	}

	private boolean checkCollision() {
		if (p1.getCBox().intersects(Level2FinalGame.platform1.getCBox())) {
			handleCollision(platform1);
			return true;
		}
		if (p1.getCBox().intersects(Level2FinalGame.platform2.getCBox())) {
			handleCollision(platform2);
			return true;
		}
		if (p1.getCBox().intersects(Level2FinalGame.platform3.getCBox())) {
			handleCollision(platform3);
			return true;
		}
		if (p1.getCBox().intersects(Level2FinalGame.platform4.getCBox())) {
			handleCollision(platform4);
			return true;
		}
		if (p1.getCBox().intersects(Level2FinalGame.start.getCBox())) {
			handleCollision(start);
			return true;
		}
		if (p1.getCBox().intersects(Level2FinalGame.finish.getCBox())) {
			handleCollision(finish);
			return true;
		}
		p1.setYLimit(500);
		return false;
	}

	private void handleCollision(Platform p) {
		if (p1.getYVelocity() >= 0 && p1.getY() + p1.getHeight() < p.getY() + 45) {
			p1.setYLimit(p.getY() - p1.getHeight());
		} else {
			p1.setYLimit(500);
		}
	}

	// 25
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			p1.left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p1.right = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			p1.jump();
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			timer.stop();
			System.exit(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_I) {
			JOptionPane.showMessageDialog(null," - Use the arrow keys to move and SPACE to jump in order to reach the finish platform to get a point. \n - As your score increases, the speed at which the platforms move also increases. \n - When your score reaches a multiple of 5, the lava will increase in height and a powerup will appear, but then the lava will eventually go down to its normal height when you score a point. \n - When you hit the powerup, the platforms will reset to the same speed at which you started the game. \n - After your score reaches 10, the powerups will no longer show up. \n - Good Luck and Have Fun! :)");
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			sound.stop();
			currentState = 1;
			if (e.getKeyCode() == KeyEvent.VK_ENTER && currentState == 2) {
				Platform.resetPlatforms(platform1, platform2, platform3, platform4);
			}
			p1.update();
			Platform.tempo = 1;
			Player.xVelocity = 5;
			Player.jumpPower = 20;
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			currentState = 0;
			updateMenuState();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			p1.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			p1.right = false;
		}
	}

	public void playSound() {
		sound = JApplet.newAudioClip(getClass().getResource(song));
		sound.play();
	}

	public void playGameOver() {
		sound = JApplet.newAudioClip(getClass().getResource(gameover));
		sound.play();
	}
	
	public void playIntro() {
		sound = JApplet.newAudioClip(getClass().getResource(intro));
		sound.play();
	}
	
	public void stopIntro() {
		sound.stop();
	}

	void FinalGamePanel() {

		try {

			faceImg = ImageIO.read(this.getClass().getResourceAsStream("smile.png"));
			lavaImg = ImageIO.read(this.getClass().getResourceAsStream("lava.png"));
			backgroundImg = ImageIO.read(this.getClass().getResourceAsStream("background.png"));
			// platImg = ImageIO.read(this.getClass().getResourceAsStream("blue.png"));

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}

	void updateMenuState() {
		score = 0;
		Player.x = 20;
		Player p1 = new Player(Player.x, y, 100, 100);
		Platform.tempo = 1;
		playIntro();
	}

	void updateGameState() {

	}

	void updateEndState() {
		currentState = 2;
		Platform.resetPlatforms(platform1, platform2, platform3, platform4);
		Platform.tempo = 0;
		Player.xVelocity = 0;
		Player.jumpPower = 0;
	}

	void drawMenuState(Graphics g) {
		g.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);
		g.setColor(Color.BLUE);
		g.drawImage(FinalGamePanel.faceImg, 600, 220, 200, 200, null);
		// System.out.println("Image printed");
		g.setFont(titleFont);
		g.drawString("Platform Jump", 870, 300);
		g.setFont(instructionsFont);
		g.drawString("Press ENTER to play", 870, 400);
		g.drawString("Press I for instructions", 870, 500);
	}

	void drawGameState(Graphics g) {
		g.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);
		g.setColor(Color.BLUE);
		g.fillRect(0, 525, 350, 75);
		g.fillRect(1600, finishY, 350, 75);
		g.setColor(Color.RED);
		// g.fillRect(350, 550, 1250, 50);
		p1.draw(g);
		g.setColor(Color.BLACK);
		g.setFont(instructionsFont);
		g.drawString("Score:" + score, x, y);
		for (Platform p : platforms) {
			p.draw(g);
		}
		/*
		 * for (Lava l : lavas) { l.drawLava(g); }
		 */
		// if(Level2FinalGame.score != Platform.lastScore && score % 5 == 0) {

		g.drawImage(FinalGamePanel.lavaImg, 350, 550, 1250, 50, null);
		if (score % 5 == 0 && score != 0 && score <= 10) {
			for (PowerUp p : powerups) {
				PowerUp.drawPowerUp(g);
			}
			int newLavaIncrease = lavaIncrease * (getScore() / 5);
			lavaY = 550 - newLavaIncrease;
			lavas.remove(lava);
			Lava lavaNew = new Lava(350, lavaY, 1250, 50 + newLavaIncrease);
			lavas.add(lavaNew);
			System.out.println(newLavaIncrease);
			g.drawImage(FinalGamePanel.lavaImg, 350, Level2FinalGame.lavaY, 1250, 50 + newLavaIncrease, null);
			if (p1.getCBox().intersects(lavaNew.getCBox())) {
				updateEndState();
				playGameOver();
			}
			if (p1.getCBox().intersects(powerup.getCBox())) {
				playSound();
				PowerUp.deleteCBOX();
				powerups.remove(powerup);
				Platform.resetPlatforms(platform1, platform2, platform3, platform4);
			}
		}
		if (p1.getCBox().intersects(lava.getCBox())) {
			playGameOver();
			updateEndState();
			System.out.println("Game Over!");
		}

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 1950, 600);
		g.setFont(endFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER!", 800, 200);
		g.setFont(instructionsFont);
		g.drawString("Your final score is " + getScore(), 800, 300);
		g.drawString("High Score: " + Player.newHighScore, 800, 400);
		g.drawString("Press P to go back to the main menu", 800, 500);
		g.drawString("Press ESC to exit the game", 800, 550);

	}

	public static void startTimer(Graphics g) {
		try {
			for (int i = 0; i <= 10; i++) {
				// System.out.println(i);
				Thread.sleep(1000);
				String time = Integer.toString(i);
				// g.drawString(time, 200, 200);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == 0) {
			drawMenuState(g);
		} else if (currentState == 1) {
			drawGameState(g);
		} else if (currentState == 2) {
			drawEndState(g);
		}
	}

	static void changeCurrentState() {
		currentState++;
	}

}

/*
 * class Platform{ private int x; private int y; private int width; private int
 * height;
 * 
 * private Rectangle cBox = new Rectangle();
 * 
 * public Platform(int x, int y, int w, int h){ this.x = x; this.y = y;
 * this.width = w; this.height = h;
 * 
 * 
 * 
 * cBox.setBounds(x, y, width, height); }
 * 
 * public void update(){ cBox.setBounds(x, y, width, height); }
 * 
 * public void draw(Graphics g){ g.setColor(Color.GREEN); g.fillRect(x, y,
 * width, height); }
 * 
 * public Rectangle getCBox(){ return cBox; }
 * 
 * public int getX(){ return x; }
 * 
 * public int getY(){ return y; } }
 * 
 * class Player{ private int x; private int y; private int width; private int
 * height;
 * 
 * private Rectangle cBox = new Rectangle();
 * 
 * 
 * public boolean left = false; public boolean right = false;
 * 
 * private int xVelocity = 5;
 * 
 * private int gravity = 1; private int yVelocity = 0; private int jumpPower =
 * 20;
 * 
 * private int yLimit = 500;
 * 
 * boolean canJump = false;
 * 
 * public Player(int x, int y, int w, int h){ this.x = x; this.y = y; this.width
 * = w; this.height = h; System.out.println(x); cBox.setBounds(x, y, width,
 * height); }
 * 
 * public void jump(){ if(canJump){ yVelocity -= jumpPower; canJump = false; } }
 * 
 * public void update(){ if(left){ x -= xVelocity; } if(right){ x += xVelocity;
 * }
 * 
 * yVelocity += gravity; y += yVelocity;
 * 
 * if(y >= yLimit + 1){ y = yLimit + 1; yVelocity = 0; canJump = true; } x = x +
 * 10; cBox.setBounds(x, y, width, height); }
 * 
 * public void draw(Graphics g){ g.setColor(Color.BLUE); g.fillRect(x, y, width,
 * height); }
 * 
 * public Rectangle getCBox(){ return cBox; }
 * 
 * public void setYLimit(int l){ yLimit = l; }
 * 
 * public int getX(){ return x; }
 * 
 * public int getY(){ return y; }
 * 
 * public int getWidth(){ return width; }
 * 
 * public int getHeight(){ return height; }
 * 
 * public int getYVelocity(){ return yVelocity; } }
 */
