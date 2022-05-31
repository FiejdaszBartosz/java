import entity.AddressInfoEntity;
import entity.PersonEntity;

import javax.persistence.*;
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

            PersonEntity person = new PersonEntity();
            person.setpName("Major");
            person.setpSurname("Suchodolski");
            person.setpEmail("major@bombas.pl");

            AddressInfoEntity address = new AddressInfoEntity();
            address.setStreet("Szkolna");
            address.setHouseNumber("17");
            address.setTown("Bialystok");
            address.setState("Podlaskie");
            address.setCountry("Polska");

            AddressInfoEntity address2 = new AddressInfoEntity();
            address.setStreet("Plac Brodowicza");
            address.setHouseNumber("1");
            address.setTown("Choroszcz");
            address.setState("Podlaskie");
            address.setCountry("Polska");

            address.setPersonByAiPId(person);
            address2.setPersonByAiPId(person);

            man.persist(person);

        } finally {
            if (tr.isActive()) tr.rollback();
            man.close();
            fac.close();
        }
    }
}

