package fr.fxjavadevblog.aid.utils.jaxrs.filtering;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FilterOperation
{
	EQUALS("eq","="), 
	LIKE("like","LIKE"), 
	GREATER_THAN("lt",">="), 
	LESSER_THAN("gt","<=");
		
	@Getter
	private final String apiOperation;
	
	@Getter
	private final String hsqlOperation;
	
	
	public FilterOperation getValue()
	{
		return this;
	}	
	
}