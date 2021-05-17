import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("salut la commu");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        do {
            if (input.equals("fibo"))
            {
                System.out.println("Entrez l'index");
                int n = parseInt(scanner.nextLine());
                if (n == 0 || n == 1)
                {
                    System.out.println("resultat :");
                    System.out.println(n);
                }
                else
                {
                    int a = 0;
                    int b = 1;
                    int c = 0;
                    for (int i=2;i<=n;i++)
                    {
                        a = b + c;
                        c = b;
                        b = a;
                    }
                    System.out.println("resultat :");
                    System.out.println(a);
                }
            }
            else
            {
                System.out.println("Unknown command");
            }
            input = scanner.nextLine();
        }while (!input.equals("quit"));
    }
}
