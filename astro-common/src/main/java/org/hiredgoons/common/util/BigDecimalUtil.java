package org.hiredgoons.common.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    // wrapper methods just to make BigDecimal comparisons look a little big cleaner

    public static boolean greaterThan(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) > 0;
    }

    public static boolean greaterThanEqual(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) >= 0;
    }

    public static boolean lessThan(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) < 0;
    }

    public static boolean lessThanEqual(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) <= 0;
    }
}
