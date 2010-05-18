package com.derbysoft.storage.scheduler;

import static org.jbehave.Ensure.ensureThat;

import java.util.Collection;

import org.jbehave.scenario.annotations.AfterStory;
import org.jbehave.scenario.annotations.BeforeStory;
import org.jbehave.scenario.annotations.Given;
import org.jbehave.scenario.annotations.Then;
import org.jbehave.scenario.annotations.When;
import org.jbehave.scenario.steps.Steps;

import com.derbysoft.jazure.sdk.JAzure;
import com.derbysoft.jazure.sdk.aggregator.Aggregator;
import com.derbysoft.jazure.sdk.aggregator.Aggregators;
import com.derbysoft.jazure.sdk.core.Console;
import com.derbysoft.jazure.sdk.core.EnterpriseSide;
import com.derbysoft.jazure.sdk.core.Module;
import com.derbysoft.jazure.sdk.core.ProjectConfiguration;
import com.derbysoft.jazure.sdk.endpoint.vm.VmQueueStorageEndpoint;
import com.derbysoft.jazure.sdk.job.polling.PollingJobConfig;
import com.derbysoft.jazure.sdk.loader.AbstractPollingLoader;
import com.derbysoft.jazure.sdk.loader.PollingLoader;
import com.derbysoft.jazure.sdk.schedule.SimpleRepeatTriggers;
import com.derbysoft.jazure.sdk.schedule.Trigger;
import com.derbysoft.jazure.sdk.task.Task;
import com.derbysoft.jazure.sdk.task.storage.MemoryTaskStorage;
import com.google.inject.internal.Lists;

public class EntryPointStep extends Steps {

	private EnterpriseSide es;
	private PollingJobConfig jobConfig;
	
	@BeforeStory
	public void beforeStory() {
		es = JAzure.createEnterprise(new Module() {
			@Override
			public void configure(Console console) {
				console.configProject(ProjectConfiguration.named("storage-scheduler"))
					   .storeTaskIn(new MemoryTaskStorage())
					   .connectBy(new VmQueueStorageEndpoint());
			}
		});
	}
	
	@AfterStory
	public void afterStory() {
		es.getConsole().stop();
	}
	
	@Given("a job configuration")
	public void givenJobConfiguration() {
		jobConfig = jobConfig();
		es.getConsole().addPollingJobConfig(jobConfig).loadAt(loader()).aggregateWith(aggregator());
		es.getConsole().rebuildJobs();
		es.getConsole().start();
	}

	private PollingJobConfig jobConfig() {
		return new PollingJobConfig() {
			private static final long serialVersionUID = 156270099746221204L;
			@Override
			public String getId() {
				return "Fetcher";
			}
			
			@Override
			public Trigger getTrigger() {
				return SimpleRepeatTriggers.create(100L);
			}
		};
	}

	private PollingLoader loader() {
		return new AbstractPollingLoader() {
			@Override
			public Collection<Task> createTasks() {
				return Lists.newArrayList();
			}
		};
	}
	
	private Aggregator aggregator() {
		return Aggregators.emptyAggregator();
	}

	@When("trigger triggered")
	public void triggerTriggered() {
		try {
			Thread.sleep(200L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	@Then("job will run")
	public void jobWillRun() {
		ensureThat(es.getConsole().getJob(jobConfig).isRunning());
	}

}
