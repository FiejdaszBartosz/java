package pl.retsuz.conversions.roman;

import pl.retsuz.conversions.GenericNumeralSystem;

public class Roman implements GenericNumeralSystem {
    private int mArabicNumbers[] = {1, 5, 10, 50, 100, 500, 1000};
    private char mRomaNumbers[] = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    @Override
    public String fromArabic(int val) {
        return "I";
        //throw new UnsupportedOperationException();
    }

    @Override
    public int toArabic(String val) {
        return 1;
        //throw new UnsupportedOperationException();
    }
}
