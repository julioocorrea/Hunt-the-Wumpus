package Models;

public class Player {
	private String Nome;
	private int Vida;
    private int TotalDeFlechas;
    
    public Player(String nome) {
    	this.Nome = nome;
    	this.Vida = 100;
    	this.TotalDeFlechas = 0;
    }
    
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public int getVida() {
		return Vida;
	}
	public void setVida(int vida) {
		Vida = vida;
	}
	public int getTotalDeFlechas() {
		return TotalDeFlechas;
	}
	public void setTotalDeFlechas(int totalDeFlechas) {
		TotalDeFlechas = totalDeFlechas;
	}
    
    
}
