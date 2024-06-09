package Views;

import Services.Input;
import Services.Jogo;


public class Menu {

    private static final int OPTION_START_GAME = 1;
    private static final int OPTION_EXIT = 2;

    private Input input;
    private Output output;

    public Menu(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    public void showMenu() {
        while (true) {
            output.printMainMenuOptions();
            String choice = input.promptUserForChoice();

            if (!input.isValidOption(choice)) {
                output.printOnlyNumbersAllowed();
            } else {
                int option = Integer.parseInt(choice);
                handleMenuOption(option);
                if (option == OPTION_EXIT) {
                    break;
                }
            }
        }
    }

    private void handleMenuOption(int option) {
        switch (option) {
            case OPTION_START_GAME:
                startGame();
                break;
            case OPTION_EXIT:
                break;
            default:
                output.printInvalidOption();
                break;
        }
    }

    private void startGame() {
        Jogo novoJogo = new Jogo();
        novoJogo.iniciarJogo();
    }
}