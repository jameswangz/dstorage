package com.derbysoft.storage.scheduler;

import org.jbehave.scenario.Scenario;

import com.derbysoft.dstorage.testsbase.jbehave.Configurations;


public class EntryPointScenario extends Scenario {

	public EntryPointScenario() {
		super(Configurations.userDefined(), new EntryPointStep());
	}


	
	
	
}
