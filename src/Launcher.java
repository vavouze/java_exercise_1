import java.util.*;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("salut la commu");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        List<Command> commandList = new ArrayList<>();
        commandList.add(new Quit());
        commandList.add(new Fibo());
        commandList.add(new Freq());
        boolean stop = false;
        boolean unknown = false;
        do {
            for (Command command : commandList)
            {
                if (input.equals(command.name()))
                {
                    stop = command.run(scanner);
                    unknown = false;
                    break;
                }
                else
                {
                    unknown = true;
                }
            }
            if (unknown)
            {
                System.out.println("Unknown command");
            }
            if (!stop)
            {
                input = scanner.nextLine();
            }
        }while (!stop);
    }
}
