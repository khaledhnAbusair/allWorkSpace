package usecases.impl;

import java.io.IOException;
import java.io.OutputStream;

import domain.Attachment;
import domain.Audit;
import domain.Employee;
import domain.repository.AttachmentRepository;
import domain.repository.AuditRepository;
import domain.repository.EmployeeRepository;
import usecases.EmployeeUseCase;

public class EmployeeUseCaseImpl implements EmployeeUseCase {

	private EmployeeRepository employeeRepository;
	private AttachmentRepository attachmentRepository;
	private AuditRepository auditRepository;

	@Override
	public void addEmployee(Employee employee, String attachmentName, byte[] picture) {
		saveEmployeeAddAudit(employee);
		Attachment save = saveAddAttachment(attachmentName, picture);
		employee.setPicature(save);
		employeeRepository.save(employee);
	}

	@Override
	public Iterable<Employee> listAllEmployees() {
		return employeeRepository.findAll();
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void setAttachmentRepository(AttachmentRepository attachmentRepository) {
		this.attachmentRepository = attachmentRepository;
	}

	@Override
	public void copyImage(Integer employeeId, OutputStream outputStream) {

		Employee findOne = employeeRepository.findOne(employeeId);
		if (java.util.Objects.isNull(findOne))
			throw new IllegalStateException("employee not found  " + employeeId);
		Attachment picture = findOne.getPicature();
		if (java.util.Objects.nonNull(picture)) {
			try {
				outputStream.write(picture.getContent());
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	public void setAuditRepository(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}

	private Attachment saveAddAttachment(String attachmentName, byte[] picture) {
		Attachment attachment = new Attachment();
		attachment.setAttachemntName(attachmentName);
		attachment.setContent(picture);
		return attachmentRepository.save(attachment);
	}

	private void saveEmployeeAddAudit(Employee employee) {
		Audit audit = new Audit();

		audit.setOperationName("Adding Employee");
		audit.setOperationDescription(
				"(" + employee.getFirstName() + " )" + " , " + "( " + employee.getPosition() + ") ");
		auditRepository.save(audit);
	}

}
