package com.etiya.renACar.core.utilities.helpers;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.Period;

public class HelperMetods {

    public static String createCode() {
        String code = RandomStringUtils.randomAlphanumeric(15);
        return code;
    }

    public static double calculateForSum(double...parametres){
        double sum = 0;
        for(double i: parametres){
            sum+=i;
        }
        return sum;
    }
    public static double calculateForMult(double...parametres){
        double mult = 0;
        for(double i: parametres){
            mult+=i;
        }
        return mult;
    }

    public static int getDaysBetween(LocalDate dateBefore, LocalDate dateAfter){
        Period daysBetween = Period.between(dateBefore,dateAfter);
        return daysBetween.getDays();
    }

}
