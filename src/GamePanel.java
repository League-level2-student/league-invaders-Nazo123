import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font smallFont;
	Font deadTitleFont;
	Font deadSmallFont;
	Timer frameDraw;
	Rocketship s;
	ObjectManager z;
	Timer alienSpawn;
	
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	  
	}
	  void startGame() {
	        alienSpawn = new Timer(500 , z);
	        alienSpawn.start();
	    }
	void updateMenuState() {

	}

	void updateGameState() {

s.update();
		z.update();
		if(!s.isActive) {
			currentState = END;
		}
		


	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 20, 150);
		g.setFont(smallFont);
		g.drawString("Press ENTER to start", 135, 300);
		g.drawString("Press SPACE for instructions", 90, 450);
	}

	void drawGameState(Graphics g) {
	
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
	
		z.draw(g);
		z.purgeObjescts();

	}

	void drawEndState(Graphics g) {
		alienSpawn.stop();
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(deadTitleFont);
		g.drawString("Game Over", 110, 150);
		g.setFont(deadSmallFont);
		g.drawString("You killed " + z.getScore() + " enemies", 135, 300);
		g.drawString("Press ENTER to restart", 120, 450);
	}

	GamePanel() {
		s = new Rocketship(250, 700, 50, 50);
		z = new ObjectManager(s);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallFont = new Font("Arial", Font.BOLD, 24);
		deadTitleFont = new Font("Arial", Font.PLAIN, 56);
		deadSmallFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		if (needImage) {
		    loadImage ("space.png");
		}
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
		
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				s = new Rocketship(250, 700, 50, 50);
				z = new ObjectManager(s);
				currentState = MENU;
			}
			if (currentState == MENU) {
				startGame();
				currentState =GAME;
				
				}
			
			 else {
				 alienSpawn.stop();
				currentState = END;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_UP ) {
		
			s.up();

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
		
			s.left();

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			s.right();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			s.down();
		
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_DOWN) {
		s.yspeed = 0;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_LEFT) {
		s.xspeed = 0;
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE&currentState==GAME) {
			z.addProjectile(s.getProjectile());
		}
		else if (currentState == MENU){
		JOptionPane.showMessageDialog(null, "Use arrow keys to move. Press SPACE to fire. Try not to die");
		}
	}
}
