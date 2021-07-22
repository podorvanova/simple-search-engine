import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        if ("--data".equals(args[0])) {
            String pathName = args[1];
            Path filePath = new File(pathName).toPath();
            Charset charset = Charset.defaultCharset();
            String[] fileLines = Files.readAllLines(filePath, charset).toArray(new String[]{});

            SortedSet<Integer> fullSet = new TreeSet<>();
            for (int i = 0; i < fileLines.length; i++) {
                fullSet.add(i);
            }

            HashMap<String, SortedSet<Integer>> invertedIndex = createInvertedIndex(fileLines);

            Scanner in = new Scanner(System.in);

            menuInteraction(in, invertedIndex, fullSet, fileLines);
        } else {
            System.err.println("File path is not correct.");
        }
    }

    private static HashMap<String, SortedSet<Integer>> createInvertedIndex(String[] fileLines) {
        HashMap<String, SortedSet<Integer>> invertedIndex = new HashMap<>();
        for (int i = 0; i < fileLines.length; i++) {
            String[] keys = Arrays.stream(fileLines[i].split("( )+"))
                    .map(String::toLowerCase).toArray(String[]::new);
            for (int j = 0; j < keys.length; j++) {
                if (!invertedIndex.containsKey(keys[j])) {
                    SortedSet<Integer> sortedSet = new TreeSet<>();
                    sortedSet.add(i);
                    invertedIndex.put(keys[j], sortedSet);
                } else {
                    SortedSet<Integer> sortedSet = invertedIndex.get(keys[j]);
                    sortedSet.add(i);
                    invertedIndex.replace(keys[j], sortedSet);
                }
            }
        }
        return invertedIndex;
    }

    private static void menuInteraction(Scanner in,
                                        HashMap<String, SortedSet<Integer>> invertedIndex,
                                        SortedSet<Integer> fullSet,
                                        String[] fileLines) {
        while (true) {
            System.out.println();
            System.out.println("=== Menu ===");
            System.out.println("1. Find an address");
            System.out.println("2. Print all addresses");
            System.out.println("0. Exit");

            int optionNumber = Integer.parseInt(in.nextLine());

            if (optionNumber == 1) {
                System.out.println("Select a matching strategy: ALL, ANY, NONE");
                String matchType = in.nextLine();
                MatchTypeDefiner type = new MatchTypeDefiner();
                type.setMatchType(matchType);

                System.out.println("Enter a street, suburb or postcode to search all suitable addresses.");
                String search = in.nextLine().toLowerCase();
                SortedSet<Integer> lineNumbers = type.match(search.split("( )+"), invertedIndex, fullSet);

                if (!lineNumbers.isEmpty()) {
                    System.out.println(lineNumbers.size() + " addresses found:");
                    for (int line : lineNumbers) {
                        System.out.println(fileLines[line]);
                    }
                } else {
                    System.out.println("No matching address found.");
                }
            } else if (optionNumber == 2) {
                System.out.println();
                System.out.println("=== List of addresses ===");
                for (String fileLine : fileLines) {
                    System.out.println(fileLine);
                }
            } else if (optionNumber == 0) {
                System.out.println();
                System.out.println("Bye!");
                in.close();
                break;
            } else {
                System.out.println("Incorrect option! Try again.");
            }
        }
    }
}
