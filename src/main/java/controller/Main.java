package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em;
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory("aplicacionMariaDB");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.getTransaction().commit();
	}

}