package com.beusable.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathHelper {

    public static double round(double value, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(value))
                .setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
