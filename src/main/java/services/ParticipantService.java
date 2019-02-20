/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import java.util.Objects;
import metier.Participant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author formation
 */
public class ParticipantService {

    private final SessionFactory sessionFactory;

    public ParticipantService(SessionFactory sessionFactory) {
        this.sessionFactory = Objects.requireNonNull(sessionFactory);
    }

    public void insertParticipant(Participant participant) {
        Objects.requireNonNull(participant);
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(participant);
        session.getTransaction().commit();

        session.close();

    }

    @SuppressWarnings("unchecked")
    public List<Participant> getAllParticipant() {
        Session session = this.sessionFactory.openSession();
        List<Participant> result = session.createQuery("from participants").list();
        session.close();
        return result;
    }
}
