package org.hiredgoons.common.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestColorUtil {

    @Test
    public void testBv2Rgb() {

        BigDecimal test = new BigDecimal("0.25");
        String testRgb = ColorUtil.bv2rgb(test);
        assertEquals("#EEEFFF", testRgb);
//        BigDecimal test = new BigDecimal("1.20");
//        String testRgb = ColorUtil.bv2rgb(test);
//        assertEquals(testRgb, "#FFDFB8");

//        double test2 = 0.46;
//        ColorUtil.bv2rgb(test2);

    }
}
