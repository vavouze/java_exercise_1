import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("salut la commu");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        String input;
        do {
            System.out.println("Unknown command");
            input = scanner.nextLine();
        }while (!input.equals("quit"));
    }
}
