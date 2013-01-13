package cdi.example.datastore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import cdi.example.datastore.bindings.Repository;
import cdi.example.datastore.decorators.EventDecorator;
import cdi.example.datastore.observers.DataStoreObserver;
import cdi.example.utils.producers.LoggerProducer;


@RunWith(Arquillian.class)
public class NoOpMetadatAwareDataStoreTest {

	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addPackage(DataStore.class.getPackage())
				.addPackage(Repository.class.getPackage())
				.addPackage(EventDecorator.class.getPackage())
				.addPackage(DataStoreObserver.class.getPackage())
				.addPackage(LoggerProducer.class.getPackage())
				.addAsManifestResource(
						new StringAsset("<beans> <alternatives><class priority='100'>cdi.example.datastore.NoOpDataStore</class></alternatives> </beans>"), 
						"beans.xml");
	}
	
	@Inject @Repository
	public DataStore noOp;
	
	
	@Test
	public void testPutGetTrueValue() {
		noOp.putValue("fdf", Boolean.TRUE);
		Assert.assertNull(noOp.getValue("fdf"));
	}

	@Test
	public void testGetNull() {
		Assert.assertNull(noOp.getValue(null));
	}

	@Test
	public void testGetScope() {
		Assert.assertEquals(((NoOpDataStore)noOp).getScope(), ApplicationScoped.class);
	}
	
}
