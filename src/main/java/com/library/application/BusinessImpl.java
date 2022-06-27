package com.library.application;

public class BusinessImpl {
	
	private DataService dataService;
	
	public BusinessImpl(DataService dataService) {
		super();
		this.dataService = dataService;
	}

	public int findGreatestValue()
	{
		int[] values = dataService.getAllValues();
		int greatest = Integer.MIN_VALUE;
		
		for (int i : values) {
			if(i> greatest)
			{
				greatest = i;
			}
		}
		
		return greatest;
	}

}
