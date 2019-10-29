package app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Gaiola {
	
	private Random seed = new Random();
	private Random rand;
	
	private List<Integer> dezenas;

	public Gaiola(int [] dezenas) {
		this.dezenas = new ArrayList<Integer>();
		for(int i: dezenas)
			this.dezenas.add(i);
		
		rand = new Random(seed.nextLong());
	}

	public Integer getBola() {
	//TODO	System.out.println(dezenas.size());
		
		//int index = rand.nextInt(dezenas.size()-1);
		int index = rand.nextInt(dezenas.size());
		int bola = dezenas.get(index);
		
		dezenas.remove(index);
		
		return bola;
	}
	
	public Integer getBolaImpar() {
		
		int bola = 0;
		int index = 0;
		boolean par = true;
		
		while(par) {
			
			//int index = rand.nextInt(dezenas.size()-1);
			index = rand.nextInt(dezenas.size());
			bola = dezenas.get(index);
			
			if(bola % 2 != 0)
				par = false;
			}
		
		dezenas.remove(index);
		
		return bola;
	}
	
	public void removeImpares() {
		for(int i = 0; i < this.dezenas.size(); i++) {
			if(this.dezenas.get(i) % 2 != 0)
				this.dezenas.remove(i);
		}
	}

	

}
