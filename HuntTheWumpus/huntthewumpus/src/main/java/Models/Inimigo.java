package Models;

public abstract class Inimigo {
	protected String Nome;
	protected int Dano;
	
    public Inimigo(String nome, int dano) {
        this.Nome = nome;
        this.Dano = dano;
    }

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public int getDano() {
		return Dano;
	}

	public void setDano(int dano) {
		Dano = dano;
	}
	
}
