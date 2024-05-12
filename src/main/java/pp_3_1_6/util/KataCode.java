package pp_3_1_6.util;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class KataCode {
    private static final Logger kataCodeLogger = Logger.getLogger(KataCode.class.getSimpleName());
    private int counter;
    private String[] finalCode = new String[3];

    public void setHalf(String half) {
        finalCode[counter] = half;
        counter++;
    }

    @Override
    public String toString() {
        if (!hasNull())
            return Arrays.stream(finalCode).reduce((a, e) -> a += e).get();
        kataCodeLogger.warning("Code is must be full enrich!");
        return null;
    }

    public boolean hasNull() {
        return Arrays.stream(finalCode).collect(Collectors.toList()).contains(null);
    }
}
