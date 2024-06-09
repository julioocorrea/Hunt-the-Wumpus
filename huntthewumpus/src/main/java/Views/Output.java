package Views;

import Models.Player;
import java.util.ArrayList;

public class Output {

    private static final String MENU_TITLE = "🧝🏻⚔️ BEM-VINDO AO HUNT THE WUMPUS ⚔️👹";
    private static final String MENU_START_GAME = "1 - Iniciar o jogo";
    private static final String MENU_EXIT = "2 - Sair";
    private static final String ASKPLAYERNAME = "Qual seu nome?";
    private static final String INVALID_OPTION = "Opção inválida. Tente novamente.";
    private static final String ONLY_NUMBERS_ALLOWED = "Somente números são permitidos!";
    private static final String YOU_AWAKE_IN_CAVERN = "🕸️🦇 Você acorda em uma caverna escura e úmida...";
    private static final String PATH_TRAVERSED = "Caminho percorrido: ";
    private static final String LIFE_STATUS = "❤️ Vida: ";
    private static final String ARROW_STATUS = "🏹 Flechas: ";
    private static final String BACK_OPTION = "5 - Voltar";
    private static final String NO_ARROWS_MESSAGE = "➴ Você está sem flechas! ➴";
    private static final String VICTORY_MESSAGE = "Você derrotou o terrível monstro Wumpus! 🧝🏻⚔️";
    private static final String DEFEAT_MESSAGE = "Que pena! Você morreu...";
    private static final String MISS_MESSAGE = "Errou o alvo!";
    private static final String BREEZE_NEARBY = "Você sente uma brisa suave...";
    private static final String FALLEN_IN_PIT = "🕳️ Você caiu em um poço e se machucou (-50 de vida) 🕳️";
    private static final String BAT_FLAPPING_NEARBY = "🦇 Ouviu um bater de asas...";
    private static final String CARRIED_BY_BAT = "🦇 Um morcego te encontrou e te levou para outra caverna! 🦇";
    private static final String WUMPUS_ODOR_NEARBY = "Você sente um cheiro horrível...";
    private static final String WUMPUS_ATTACK = "👹 Você entrou na caverna do Wumpus e ele te atacou! 👹";
    private static final String PICKED_UP_ARROW = "➴ Você pegou uma flecha ➴";
    private static final String OUT_OF_ARROWS = "Todas as flechas acabaram. Você não pode mais matar o monstro!";
    private static final String GAME_OVER = "Obrigado por jogar! Volte novamente a qualquer momento";
    private static final String DIVIDER = "☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵☵";

    public void printMainMenuOptions() {
    	System.out.println(DIVIDER);
        System.out.println(MENU_TITLE);
        System.out.println(MENU_START_GAME);
        System.out.println(MENU_EXIT);
    	System.out.println(DIVIDER);
    }

    public void printOnlyNumbersAllowed() {
    	System.out.println(DIVIDER);
        System.out.println(ONLY_NUMBERS_ALLOWED);
    	System.out.println(DIVIDER);
    }

    public void printInvalidOption() {
    	System.out.println(DIVIDER);
        System.out.println(INVALID_OPTION);
    	System.out.println(DIVIDER);
    }

    public void printStartMessage() {
    	System.out.println(DIVIDER);
        System.out.println(YOU_AWAKE_IN_CAVERN);
    	System.out.println(DIVIDER);
    }

    public void printMoveOptions(ArrayList<Integer> visitedCaves, Player player, String northOption,
                                 String eastOption, String southOption, String westOption, String shootOption) {
        StringBuilder sb = new StringBuilder();
        sb.append(PATH_TRAVERSED).append(visitedCaves).append("\n")
                .append(LIFE_STATUS).append(player.getVida()).append("\n")
                .append(ARROW_STATUS).append(player.getFlechas()).append("\n")
                .append(northOption).append("\n")
                .append(eastOption).append("\n")
                .append(southOption).append("\n")
                .append(westOption).append("\n")
                .append(shootOption);
    	System.out.println(DIVIDER);
        System.out.println(sb.toString());
    	System.out.println(DIVIDER);
    }

    public void printShootOptions(ArrayList<Integer> visitedCaves, Player player, String northOption,
                                  String eastOption, String southOption, String westOption) {
        StringBuilder sb = new StringBuilder();
        sb.append(PATH_TRAVERSED).append(visitedCaves).append("\n")
                .append(LIFE_STATUS).append(player.getVida()).append("\n")
                .append(ARROW_STATUS).append(player.getFlechas()).append("\n")
                .append(northOption).append("\n")
                .append(eastOption).append("\n")
                .append(southOption).append("\n")
                .append(westOption).append("\n")
                .append(BACK_OPTION);
    	System.out.println(DIVIDER);
        System.out.println(sb.toString());
    	System.out.println(DIVIDER);
    }

    public void printNoArrows() {
    	System.out.println(DIVIDER);
        System.out.println(NO_ARROWS_MESSAGE);
    	System.out.println(DIVIDER);
    }

    public void printVictory() {
    	System.out.println(DIVIDER);
        System.out.println(VICTORY_MESSAGE);
    	System.out.println(DIVIDER);
    }

    public void printDefeat(Player player) {
    	System.out.println(DIVIDER);
        System.out.println(LIFE_STATUS + player.getVida());
        System.out.println(DEFEAT_MESSAGE);
    	System.out.println(DIVIDER);
    }

    public void printMiss() {
    	System.out.println(DIVIDER);
        System.out.println(MISS_MESSAGE);
    	System.out.println(DIVIDER);
    }

    public void printNearPit() {
    	System.out.println(DIVIDER);
        System.out.println(BREEZE_NEARBY);
    	System.out.println(DIVIDER);
    }

    public void printFallenPit() {
    	System.out.println(DIVIDER);
        System.out.println(FALLEN_IN_PIT);
    	System.out.println(DIVIDER);
    }

    public void printNearBat() {
    	System.out.println(DIVIDER);
        System.out.println(BAT_FLAPPING_NEARBY);
    	System.out.println(DIVIDER);
    }

    public void printCarriedByBat() {
    	System.out.println(DIVIDER);
        System.out.println(CARRIED_BY_BAT);
    	System.out.println(DIVIDER);
    }

    public void printNearWumpus() {
    	System.out.println(DIVIDER);
        System.out.println(WUMPUS_ODOR_NEARBY);
    	System.out.println(DIVIDER);
    }

    public void printWumpusAttack() {
    	System.out.println(DIVIDER);
        System.out.println(WUMPUS_ATTACK);
    	System.out.println(DIVIDER);
    }

    public void printArrowPickup() {
    	System.out.println(DIVIDER);
        System.out.println(PICKED_UP_ARROW);
    	System.out.println(DIVIDER);
    }

    public void printOutOfArrows() {
    	System.out.println(DIVIDER);
        System.out.println(OUT_OF_ARROWS);
    	System.out.println(DIVIDER);
    }
    public void printGameOver(){
    	System.out.println(DIVIDER);
        System.out.println(GAME_OVER);
    	System.out.println(DIVIDER);
    }

    public void AskPlayerName(){
    	System.out.println(DIVIDER);
        System.out.println(ASKPLAYERNAME);
    	System.out.println(DIVIDER);
    }
}