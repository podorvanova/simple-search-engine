import java.util.HashMap;
import java.util.SortedSet;

public interface MatchingStrategy {

    SortedSet<Integer> match(String[] searchWords,
                             HashMap<String, SortedSet<Integer>> invertedIndex,
                             SortedSet<Integer> fullSet);
}
