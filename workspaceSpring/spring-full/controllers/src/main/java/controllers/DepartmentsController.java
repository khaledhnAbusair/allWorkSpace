/**
 * 
 */
package controllers;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import domain.Department;
import domain.repository.DepartmetRepository;

/**
 * @author PSLPT 147
 *
 */
@Controller
@RequestMapping(path = "/departments") // base path for all
public class DepartmentsController {

	@Autowired
	private DepartmetRepository repository;

	@PostConstruct
	public void init() {
		System.out.println("done");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listDepartments(Map<String, Object> model) {
		Iterable<Department> all = repository.findAll();
		model.put("departments", all);
		return "departments";// view name
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
