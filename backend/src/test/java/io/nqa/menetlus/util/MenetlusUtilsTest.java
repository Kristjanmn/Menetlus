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
    void isIsikukoodValid_true() {
        long isikukood = 34501234215L;
        assertTrue(MenetlusUtils.isIsikukoodValid(isikukood));
    }

    @Test
    void isIsikukoodValid_false() {
        long isikukood = 20502236235L;
        assertFalse(MenetlusUtils.isIsikukoodValid(isikukood));
    }

    @Test
    void isIsikukoodValid_false_short() {
        long isikukood = 356272376L;
        assertFalse(MenetlusUtils.isIsikukoodValid(isikukood));
    }

    @Test
    void isIsikukoodValid_false_long() {
        long isikukood = 3437347347384L;
        assertFalse(MenetlusUtils.isIsikukoodValid(isikukood));
    }
}
