/**
 * 
 */
package uploader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * @author PSLPT 147
 *
 */
@WebServlet(urlPatterns = { "/uploader" })
@MultipartConfig
public class FileUploaderServlet extends HttpServlet {
	private FileUploader fileUploader;
	private Path rootPath;

	@Override
	public void init() throws ServletException {
		fileUploader = new PerUserFileUploader();
		String path = getServletContext().getInitParameter("path.to.upload");
		if (path == null) {
			throw new ServletException("expected context-param path.to.upload");
		}
		rootPath = Paths.get(path);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("pageContent", "/WEB-INF/views/uploader.jsp");
		req.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part fileToUpload = req.getPart("uploaded");
		try (InputStream inputStream = fileToUpload.getInputStream()) {
			fileUploader.uploadFile(rootPath, fileToUpload.getSubmittedFileName(), inputStream);
		}
		resp.sendRedirect(req.getContextPath() + "/uploader");
	}
}
