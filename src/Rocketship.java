import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Rocketship extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		speed = 5;
		if (needImage) {
		    loadImage ("rocket.png");
		}
	}


	int yspeed = 0;
	int xspeed = 0;

	public void up() {

		yspeed = -speed;
	}

	public void left() {

		xspeed = -speed;
	}

	public void down() {

		yspeed = speed;

	}

	public void right() {

		xspeed = speed;

	}

	void update() {

		if (x >= 450 && xspeed > 1) {
			xspeed = 0;
		}
		if (y >= 730 && yspeed > 1) {
			yspeed = 0;
		}
		if (x <= 0 && xspeed < 1) {
			xspeed = 0;
		}
		if (y <= 0 && yspeed < 1) {
			yspeed = 0;
		}

		x += xspeed;
		y += yspeed;
	}
	

	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(x, y, width, height);
		}
	}
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
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10);
} 
}
