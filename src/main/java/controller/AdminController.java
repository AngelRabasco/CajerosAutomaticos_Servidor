package controller;


import java.io.IOException;

import model.Admin;
import model.dao.AdminDAO;



public class AdminController {
	
	   public Admin getAdminById(Long id) {
	        return new AdminDAO().getById(id);
	    }
		public Admin logAdministrador(Admin admin) {
			return new AdminDAO().getByUsernamePassword(admin.getUsername(),admin.getPassword());
		}
}
