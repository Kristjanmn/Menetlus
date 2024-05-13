package io.nqa.menetlus.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MenetlusUtilsTest {
    @Test
    void isEmailValid_true() {
        String email = "kiisu@miisu.ee";
        assertTrue(MenetlusUtils.isEmailValid(email));
    }

    @Test
    void isEmailValid_false() {
        String email = "kiisu@miisu@miisu.";
        assertFalse(MenetlusUtils.isEmailValid(email));
    }

    @Test
    void isPersonalCodeValid_true() {
        long personalCode = 34501234215L;
        assertTrue(MenetlusUtils.isPersonalCodeValid(personalCode));
    }

    @Test
    void isPersonalCodeValid_false() {
        long personalCode = 20502236235L;
        assertFalse(MenetlusUtils.isPersonalCodeValid(personalCode));
    }

    @Test
    void isPersonalCodeValid_false_short() {
        long personalCode = 356272376L;
        assertFalse(MenetlusUtils.isPersonalCodeValid(personalCode));
    }

    @Test
    void isPersonalCodeValid_false_long() {
        long personalCode = 3437347347384L;
        assertFalse(MenetlusUtils.isPersonalCodeValid(personalCode));
    }
}
