package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Client;
import model.interfaces.IClientDAO;
import util.PersistenceUnit;

public class ClientDAO implements IClientDAO {

	public void save(Client val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.persist(val);
		em.getTransaction().commit();
	}

	public void edit(Client val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.merge(val);
		em.getTransaction().commit();
	}

	public void delete(Client val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.remove(val);
		em.getTransaction().commit();
	}

	public Client getById(Long id) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		Client result = em.find(Client.class, id);
		em.getTransaction().commit();
		return result;
	}

	public Client getByUsername(String username) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Client> query = em.createNamedQuery("findClientByUsername", Client.class);
		query.setParameter("username", username);
		Client result = query.getSingleResult();
		em.getTransaction().commit();
		return result;
	}

	public Client getByUsernamePassword(String username, String password) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Client> query = em.createNamedQuery("findClientByUsernamePassword", Client.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		Client result = query.getSingleResult();
		em.getTransaction().commit();
		return result;
	}

	public List<Client> getAll() {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Client> query = em.createNamedQuery("findAllClient", Client.class);
		List<Client> result = query.getResultList();
		em.getTransaction().commit();
		return result;
	}

}
