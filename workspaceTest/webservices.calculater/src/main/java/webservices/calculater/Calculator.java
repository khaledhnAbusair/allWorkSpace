package webservices.calculater;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace = "http://www.progressoft.com/ws/calculator")
@SOAPBinding(style = Style.RPC)
public interface Calculator {

	@WebMethod(operationName = "add-request")
	public int add(@WebParam(name = "left") int left, @WebParam(name = "right") int right);

	@WebMethod(operationName = "subtract-request")
	public int subtract(@WebParam(name = "a") int a, @WebParam(name = "b") int b);
}
