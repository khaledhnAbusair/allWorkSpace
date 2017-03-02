package controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Department;
import domain.repository.DepartmetRepository;

@Controller
@RequestMapping(path = "/departments") // Base path for all
public class DepartemntsController {

	@Autowired
	private DepartmetRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public String listDepartments(Map<String, Object> model) {
		Iterable<Department> departments = repository.findAll();
		model.put("departments", departments);
		return "departments";// view name (jsp page)
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addDepartment(@ModelAttribute Department department, Map<String, Object> model,
			HttpServletRequest request) {
		try {
			repository.save(department);
		} catch (Exception e) {
			model.put("error", e);
			return "departments";
		}
		return "redirect:" + request.getServletPath() + "/departments";
	}
}
