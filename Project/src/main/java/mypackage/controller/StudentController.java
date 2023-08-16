package mypackage.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mypackage.model.*;
import mypackage.service.*;
import mypackage.service.fileupload.FileStorageService;
import mypackage.service.fileupload.UploadFileResponse;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class StudentController {

	@Autowired
	StudentServices studservice;

	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping("api/studentdeatils/{id}")
	public StudentDetails GetStudentById(@PathVariable("id") int id) {
		return studservice.GetStudentDetailsById(id);
	}

	@GetMapping("api/nextcode")
	public StudentDetails NextStudentCode() {
		String code = studservice.NextStudentCode();
		StudentDetails s = new StudentDetails(0, "", "", code, "", "", "", "", null, null, 0);
		return s;
	}

	@PutMapping("api/studentdeatils")
	public StudentDetails UpdateStudentDetails(@RequestBody StudentDetails st) {
		return studservice.UpdateStudentDetails(st);
	}

	@DeleteMapping("api/studentdeatils/{id}")
	public StudentDetails DeleteStudentDetails(@PathVariable("id") int id) {
		return studservice.DeleteStudentDetails(id);
	}

	@PostMapping("/Uploadfile")
	public UploadFileResponse Uploadfile(@RequestParam("file") MultipartFile file) {
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile")
				.path(fileName).toUriString();
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@PostMapping("api/studentdeatils")
	public StudentDetails AddNewStudentDetails(@RequestParam("student_name") String student_name, 
			@RequestParam("student_code") String student_code,
			@RequestParam("email_address") String email_address,
			@RequestParam("mobile_number") String mobile_number,
			@RequestParam("file") MultipartFile file,
			@RequestParam("city") String city) {
		String fileName = fileStorageService.storeFile(file);
		StudentDetails sd = new StudentDetails(0, "", student_name, student_code, email_address, mobile_number,
				fileName, city, null, null, 0);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		UploadFileResponse r = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		
		studservice.AddStudentDetails(sd);
		
		return sd;
	}

	@GetMapping("api/studentdeatils")
	public List<StudentDetails> GetAllStudentDetails() {
		List<StudentDetails> lst = new ArrayList<StudentDetails>();
		for (StudentDetails s : studservice.GetAllStudentDetails()) {//getallstudentdetails method for services
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(s.getProfile_photo()).toUriString();
			StudentDetails sd = new StudentDetails(s.getStudent_id(), s.getPassword(), s.getStudent_name(),
					s.getStudent_code(), s.getEmail_address(), s.getMobile_number(), fileDownloadUri, s.getCity(), null,
					null, s.getFlag());
			lst.add(sd);
		}
		return lst;
	}
	
//	====================================================================================================================

	@PostMapping("api/checklogin")
	public StudentDetails checkLogin(@RequestBody StudentDetails s) {
		return studservice.GetStudentLoginDetails(s);
	}
	
	
}
