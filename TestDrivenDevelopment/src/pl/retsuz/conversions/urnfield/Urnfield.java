package pl.retsuz.conversions.urnfield;

import pl.retsuz.conversions.GenericNumeralSystem;

public class Urnfield implements GenericNumeralSystem {
    @Override
    public String fromArabic(int val) {
        String one = "";
        String five = "";

        if (val > 0 && val <= 29) {
            while (val > 4) {
                val -= 5;
                five += "\\";
            }
            while (val > 0) {
                val -= 1;
                one += "/";
            }
        } else
            throw new UnsupportedOperationException();

        return one + five;
    }

    @Override
    public int toArabic(String val) {
        int result = 0;
        char[] number = new char[val.length()];

        if (!validateNumber(val))
            throw new UnsupportedOperationException();

        for (int i = 0; i < val.length(); i++) {
            number[i] = val.charAt(i);
        }

        for (char i : number) {
            if (i == '\\')
                result += 5;
            else
                result += 1;
        }

        return result;
    }

    private boolean validateNumber(String val) {
        return val.matches("/{0,4}\\\\{0,5}");
    }
}
