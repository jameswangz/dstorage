package com.derbysoft.dstorage.testsbase.jbehave;


import org.jbehave.scenario.Configuration;
import org.jbehave.scenario.MostUsefulConfiguration;
import org.jbehave.scenario.errors.PendingErrorStrategy;
import org.jbehave.scenario.parser.CasePreservingResolver;
import org.jbehave.scenario.parser.ClasspathScenarioDefiner;
import org.jbehave.scenario.parser.PatternScenarioParser;
import org.jbehave.scenario.parser.ScenarioDefiner;
import org.jbehave.scenario.reporters.PrintStreamScenarioReporter;
import org.jbehave.scenario.reporters.ScenarioReporter;

public class Configurations {

	public static Configuration userDefined() {
		return new MostUsefulConfiguration() {
			@Override
			public PendingErrorStrategy forPendingSteps() {
				return PendingErrorStrategy.FAILING;
			}

			@Override
			public ScenarioReporter forReportingScenarios() {
				return new PrintStreamScenarioReporter();
			}
			
			@Override
			public ScenarioDefiner forDefiningScenarios() {
				return new ClasspathScenarioDefiner(
					new CasePreservingResolver(),
					new PatternScenarioParser()
				);
			}
		};
	}

}
