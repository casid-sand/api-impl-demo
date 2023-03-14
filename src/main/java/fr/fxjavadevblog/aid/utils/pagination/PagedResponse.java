package fr.fxjavadevblog.aid.utils.pagination;


import java.util.List;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.fxjavadevblog.aid.utils.jaxrs.fields.FieldSet;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PagedResponse <T> 
{
	@Getter
	@Schema(description = "contains the pagination metadata of the query")
	private Metadata metadata;
	
	@Getter
	@Schema(description = "contains the resource collection of the query")
	private List<T> data;
	
	@JsonIgnore
	private PanacheQuery<T> query;
	
	@JsonIgnore
	private String fieldSetExpression;
	
	public static <T> PagedResponse<T> build(PanacheQuery<T> query, String fieldSetExpression)
	{
		PagedResponse<T> pagedResponse = new PagedResponse<>();
		pagedResponse.query = query;
		pagedResponse.fieldSetExpression = fieldSetExpression;
		return pagedResponse;
	}
	
	public static <T> PagedResponse<T> build(PanacheQuery<T> query)
	{
		return build(query,null);
	}
	
	@JsonIgnore
	public Response getResponse()
	{
	  this.wrap(query);
	  return Response.status(Response.Status.PARTIAL_CONTENT)
	   .header("Resource-Count", this.getMetadata().getResourceCount())
	   .entity(FieldSet.getJson(fieldSetExpression, this))
	   .build();
	}
	
	private void wrap(PanacheQuery<T> query) 
	{	
		this.metadata = Metadata.builder()
		  .resourceCount(query.count())
		  .pageCount(query.pageCount())
		  .currentPage(query.page().index)
		  .build();		
    	  this.data = query.list();
	}

}


