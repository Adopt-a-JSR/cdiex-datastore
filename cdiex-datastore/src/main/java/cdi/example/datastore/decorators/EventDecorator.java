package cdi.example.datastore.decorators;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import org.slf4j.Logger;


// CDI 1.1 - Decorating built in beans
// See: https://issues.jboss.org/browse/CDI-164
// Note: Decorator of built-in bean Event must be passivation-capable
@Decorator
@Dependent
public abstract class EventDecorator<T> implements Event<T>, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject @Delegate @Any 
	private Event<T> event;

	@Inject
	private Logger logger;

	private Queue<T> queue = new ConcurrentLinkedQueue<T>();
	
	
	// Note: I believe this might change event ordering. Don't use it blindly! 
	// It's just meant to give a simple example of what can be done by decorating 
	// built-in beans (e.g. event queueing).
	@Override
	public void fire(T arg) {
		queue.add(arg);
		if ( queue.size() >= 3 ) {
			for ( T next = queue.poll(); next != null; next = queue.poll() ) {
				logger.info("Event Decorator firing event for type: [{}]", arg);
				event.fire(next);
			}
		}
	}

}
