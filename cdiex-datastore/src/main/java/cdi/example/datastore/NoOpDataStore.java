package cdi.example.datastore;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.slf4j.Logger;

import cdi.example.datastore.bindings.Repository;


@Alternative // A bean can have alternatives. See beans.xml for alternative in use.
@Repository
@ApplicationScoped
public class NoOpDataStore implements DataStore {

	@Inject
	private Logger logger;

	public Boolean getValue(String word) {
		logger.info("Searching NoOp datastore for [{}]", word);
		return null;
	}

	public void putValue(String word, boolean palindrome) {
		logger.info("Adding [{}]:[{}] to NoOp datastore", word, palindrome);
	}

} 
