import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class AllMatching implements MatchingStrategy {
    @Override
    public SortedSet<Integer> match(String[] searchWords,
                                    HashMap<String, SortedSet<Integer>> invertedIndex,
                                    SortedSet<Integer> fullSet) {
        SortedSet<Integer> resultSet = new TreeSet<>(fullSet);
        for (String word : searchWords) {
            if (invertedIndex.containsKey(word)) {
                resultSet.retainAll(invertedIndex.get(word));
            }
        }
        return resultSet;
    }
}
