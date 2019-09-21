package app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorteio {
	
	private Random seed = new Random();
	private Random rand;
	
	public List<Integer> gaiola;
	
	public Sorteio() {
		rand = new Random(seed.nextLong());
	}

	public Aposta gerarAposta(int qtdDezenas) {
		Aposta aposta = new Aposta();
		List<Integer> a = new ArrayList<Integer>();
		gaiola = new ArrayList<Integer>();
		
		int[] i = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
		for (int j : i) {
			gaiola.add(j);
		}
		
		while(a.size() < qtdDezenas) {
			int r = rand.nextInt(25);
			int valor = gaiola.get(r);
			
			if(!a.contains(valor)) {
				a.add(valor);
			}
		}
		
		aposta.setDezenas(a);
		return aposta;
	}

}
