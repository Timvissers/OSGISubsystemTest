package be.aca.service.user.internal;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import be.aca.service.api.Service;

public class Activator implements BundleActivator {

	private ServiceTracker<Service, Service> serviceTracker;
	private ServiceUser serviceUser;

	@Override
	public void start(BundleContext context) throws Exception {
		serviceTracker = new ServiceTracker<>(context, Service.class, null);
		serviceTracker.open();

		serviceUser = new ServiceUser(serviceTracker);
		serviceUser.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		serviceUser.stop();
		serviceTracker.close();
	}
}
