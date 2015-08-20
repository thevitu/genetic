package evolution;

import java.util.Random;

public class Creature {

	public static final int MUTATION_FACTOR = 10;
	public static final int MUTATION_VAR = 1;
	
	private static Random rand = new Random();
	
	private String race;
	private int str;
	private int acc;
	private int age;
	private int hit;
	private long totalShots;
	
	public Creature() {
		this.race = "Creature";
		this.str = 100;
		this.acc = 100;
		this.age = 1;
		this.hit = 0;
		this.totalShots = 0;
	}
	
	public Creature(String creature, int str, int acc) {
		this();
		this.race = creature;
		this.str = str;
		this.acc = acc;
	}
	
	public int shot() {
		int shot = rand.nextInt(this.acc) * this.str;
		this.totalShots += shot;
		this.hit++;
		return shot;
	}
	
	public double avg() {
		return this.totalShots / this.hit;
	}
	
	public Creature reproduce() {
		this.age++;
		return new Creature(this.getRace(), mutation(this.str), mutation(this.acc));
	}

	private int mutation(int value) {
		return rand.nextInt(100) <= MUTATION_FACTOR ? value - MUTATION_VAR + rand.nextInt((MUTATION_VAR + 1) * 2) : value;
	}

	public String info() {
		return String.format("%s Age: %d Str: %d Acc: %d Avg %f", this.getRace(), this.age, this.str, this.acc, this.avg());
	}

	public String getRace() {
		return this.race;
	}

}
