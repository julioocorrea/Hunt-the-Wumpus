package Services;

import Models.Caverna;
import Models.Direcao;

public class Mapa {
    private Caverna raiz;

    public void setRaiz(Caverna raiz, Caverna cavernaSul) {
        this.raiz = raiz;
        this.raiz.mapear(Direcao.SUL,cavernaSul );
        this.raiz.mapear(Direcao.NORTE, null);
        this.raiz.mapear(Direcao.LESTE, null);
        this.raiz.mapear(Direcao.OESTE, null);
    }
    public void criarPrimeiraSubArvore(Caverna caverna, Caverna cavernaSul, Caverna cavernaLeste, Caverna cavernaOeste){
        caverna.mapear(Direcao.NORTE,raiz);
        caverna.mapear(Direcao.SUL, cavernaSul);
        caverna.mapear(Direcao.LESTE, cavernaLeste);
        caverna.mapear(Direcao.OESTE, cavernaOeste);
    }

}