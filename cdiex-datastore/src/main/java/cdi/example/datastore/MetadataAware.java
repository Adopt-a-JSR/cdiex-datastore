package cdi.example.datastore;

import java.lang.annotation.Annotation;


// CDI 1.1 - Allow injection of Bean object for a bean 
// See: https://issues.jboss.org/browse/CDI-92
public interface MetadataAware {

	public Class<? extends Annotation> getScope();

}
