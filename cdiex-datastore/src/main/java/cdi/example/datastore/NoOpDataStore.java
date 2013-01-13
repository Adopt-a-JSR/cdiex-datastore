package cdi.example.datastore;

import java.lang.annotation.Annotation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.Bean;
import javax.inject.Inject;

import org.slf4j.Logger;

import cdi.example.datastore.bindings.Repository;


@Alternative // A bean can have alternatives. See beans.xml for alternative in use.
@Repository
@ApplicationScoped
public class NoOpDataStore implements DataStore, MetadataAware {

	@Inject
	private Logger logger;

	@Inject
	private Bean<NoOpDataStore> metadata;
	
	
	public Boolean getValue(String word) {
		logger.info("Searching NoOp datastore for [{}]", word);
		return null;
	}

	public void putValue(String word, boolean palindrome) {
		logger.info("Adding [{}]:[{}] to NoOp datastore", word, palindrome);
	}

	// CDI 1.1 - Allow injection of Bean object for a bean 
	// See: https://issues.jboss.org/browse/CDI-92
	public Class<? extends Annotation> getScope() {
		return metadata.getScope();
	}
	
} 
