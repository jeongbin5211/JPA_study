package io.jeongbin.jpa;

import io.jeongbin.jpa.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        //
        try{
//            em.persist(Customer.sample());
            Customer foundCustomer = em.find(Customer.class, "ID1001");

            em.remove(foundCustomer);
            // foundCustomer.setName("Park");

            System.out.println(foundCustomer.toString());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
