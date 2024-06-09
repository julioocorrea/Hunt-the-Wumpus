package Services;

import Views.Menu;
import Views.Output;

public class Main {

    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();

        Menu menu = new Menu(input, output);
        menu.showMenu();

        output.printGameOver();
    }
}