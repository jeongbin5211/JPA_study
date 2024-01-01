package io.jeongbin.jpa;

import io.jeongbin.jpa.entity.Customer;
import jakarta.persistence.*;

import java.util.List;

public class CustomerJpaExam {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-exam");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        //
        try{
            // em.persist(Customer.sample());
            //Customer foundCustomer = em.find(Customer.class, "ID1001");

            // foundCustomer.setName("Park");

            // em.remove(foundCustomer);

            // System.out.println(foundCustomer.toString());

            Customer customer = new Customer("ID0005", "Jin"); // 비영속 상태(new)
            em.persist(customer); // Customer 객체가 영속 상태로 됨 (Managed 상태)
            em.detach(customer);  // Customer 객체를 준영속 상태로 (Detached 상태)
            // em.flush(); // 데이터베이스에 반영이 됨 단, 커밋하지 않으면 다른프로그램에서 인식하지 못함

            // JPQL
//            Query query = em.createQuery("select c from Customer c", Customer.class);
//            List<Customer> customers = query.getResultList();
//            System.out.println(customers);

            Customer foundCustomer = em.find(Customer.class, "ID0005");
            System.out.println(foundCustomer.toString());
            
            tx.commit(); // 플러시 또한 같이 진행되므로 플러시를 따로 실행할 필요가 없음
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
