package mypackage.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.*;
import mypackage.repository.*;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminrepo;
	
	public Admin AddAdmin(Admin a) {
		Admin admin = adminrepo.save(a);
		return admin;
	}
	
	public List<Admin> GetAllAdmin(){
		List<Admin> lst = new ArrayList<Admin>();
		for(Admin admin : adminrepo.findAll()) {
			Admin a = new Admin(admin.getAdmin_id(), admin.getUser_name(),admin.getPassword(), 0);
			lst.add(a);
		}
		return lst;
	}
	
	public Admin GetAdminById(int id) {
		Admin a = adminrepo.findById(id).get();
		Admin admin = new Admin(a.getAdmin_id(), a.getUser_name(),a.getPassword(), 0);
		return admin;
	}
	
	public Admin UpdateAdmin(Admin a) {
		Admin admin = adminrepo.save(a);
		return admin;
	}
	
	public Admin DeleteAdmin(int id) {
		Admin a = GetAdminById(id);
		adminrepo.delete(a);
		return a;
	}
}
