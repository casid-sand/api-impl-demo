package fr.fxjavadevblog.aid.utils.jaxrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import fr.fxjavadevblog.aid.api.exceptions.ApiException;
import fr.fxjavadevblog.aid.api.videogame.VideoGame;
import fr.fxjavadevblog.aid.utils.TestingGroups;
import fr.fxjavadevblog.aid.utils.jaxrs.filtering.Filtering;

@Tag(TestingGroups.UNIT_TESTING)
class FilteringTest
{
	private MultivaluedMap<String, String> fakeQueryParams = new MultivaluedHashMap<>();
	private UriInfo mockUriInfo = mock(UriInfo.class);
	
	@BeforeEach
	private void init()
	{
    	fakeQueryParams.put("name", List.of("like:xenon"));
    	fakeQueryParams.put("price", List.of("gt:100"));
    	fakeQueryParams.put("version", List.of("lt:500"));	
    	when(mockUriInfo.getQueryParameters()).thenReturn(fakeQueryParams);
	}
	
    
    @DisplayName("Filtering query test")
    @Test
    void testReturnedQuery()
    {
        Filtering f = Filtering.of(VideoGame.class, mockUriInfo);
        assertEquals(true,f.isFilterPresent());        
        assertEquals("price <= :price AND name LIKE :name AND version >= :version", f.getQuery());
    }
    
    @DisplayName("Filtering exception test")
    @Test
    void testExceptionParsingFilters()
    {  	
    	fakeQueryParams.put("machine", List.of("gt:100")); // machine does not exist, an exception should be raised
    	// should throw fr.fxjavadevblog.aid.api.exceptions.ApiException: No such field: machine
        
        assertThrows(ApiException.class, () -> { Filtering.of(VideoGame.class, mockUriInfo);}, "No such field: machine" );       
    }
    
}
