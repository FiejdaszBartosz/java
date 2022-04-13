package pl.retsuz.conversions.roman;

import pl.retsuz.conversions.GenericNumeralSystem;

public class Roman implements GenericNumeralSystem {
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
