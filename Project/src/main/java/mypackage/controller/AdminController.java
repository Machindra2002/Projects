package mypackage.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.model.*;
import mypackage.repository.*;
import mypackage.service.*;


@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST},allowedHeaders = "*")
public class AdminController {

	@Autowired
	AdminService service;
	
	@GetMapping("api/admin")
	public List<Admin> GetAllAdmin() {
		return service.GetAllAdmin();
	}
	
	@PostMapping("api/admin")
	public Admin AddAdmin(@RequestBody Admin a) {
		return service.AddAdmin(a);
	}
	 
	@PutMapping("api/admin")
	public Admin UpdateAdmin(@RequestBody Admin a) {
		return service.UpdateAdmin(a);
	}
	
	@DeleteMapping("api/admin/{id}")
	public Admin DeleteAdmin(@PathVariable("id")int id) {
		return service.DeleteAdmin(id);
	}
	 
}
