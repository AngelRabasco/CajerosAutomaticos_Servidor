package controller;

import model.Admin;
import model.dao.AdminDAO;

public class AdminController {

	public synchronized Admin getAdminById(Long id) {
		return new AdminDAO().getById(id);
	}

	public synchronized Admin logInAdmin(Admin admin) {
		return new AdminDAO().getByUsernamePassword(admin.getUsername(), admin.getPassword());
	}
}
