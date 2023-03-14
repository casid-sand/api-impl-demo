package fr.fxjavadevblog.aid.api.genre;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

import org.apache.commons.beanutils.ConvertUtils;

import fr.fxjavadevblog.aid.utils.jaxrs.converters.GenericEnumConverter;
import lombok.extern.slf4j.Slf4j;

/**
 * JAX-RS provider for Genre conversion. This converter is registered because of
 * the Provider annotation.
 * 
 * @author François-Xavier Robin
 */

@Provider
@Slf4j
public class GenreConverterProvider implements ParamConverterProvider
{
    private static final GenericEnumConverter<Genre> converter = GenericEnumConverter.of(Genre.class);

    static
    {
        log.debug("Registering converter provider for Genre (JAX-RS + ConvertUtils)");
        ConvertUtils.register(converter, Genre.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations)
    {    
        return Genre.class.equals(rawType) ? (ParamConverter<T>) converter : null;
    }
}
