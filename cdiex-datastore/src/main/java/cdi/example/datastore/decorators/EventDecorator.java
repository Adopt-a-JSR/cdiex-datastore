package cdi.example.datastore.decorators;

import java.io.Serializable;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.slf4j.Logger;


//CDI 1.1 - Decorating built in beans
//See: https://issues.jboss.org/browse/CDI-164
// Decorator of built-in bean Event must be passivation-capable
@Decorator
@Dependent
public abstract class EventDecorator<T> implements Event<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject @Delegate @Any 
	private Event<T> event;

	@Inject
	private Logger logger;

	
	@Override
	public void fire(T arg) {
		logger.info("Event Decorator firing event for type: [{}]", arg);
		event.fire(arg);
	}
	
}
