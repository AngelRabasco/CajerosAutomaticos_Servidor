package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Admin;
import model.interfaces.IAdminDAO;
import util.PersistenceUnit;

public class AdminDAO implements IAdminDAO {

	public void save(Admin val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.persist(val);
		em.getTransaction().commit();
	}

	public void edit(Admin val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.merge(val);
		em.getTransaction().commit();
	}

	public void delete(Admin val) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		em.remove(val);
		em.getTransaction().commit();
	}

	public Admin getById(Long id) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		Admin result = em.find(Admin.class, id);
		em.getTransaction().commit();
		return result;
	}

	public Admin getByUsername(String username) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Admin> query = em.createNamedQuery("findAdminByUsername", Admin.class);
		query.setParameter("username", username);
		Admin result = query.getSingleResult();
		em.getTransaction().commit();
		return result;
	}

	public Admin getByUsernamePassword(String username, String password) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Admin> query = em.createNamedQuery("findAdminByUsernamePassword", Admin.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		Admin result = query.getSingleResult();
		em.getTransaction().commit();
		System.out.println(result);
		return result;
	}

	public List<Admin> getAll() {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Admin> query = em.createNamedQuery("findAllAdmin", Admin.class);
		List<Admin> result = query.getResultList();
		em.getTransaction().commit();
		return result;
	}

}
