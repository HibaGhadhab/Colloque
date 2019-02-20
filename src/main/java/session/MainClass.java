/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.text.DateFormat;
import java.util.Date;
import metier.Participant;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import services.ParticipantService;
import java.text.SimpleDateFormat;

/**
 *
 * @author formation
 */
public class MainClass {

    private static SessionFactory createSessionFactory() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            return new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return null;
    }

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = createSessionFactory()) {
            ParticipantService participantService = new ParticipantService(sessionFactory);

            /**
             * ************ Insertion de deux participants
             * *********************************
             */
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Participant participant1 = new Participant("Ghadhab", "Hiba", "toto@gmail.com", dateFormat.format(date), "organisationX", "observationY");
            participantService.insertParticipant(participant1);
            Participant participant2 = new Participant("ENSG", "ENSG", "toto@gmail.com", dateFormat.format(date), "organisationENSG", "observationENSG");
            participantService.insertParticipant(participant2);

        }
    }

}
