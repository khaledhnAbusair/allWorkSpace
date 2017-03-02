/**
 * 
 */
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

/**
 * @author PSLPT 147
 *
 */
public class EmployeeUseCaseImpl implements EmployeeUseCase {

	private EmployeeRepository employeeRepository;

	private AttachmentRepository attachmentRepository;

	private AuditRepository auditRepository;

	@Override
	public void addEmployee(Employee employee, String attachmentName, byte[] picture) {
		saveEmployeeAddAudit(employee, attachmentName);

		Attachment attachment = saveAttachement(attachmentName, picture);
		employee.setPicture(attachment);

		employeeRepository.save(employee);

	}

	private Attachment saveAttachement(String attachmentName, byte[] picture) {
		Attachment attachment = new Attachment();
		attachment.setAttachmentName(attachmentName);
		attachment.setContent(picture);

		attachment = attachmentRepository.save(attachment);
		return attachment;
	}

	private void saveEmployeeAddAudit(Employee employee, String attachmentName) {
		Audit audit = new Audit();
		audit.setOperationName("adding employee");
		audit.setOperationDescription(employee.getFirstName() + "," + employee.getLastName() + "," + attachmentName);
		auditRepository.save(audit);
	}

	@Override
	public void copyImage(Integer employeeId, OutputStream os) {
		Employee found = employeeRepository.findOne(employeeId);
		if (found == null) {
			throw new IllegalStateException("employee not found: " + employeeId);
		}
		Attachment picture = found.getPicture();
		if (picture != null) {
			try {
				os.write(picture.getContent());
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
	}

	@Override
	public Iterable<Employee> listAllEmployees() {
		return employeeRepository.findAll();
	}

	public void setAuditRepository(AuditRepository auditRepository) {
		this.auditRepository = auditRepository;
	}

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void setAttachmentRepository(AttachmentRepository attachmentRepository) {
		this.attachmentRepository = attachmentRepository;
	}
}
