import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship e;
	int score;

	Random random = new Random();

	ArrayList<Projectile> projectile = new ArrayList<Projectile>();
	ArrayList<Alien>alien= new ArrayList<Alien>();

	ObjectManager(Rocketship e) {
		this.e = e;
	}

	void addProjectile(Projectile a) {
		projectile.add(a);

	}

	void addAlien() {
		alien.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));

	}
	int getScore() {
		
		return score;
	}

	void update() {

		e.update();
		for (Projectile r : projectile) {
			r.update();
			if (r.y < 30) {
				r.isActive = false;
			}
		}
		for (int i = 0; i < alien.size(); i++) {
			alien.get(i).update();
			if (alien.get(i).y > LeagueInvaders.HEIGHT) {
				alien.get(i).isActive = false;
			}
		}
		checkCollision();
		purgeObjescts();
	}

	void draw(Graphics g) {
		e.draw(g);
		for (Alien a : alien) {
			a.draw(g);

		}
		for (Projectile s : projectile) {
			s.draw(g);

		}

	} 

	void purgeObjescts() {
		int e = alien.size();
		int g = projectile.size();
		for (int i = 0; i < e; i++) {
			if (!alien.get(i).isActive) {
				alien.remove(i);
				e = alien.size();
			}

		}
		for (int i = 0; i < g; i++) {
			if (!projectile.get(i).isActive) {

				projectile.remove(i);
				g = projectile.size();

			}

		}

	}

	void checkCollision() {
		int f = projectile.size();
		int g = alien.size();
		for (int i = 0; i < g; i++) {
			if (e.collisionBox.intersects(alien.get(i).collisionBox)) {
				e.isActive = false;
				alien.get(i).isActive = false;

			}
			for (int projCount = 0; projCount < f; projCount++) {
				if (projectile.get(projCount).collisionBox.intersects(alien.get(i).collisionBox)) {

					projectile.get(projCount).isActive = false;
					alien.get(i).isActive = false;
					score++;

				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
