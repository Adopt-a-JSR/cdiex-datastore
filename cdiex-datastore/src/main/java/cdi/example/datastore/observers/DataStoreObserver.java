package cdi.example.datastore.observers;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.slf4j.Logger;

import cdi.example.datastore.bindings.Updated;


@ApplicationScoped
public class DataStoreObserver {

	@Inject
	private Logger logger;
	
	private ConcurrentMap<String, Date> words = new ConcurrentHashMap<String, Date>();
	
	
	public void afterDataStoreUpdate(@Observes @Updated String word) { 
		logger.info("Update Observed - Word added to datastore: [{}]", word);
		words.put(word, new Date());
	}


	public Map<String, Date> getWords() {
		return words;
	}

	public int getNumWords() {
		return words.size();
	}

}
