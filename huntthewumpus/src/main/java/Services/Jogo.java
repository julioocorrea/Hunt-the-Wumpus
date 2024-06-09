package Services;

import Models.*;
import Views.Output;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {

    private final int NUM_CAVERNAS = 26;
    Caverna[] cavernas = new Caverna[NUM_CAVERNAS];
    ArrayList<Integer> cavernasVisitadas = new ArrayList<>();

    int cavernaAtual = 0;
    Morcego morcego = new Morcego("Bat");
    Wumpus wumpus = new Wumpus("Wumpus");
    Poco poco = new Poco("Pit");
    Mapa mapa = new Mapa();
    Player player = new Player("");
    boolean fimDeJogo = false;

    public int compararCaverna(Caverna caverna) {
        int posicao = 0;
        for(int i = 0; i < cavernas.length; i++) {
            if(cavernas[i].equals(caverna)) {
                posicao = i;
                break;
            }
        }
        return posicao;
    }

    public boolean verificarInimigo(String inimigo) {
        if(cavernas[cavernaAtual].getLeste() != null) {
            if(cavernas[cavernaAtual].getLeste().getInimigo() != null) {
                if (cavernas[cavernaAtual].getLeste().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        if(cavernas[cavernaAtual].getOeste() != null) {
            if(cavernas[cavernaAtual].getOeste().getInimigo() != null) {
                if (cavernas[cavernaAtual].getOeste().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        if(cavernas[cavernaAtual].getNorte() != null) {
            if(cavernas[cavernaAtual].getNorte().getInimigo() != null) {
                if (cavernas[cavernaAtual].getNorte().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        if(cavernas[cavernaAtual].getSul() != null) {
            if(cavernas[cavernaAtual].getSul().getInimigo() != null) {
                if (cavernas[cavernaAtual].getSul().getInimigo().getNome().equals(inimigo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verificarVida() {
        if(player.getVida() <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void verificarCavernas() {
        if(!fimDeJogo) {
            verificarWumpus();
        }
        if(!fimDeJogo) {
            verificarPoco();
        }
        if(!fimDeJogo) {
            verificarMorcego();
        }
        if(!fimDeJogo) {
            verificarFlechas();
        }
    }

    public void verificarWumpus() {
        Output output = new Output();
        if(verificarInimigo("Wumpus")) {
            output.printNearWumpus();
        }
        if(cavernas[cavernaAtual].getInimigo() != null) {
            if(cavernas[cavernaAtual].getInimigo().getNome().equals("Wumpus")) {
                output.printWumpusAttack();
                finalizarJogo();
            }
        }
    }

    public void verificarPoco() {
        Output output = new Output();
        if(verificarInimigo("Pit")) {
            output.printNearPit();
        }

        if(cavernas[cavernaAtual].getInimigo() != null) {
            if(cavernas[cavernaAtual].getInimigo().getNome().equals("Pit")) {
                player.setVida(player.getVida() - 50);
                output.printFallenPit();
            }
        }
        boolean vivo = verificarVida();
        if(!vivo) {
            output.printDefeat(player);
            finalizarJogo();
        }
    }

    public void verificarFlechas() {
        Output output = new Output();
        if(cavernas[cavernaAtual].getFlecha() != null) {
            player.setFlechas(player.getFlechas() + 1);
            cavernas[cavernaAtual].setFlecha(null);
            output.printArrowPickup();
        }
    }

    public void verificarMorcego() {
        Output output = new Output();
        if(verificarInimigo("Bat")) {
            output.printNearBat();
        }
        if(cavernas[cavernaAtual].getInimigo() != null) {
            if(cavernas[cavernaAtual].getInimigo().getNome().equals("Bat")) {
                Random r = new Random();
                int cavernaAleatoria = r.nextInt(25);
                cavernas[cavernaAleatoria].setPlayer(cavernas[cavernaAtual].getPlayer());
                cavernas[cavernaAtual].setPlayer(null);
                cavernaAtual = compararCaverna(cavernas[cavernaAleatoria]);
                cavernasVisitadas.add(cavernaAleatoria);
                output.printNearBat();
                verificarWumpus();
                verificarPoco();
                verificarMorcego();
            }
        }
    }

    public void iniciarJogo() {
        Output output = new Output();
        output.AskPlayerName();
        Input input = new Input();
        String nomeDoJogador = input.nameOfPlayer();

        player.setNome(nomeDoJogador);

        output.printStartMessage();
        for (int i= 0; i<26;i++){
            cavernas[i]= new Caverna();
        }

        MapearCavernas();

        Random r = new Random();
        
        int cavernaMorcego = r.nextInt(25);
        cavernas[cavernaMorcego].inimigo=morcego;
        
        int cavernaPoco =0;
        while (cavernaPoco != cavernaMorcego){
            cavernaPoco=r.nextInt(25);
        }
        cavernas[cavernaPoco].inimigo=poco;
        
        int cavernaWumpus =0;
        while (cavernaWumpus != cavernaMorcego && cavernaWumpus != cavernaPoco){
            cavernaWumpus=r.nextInt(25);
        }
        cavernas[cavernaWumpus].inimigo=wumpus;

        cavernas[r.nextInt(25)].setFlecha(new Flecha("flecha 1"));
        cavernas[r.nextInt(25)].setFlecha( new Flecha("flecha 2"));
        cavernas[r.nextInt(25)].setFlecha(new Flecha("flecha 3"));

        cavernas[cavernaAtual].setPlayer(new Player(nomeDoJogador));
        
        andar(output, input);
    
    }
    
    public void andar(Output output, Input input){
        while(!fimDeJogo) {
            String opcaoNorte;
            String opcaoLeste;
            String opcaoSul;
            String opcaoOeste;
            String opcaoFlecha;
            if(cavernas[cavernaAtual].getNorte() != null && cavernasVisitadas.size() != 0) {
                opcaoNorte = "1 - Ir para o norte";
            } else {
                opcaoNorte = "Não há caminho para o norte!";
            }
            if(cavernas[cavernaAtual].getLeste() != null && cavernasVisitadas.size() != 0) {
                opcaoLeste = "2 - Ir para o leste ";
            } else {
                opcaoLeste = "Não há caminho para o leste!";
            }
            if(cavernas[cavernaAtual].getSul() != null) {
                opcaoSul = "3 - Ir para o sul ";
            } else {
                opcaoSul = "Não há caminho para o sul!";
            }
            if(cavernas[cavernaAtual].getOeste() != null && cavernasVisitadas.size() != 0) {
                opcaoOeste = "4 - Ir para o oeste ";
            } else {
                opcaoOeste = "Não há caminho para o oeste!";
            }
            if(verificarInimigo("wumpus")) {
                opcaoFlecha = "5 - Escolher caverna para atirar uma flecha";
            } else {
                opcaoFlecha = "Você ainda não corre perigo";
            }

            output.printMoveOptions(cavernasVisitadas, player, opcaoNorte, opcaoLeste, opcaoSul, opcaoOeste, opcaoFlecha);

            String opcao = input.promptUserForChoice();
            boolean eNumero = input.isValidOption(opcao);
            if (!eNumero) {
                output.printOnlyNumbersAllowed();
            } else {
                int numero = Integer.parseInt(opcao);
                if (numero == 1) {
                    if (cavernas[cavernaAtual].getNorte() != null) {
                        cavernas[cavernaAtual].getNorte().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getNorte());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.printInvalidOption();
                    }
                } else if (numero == 2) {
                    if (cavernas[cavernaAtual].getLeste() != null) {
                        cavernas[cavernaAtual].getLeste().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getLeste());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.printInvalidOption();
                    }
                } else if (numero == 3) {
                    if (cavernas[cavernaAtual].getSul() != null) {
                        cavernas[cavernaAtual].getSul().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getSul());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.printInvalidOption();
                    }
                } else if (numero == 4) {
                    if (cavernas[cavernaAtual].getOeste() != null) {
                        cavernas[cavernaAtual].getOeste().setPlayer(cavernas[cavernaAtual].getPlayer());
                        cavernas[cavernaAtual].setPlayer(null);
                        cavernaAtual = compararCaverna(cavernas[cavernaAtual].getOeste());
                        cavernasVisitadas.add(cavernaAtual);
                        verificarCavernas();
                    } else {
                        output.printInvalidOption();
                    }
                } else if (numero == 5) {
                    if (player.getFlechas() > 0) {

                    } else {
                        output.printNoArrows();
                    }
                } else {
                    output.printInvalidOption();
                }

            }

        }

    }
    
    public void finalizarJogo() {
        fimDeJogo = true;
    }
    
    public void MapearCavernas() {
        mapa.setRaiz(cavernas[0], cavernas[1]);
        mapa.criarPrimeiraSubArvore(cavernas[1],cavernas[3],cavernas[4],cavernas[2] );

        cavernas[2].mapear(Direcao.NORTE, cavernas[1]);
        cavernas[2].mapear(Direcao.LESTE, cavernas[7]);
        cavernas[2].mapear(Direcao.SUL, cavernas[6]);
        cavernas[2].mapear(Direcao.OESTE, cavernas[5]);

        cavernas[3].mapear(Direcao.NORTE, cavernas[1]);
        cavernas[3].mapear(Direcao.LESTE, cavernas[10]);
        cavernas[3].mapear(Direcao.SUL, cavernas[9]);
        cavernas[3].mapear(Direcao.OESTE, cavernas[8]);

        cavernas[4].mapear(Direcao.NORTE, cavernas[1]);
        cavernas[4].mapear(Direcao.LESTE, cavernas[13]);
        cavernas[4].mapear(Direcao.SUL, cavernas[12]);
        cavernas[4].mapear(Direcao.OESTE, cavernas[11]);

        cavernas[5].mapear(Direcao.NORTE, cavernas[2]);
        cavernas[5].mapear(Direcao.LESTE, cavernas[16]);
        cavernas[5].mapear(Direcao.SUL, cavernas[15]);
        cavernas[5].mapear(Direcao.OESTE, cavernas[14]);

        cavernas[6].mapear(Direcao.NORTE, cavernas[2]);
        cavernas[6].mapear(Direcao.LESTE, cavernas[19]);
        cavernas[6].mapear(Direcao.SUL, cavernas[18]);
        cavernas[6].mapear(Direcao.OESTE, cavernas[17]);

        cavernas[7].mapear(Direcao.NORTE, cavernas[2]);
        cavernas[7].mapear(Direcao.LESTE, cavernas[22]);
        cavernas[7].mapear(Direcao.SUL, cavernas[21]);
        cavernas[7].mapear(Direcao.OESTE, cavernas[20]);

        cavernas[8].mapear(Direcao.NORTE, cavernas[3]);
        cavernas[8].mapear(Direcao.LESTE, cavernas[25]);
        cavernas[8].mapear(Direcao.SUL, cavernas[24]);
        cavernas[8].mapear(Direcao.OESTE, cavernas[23]);
        
        cavernas[9].mapear(Direcao.NORTE, cavernas[3]);
        cavernas[9].mapear(Direcao.LESTE, null);
        cavernas[9].mapear(Direcao.SUL, null);
        cavernas[9].mapear(Direcao.OESTE, null);
        
        cavernas[10].mapear(Direcao.NORTE, cavernas[3]);
        cavernas[10].mapear(Direcao.LESTE, null);
        cavernas[10].mapear(Direcao.SUL, null);
        cavernas[10].mapear(Direcao.OESTE, null);
        
        cavernas[11].mapear(Direcao.NORTE, cavernas[4]);
        cavernas[11].mapear(Direcao.LESTE, null);
        cavernas[11].mapear(Direcao.SUL, null);
        cavernas[11].mapear(Direcao.OESTE, null);
        
        cavernas[12].mapear(Direcao.NORTE, cavernas[4]);
        cavernas[12].mapear(Direcao.LESTE, null);
        cavernas[12].mapear(Direcao.SUL, null);
        cavernas[12].mapear(Direcao.OESTE, null);
        
        cavernas[13].mapear(Direcao.NORTE, cavernas[4]);
        cavernas[13].mapear(Direcao.LESTE, null);
        cavernas[13].mapear(Direcao.SUL, null);
        cavernas[13].mapear(Direcao.OESTE, null);
        
        cavernas[14].mapear(Direcao.NORTE, cavernas[5]);
        cavernas[14].mapear(Direcao.LESTE, null);
        cavernas[14].mapear(Direcao.SUL, null);
        cavernas[14].mapear(Direcao.OESTE, null);
        
        cavernas[15].mapear(Direcao.NORTE, cavernas[5]);
        cavernas[15].mapear(Direcao.LESTE, null);
        cavernas[15].mapear(Direcao.SUL, null);
        cavernas[15].mapear(Direcao.OESTE, null);
        
        cavernas[16].mapear(Direcao.NORTE, cavernas[5]);
        cavernas[16].mapear(Direcao.LESTE, null);
        cavernas[16].mapear(Direcao.SUL, null);
        cavernas[16].mapear(Direcao.OESTE, null);
        
        cavernas[17].mapear(Direcao.NORTE, cavernas[6]);
        cavernas[17].mapear(Direcao.LESTE, null);
        cavernas[17].mapear(Direcao.SUL, null);
        cavernas[17].mapear(Direcao.OESTE, null);
        
        cavernas[18].mapear(Direcao.NORTE, cavernas[6]);
        cavernas[18].mapear(Direcao.LESTE, null);
        cavernas[18].mapear(Direcao.SUL, null);
        cavernas[18].mapear(Direcao.OESTE, null);
        
        cavernas[19].mapear(Direcao.NORTE, cavernas[6]);
        cavernas[19].mapear(Direcao.LESTE, null);
        cavernas[19].mapear(Direcao.SUL, null);
        cavernas[19].mapear(Direcao.OESTE, null);
        
        cavernas[20].mapear(Direcao.NORTE, cavernas[7]);
        cavernas[20].mapear(Direcao.LESTE, null);
        cavernas[20].mapear(Direcao.SUL, null);
        cavernas[20].mapear(Direcao.OESTE, null);
        
        cavernas[21].mapear(Direcao.NORTE, cavernas[7]);
        cavernas[21].mapear(Direcao.LESTE, null);
        cavernas[21].mapear(Direcao.SUL, null);
        cavernas[21].mapear(Direcao.OESTE, null);
        
        cavernas[22].mapear(Direcao.NORTE, cavernas[7]);
        cavernas[22].mapear(Direcao.LESTE, null);
        cavernas[22].mapear(Direcao.SUL, null);
        cavernas[22].mapear(Direcao.OESTE, null);
        
        cavernas[23].mapear(Direcao.NORTE, cavernas[8]);
        cavernas[23].mapear(Direcao.LESTE, null);
        cavernas[23].mapear(Direcao.SUL, null);
        cavernas[23].mapear(Direcao.OESTE, null);
        
        cavernas[24].mapear(Direcao.NORTE, cavernas[8]);
        cavernas[24].mapear(Direcao.LESTE, null);
        cavernas[24].mapear(Direcao.SUL, null);
        cavernas[24].mapear(Direcao.OESTE, null);
        
        cavernas[25].mapear(Direcao.NORTE, cavernas[8]);
        cavernas[25].mapear(Direcao.LESTE, null);
        cavernas[25].mapear(Direcao.SUL, null);
        cavernas[25].mapear(Direcao.OESTE, null);
    }

}