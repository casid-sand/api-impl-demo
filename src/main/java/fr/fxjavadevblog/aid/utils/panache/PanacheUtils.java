package fr.fxjavadevblog.aid.utils.panache;

import fr.fxjavadevblog.aid.utils.jaxrs.filtering.Filtering;
import fr.fxjavadevblog.aid.utils.jaxrs.pagination.Pagination;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PanacheUtils
{
	public static <T> PanacheQuery<T> buildPanacheQuery(PanacheRepositoryBase<T, String> repository, Sort sort, Filtering filtering)
	{
		PanacheQuery<T> query;

		if (!filtering.isFilterPresent())
		{
			query = repository.findAll(sort);
		}
		else
		{
			String hqlQueryString = filtering.getQuery();
			log.info("query string : {}", hqlQueryString);
			query = repository.find(hqlQueryString, sort, filtering.getParameterMap());
		}
		return query;
	}
	
	public static <T> PanacheQuery<T> applyPagination (PanacheQuery<T> query, Pagination pagination)
	{
		return query.page(pagination.getPage(), pagination.getSize());
	}
}
