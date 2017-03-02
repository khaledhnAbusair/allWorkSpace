/**
 * 
 */
package uploader;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author PSLPT 147
 *
 */
public interface FileUploader {

	public void uploadFile(Path path, String targetFileName, InputStream streamToSave);
}
