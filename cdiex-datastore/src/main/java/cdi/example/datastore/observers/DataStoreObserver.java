package cdi.example.datastore.observers;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import cdi.example.datastore.bindings.Updated;



public class DataStoreObserver {

	@Inject
	private Logger logger;
	
	public void afterDataStoreUpdate(@Observes @Updated String word) { 
		logger.info("Update Observed - Word added to datastore: [{}]", word);
	}

}
