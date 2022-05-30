package org.hiredgoons.distancecalculator.util;

import org.hiredgoons.common.dto.Positional;
import org.hiredgoons.common.util.BigDecimalUtil;
import org.hiredgoons.distancecalculator.service.DistanceService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDistanceUtil {

    @Test
    public void testConvertRightAscensionToDegrees() {

        String input = "10 56 28.99";
        BigDecimal degrees = DistanceUtil.convertRightAscensionToDegrees(input);

        assertTrue(new BigDecimal("164.1210").equals(degrees));
    }

    @Test
    public void testConvertDeclinationToDegrees() {

        {
            String input = "+7 00 52.0";
            BigDecimal result = DistanceUtil.convertDeclinationToDegrees(input);

            //System.out.println(degrees);

            assertTrue(new BigDecimal("7.0144").equals(result));
        }

//            String input = "-16 10 12.02";
//            BigDecimal result = DistanceUtil.convertDeclinationToDegrees(input);
//
//            System.out.println(result);
    }

    @Test
    public void testComputeDistanceBetweenObjects() {

        Positional alphaCentauri = new Positional() {
            @Override
            public BigDecimal getParallax() {
                return new BigDecimal("750.81");
            }

            @Override
            public String getRightAscension() {
                return "14 39 36.49400";
            }

            @Override
            public String getDeclination() {
                return "-60 50 02.3737";
            }
        };

        Positional sirius = new Positional() {
            @Override
            public BigDecimal getParallax() {
                return new BigDecimal("374.4896");
            }

            @Override
            public String getRightAscension() {
                return "6 45 08.91728";
            }

            @Override
            public String getDeclination() {
                return "-16 42 58.0171";
            }
        };

        BigDecimal result = DistanceUtil.computeDistanceBetweenObjects(alphaCentauri, sirius);
        boolean inRange = BigDecimalUtil.greaterThanEqual(result, new BigDecimal("9.5")) &&
                BigDecimalUtil.lessThanEqual(result, new BigDecimal("9.7"));
        assertTrue(inRange, "Distance should be between 9.5 and 9.7, actual: " + result);
    }
}
