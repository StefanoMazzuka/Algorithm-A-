package Functions;
import java.util.Comparator;

public class BoxComparator implements Comparator<Box> {
    public int compare(Box x, Box y)
    {
        if (x.getValue() < y.getValue()) return -1;
        if (x.getValue() > y.getValue()) return 1;
        return 0;
    }
}