import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Freq implements Command
{
    @Override
    public String name() {
        return "Freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("entrez le chemin d'un fichier");
        String pathStr = scanner.nextLine();
        try {
            Path path = Paths.get(pathStr);
            String content = java.nio.file.Files.readString(path);
            content = content.replaceAll("[^\\w\\s]"," ");
            content = content.replaceAll("\\s{2,}"," ");
            String[] words = content.split("\\s+");
            Stream<String> stream = Arrays.stream(words);
            Map<String, Long> wordCount = stream.filter(s -> !s.isBlank()).map(String::toLowerCase).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            Comparator<Map.Entry<String, Long>> reverse = Comparator.<Map.Entry<String, Long>,Long>comparing(Map.Entry::getValue).reversed();
            String mostFrequent = wordCount.entrySet().stream().sorted(reverse).limit(3).map(Map.Entry::getKey).collect(Collectors.joining(" "));
            System.out.println(mostFrequent);

        }catch (Exception e){
            System.out.println("Unreadable file: " + e.getClass().getName() +" "+ e.getMessage());

        }
        return false;
    }
}
