package pl.retsuz.view;

import pl.retsuz.collections.IDataCollection;
import pl.retsuz.currency.ICurrency;
import pl.retsuz.exchange.IExchange;

import java.util.Scanner;

public class StandardView implements ICurrencyView {
    private IExchange exchange;
    private IDataCollection dataCollection;
    private Scanner sc;

    public void setExchange(IExchange exchange) {
        this.exchange = exchange;
    }

    public void setDataCollection(IDataCollection collection) {
        this.dataCollection = collection;
    }

    public void ViewAll(IDataCollection coll) {
        dataCollection.ToString();
    }

    public ICurrency StringToCurrency(String code) {
        for (ICurrency i : dataCollection.getCurrencyList()) {
            if (i.getCode().equals(code))
                return i;
        }

        return null;
    }

    public ICurrency ChooseCurrency(String label) {
        String line;
        ICurrency temp;

        if (!label.equals(""))
            System.out.println(label);

        System.out.println("\nPodaj kod waluty: ");
        line = sc.nextLine();

        temp = StringToCurrency(line);

        if (temp == null) {
            System.err.println("\nPodano nieprawidłowy kod waluty");
            ChooseCurrency("");
        }

        return temp;
    }

    public void exchange() {
        ICurrency fromCurrency;
        ICurrency toCurrency;
        double amount = 0.0, afterExchange = 0.0;

        fromCurrency = ChooseCurrency("\nPodaj kod waluty z której chcesz dokonaać wymiany: ");
        toCurrency = ChooseCurrency("\nPodaj kod waluty do której chcesz dokonaać wymiany: ");

        System.out.println("Podaj ilość jaką chcesz wymienić: ");
        amount = Double.parseDouble(sc.nextLine());

        if (amount < 0.0) {
            System.err.println("Podano nieprawidłową ilość\n");
            exchange();
        }

        afterExchange = exchange.exchange(fromCurrency, toCurrency, amount);

        System.out.println("\nPo wymianie: " + afterExchange);

    }

    public void menu() {
        String choice;

        System.out.println("Pokaż waluty      ->    1\n");
        System.out.println("Dokonaj wymiany   ->    2\n");
        System.out.println("Wybierz numer operacje: ");
        choice = sc.nextLine();

        if (choice.equals("1"))
            ViewAll(dataCollection);
        else if (choice.equals("2"))
            exchange();
        else {
            System.err.println("!!! Nieprawidłowy wybór !!!\n");
            menu();
        }
    }

    public StandardView() {
        this.sc = new Scanner(System.in);
    }
}
