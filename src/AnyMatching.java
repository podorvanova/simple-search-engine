import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class AnyMatching implements MatchingStrategy {
    @Override
    public SortedSet<Integer> match(String[] searchWords,
                                    HashMap<String, SortedSet<Integer>> invertedIndex,
                                    SortedSet<Integer> fullSet) {
        SortedSet<Integer> resultSet = new TreeSet<>();
        for (String word : searchWords) {
            if (invertedIndex.containsKey(word)) {
                resultSet.addAll(invertedIndex.get(word));
            }
        }
        return resultSet;
    }
}
