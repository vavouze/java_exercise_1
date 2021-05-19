import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command
{
    @Override
    public String name() {
        return "Predict";
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
            Map<String, String> wordRef = new HashMap<>();
            for (String word : words)
            {
                if (!wordRef.containsKey(word))
                {
                    String next = getwordOccur(words,word);
                    if (next != null)
                    {
                        wordRef.put(word,next);
                    }
                }
            }
            printResult(wordRef,scanner);
        }catch (Exception e){
            System.out.println("Unreadable file: " + e.getClass().getName() +" "+ e.getMessage());
        }
        return false;
    }
    private String getwordOccur(String[] words,String w)
    {
        boolean found = false;
        Map<String, Integer> occur = new HashMap<>();
        for (String currWord : words)
        {
            if (found)
            {
                if (occur.containsKey(currWord))
                {
                    occur.put(currWord,occur.get(currWord) + 1);
                }
                else
                {
                    occur.put(currWord,1);
                }
                found = false;
            }
            if (currWord.equals(w))
            {
                found = true;
            }
        }
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : occur.entrySet())
        {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
            {
                maxEntry = entry;
            }
        }
        return maxEntry != null ? maxEntry.getKey() : null;
    }
    private void printResult(Map<String,String> wordRef,Scanner scanner)
    {
        System.out.println("Entrez un mot");
        String start = scanner.nextLine();
        if (!wordRef.containsKey(start))
        {
            System.out.println("Ce mot ne fait aps parti du text");
        }
        else
        {
            ArrayList<String> result = new ArrayList<>();
            for (int i = 0; i < 20; i++)
            {
                if (wordRef.get(start) != null)
                {
                    result.add(wordRef.get(start));
                    start = wordRef.get(start);
                    if (start == null)
                        break;
                }
            }
            System.out.println(String.join(" ",result));
        }
    }
}
