package collections;

import model.entities.Currency;
import model.entities.ICurrency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyDataCollection implements IDataCollection {
    protected Currency currency;
    protected List<ICurrency> currencyList;

    public CurrencyDataCollection() {
        currencyList = new ArrayList<>();
    }

    public String ToString() {
        String temp = "";
        for(ICurrency i : currencyList) {
            temp = temp + i.getName() + " " + i .getCode() + " " + i.getFactor() + " " + i.getRate() + "\n";
        }

        return temp;
    }

    public List<ICurrency> getCurrencyList() {
        return currencyList;
    }
    
    public ICurrency getCurrencyByCode(ICurrency currency) {
        for (ICurrency temp : currencyList) {
            if (currency.getCode().equals(temp.getCode()))
                return temp;
        }
        return null;
    }
}
