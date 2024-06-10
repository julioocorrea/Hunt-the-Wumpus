package Services;

import Models.*;
import Views.Output;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {

	// Define o número total de cavernas no jogo.
	private final int NUM_CAVERNAS = 26;

	CavernaService CavService = new CavernaService();
	
	// Array que armazena todas as instâncias de Caverna no jogo.
	Caverna[] cavernas = new Caverna[NUM_CAVERNAS];

	// Lista que armazena os índices das cavernas que foram visitadas pelo jogador.
	ArrayList<Integer> cavernasVisitadas = new ArrayList<>();

	// Índice da caverna onde o jogador está atualmente.
	int cavernaAtual = 0;
	
	// Total de flechas do jogo.
	int TotalDeFlechas = 3;

	// Instância do inimigo Morcego (Bat) no jogo.
	Morcego morcego = new Morcego("Bat");

	// Instância do inimigo Wumpus no jogo.
	Wumpus wumpus = new Wumpus("Wumpus");

	// Instância do inimigo Poço1 (Pit1) no jogo.
	Poco poco1 = new Poco("Pit");
	
	// Instância do inimigo Poço2 (Pit2) no jogo.
	Poco poco2 = new Poco("Pit");

	// Instância do mapa que mapeia as conexões entre as cavernas.
	Mapa mapa = new Mapa();

	// Instância do jogador.
	Player player = new Player("");

	// Variável que controla se o jogo chegou ao fim.
	boolean fimDeJogo = false;
	
	String ResultadoJogo = "";

	/**
	 * Inicia o jogo, realizando as seguintes etapas:
	 * - Solicita o nome do jogador.
	 * - Cria instâncias de cavernas.
	 * - Mapeia as conexões entre as cavernas.
	 * - Posiciona aleatoriamente os inimigos (morcego, poço e wumpus) em cavernas diferentes.
	 * - Coloca aleatoriamente flechas nas cavernas.
	 * - Posiciona o jogador em uma caverna inicial.
	 * - Inicia a aventura do jogador.
	 */
	public void iniciarJogo() {
		this.fimDeJogo = false;
	    Output output = new Output(); // Instância da classe de saída para exibir mensagens
	    output.AskPlayerName(); // Solicita o nome do jogador
	    Input input = new Input(); // Instância da classe de entrada para receber dados do jogador
	    String nomeDoJogador = input.nameOfPlayer(); // Obtém o nome do jogador

	    player.setNome(nomeDoJogador); // Define o nome do jogador

	    output.printStartMessage(); // Imprime a mensagem de início do jogo

	    // Loop para criar instâncias de cavernas
	    for (int i = 0; i < 26; i++) {
	        cavernas[i] = new Caverna();
	    }

	    MapearCavernas(); // Mapeia as conexões entre as cavernas

	    Random r = new Random(); // Instância um objeto Random para gerar números aleatórios

	    // Posiciona aleatoriamente o morcego em uma caverna
	    int cavernaMorcego = 10;
	    cavernas[cavernaMorcego].inimigo = morcego;

	    // Posiciona aleatoriamente o poço em uma caverna diferente da caverna do morcego
	    int cavernaPoco1 = 0;
	    while (cavernaPoco1 != cavernaMorcego) {
	        cavernaPoco1 = r.nextInt(13);
	    }
	    cavernas[cavernaPoco1].inimigo = poco1;
	    
	    // Posiciona aleatoriamente o poço em uma caverna diferente da caverna do morcego
	    int cavernaPoco2 = 0;
	    while (cavernaPoco2 != cavernaMorcego && cavernaPoco1 != cavernaPoco2) {
	        cavernaPoco2 = r.nextInt(13);
	    }
	    cavernas[cavernaPoco2].inimigo = poco2;

	    // Posiciona aleatoriamente o wumpus em uma caverna diferente das cavernas do morcego e do poço
	    int cavernaWumpus = 0;
	    while (cavernaWumpus != cavernaMorcego && cavernaWumpus != cavernaPoco1 && cavernaWumpus != cavernaPoco2) {
	        cavernaWumpus = r.nextInt(21) + 5;
	    }
	    cavernas[cavernaWumpus].inimigo = wumpus;

	    // Coloca aleatoriamente flechas nas cavernas
	    cavernas[r.nextInt(25)].setFlecha(new Flecha("flecha 1"));
	    cavernas[r.nextInt(25)].setFlecha(new Flecha("flecha 2"));
	    cavernas[r.nextInt(25)].setFlecha(new Flecha("flecha 3"));

	    // Posiciona o jogador em uma caverna inicial
	    cavernas[cavernaAtual].setPlayer(new Player(nomeDoJogador));

	    IniciarAventura(output, input); // Inicia a aventura do jogador
	    
	    if (ResultadoJogo.equals("Vitoria")) {
	    	output.printVictory(); // Imprime uma mensagem de derrota
	    }
	    else {
  	        output.printDefeat(player); // Imprime uma mensagem de derrota
	    }
	}
    
	// Método responsável por iniciar a aventura do jogador, permitindo que ele faça movimentos e tome decisões enquanto o jogo não acabar.
    public void IniciarAventura(Output output, Input input){
        // Loop principal que executa enquanto o jogo não acabar
        while(!fimDeJogo) {
        	// Declaração das opções de movimento disponíveis para o jogador
            String opcaoNorte;
            String opcaoLeste;
            String opcaoSul;
            String opcaoOeste;
            String opcaoFlecha;
            
            // Verifica se há caminho para o norte e se a lista de cavernas visitadas não está vazia
            if(cavernas[cavernaAtual].getNorte() != null && cavernasVisitadas.size() != 0) {
                opcaoNorte = "1 - Ir para o norte";
            } else {
                opcaoNorte = "Não há caminho para o norte!";
            }
            // Verifica se há caminho para o leste e se a lista de cavernas visitadas não está vazia
            if(cavernas[cavernaAtual].getLeste() != null && cavernasVisitadas.size() != 0) {
                opcaoLeste = "2 - Ir para o leste ";
            } else {
                opcaoLeste = "Não há caminho para o leste!";
            }
            // Verifica se há caminho para o sul
            if(cavernas[cavernaAtual].getSul() != null) {
                opcaoSul = "3 - Ir para o sul ";
            } else {
                opcaoSul = "Não há caminho para o sul!";
            }
            // Verifica se há caminho para o oeste e se a lista de cavernas visitadas não está vazia
            if(cavernas[cavernaAtual].getOeste() != null && cavernasVisitadas.size() != 0) {
                opcaoOeste = "4 - Ir para o oeste ";
            } else {
                opcaoOeste = "Não há caminho para o oeste!";
            }
            // Verifica se o Wumpus está próximo para possibilitar o uso de flechas
            if(CavService.verificarInimigo("Wumpus", cavernas, cavernaAtual)) {
                opcaoFlecha = "5 - Escolher caverna para atirar uma flecha";
            } else {
                opcaoFlecha = "Você ainda não corre perigo";
            }
            
            // Exibe as opções de movimento e interação para o jogador
            output.printMoveOptions(cavernasVisitadas, player, opcaoNorte, opcaoLeste, opcaoSul, opcaoOeste, opcaoFlecha);

            // Solicita uma opção de movimento ao jogador
            String opcao = input.promptUserForChoice();
            
            // Verifica se a opção inserida é um número válido
            boolean eNumero = input.isValidOption(opcao);
            
            if (!eNumero) {
                output.printOnlyNumbersAllowed();// Exibe mensagem caso a opção inserida não seja um número
            } else {
            	// Converte a opção para inteiro
                int numero = Integer.parseInt(opcao);
                
                MoverJogador(input, output, numero);
            }
        }
    }
    
  //Verifica a presença de elementos importantes nas cavernas ao redor do jogador atual.
  	public void verificarCavernas() {
  	    // Se o jogo não terminou, verifica a presença do Wumpus na caverna atual ou adjacente.
  	    if(!fimDeJogo) {
  	        verificarWumpus();
  	    }
  	    // Se o jogo ainda não terminou após a verificação do Wumpus, verifica a presença de um poço.
  	    if(!fimDeJogo) {
  	        verificarPoco();
  	    }
  	    // Se o jogo ainda não terminou após a verificação do poço, verifica a presença de morcego.
  	    if(!fimDeJogo) {
  	        verificarMorcego();
  	    }
  	    // Se o jogo ainda não terminou após a verificação do morcego, verifica a presença de flechas.
  	    if(!fimDeJogo) {
  	        verificarFlechas();
  	    }
  	}

  	// Verifica se o Wumpus está presente em uma caverna adjacente à caverna atual.
  	public void verificarWumpus() {
  	    Output output = new Output(); // Instância da classe de saída para exibir mensagens
  	    // Verifica se o Wumpus está presente em uma caverna adjacente
  	    if (CavService.verificarInimigo("Wumpus", cavernas, cavernaAtual)) {
  	        output.printNearWumpus(); // Imprime uma mensagem informando a proximidade do Wumpus
  	    }
  	    // Verifica se o jogador entrou na caverna com o Wumpus
  	    if (cavernas[cavernaAtual].getInimigo() != null) {
  	        if (cavernas[cavernaAtual].getInimigo().getNome().equals("Wumpus")) {
  	            output.printWumpusAttack(); // Imprime uma mensagem de ataque do Wumpus
  	            finalizarJogo("Derrota!"); // Encerra o jogo
  	        }
  	    }
  	}

  	//Verifica se há um poço em uma caverna adjacente à caverna atual.
  	public void verificarPoco() {
  	    Output output = new Output(); // Instância da classe de saída para exibir mensagens
  	    // Verifica se há um poço em uma caverna adjacente
  	    if (CavService.verificarInimigo("Pit", cavernas, cavernaAtual)) {
  	        output.printNearPit(); // Imprime uma mensagem informando a proximidade do poço
  	    }

  	    // Verifica se o jogador entrou em uma caverna com um poço
  	    if (cavernas[cavernaAtual].getInimigo() != null) {
  	        if (cavernas[cavernaAtual].getInimigo().getNome().equals("Pit")) {
  	            player.setVida(player.getVida() - 50); // Reduz 50 pontos de vida do jogador
  	            output.printFallenPit(); // Imprime uma mensagem informando que o jogador caiu no poço
  	        }
  	    }
  	    boolean vivo = verificarVida(); // Verifica se o jogador ainda está vivo
  	    if (!vivo) {
  	        finalizarJogo("Derrota"); // Encerra o jogo
  	    }
  	}

  	//Verifica se há uma flecha na caverna atual. Se houver, adiciona uma flecha ao inventário do jogador
  	public void verificarFlechas() {
  	    Output output = new Output(); // Instância da classe de saída para exibir mensagens
  	    // Verifica se há uma flecha na caverna atual
  	    if (cavernas[cavernaAtual].getFlecha() != null) {
  	        player.setFlechas(player.getFlechas() + 1); // Adiciona uma flecha ao inventário do jogador
  	        cavernas[cavernaAtual].setFlecha(null); // Remove a flecha da caverna
  	        TotalDeFlechas--; // Remove uma flecha do Jogo
  	        output.printArrowPickup(); // Imprime uma mensagem informando que uma flecha foi coletada
  	    }
  	}

  	//Verifica se há um morcego na caverna atual ou em uma caverna adjacente
  	public void verificarMorcego() {
  	    Output output = new Output(); // Instância da classe de saída para exibir mensagens
  	    // Verifica se há um morcego na caverna atual ou adjacente
  	    if (CavService.verificarInimigo("Bat", cavernas, cavernaAtual)) {
  	        output.printNearBat(); // Imprime uma mensagem informando a proximidade do morcego
  	    }
  	    // Verifica se o jogador entrou na caverna com o morcego
  	    if (cavernas[cavernaAtual].getInimigo() != null) {
  	        if (cavernas[cavernaAtual].getInimigo().getNome().equals("Bat")) {
  	            Random r = new Random(); // Instância um objeto Random para gerar números aleatórios
  	            int cavernaAleatoria = r.nextInt(25); // Gera um índice aleatório para uma caverna
  	            // Move o jogador para a caverna aleatória e atualiza a caverna atual
  	            cavernas[cavernaAleatoria].setPlayer(cavernas[cavernaAtual].getPlayer());
  	            cavernas[cavernaAtual].setPlayer(null);
  	            cavernaAtual = CavService.compararCaverna(cavernas[cavernaAleatoria], cavernas); // Atualiza a caverna atual
  	            cavernasVisitadas.add(cavernaAleatoria); // Adiciona a caverna à lista de cavernas visitadas
  	            output.printNearBat(); // Imprime uma mensagem informando a proximidade do morcego
  	            // Após o movimento do jogador, verifica novamente a presença de Wumpus, poço e morcego
  	            verificarWumpus();
  	            verificarPoco();
  	            verificarMorcego();
  	        }
  	    }
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

    public void finalizarJogo(String resultado) {
        fimDeJogo = true;
        ResultadoJogo = resultado;
    }
    
    public void MoverJogador(Input input, Output output, int numero) {
        if (numero == 1) {
        	MoverPara(output, cavernas[cavernaAtual].getNorte());
        } 
        else if (numero == 2) {
        	MoverPara(output, cavernas[cavernaAtual].getLeste());
        } 
        else if (numero == 3) {
        	MoverPara(output, cavernas[cavernaAtual].getSul());
        } 
        else if (numero == 4) {
        	MoverPara(output, cavernas[cavernaAtual].getOeste());
        } 
        else if (numero == 5) {
        	AtirarFlecha(input, output);
        } 
        else {
            output.printInvalidOption();
        }
    }

    public void MoverPara(Output output, Caverna novaCaverna) {
        if (novaCaverna != null) {
        	novaCaverna.setPlayer(cavernas[cavernaAtual].getPlayer());
            cavernas[cavernaAtual].setPlayer(null);
            cavernaAtual = CavService.compararCaverna(novaCaverna, cavernas);
            cavernasVisitadas.add(cavernaAtual);
            verificarCavernas();
        } else {
            output.printInvalidOption();
        }
    }
    
    public void AtirarFlecha(Input input, Output output) {
    	if (player.getFlechas() > 0) {
            output.printMenuShootArrow();
            int escolha = Integer.parseInt(input.promptUserForChoice());
            
            if (escolha == 1) {
            	if (CavService.VerificarInimigoNaCaverna(cavernas[cavernaAtual].getLeste(), "Wumpus")) {
            		finalizarJogo("Vitoria");
            	}
            	else {
            		player.setFlechas(player.getFlechas() - 1);
            		output.printMiss();
            	}
            }
            else if (escolha == 2) {
            	if (CavService.VerificarInimigoNaCaverna(cavernas[cavernaAtual].getSul(), "Wumpus")) {
            		finalizarJogo("Vitoria");
            	}
            	else {
            		player.setFlechas(player.getFlechas() - 1);
            		output.printMiss();
            	}
            }
            else if (escolha == 3) {
            	if (CavService.VerificarInimigoNaCaverna(cavernas[cavernaAtual].getOeste(), "Wumpus")) {
            		finalizarJogo("Vitoria");
            	}
            	else {
            		player.setFlechas(player.getFlechas() - 1);
            		output.printMiss();
            	}
            }
            else {
            	output.printInvalidOption();
            }
            
        } else {
        	if (TotalDeFlechas == 0) {
        		output.printOutOfArrows();
        		finalizarJogo("Derrota");
        	}
        	else {
                output.printNoArrows();
        	}
        }
    }
    
	//Verifica se o jogador ainda está vivo.
	public boolean verificarVida() {
	    // Se a vida do jogador for menor ou igual a zero, retorna false (jogador morto)
	    if (player.getVida() <= 0) {
	        return false;
	    } else {
	        // Caso contrário, retorna true (jogador vivo)
	        return true;
	    }
	}

}