import entity.PersonEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory fac;
        fac = Persistence.createEntityManagerFactory("default");
        EntityManager man = fac.createEntityManager();
        EntityTransaction tr = man.getTransaction();
        Scanner sc = new Scanner(System.in);

        try {
            tr.begin();

            PersonEntity major = new PersonEntity();

            System.out.println("Podaj imie:");
            major.setpName(sc.nextLine());

            System.out.println("Podaj nazwisko:");
            major.setpSurname(sc.nextLine());

            System.out.println("Podaj mail:");
            major.setpEmail(sc.nextLine());

            man.persist(major);

            tr.commit();
        } finally {
            if (tr.isActive()) tr.rollback();
            man.close();
            fac.close();
        }
    }
}

