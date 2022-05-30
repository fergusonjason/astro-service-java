package org.hiredgoons.common.dto;

import java.math.BigDecimal;

public interface Positional {

    BigDecimal getParallax();

    String getRightAscension();

    String getDeclination();
}
