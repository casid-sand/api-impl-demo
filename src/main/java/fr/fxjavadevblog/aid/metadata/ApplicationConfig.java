package fr.fxjavadevblog.aid.metadata;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import fr.fxjavadevblog.aid.utils.jaxrs.exceptions.ApiExceptionMapper.ApiError;
import fr.fxjavadevblog.aid.utils.jaxrs.media.SpecificMediaType;



// this definition is merged with resources/META-INF/openapi.yml

@OpenAPIDefinition (
		
	info = @Info(title = "Best Practises API DEMO", version="1.0.0"),	
	servers = @Server(url = ApplicationConfig.API_FULL_PATH ),
	components = @Components(
					responses =	{
						@APIResponse(name = ApplicationConfig.RESPONSE_API_ERROR, 						
							         description = "JSON response in case of error",
							         content=  @Content(mediaType=SpecificMediaType.APPLICATION_PROBLEM_JSON , 
							                            schema = @Schema(ref = ApplicationConfig.SCHEMA_API_ERROR)))
					},
					schemas = {
						@Schema(name = ApplicationConfig.SCHEMA_API_ERROR, 
								implementation = ApiError.class)
					}				
			)
)


@ApplicationPath(ApplicationConfig.API_VERSIONED_PATH)

public class ApplicationConfig extends Application {
	
	// these are used by OpenApi definition and by the response of /health
	
	public static final String ROOT_PATH = "/api-impl-demo";
	
	public static final String API_VERSION = "v1";
	public static final String API_ENDPOINT_BASEPATH = "api";	
	public static final String API_VERSIONED_PATH = "/" + API_VERSION + "/" + API_ENDPOINT_BASEPATH;
	public static final String API_FULL_PATH = ROOT_PATH + API_VERSIONED_PATH;
	
	
	// aliases for OpenApi references via "ref=" annotation attribute.
	public static final String SCHEMA_API_ERROR = "ApiError";
	public static final String RESPONSE_API_ERROR = "ApiErrorResponse";
}
