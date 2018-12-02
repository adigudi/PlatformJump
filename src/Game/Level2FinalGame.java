package Game;
//HERE ARE SOME CHANGES!!!

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
import javax.swing.JFrame;
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
	static Graphics g;
	FinalGamePanel GP;
	static Player p1 = new Player(Player.x, y, 100, 100);
	Random r = new Random();
	int rand = r.nextInt(500 - 400) + 400;
	int rand2 = r.nextInt(500 - 400) + 400;
	int rand3 = r.nextInt(500 - 400) + 400;
	int rand4 = r.nextInt(500 - 400) + 400;
	static Platform platform1 = new Platform(1400, 550, 200, 50);
	static Platform platform2 = new Platform(1050, 550, 200, 50);
	static Platform platform3 = new Platform(700, 550, 200, 50);
	static Platform platform4 = new Platform(350, 550, 200, 50);
	static Platform start = new Platform(0, 525, 350, 75);
	static Platform finish = new Platform(1600, 525, 350, 75);
	static Platform lava = new Platform(350, 550, 1250, 50);
	static ArrayList<Player> players = new ArrayList<Player>();
	static ArrayList<Platform> platforms = new ArrayList<Platform>();
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
	static int score = (int) Platform.tempo;

	Level2FinalGame() {
		GP = new FinalGamePanel();
	}

	public static void main(String[] args) {
		Level2FinalGame game = new Level2FinalGame();
		game.run();
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
		platforms.add(start);
		platforms.add(finish);
		platforms.add(lava);
		players.add(p1);
		// platforms.add(new Platform(1200, 600, 200, 50));
		timer.start();
		// g1.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);


	}

	public void actionPerformed(ActionEvent e) {
		checkCollision();

		p1.update();

		for (Platform p : platforms) {
			p.update();
		}
		System.out.println(currentState);

		repaint();

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
		if (p1.getCBox().intersects(Level2FinalGame.lava.getCBox())) {
			currentState = 2;
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
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			currentState = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			currentState = 0;
			Player.x = 20;
			score = 0;
			Player p1 = new Player(Player.x, y, 100, 100);
			Platform.tempo = 1;
			
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

	}

	void updateGameState() {

	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawImage(FinalGamePanel.faceImg, 600, 220, 200, 200, null);
		//System.out.println("Image printed");
		g.setFont(titleFont);
		g.drawString("Platform Jump", 870, 300);
		g.setFont(instructionsFont);
		g.drawString("Press ENTER to play", 870, 400);
	}

	void drawGameState(Graphics g) {
		g.drawImage(FinalGamePanel.backgroundImg, 0, 0, 1950, 600, null);
		p1.draw(g);
		g.setColor(Color.BLUE);
		g.setColor(Color.BLACK);
		g.setFont(instructionsFont);
		g.drawString("Score:" + score, x, y);
		for (Platform p : platforms) {
			p.draw(g);
		}

	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, 1950, 600);
		g.setFont(endFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER!", 800, 300);
		g.setFont(instructionsFont);
		g.drawString("Press P to go back to the main menu", 900, 400);
		
		
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		repaint();
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == GAME_STATE) {
			drawGameState(g);
		} else if (currentState == END_STATE) {
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
