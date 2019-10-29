package app.model;

import java.util.ArrayList;
import java.util.List;

public class Aposta {
	
	//TODO Contador de numeros e apostas
	Integer [] dez = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
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
			
			if(aux > contador) {
				contador = aux;
				//TODO
				System.out.println("Concurso: " + apostas.get(i).toString() + " Contador: " + contador + " ");
				
			}
				
		}
		
		return contador;
	}
	
	public Integer[] contagem(List<Aposta> apostas) {
		for(Aposta aposta : apostas)
    	for(int i : aposta.getDezenas()) {
    		switch (i) {
			case 1:
				dez[0]++;
				break;
			case 2:
				dez[1]++;
				break;
			case 3:
				dez[2]++;
				break;
			case 4:
				dez[3]++;
				break;
			case 5:
				dez[4]++;
				break;
			case 6:
				dez[5]++;
				break;
			case 7:
				dez[6]++;
				break;
			case 8:
				dez[7]++;
				break;
			case 9:
				dez[8]++;
				break;
			case 10:
				dez[9]++;
				break;
			case 11:
				dez[10]++;
				break;
			case 12:
				dez[11]++;
				break;
			case 13:
				dez[12]++;
				break;
			case 14:
				dez[13]++;
				break;
			case 15:
				dez[14]++;
				break;
			case 16:
				dez[15]++;
				break;
			case 17:
				dez[16]++;
				break;
			case 18:
				dez[17]++;
				break;
			case 19:
				dez[18]++;
				break;
			case 20:
				dez[19]++;
				break;
			case 21:
				dez[20]++;
				break;
			case 22:
				dez[21]++;
				break;
			case 23:
				dez[22]++;
				break;
			case 24:
				dez[23]++;
				break;
			case 25:
				dez[24]++;
				break;
			default:
				break;
			}
    	}
    	return dez;
	}

	@Override
	public String toString() {
		return "Aposta [ " + dezenas + " ]";
	}

	
}
