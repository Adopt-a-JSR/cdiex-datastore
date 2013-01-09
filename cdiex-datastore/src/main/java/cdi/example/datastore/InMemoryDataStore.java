package cdi.example.datastore;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;

import org.slf4j.Logger;

import cdi.example.datastore.bindings.Repository;
import cdi.example.datastore.bindings.Updated;



@Alternative // A bean can have alternatives. See beans.xml for alternative in use.
@Repository
@ApplicationScoped
public class InMemoryDataStore implements DataStore {

	private ConcurrentMap<String, Boolean> values = new ConcurrentHashMap<String, Boolean>();
	
	@Inject
	private Logger logger;

//	@Inject 
	@Inject @Updated 
	private Event<String> dataStoreUpdatedEvent;

	
	public Boolean getValue(String word) {
		if ( word == null ) 
			return null;
		logger.info("Searching InMemory datastore for [{}]", word);
		Boolean isPalindrome = values.get(word);
		return isPalindrome;
	}

//	@SuppressWarnings("serial")
	public void putValue(String word, boolean palindrome) {
		if ( word != null ) {
			logger.info("Adding [{}]:[{}] to InMemory datastore", word, palindrome);
			values.put(word, palindrome);
//			dataStoreUpdatedEvent.select(new AnnotationLiteral<Updated>(){}).fire(word);		
			dataStoreUpdatedEvent.fire(word);		
		}
	}

} 
