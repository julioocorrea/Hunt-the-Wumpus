package Services;

import java.util.Random;

import Models.Caverna;
import Views.Output;

public class CavernaService {
	public int compararCaverna(Caverna caverna, Caverna[] cavernas) {
	    int posicao = 0;
	    // Percorre o array de cavernas para encontrar a caverna que corresponde à passada como parâmetro.
	    for(int i = 0; i < cavernas.length; i++) {
	        if(cavernas[i].equals(caverna)) {
	            posicao = i;
	            break; // Interrompe o loop assim que a caverna é encontrada.
	        }
	    }
	    return posicao;
	}
	
	//Verifica se existe um inimigo específico nas cavernas adjacentes à caverna atual do jogador.
	public boolean verificarInimigo(String inimigo, Caverna[] cavernas, int cavernaAtual) {
	    // Verifica a caverna a leste da caverna atual
	    if (VerificarInimigoNaCaverna(cavernas[cavernaAtual].getLeste(), inimigo)) {
    		return true; // Inimigo encontrado na caverna a leste
	    }

	    // Verifica a caverna a oeste da caverna atual
	    if (VerificarInimigoNaCaverna(cavernas[cavernaAtual].getOeste(), inimigo)) {
    		return true; // Inimigo encontrado na caverna a leste
	    }
	    
	    // Verifica a caverna ao norte da caverna atual
	    if (VerificarInimigoNaCaverna(cavernas[cavernaAtual].getNorte(), inimigo)) {
    		return true; // Inimigo encontrado na caverna a leste
	    }
	    
	    // Verifica a caverna ao sul da caverna atual
	    if (VerificarInimigoNaCaverna(cavernas[cavernaAtual].getSul(), inimigo)) {
    		return true; // Inimigo encontrado na caverna a leste
	    }
	    
	    // Se o inimigo não foi encontrado em nenhuma das cavernas adjacentes
	    return false;
	}
	
	//Verifica se existe um inimigo específico nas cavernas adjacentes à caverna atual do jogador.
	public boolean VerificarInimigoNaCaverna (Caverna caverna, String inimigo) {
	    if (caverna != null) {
	        if (caverna.getInimigo() != null) {
	            if (caverna.getInimigo().getNome().equals(inimigo)) {
	                return true; 
	            }
	    	    else {
	    		    return false;
	    	    }
	        }
		    else {
			    return false;
		    }
	    }
	    else {
		    return false;
	    }
	}

}
