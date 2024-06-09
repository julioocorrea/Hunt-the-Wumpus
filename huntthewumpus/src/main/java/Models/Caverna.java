package Models;

public class Caverna {

    private Caverna leste;
    private Caverna oeste;
    private Caverna norte;
    private Caverna sul;
    public  Inimigo inimigo;
    private Flecha flecha;
    private Player player;

    public Caverna getLeste() {
        return leste;
    }

    public Caverna getOeste() {
        return oeste;
    }

    public Caverna getNorte() {
        return norte;
    }

    public Caverna getSul() {
        return sul;
    }

    public Inimigo getInimigo() {
        return inimigo;
    }

    public void setInimigo(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    public Flecha getFlecha() {
        return flecha;
    }

    public void setFlecha(Flecha flecha) {
        this.flecha = flecha;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void mapear(Direcao direcao, Caverna caverna) {
        switch (direcao) {
            case LESTE:
                this.leste = caverna;
                break;
            case OESTE:
                this.oeste = caverna;
                break;
            case NORTE:
                this.norte = caverna;
                break;
            case SUL:
                this.sul = caverna;
                break;
        }
    }
}