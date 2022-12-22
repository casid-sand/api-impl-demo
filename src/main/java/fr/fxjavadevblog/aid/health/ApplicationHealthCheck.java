package fr.fxjavadevblog.aid.health;

import static org.apache.commons.lang3.time.DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT;

import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang3.time.StopWatch;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import fr.fxjavadevblog.aid.metadata.ApplicationConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * Simple Health Check.
 * 
 * @author François-Xavier Robin
 *
 */

@ApplicationScoped
@Slf4j
public class ApplicationHealthCheck 
{
	private static StopWatch chrono = StopWatch.createStarted();

	static 
	{
		Properties parameters = new Properties();
		try 
		{
			parameters.load(ApplicationHealthCheck.class.getResourceAsStream("/health.properties"));
			artifactId = parameters.getProperty("health.artifactId");
			version = parameters.getProperty("health.version");
			title = parameters.getProperty("health.title");
		} 
		catch (IOException e) 
		{
			log.error("Impossible de lire le fichier health.properties");
		}
	}

	static String artifactId;
	static String version;
	static String title;

	@Liveness
	public HealthCheck firstApiCheck() 
	{
		return () -> HealthCheckResponse.named(artifactId).up().withData("app_name", artifactId)
				.withData("app_title", title).withData("app_version", version)
				.withData("api_version", ApplicationConfig.API_VERSION)
				.withData("api_full_path", ApplicationConfig.API_FULL_PATH)
				.withData("started_at", ISO_8601_EXTENDED_DATETIME_FORMAT.format(chrono.getStartTime()))
				.withData("uptime", chrono.toString()).build();
	}

}
