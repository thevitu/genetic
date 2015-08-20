package evolution;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Test {

	public static final int MAX_POPULATION = 10000;
	public static final int SHOTS = 100;
	
	public static void main(String[] args) {
		LinkedList<Creature> population = genPopulation(MAX_POPULATION);
		
		evolve(population, SHOTS);		
		rank(population);
		
		for (int x = 0; x < 30; x++) {
			elimineWeak(population, MAX_POPULATION / 2);
			nextAge(population);
			evolve(population, SHOTS);		
			rank(population);
		}
		
		showPopulation(population);
	}

	private static LinkedList<Creature> genPopulation(int maxPopulation) {
		System.out.println("Gerando populacao");
		LinkedList<Creature> population = new LinkedList<Creature>();
		for (int x = 0; x < maxPopulation / 2; x++) {
			population.add(new Creature("Forte", 100, 200));
			population.add(new Creature("Agil", 200, 100));
		}
		return population;
	}

	private static void rank(LinkedList<Creature> population) {
		System.out.println("Classificando populacao");
		Collections.sort(population, new Comparator<Creature>() {
			@Override
			public int compare(Creature o1, Creature o2) {
				return Double.compare(o1.avg(), o2.avg());
			}
		});
		Collections.reverse(population);
	}

	private static void evolve(LinkedList<Creature> population, int shots) {
		System.out.println("Desenvolvendo populacao");
		long totalShots = 0;
		int hits = 0;
		for (Creature creature : population) {
			for (int x = 0; x < shots; x++) {
				totalShots += creature.shot();
				hits++;
			}
		}
		System.out.println("Pontuacao media " + totalShots / hits);
	}

	private static void elimineWeak(LinkedList<Creature> population, int amount) {
		for (int x = 0; x < amount; x++) {
			population.removeLast();
		}
	}
	
	private static void nextAge(LinkedList<Creature> population) {
		LinkedList<Creature> newCreatures = new LinkedList<Creature>();
		for (Creature creature : population) {
			newCreatures.add(creature.reproduce());
		}
		population.addAll(newCreatures);
	}

	private static void showPopulation(LinkedList<Creature> population) {
		int forte = 0;
		int agil = 0;
		for (Creature creature : population) {
			System.out.println(creature.info());
			if ("Forte".equals(creature.getRace())) {
				forte++;
			} else {
				agil++;
			}
		}
		System.out.println("Forte: " + forte + " Agil: " + agil);
	}

}
