package controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import domain.Employee;
import usecases.EmployeeUseCase;

@Controller
@RequestMapping(path = "/employees")
@Scope("request")
public class EmployeeController {

	@Autowired
	private EmployeeUseCase employeeUseCase;

	@ModelAttribute(name = "employees")
	public Iterable<Employee> getEmployees() {
		return employeeUseCase.listAllEmployees();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listEmployees() {
		return "employees";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute @Validated Employee employee, BindingResult bindingResult,
			@RequestPart(name = "picture") MultipartFile picture, Map<String, Object> model,
			UriComponentsBuilder builder, RedirectAttributes redirectAttributes) throws IOException {
		if (bindingResult.hasErrors()) {
			model.put("errors", bindingResult);
			return "employees";
		}
		
		employeeUseCase.addEmployee(employee, picture.getOriginalFilename(), picture.getBytes());
		redirectAttributes.addFlashAttribute("successMessage", "Employee Record added successfully");

		return "redirect:" + builder.path("/employees").build().toUriString();
	}

	@RequestMapping(path = "/image/{employeeId}", method = RequestMethod.GET)
	public void downloadEmployeeImage(@PathVariable(name = "employeeId") Integer employeeId,
			HttpServletResponse response) throws IOException {
		response.setContentType("image/jpg");
		employeeUseCase.copyImage(employeeId, response.getOutputStream());
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("failureError", e);
		return "employees";
	}

}
