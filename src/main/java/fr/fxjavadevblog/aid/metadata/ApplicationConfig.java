package fr.fxjavadevblog.aid.metadata;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;



// this definition is merged with resources/META-INF/openapi.yml

@OpenAPIDefinition (
	info = @Info(
			title = ApplicationConfig.APP_NAME, 
			version = ApplicationConfig.APP_VERSION			
			),
	servers = @Server(url = ApplicationConfig.API_VERSIONED_BASE_PATH )
		
)

@ApplicationPath("/api/v1")

public class ApplicationConfig extends Application {
	
	// these constants are used by OpenApi definition and by the response of /health
	
	public static final String PATH_DELIM = "/";

	public static final String APP_NAME = "API for Atari ST Floppy Catalog";
	public static final String APP_VERSION = "0.0.5";
	public static final String API_BASE_PATH = "api";
	public static final String API_VERSION = "v1";
	public static final String API_VERSIONED_BASE_PATH = PATH_DELIM + API_BASE_PATH + PATH_DELIM + API_VERSION;
	
	

}
