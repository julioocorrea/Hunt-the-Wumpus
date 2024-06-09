package Services;

import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public String promptUserForChoice() {
        return scanner.nextLine();
    }

    public boolean isValidOption(String option) {
        return option.matches("\\d+");
    }

    public String nameOfPlayer(){
        return scanner.nextLine();
    }
}