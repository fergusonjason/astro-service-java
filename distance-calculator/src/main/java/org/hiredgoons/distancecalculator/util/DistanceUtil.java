package org.hiredgoons.distancecalculator.util;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.hiredgoons.common.dto.Positional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DistanceUtil {

    private static final String conversionRegex = "^[+-]?(\\d{1,2}) (\\d{1,2})? ([0-9]+\\.?[0-9]*|\\.[0-9]+)?$";

    private static final Pattern conversionPattern = Pattern.compile(conversionRegex);

    private static final BigDecimal BD_60 = new BigDecimal("60.0000");
    private static final BigDecimal BD_3600 = new BigDecimal("3600.0000");
    private static final BigDecimal BD_180 = new BigDecimal("180.0000");
    private static final BigDecimal BD_360 = new BigDecimal("360.0000");

    /**
     * Convert a Right Ascension in form h mm ss.ssss to decimal degrees value
     *
     * @param input String representing value of Right Ascension
     * @return  BigDecimal representing decimal value of right ascension
     */
    public static BigDecimal convertRightAscensionToDegrees(String input) {

        BigDecimal hours;
        BigDecimal minutes;
        BigDecimal seconds;

        Matcher matcher = conversionPattern.matcher(input);
        if (matcher.matches()) {
            hours = matcher.group(1) != null ? new BigDecimal(matcher.group(1)) : BigDecimal.ZERO;
            minutes = matcher.group(2) != null ? new BigDecimal(matcher.group(2)) : BigDecimal.ZERO;
            seconds = matcher.group(3) != null ? new BigDecimal(matcher.group(3)) : BigDecimal.ZERO;
        } else {
            return BigDecimal.ZERO;
        }

        BigDecimal result = new BigDecimal("0.0000");

        result = result.add(hours);
        result = result.add(minutes.divide(BD_60, 4, RoundingMode.HALF_UP));
        result = result.add(seconds.divide(BD_3600, 4, RoundingMode.HALF_UP));
        result = result.multiply(new BigDecimal("15.0"));

        if (result.compareTo(BD_180) > 0) {
            result = result.subtract(BD_360);
        }

        result = result.setScale(4, RoundingMode.HALF_UP);

        return result;
    }

    /**
     * Convert declination in form dd aa bb.bbbb to decimal degrees, where aa represents
     * arcminutes and bb represents arcseconds
     *
     * @param input String containing input
     * @return  BigDecimal representing decimal degrees value
     */
    public static BigDecimal convertDeclinationToDegrees(String input) {

        BigDecimal degrees;
        BigDecimal arcmin;
        BigDecimal arcsec;

        Matcher matcher = conversionPattern.matcher(input);
        if (matcher.matches()) {
            degrees = matcher.group(1) != null ? new BigDecimal(matcher.group(1)) : BigDecimal.ZERO;
            arcmin = matcher.group(2) != null ? new BigDecimal(matcher.group(2)) : BigDecimal.ZERO;
            arcsec = matcher.group(3) != null ? new BigDecimal(matcher.group(3)) : BigDecimal.ZERO;
        } else {
            return BigDecimal.ZERO;
        }

        BigDecimal result = BigDecimal.ZERO;
        result = result.add(degrees);
        result = result.add(arcmin.divide(BD_60, 4, RoundingMode.HALF_UP));
        result = result.add(arcsec.divide(BD_3600, 4, RoundingMode.HALF_UP));

        return result;
    }

    /**
     * Compute distance from Sun to a given object
     *
     * @param object    instance of Positional representing object to compute distance to
     * @return  BigDecimal representing distance from Sun to Object, in ly
     */
    public static BigDecimal computeDistanceToObject(Positional object) {

        BigDecimal adjustedParallax = object.getParallax().divide(new BigDecimal("1000"), MathContext.DECIMAL32);
        BigDecimal result = BigDecimal.ONE.divide(adjustedParallax, MathContext.DECIMAL32);
        result = result.multiply(new BigDecimal("3.26"));
        return result;
    }

    /**
     * Compute distance between 2 objects via finding missing length of triangle. Thanks to James K via
     * https://astronomy.stackexchange.com/a/48953/30889
     *
     * Formula:
     * cos(C) = sin(dec1) * sin(dec2) + cos(dec1) * cos(dec2) * cos(ra1 - ra2)
     *
     * Then (a = distance from Sun to object 1, b = distance to object 2, c = distance from a to b):
     *
     * c^2 = a^2 + b^2 - 2*a*b*cos(C)
     * @param object1    instance of Positional interface representing the first object
     * @param object2   instance of Positional interface representing the second object
     * @return
     */
    public static BigDecimal computeDistanceBetweenObjects(Positional object1, Positional object2) {

        // get values and convert to radians

        BigDecimal ra1 = convertRightAscensionToDegrees(object1.getRightAscension());
        ra1 = ra1.multiply(new BigDecimal("3.1415927").divide(new BigDecimal(180), MathContext.DECIMAL32));

        BigDecimal ra2 = convertRightAscensionToDegrees(object2.getRightAscension());
        ra2 = ra2.multiply(new BigDecimal("3.1415927").divide(new BigDecimal(180), MathContext.DECIMAL32));

        BigDecimal dec1 = convertDeclinationToDegrees(object1.getDeclination());
        dec1 = dec1.multiply(new BigDecimal("3.1415927").divide(new BigDecimal(180), MathContext.DECIMAL32));

        BigDecimal dec2 = convertDeclinationToDegrees(object2.getDeclination());
        dec2 = dec2.multiply(new BigDecimal("3.1415927").divide(new BigDecimal(180), MathContext.DECIMAL32));

        // compute sin(dec1) * sin(dec2)
        BigDecimal fragment1 = BigDecimalMath.sin(dec1, MathContext.DECIMAL32).
                        multiply(BigDecimalMath.sin(dec2, MathContext.DECIMAL32));

        // compute cos(dec1) * cos(dec2) * cos(ra1 - ra2)
        BigDecimal fragment2 = BigDecimalMath.cos(dec1, MathContext.DECIMAL32)
                .multiply(BigDecimalMath.cos(dec2, MathContext.DECIMAL32))
                .multiply(BigDecimalMath.cos(ra1.subtract(ra2), MathContext.DECIMAL32));

        // compute cos(C) by adding the fragments
        BigDecimal cosC = fragment1.add(fragment2);

        // compute distance Sun to objects
        BigDecimal distance1 = computeDistanceToObject(object1);
        BigDecimal distance2 = computeDistanceToObject(object2);

        // compute c^2 = a^2 + b^ - 2ab * cos(C)
        BigDecimal result = distance1.pow(2).add(distance2.pow(2)).subtract(
                new BigDecimal("2.0").multiply(distance1).multiply(distance2).multiply(cosC)
        );

        // get sqrt of c for final result
        result = BigDecimalMath.sqrt(result, MathContext.DECIMAL32);

        return result;
    }

}
