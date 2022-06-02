package view;

import entity.PersonEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class View {
    public Scanner sc;

    public View() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Adds person to database
     * @param name person's name
     * @param surname person's surname
     * @param mail person's mail
     */
    private void addPerson(String name, String surname, String mail) {
        EntityManagerFactory fac;
        fac = Persistence.createEntityManagerFactory("default");
        EntityManager man = fac.createEntityManager();
        EntityTransaction tr = man.getTransaction();


        try {
            tr.begin();

            PersonEntity newPerson = new PersonEntity();

            newPerson.setpName(name);
            newPerson.setpSurname(surname);
            newPerson.setpEmail(mail);

            man.persist(newPerson);

            tr.commit();
        } finally {
            if (tr.isActive()) tr.rollback();
            man.close();
            fac.close();
        }
    }

    /**
     * This method gets frm the user information about new user and pass them to addPerson
     */
    public void getPersonInfo() {
        String name = "", surname = "", mail = "";

        System.out.println("Podaj imie:");
        name = sc.nextLine();

        System.out.println("Podaj nazwisko:");
        surname = sc.nextLine();

        System.out.println("Podaj mail:");
        mail = sc.nextLine();

        if (!name.equals("") && !surname.equals("") && !mail.equals(""))
            addPerson(name, surname, mail);
        else {
            System.out.println("Input was empty");
            getPersonInfo();
        }
    }

    public static void main(String[] args) {
        View view = new View();
        view.getPersonInfo();
    }
}
