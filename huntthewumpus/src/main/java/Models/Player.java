package Models;

public class Player {

    private String nome;
    private int vida;
    private int flechas;


    public Player(String nome) {
        this.nome = nome;
        this.vida = 100;
        this.flechas = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getFlechas() {
        return flechas;
    }

    public void setFlechas(int flechas) {
        this.flechas = flechas;
    }
}