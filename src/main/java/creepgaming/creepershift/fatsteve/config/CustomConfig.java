package creepgaming.creepershift.fatsteve.config;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class CustomConfig{

	private Path path;
	private Path actualPath;
	private byte[] defaultText;
	String name;

	public CustomConfig(String pathIn, String nameIn, String defaultTextIn, ICustomConfig i) {
		
		path = FileSystems.getDefault().getPath(pathIn + File.separatorChar + "fatsteve" + File.separatorChar);
		defaultText = defaultTextIn.getBytes();
		name = nameIn;
		init();
		i.readConfig(actualPath);
	}


	

	private void init() {
		try {
			
		if (!path.toFile().exists()) path.toFile().mkdirs();		
		
		actualPath = FileSystems.getDefault().getPath(path.toString(), name);
		
		if(!actualPath.toFile().exists()) Files.write(actualPath, defaultText);		
		
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

	
}
