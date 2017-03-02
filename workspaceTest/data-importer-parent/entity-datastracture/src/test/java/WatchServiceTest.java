import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.Test;

public class WatchServiceTest {

	@Test
	public void givenWatchService_WhenTrackFile_ThenReturnedFolder() throws IOException, InterruptedException {
		Path folder = Paths.get("/home/khaled/task");

		WatchService watchService = FileSystems.getDefault().newWatchService();
		folder.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
		while (true) {
			final WatchKey watchKey = watchService.poll();

			for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
				final Kind<?> kind = watchEvent.kind();
				if (StandardWatchEventKinds.OVERFLOW == kind) {
					continue;
				}
			}
		}
	}
}
