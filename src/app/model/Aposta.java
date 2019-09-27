package app.model;

import java.util.ArrayList;
import java.util.List;

public class Aposta {
	
	private List<Integer> dezenas;

	public List<Integer> getDezenas() {
		return dezenas;
	}

	public void setDezenas(List<Integer> dezenas) {
		this.dezenas = dezenas;
	}
	
	
	public Integer comparador(Aposta aposta, List<Aposta> apostas) {
		Integer contador = 0;
		int aux = 0;
		for(int i = 0; i < apostas.size(); i++) {
			aux = 0;
			for(int j = 0; j < aposta.getDezenas().size(); j++) {
				if(apostas.get(i).getDezenas().contains(aposta.getDezenas().get(j)))
				aux++;
			}
			
			if(aux > contador)
				contador = aux;
		}
		
		return contador;
	}

}
