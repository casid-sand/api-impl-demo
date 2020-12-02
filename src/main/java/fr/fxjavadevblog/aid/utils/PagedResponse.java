package fr.fxjavadevblog.aid.utils;


import javax.ws.rs.core.Response;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.Builder;
import lombok.Getter;


@Builder
public class PagedResponse <T> 
{
	@Getter
	private PagedMetadata metadata;
	
	@Getter
	private T data;
	
	
	public static Response of(PanacheQuery<?> query)
	{
	  return Response.status(Response.Status.OK)
	   .entity(PagedQueryWrapper.wrap(query))
	   .build();
	}

}
