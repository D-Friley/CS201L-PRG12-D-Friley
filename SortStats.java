import java.text.NumberFormat;
import java.util.Locale;

public class SortStats {

    public String sortName;
    public String fileName;

    public long comparisons;
    public long swaps;
    public long loops;
    public long timeNano;


    public SortStats(String sortName, String fileName) {

        this.sortName = sortName;
        this.fileName = fileName;

        comparisons = 0;
        swaps = 0;
        loops = 0;
        timeNano = 0;
    }

 
    public String toString() {

        NumberFormat formatter =
            NumberFormat.getNumberInstance(Locale.US);

        return String.format(
            "%-20s %-25s %15s %20s %15s %20s",
            sortName,
            fileName,
            formatter.format(swaps),
            formatter.format(comparisons),
            formatter.format(loops),
            formatter.format(timeNano)
        );
    }
}