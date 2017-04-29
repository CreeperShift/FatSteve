package creepgaming.creepershift.fatsteve.config;

import java.nio.file.Path;

@FunctionalInterface
public interface ICustomConfig {

	public void readConfig(Path path);
	
}
