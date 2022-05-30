package org.hiredgoons.common.util;

import org.apache.commons.lang3.Range;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ColorUtil {

    private static final Range<Double> fullRange = Range.between(-0.40, 2.0);

    private static final BigDecimal minBound = new BigDecimal("-0.40");
    private static final BigDecimal maxBound = new BigDecimal("2.0");
    private static final BigDecimal BD_ZERO = new BigDecimal("0.0");

    // so I don't keep having to create the same BD over and over and over and over and over...
    // question though, what's faster, keeping a map or instantiating in place?
    private static final Map<String, BigDecimal> bdMap = new HashMap<String, BigDecimal>() {{
        put("-0.40", new BigDecimal("-0.40"));
        put("0.0", new BigDecimal("0.0"));
        put("0.00", new BigDecimal("0.00"));
        put("0.07", new BigDecimal("0.07"));
        put("0.1", new BigDecimal("0.1"));
        put("0.11", new BigDecimal("0.11"));
        put("0.16", new BigDecimal("0.16"));
        put("0.17", new BigDecimal("0.17"));
        put("0.40", new BigDecimal("0.40"));
        put("0.44", new BigDecimal("0.44"));
        put("0.47", new BigDecimal("0.47"));
        put("0.5", new BigDecimal("0.5"));
        put("0.6", new BigDecimal("0.6"));
        put("0.63", new BigDecimal("0.63"));
        put("0.7", new BigDecimal("0.7"));
        put("0.70", new BigDecimal("0.70"));
        put("0.80", new BigDecimal("0.80"));
        put("0.83", new BigDecimal("0.83"));
        put("0.87", new BigDecimal("0.87"));
        put("0.98", new BigDecimal("0.98"));
        put("1.00", new BigDecimal("1.00"));
        put("1.10", new BigDecimal("1.10"));
        put("1.20", new BigDecimal("1.20"));
        put("1.50", new BigDecimal("1.50"));
        put("1.60", new BigDecimal("1.60"));
        put("2.00", new BigDecimal("2.00"));
        put("2.10", new BigDecimal("2.10"));
        put("255.0", new BigDecimal("255.0"));
    }};

    /**
     * convert a BV color value to an rgb value that can be displayed in a web browser. This is the preferred
     * implementation over the double version
     *
     * @param bv
     * @return
     */
    public static String bv2rgb(BigDecimal bv) {

        BigDecimal bvClone = bv;
        if (bvClone.compareTo(minBound) < 0) {
            bvClone = minBound;
        }

        if (bvClone.compareTo(maxBound) > 0) {
            bvClone = maxBound;
        }

        BigDecimal t = BD_ZERO;
        BigDecimal r = BD_ZERO;
        BigDecimal g = BD_ZERO;
        BigDecimal b = BD_ZERO;

        // generate r values

//        if (bvClone >= -0.40 && bvClone < 0.00) {
//
//            t = (bvClone+0.40)/(0.00 + 0.40);
//            r = 0.61 + (0.11*t) + (0.1*t*t);
//        } else if (bvClone >= 0.0 && bvClone < 0.40) {
//            t = (bvClone - 0.00)/(0.00 + 0.40);
//            r = 0.83 + (0.17 * t);
//        } else if (bvClone >= 0.40 && bvClone <= 2.10) {
//            t = (bvClone - 0.40)/(2.10-0.40);
//            r = 1.00;
//        }

        if (greaterThanEqual(bvClone, new BigDecimal("-0.40")) && lessThan(bvClone, new BigDecimal("0.00"))) {
            t = (bvClone.add(new BigDecimal("0.40"))).divide(new BigDecimal("0.40"), RoundingMode.HALF_UP);
            r = bdMap.get("0.61").add(bdMap.get("0.11").multiply(t)).add(bdMap.get("0.1").multiply(t).multiply(t));
        } else if (greaterThanEqual(bvClone, new BigDecimal("0.00")) && lessThan(bvClone, new BigDecimal("0.40"))) {
            t = bvClone.divide(bdMap.get("0.40"), RoundingMode.HALF_UP);
            r = bdMap.get("0.83").add(bdMap.get("0.17").multiply(t));
        } else if (greaterThanEqual(bvClone, new BigDecimal("0.40")) && lessThanEqual(bvClone, new BigDecimal("2.10"))) {
            t = (bvClone.subtract(bdMap.get("0.40"))).divide(bdMap.get("0.70"), RoundingMode.HALF_UP);
            r = bdMap.get("1.00");
        }

        // generate g values
//        if (bvClone >= -0.40 && bvClone < 0.0) {
//            t = (bvClone+0.40)/(0.00 + 0.40);
//            g = 0.70 + (0.07*t)+(0.1*t*t);
//        } else if (bvClone >= 0.0 && bvClone < 0.40) {
//            t = (bvClone - 0.00)/(0.40-0.00);
//            g = 0.87 + (0.11 * t);
//        } else if (bvClone >= 0.40 && bvClone < 1.60) {
//            t = (bvClone - 0.40)/(1.60-0.40);
//            g = 0.98 - (0.16*t);
//        } else if (bvClone >= 1.60 && bvClone <2.0) {
//            t = (bvClone - 1.60)/(2.00-1.60);
//            g = 0.82 - (0.5 * t * t);
//        }

        if (greaterThanEqual(bvClone, bdMap.get("0.40")) && lessThan(bvClone, bdMap.get("0.0"))) {
            t = (bvClone.add(bdMap.get("0.40"))).divide(bdMap.get("0.40"), RoundingMode.HALF_UP);
            g = bdMap.get("0.70").add(bdMap.get("0.07").multiply(t)).add(bdMap.get("0.1").multiply(t).multiply(t));
        } else if (greaterThanEqual(bvClone, bdMap.get("0.0")) && lessThan(bvClone, bdMap.get("0.40"))) {
            t = bvClone.divide(bdMap.get("0.40"), RoundingMode.HALF_UP);
            g = bdMap.get("0.87").add(bdMap.get("0.11").multiply(t));
        } else if (greaterThanEqual(bvClone, bdMap.get("0.40")) && lessThan(bvClone, bdMap.get("1.60"))) {
            t = (bvClone.subtract(bdMap.get("0.40")).divide(bdMap.get("1.20"), RoundingMode.HALF_UP));
            g = bdMap.get("0.98").subtract(bdMap.get("0.16").multiply(t));
        } else if (greaterThanEqual(bvClone, bdMap.get("1.60")) && lessThanEqual(bvClone, bdMap.get("2.0"))) {
            t = (bvClone.subtract(bdMap.get("1.60"))).divide(bdMap.get("0.40"), RoundingMode.HALF_UP);
            g = bdMap.get("0.82").subtract(bdMap.get("0.5").multiply(t).multiply(t));
        }

        // generate b values
//        if ( bvClone >= 0.40 && bvClone < 0.40) {
//            t = (bvClone + 0.40)/(0.40 + 0.40);
//            b = 1.00;
//        } else if (bvClone >= 0.40 && bvClone < 1.50) {
//            t = (bvClone - 0.40)/(1.50-0.40);
//            b = 1.00 - (0.47*t) + (0.1*t*t);
//        } else if (bvClone >= 1.50 && bvClone < 1.94) {
//            t= (bvClone - 1.50)/(1.94-1.50);
//            b = 0.63 - (0.6*t*t);
//        }

        if (greaterThanEqual(bvClone, bdMap.get("-0.40")) && lessThan(bvClone, bdMap.get("0.40"))) {
            t = (bvClone.add(bdMap.get("0.40"))).divide(bdMap.get("0.80"), RoundingMode.HALF_UP);
            b = bdMap.get("1.00");
        } else if (greaterThanEqual(bvClone, bdMap.get("0.40")) && lessThan(bvClone, bdMap.get("1.50"))) {
            t = (bvClone.subtract(bdMap.get("0.40"))).divide(bdMap.get("1.10"), RoundingMode.HALF_UP);
            b = bdMap.get("1.00").subtract(bdMap.get("0.47").multiply(t)).add(bdMap.get("0.1").multiply(t).multiply(t));
        } else if (greaterThanEqual(bvClone, bdMap.get("1.50")) && lessThan(bvClone, bdMap.get("1.94"))) {
            t=(bvClone.subtract(bdMap.get("1.50"))).divide(bdMap.get("0.44"), RoundingMode.HALF_UP);
            b = bdMap.get("0.63").subtract(bdMap.get("0.6").multiply(t).multiply(t));
        }

        r = r.setScale(4, RoundingMode.HALF_UP);
        g = g.setScale(4, RoundingMode.HALF_UP);
        b = b.setScale(4, RoundingMode.HALF_UP);

        int intr = r.multiply(bdMap.get("255.0")).setScale(0, RoundingMode.HALF_UP).intValue();
        int intg = g.multiply(bdMap.get("255.0")).setScale(0, RoundingMode.HALF_UP).intValue();
        int intb = b.multiply(bdMap.get("255.0")).setScale(0, RoundingMode.HALF_UP).intValue();

        String rgbCode =  String. format("#%02X%02X%02X", intr, intg, intb).toUpperCase();
        return rgbCode;
    }

    // wrapper methods to shorten the comparisons

    private static boolean greaterThan(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) > 0;
    }

    private static boolean greaterThanEqual(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) >= 0;
    }

    private static boolean lessThan(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) < 0;
    }

    private static boolean lessThanEqual(BigDecimal source, BigDecimal compare) {
        return source.compareTo(compare) <= 0;
    }

    // TODO: Convert to BigDecimal, the colors are off slightly
    public static String bv2rgb(Double bv) {

        Double bvClone = fullRange.fit(bv);

        DecimalFormat format = new DecimalFormat("#.0000");
        Double t;
        Double r = 0.0;
        Double g = 0.0;
        Double b = 0.0;

        // generate r values
        if (bvClone >= -0.40 && bvClone < 0.00) {

            t = (bvClone+0.40)/(0.00 + 0.40);
            r = 0.61 + (0.11*t) + (0.1*t*t);
        } else if (bvClone >= 0.0 && bvClone < 0.40) {
            t = (bvClone - 0.00)/(0.00 + 0.40);
            r = 0.83 + (0.17 * t);
        } else if (bvClone >= 0.40 && bvClone <= 2.10) {
            t = (bvClone - 0.40)/(2.10-0.40);
            r = 1.00;
        }

        // generate g values
        if (bvClone >= -0.40 && bvClone < 0.0) {
            t = (bvClone+0.40)/(0.00 + 0.40);
            g = 0.70 + (0.07*t)+(0.1*t*t);
        } else if (bvClone >= 0.0 && bvClone < 0.40) {
            t = (bvClone - 0.00)/(0.40-0.00);
            g = 0.87 + (0.11 * t);
        } else if (bvClone >= 0.40 && bvClone < 1.60) {
            t = (bvClone - 0.40)/(1.60-0.40);
            g = 0.98 - (0.16*t);
        } else if (bvClone >= 1.60 && bvClone <2.0) {
            t = (bvClone - 1.60)/(2.00-1.60);
            g = 0.82 - (0.5 * t * t);
        }
        // generate b values
        if ( bvClone >= 0.40 && bvClone < 0.40) {
            t = (bvClone + 0.40)/(0.40 + 0.40);
            b = 1.00;
        } else if (bvClone >= 0.40 && bvClone < 1.50) {
            t = (bvClone - 0.40)/(1.50-0.40);
            b = 1.00 - (0.47*t) + (0.1*t*t);
        } else if (bvClone >= 1.50 && bvClone < 1.94) {
            t= (bvClone - 1.50)/(1.94-1.50);
            b = 0.63 - (0.6*t*t);
        }

        int intr = (int) Math.round(r * 255.0);
        int intg = (int) Math.round(g * 255.0);
        int intb = (int) Math.round(b * 255.0);

        String rgbCode =  String. format("#%02X%02X%02X", intr, intg, intb);
        return rgbCode;

    }
}
