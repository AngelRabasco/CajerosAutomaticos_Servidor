package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Admin;
import model.Client;
import model.interfaces.IClientDAO;
import util.PersistenceUnit;

public class ClientDAO implements IClientDAO {

	public void save(Client val) {
		// TODO Auto-generated method stub

	}

	public void edit(Client val) {
		// TODO Auto-generated method stub

	}

	public void delete(Client val) {
		// TODO Auto-generated method stub

	}

	public Client getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Client getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public Client getByUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Client> getByAdmin(Admin admin) {
		EntityManager em = PersistenceUnit.getEM();
		em.getTransaction().begin();
		TypedQuery<Client> query = em.createNamedQuery("findClientsByAdmin", Client.class);
		query.setParameter("adminId", admin.getId());
		List<Client> result = query.getResultList();
		em.getTransaction().commit();
		return result;
	}

	public List<Client> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
