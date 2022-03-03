package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Account;
import model.Client;
import model.interfaces.IAccountDAO;
import util.PersistenceUnit;

public class AccountDAO implements IAccountDAO {

	public void save(Account val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.persist(val);
		em.getTransaction().commit();
	}

	public void edit(Account val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.merge(val);
		em.getTransaction().commit();
	}

	public void delete(Account val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.remove(val);
		em.getTransaction().commit();
	}

	public Account getById(Long id) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		Account result = em.find(Account.class, id);
		em.getTransaction().commit();
		return result;
	}

	public Account getAccountByNumber(String number) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Account> query = em.createNamedQuery("findAccountByNumber", Account.class);
		query.setParameter("number", number);
		Account result = query.getSingleResult();
		em.getTransaction().commit();
		return result;
	}

	public List<Account> getAccountsByClient(Client client) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Account> query = em.createNamedQuery("findAccountByClient", Account.class);
		query.setParameter("clientId", client.getId());
		List<Account> result = query.getResultList();
		em.getTransaction().commit();
		return result;
	}

	public List<Account> getAll() {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Account> query = em.createNamedQuery("findAll", Account.class);
		List<Account> result = query.getResultList();
		em.getTransaction().commit();
		return result;
	}

}
