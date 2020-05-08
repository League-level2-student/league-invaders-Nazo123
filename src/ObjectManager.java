import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Rocketship e;
Random random = new Random();

	ArrayList<Projectile> s = new ArrayList<Projectile>();
	ArrayList<Alien> w = new ArrayList<Alien>();
	ObjectManager(Rocketship e){
		this.e=e;
	}
	void addProjectile(Projectile a) {
		s.add(a);
		
	}
	void addAlien() {
		w.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
		
	}
	void update() {
		
		e.update();
		for(Projectile r : s) {
			r.update();
			if(r.y<30) {
				r.isActive = false;
			}
		}
	for(int i = 0; i<w.size();i++) {
		w.get(i).update();
		if(w.get(i).y>LeagueInvaders.HEIGHT) {
			w.get(i).isActive = false;
		}
	}
	
		}
	

	void draw(Graphics g) {
		e.draw(g);
		for(Alien a : w) {
			a.draw(g);
			
		}
		for(Projectile a : s) {
			a.draw(g);
			
		}
			
	}
	void purgeObjescts() {
		int e = w.size();
		int g = s.size();
		for(int i = 0; i<e; i++) {
			if(w.get(i).isActive) {
		
			}
			else {
				w.remove(i);
				i++;
				
			}
			
		}
		for(int i = 0; i<g; i++) {
			if(s.get(i).isActive) {
			
			}
			else {
				s.remove(i);
				i++;
			
			}
		
	}
}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
