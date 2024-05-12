package io.nqa.menetlus.util;

import java.util.regex.Pattern;

public class MenetlusUtils {
    public static boolean isEmailValid(String email) {
        if (email == null || email.isBlank()) return false;
        String emailRegex = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
        return Pattern.compile(emailRegex)
                .matcher(email)
                .matches();
    }

    public static boolean isIsikukoodValid(long isikukood) {
        String ikString = String.valueOf(isikukood);
        if (ikString.length() != 11) return false;
        int[] kood = new int[ikString.length()];
        for (int i = 0; i < ikString.length(); i++) {
            kood[i] = Integer.parseInt(ikString.substring(i, i + 1));
        }
        // I astme kaal: 1 2 3 4 5 6 7 8 9 1
        int kontroll = (kood[0] + kood[1] * 2 + kood[2] * 3 + kood[3] * 4 +
                kood[4] * 5 + kood[5] * 6 + kood[6] * 7 + kood[7] * 8 +
                kood[8] * 9 + kood[9]) % 11;
        if (kontroll == 10) {
            // II astme kaal: 3 4 5 6 7 8 9 1 2 3
            kontroll = (kood[0] * 3 + kood[1] * 4 + kood[2] * 5 +
                    kood[3] * 6 + kood[4] * 7 + kood[5] * 8 + kood[6] * 9 +
                    kood[7] + kood[8] * 2 + kood[9] * 3) % 11;
        }
        if (kontroll == 10) kontroll = 0;
        return kontroll == kood[10];
    }
}
