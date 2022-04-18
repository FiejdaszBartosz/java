package pl.retsuz.conversions.roman;

import pl.retsuz.conversions.GenericNumeralSystem;

import java.util.Locale;

public class Roman implements GenericNumeralSystem {
    private String mThousands[] = {"", "M", "MM", "MMM"};
    private String mHundreds[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private String mDozens[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private String mUnity[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    @Override
    public String fromArabic(int val) {
        String result = "";
        int temp;

        if (val < 0 || val  > 3999)
            throw new IllegalArgumentException();

        result = mThousands[val / 1000]
                + mHundreds[(val % 1000) / 100]
                + mDozens[(val % 100) / 10]
                + mUnity[val % 10];

        return result;
    }

    @Override
    public int toArabic(String val) {
        int result = 0, previous = 0;
        char temp;
        val = val.toUpperCase(Locale.ROOT);

        for (int i = val.length() - 1; i >= 0; i--) {
            temp = val.charAt(i);
            switch (temp) {
                case 'M' -> {
                    result = specifyNumber(1000, previous, result);
                    previous = 1000;
                }
                case 'D' -> {
                    result = specifyNumber(500, previous, result);
                    previous = 500;
                }
                case 'C' -> {
                    result = specifyNumber(100, previous, result);
                    previous = 100;
                }
                case 'L' -> {
                    result = specifyNumber(50, previous, result);
                    previous = 50;
                }
                case 'X' -> {
                    result = specifyNumber(10, previous, result);
                    previous = 10;
                }
                case 'V' -> {
                    result = specifyNumber(5, previous, result);
                    previous = 5;
                }
                case 'I' -> {
                    result = specifyNumber(1, previous, result);
                    previous = 1;
                }
                default -> throw new IllegalArgumentException();
            }
        }
        return result;
    }

    /**
     * Chooses whether the current number should be subtracted or added to the result.
     * @param number
     * @param previous
     * @param result
     * @return
     */
    private int specifyNumber(int number, int previous, int result) {
        if (previous > number) {
            return result - number;
        } else {
            return  result + number;
        }
    }
}
