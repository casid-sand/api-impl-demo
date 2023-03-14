package fr.fxjavadevblog.aid.utils.commons;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnumUtils
{
	public static <E extends Enum<E>,K,V> Map<K,V> convertToMap(Class<E> enumClass,
																Function <? super E, ? extends K> keyMapper,
																Function <? super E, ? extends V> valueMapper)
	{
		return Set.of(enumClass.getEnumConstants())
	              .stream()
			   	  .collect(Collectors.toMap(keyMapper,valueMapper));
	}
}
