package fr.fxjavadevblog.aid.global;

import fr.fxjavadevblog.aid.metadata.ApplicationConfig;

/**
 * Constant definitions for RestAssured Tests.
 * 
 * @author François-Xavier Robin
 *
 */

public class TestApplicationConfig {
	
	/**
	 * internal HTTP address launched by Quarkus
	 */
	public static final String  REST_ASSURED_HTTP_BASE = "http://localhost:8081";
	
	/**
	 * full URL to baseApi. Ex: http://localhost:8081/api/v1
	 */
	public static final String  REST_ASSURED_FULL_BASE_URI = REST_ASSURED_HTTP_BASE + ApplicationConfig.API_VERSIONED_BASE_PATH;

}
