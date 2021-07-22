import java.util.HashMap;
import java.util.SortedSet;

public class MatchTypeDefiner {

    private MatchingStrategy strategy;

    public void setMatchType(String matchType) {
        switch (matchType) {
            case "ALL":
                strategy = new AllMatching();
                break;
            case "ANY":
                strategy = new AnyMatching();
                break;
            case "NONE":
                strategy = new NoneMatching();
                break;
        }
    }

    public SortedSet<Integer> match(String[] search,
                                    HashMap<String, SortedSet<Integer>> invertedIndex,
                                    SortedSet<Integer> fullSet) {
        return this.strategy.match(search, invertedIndex, fullSet);
    }
}
