package com.progressoft.jip.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.progressoft.jip.entities.Department;

@Path("/departments")
public class DepartmentService {

	@PersistenceContext(unitName = "webservices-restful")
	private EntityManager entityManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@SuppressWarnings("unchecked")
	public List<Department> departmentsList() {
		return entityManager.createQuery("select d from Departments as d").getResultList();
	}
}
