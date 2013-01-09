package cdi.example.utils.producers;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// See: http://lists.jboss.org/pipermail/weld-dev/2010-May/002521.html
public class LoggerProducer {

    @Produces 
    Logger createLogger(InjectionPoint injectionPoint) {
        String name = injectionPoint.getMember().getDeclaringClass().getName(); 
        return LoggerFactory.getLogger(name);
     }


}
