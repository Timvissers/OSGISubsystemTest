package be.aca.service.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import be.aca.service.api.Service;

public class Activator implements BundleActivator {

	private ServiceRegistration<Service> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {
		DefaultService service = new DefaultService();
		serviceRegistration = context.registerService(Service.class, service, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		serviceRegistration.unregister();
	}
}
