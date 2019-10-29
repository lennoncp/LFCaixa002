package app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorteio {
	
	private Gaiola gaiola;
	private final int [] cartela = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
	
	public Sorteio() {
	}

	public Aposta gerarAposta(int qtdDezenas) {
		
		gaiola = new Gaiola(cartela);
		Aposta aposta = new Aposta();
		List<Integer> a = new ArrayList<Integer>();	
		
		while(a.size() < qtdDezenas) {
			int valor = gaiola.getBola();
			a.add(valor);
		}	
		
		aposta.setDezenas(a);
		return aposta;
	}
	
	
	public Aposta gerarAposta(int qtdDezenas, int qtdImpares) {
		
		gaiola = new Gaiola(cartela);
		Aposta aposta = new Aposta();
		List<Integer> a = new ArrayList<Integer>();	
		int valor = 0;
		
		int contImpar = 0;
		while(contImpar < qtdImpares) {
			valor = gaiola.getBolaImpar();
			a.add(valor);
			contImpar++;
		}
		
		gaiola.removeImpares();
		
		while(a.size() < qtdDezenas) {
			valor = gaiola.getBola();
			a.add(valor);
		}	
		
		aposta.setDezenas(a);
		return aposta;
	}
	
	

}
