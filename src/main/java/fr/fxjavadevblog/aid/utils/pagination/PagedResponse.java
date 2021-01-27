package fr.fxjavadevblog.aid.utils.pagination;


import javax.ws.rs.core.Response;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponse <T> 
{
	@Getter
	private PagedMetadata metadata;
	
	@Getter
	private T data;
	
	
	public static Response of(PanacheQuery<?> query)
	{
		
	  PagedResponse<?> pagedResponse =	PagedQueryWrapper.wrap(query);
	  return Response.status(Response.Status.PARTIAL_CONTENT)
	   .header("Resource-Count", pagedResponse.getMetadata().getResourceCount())
	   .entity(pagedResponse)
	   .build();
	}

}
