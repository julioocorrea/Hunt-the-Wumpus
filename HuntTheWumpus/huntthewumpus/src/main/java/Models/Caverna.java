package Models;

public class Caverna {
    private Caverna Leste; 
    private Caverna Oeste;
    private Caverna Norte;
    private Caverna Sul;
    private Inimigo Inimigo; 
    private Flecha Flecha; 
    private Player player;
    
	public Caverna getLeste() {
		return Leste;
	}
	public void setLeste(Caverna leste) {
		Leste = leste;
	}
	public Caverna getOeste() {
		return Oeste;
	}
	public void setOeste(Caverna oeste) {
		Oeste = oeste;
	}
	public Caverna getNorte() {
		return Norte;
	}
	public void setNorte(Caverna norte) {
		Norte = norte;
	}
	public Caverna getSul() {
		return Sul;
	}
	public void setSul(Caverna sul) {
		Sul = sul;
	}
	public Inimigo getInimigo() {
		return Inimigo;
	}
	public void setInimigo(Inimigo inimigo) {
		Inimigo = inimigo;
	}
	public Flecha getFlecha() {
		return Flecha;
	}
	public void setFlecha(Flecha flecha) {
		Flecha = flecha;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
    
    
}
