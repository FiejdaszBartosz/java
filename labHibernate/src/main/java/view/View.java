package view;

import entity.AddressInfoEntity;
import entity.PersonEntity;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.Scanner;

public class View {
    private Scanner mScanner;
    private EntityManagerFactory mEntityManagerFactory;
    private EntityManager mEntityManager;
    private EntityTransaction mEntityTransaction;
    private Session mSession;

    TypedQuery<PersonEntity> personByTown, personByName, personBySurname, personByEmail, personByState, personByCountry, personById;

    public View() {
        this.mScanner = new Scanner(System.in);
        this.mEntityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.mEntityManager = mEntityManagerFactory.createEntityManager();
        this.mSession = mEntityManager.unwrap(org.hibernate.Session.class);
        this.mEntityTransaction = mEntityManager.getTransaction();
    }

    /**
     * Adds person to database
     *
     * @param name    person's name
     * @param surname person's surname
     * @param mail    person's mail
     */
    private void addPerson(String name, String surname, String mail) {
        int check;
        personByEmail = mEntityManager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
        personByEmail.setParameter(1, mail);
        check = personByEmail.getResultList().size();

        if (check == 0) {
            try {
                mEntityTransaction.begin();

                PersonEntity newPerson = new PersonEntity();

                newPerson.setpName(name);
                newPerson.setpSurname(surname);
                newPerson.setpEmail(mail);

                mEntityManager.persist(newPerson);

                mEntityTransaction.commit();
            } finally {
                if (mEntityTransaction.isActive()) mEntityTransaction.rollback();
                mEntityManager.close();
                mEntityManagerFactory.close();
            }
        } else
            System.out.println("Podany mail istnieje juz w bazie");
    }

    /**
     * Adds address to database
     *
     * @param street  address's street
     * @param number  address's number
     * @param town    address's town
     * @param state   address's state
     * @param country address's country
     */
    private void addAddress(String street, String number, String town, String state, String country, String mail) {
        int check;

        personByEmail = mEntityManager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
        personByEmail.setParameter(1, mail);
        check = personByEmail.getResultList().size();
        if (check != 0) {
            try {
                mEntityTransaction.begin();

                PersonEntity person = personByEmail.getSingleResult();
                AddressInfoEntity newAddressInfoEntity = new AddressInfoEntity();

                newAddressInfoEntity.setStreet(street);
                newAddressInfoEntity.setHouseNumber(number);
                newAddressInfoEntity.setTown(town);
                newAddressInfoEntity.setState(state);
                newAddressInfoEntity.setCountry(country);
                person.getAddressInfosByPId().add(newAddressInfoEntity);
                newAddressInfoEntity.setPersonByAiPId(person);

                mEntityManager.persist(newAddressInfoEntity);

                mEntityTransaction.commit();
            } finally {
                if (mEntityTransaction.isActive()) mEntityTransaction.rollback();
                mEntityManager.close();
                mEntityManagerFactory.close();
            }
        } else
            System.out.println("Nie znaleziono takiego adresu");
    }


    /**
     * This method gets from the user information about new user and pass them to addPerson
     */
    public void getPersonInfo() {
        String name = "", surname = "", mail = "";

        System.out.println("Podaj imie:");
        name = mScanner.nextLine();

        System.out.println("Podaj nazwisko:");
        surname = mScanner.nextLine();

        System.out.println("Podaj mail:");
        mail = mScanner.nextLine();

        if (!name.equals("") && !surname.equals("") && !mail.equals(""))
            addPerson(name, surname, mail);
        else {
            System.out.println("Input was empty");
            getPersonInfo();
        }
    }

    /**
     * This method gets from the user information about user's address
     */
    public void getAddressInfo() {
        String street = "", number = "", town = "", state = "", country = "", mail = "";

        System.out.println("Podaj ulice:");
        street = mScanner.nextLine();

        System.out.println("Podaj numer:");
        number = mScanner.nextLine();

        System.out.println("Podaj miasto:");
        town = mScanner.nextLine();

        System.out.println("Podaj wojewodztwo:");
        state = mScanner.nextLine();

        System.out.println("Podaj panstwo:");
        country = mScanner.nextLine();

        System.out.println("Podaj mail do ktorego chcesz przypisac adres:");
        mail = mScanner.nextLine();

        if (!street.equals("") && !number.equals("") && !town.equals("") && !state.equals("") && !country.equals(""))
            addAddress(street, number, town, state, country, mail);
        else {
            System.out.println("Input was empty");
            getAddressInfo();
        }
    }

    private void searchByName() {
        String tempName;
        System.out.println("Podaj imie: ");
        tempName = mScanner.nextLine();

        personByName = mEntityManager.createNamedQuery("PersonEntity.ByName", PersonEntity.class);
        personByName.setParameter(1, tempName);
        for (PersonEntity p : personByName.getResultList()) {
            System.out.println(p.personEntityToString() + "\nAdresy:");
            for (AddressInfoEntity a : p.getAddressInfosByPId()) {
                System.out.println(" " + a.addressToString());
            }
        }
    }

    private void searchBySurname() {
        String tempSurname;
        System.out.println("Podaj nazwisko: ");
        tempSurname = mScanner.nextLine();

        personByName = mEntityManager.createNamedQuery("PersonEntity.BySurname", PersonEntity.class);
        personByName.setParameter(1, tempSurname);
        for (PersonEntity p : personByName.getResultList()) {
            System.out.println(p.personEntityToString() + "\nAdresy:");
            for (AddressInfoEntity a : p.getAddressInfosByPId()) {
                System.out.println(" " + a.addressToString());
            }
        }
    }

    private void searchByMail() {
        String tempMail;
        System.out.println("Podaj mail: ");
        tempMail = mScanner.nextLine();

        personByName = mEntityManager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
        personByName.setParameter(1, tempMail);
        for (PersonEntity p : personByName.getResultList()) {
            System.out.println(p.personEntityToString() + "\nAdresy:");
            for (AddressInfoEntity a : p.getAddressInfosByPId()) {
                System.out.println(" " + a.addressToString());
            }
        }
    }

    private void searchByTown() {
        String tempTown;
        System.out.println("Podaj miasto: ");
        tempTown = mScanner.nextLine();

        personByName = mEntityManager.createNamedQuery("PersonEntity.ByTown", PersonEntity.class);
        personByName.setParameter(1, tempTown);
        for (PersonEntity p : personByName.getResultList()) {
            System.out.println(p.personEntityToString() + "\nAdresy:");
            for (AddressInfoEntity a : p.getAddressInfosByPId()) {
                System.out.println(" " + a.addressToString());
            }
        }
    }

    private void searchByState() {
        String tempState;
        System.out.println("Podaj wojewodztwo: ");
        tempState = mScanner.nextLine();

        personByName = mEntityManager.createNamedQuery("PersonEntity.ByState", PersonEntity.class);
        personByName.setParameter(1, tempState);
        for (PersonEntity p : personByName.getResultList()) {
            System.out.println(p.personEntityToString() + "\nAdresy:");
            for (AddressInfoEntity a : p.getAddressInfosByPId()) {
                System.out.println(" " + a.addressToString());
            }
        }
    }

    private void searchByCountry() {
        String tempCountry;
        System.out.println("Podaj kraj: ");
        tempCountry = mScanner.nextLine();

        personByName = mEntityManager.createNamedQuery("PersonEntity.ByCountry", PersonEntity.class);
        personByName.setParameter(1, tempCountry);
        for (PersonEntity p : personByName.getResultList()) {
            System.out.println(p.personEntityToString() + "\nAdresy:");
            for (AddressInfoEntity a : p.getAddressInfosByPId()) {
                System.out.println(" " + a.addressToString());
            }
        }
    }

    public void search() {
        String choice;
        System.out.println("Podaj rekord po ktorym chcesz szukac?/n" +
                "1 - Imie\n" +
                "2 - Nazwisko\n" +
                "3 - E-mail\n" +
                "4 - Miasto \n" +
                "5 - Stan\n" +
                "6 - Kraj\n");
        choice = mScanner.nextLine();
        switch (choice) {
            case "1": {
                searchByName();
            }
            case "2": {
                searchBySurname();
            }
            case "3": {
                searchByMail();
            }
            case "4": {
                searchByTown();
            }
            case "5": {
                searchByState();
            }
            case "6": {
                searchByCountry();
            }
            default: {
                System.out.println("Nieprawidlowy wybor");
            }
        }
    }

    public void updatePerson() {
        String name, surname, mail;
        int check;

        System.out.println("Podaj mail: ");
        mail = mScanner.nextLine();
        personByEmail = mEntityManager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
        personByEmail.setParameter(1, mail);
        check = personByEmail.getResultList().size();

        if (check != 0) {

            System.out.println("Podaj imie:");
            name = mScanner.nextLine();

            System.out.println("Podaj nazwisko:");
            surname = mScanner.nextLine();

            System.out.println("Podaj mail:");
            mail = mScanner.nextLine();

            PersonEntity newPerson = personById.getSingleResult();

            if (!name.equals("") && !surname.equals("") && !mail.equals("")) {
                try {
                    mEntityTransaction.begin();


                    newPerson.setpName(name);
                    newPerson.setpSurname(surname);
                    newPerson.setpEmail(mail);

                    mSession.update(newPerson);

                    mEntityTransaction.commit();
                } finally {
                    if (mEntityTransaction.isActive()) mEntityTransaction.rollback();
                    mEntityManager.close();
                    mEntityManagerFactory.close();
                }
            } else {
                System.out.println("Nieprawidlowe dane");
            }
        }
    }

    public void updateAddress() {
        String street, number, town, state, country, mail;
        int check;

        System.out.println("Podaj mail: ");
        mail = mScanner.nextLine();
        personByEmail = mEntityManager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
        personByEmail.setParameter(1, mail);
        check = personByEmail.getResultList().size();

        if (check != 0) {
            PersonEntity person = personByEmail.getSingleResult();

            System.out.println("Podaj ulice:");
            street = mScanner.nextLine();

            System.out.println("Podaj numer:");
            number = mScanner.nextLine();

            System.out.println("Podaj miasto:");
            town = mScanner.nextLine();

            System.out.println("Podaj wojewodztwo:");
            state = mScanner.nextLine();

            System.out.println("Podaj panstwo:");
            country = mScanner.nextLine();

            if (!street.equals("") && !number.equals("") && !town.equals("") && !state.equals("") && !country.equals("")) {
                for (AddressInfoEntity i : person.getAddressInfosByPId()) {

                    try {
                        mEntityTransaction.begin();

                        AddressInfoEntity addressInfoEntity = new AddressInfoEntity();

                        i.setStreet(street);
                        i.setHouseNumber(number);
                        i.setTown(town);
                        i.setState(state);
                        i.setCountry(country);

                        mSession.update(i);

                        mEntityTransaction.commit();
                    } finally {
                        if (mEntityTransaction.isActive()) mEntityTransaction.rollback();
                        mEntityManager.close();
                        mEntityManagerFactory.close();
                    }
                }

            } else {
                System.out.println("Nieprawidlowe dane");
            }
        }
    }

    public void deletePerson() {
        String mail;
        System.out.println("Podaj mail: ");
        mail = mScanner.nextLine();
        personByEmail = mEntityManager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
        personByEmail.setParameter(1, mail);
        PersonEntity person = personByEmail.getSingleResult();

        try {
            mEntityTransaction.begin();

            mEntityManager.remove(person);
            mEntityManager.flush();
            mEntityManager.clear();

            mEntityTransaction.commit();
        } finally {
            if (mEntityTransaction.isActive()) mEntityTransaction.rollback();
            mEntityManager.close();
            mEntityManagerFactory.close();
        }
    }

    public void deleteAddress() {
        String mail;
        int check;

        System.out.println("Podaj mail: ");
        mail = mScanner.nextLine();
        personByEmail = mEntityManager.createNamedQuery("PersonEntity.ByEmail", PersonEntity.class);
        personByEmail.setParameter(1, mail);
        check = personByEmail.getResultList().size();

        if (check != 0) {
            PersonEntity person = personByEmail.getSingleResult();

            for (AddressInfoEntity i : person.getAddressInfosByPId()) {

                mEntityTransaction.begin();
                Query deleteAddress = mEntityManager.createNamedQuery("DeleteAddressRecord");
                int temp = i.getAiId();
                deleteAddress.setParameter(1, temp);
                deleteAddress.executeUpdate();
                mEntityTransaction.commit();

            }
        }
    }


    public static void main(String[] args) {
        View view = new View();
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean ifContinue = true;
        while (ifContinue) {
            System.out.println("Wybierz operacje:\n" +
                    "1. Dodaj do bazy danych\n" +
                    "2. Szukaj w bazie danych\n" +
                    "3. Zmień w bazie danych\n" +
                    "4. Usuń z bazy danych\n" +
                    "5. Wyjdz");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Wybierz co chcesz dodac:\n" +
                            "1. Osoba\n" +
                            "2. Adres");
                    choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            view.getPersonInfo();
                            break;
                        case 2:
                            view.getAddressInfo();
                            break;
                        default:
                            System.out.println("Zly wybor");
                    }
                    break;
                case 2:
                    view.search();
                    break;
                case 3:
                    System.out.println("Wybierz co chcesz zaktualizowac:\n" +
                            "1. Osoba\n" +
                            "2. Adres");
                    choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            view.updatePerson();
                            break;
                        case 2:
                            view.updateAddress();
                            break;
                        default:
                            System.out.println("Zly wybor");
                    }
                    break;
                case 4:
                    System.out.println("Wybierz co chcesz usunac:\n" +
                            "1. Osoba\n" +
                            "2. Adres");
                    choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            view.deletePerson();
                            break;
                        case 2:
                            view.deleteAddress();
                            break;
                        default:
                            System.out.println("Zly wybor");
                    }
                    break;
                case 5:
                    ifContinue = false;
                    break;
                default:
                    System.out.println("Zly wybor");
                    break;
            }
        }
    }
}
