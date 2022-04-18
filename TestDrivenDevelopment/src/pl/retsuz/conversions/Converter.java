package pl.retsuz.conversions;

public class Converter {

    public String convert(GenericNumeralSystem from, GenericNumeralSystem to, String value){
        return to.fromArabic(from.toArabic(value));
    }
}
