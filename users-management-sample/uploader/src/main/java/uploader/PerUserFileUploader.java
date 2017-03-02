/**
 * 
 */
package uploader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import login.SecurityContext;
import login.UserInfo;

/**
 * @author PSLPT 147
 *
 */
public class PerUserFileUploader implements FileUploader {

	@Override
	public void uploadFile(Path path, String targetFileName, InputStream streamToSave) {
		// I will be always called after LoginFilter is invoked
		SecurityContext context = SecurityContext.getCurrentContext();
		UserInfo userInfo = context.getUserInfo();
		String username = userInfo.getUsername();
		Path targetDir = path.resolve(username);
		Path targetPath = targetDir.resolve(targetFileName);

		try {
			Files.createDirectories(targetDir);
			Files.copy(streamToSave, targetPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IllegalStateException("Unable to write file", e);
		}
	}

}
