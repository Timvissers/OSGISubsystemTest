package be.aca.service.user.internal;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.osgi.util.tracker.ServiceTracker;

import be.aca.service.api.Service;

public class ServiceUser {

	private final AtomicBoolean running = new AtomicBoolean(false);
	private final AtomicInteger counter = new AtomicInteger(0);
	private final ServiceTracker<Service, Service> serviceTracker;

	public ServiceUser(ServiceTracker<Service, Service> serviceTracker) {
		this.serviceTracker = serviceTracker;
	}

	public void start() {
		System.out.println("ServiceUser starting");
		running.set(true);
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("ServiceUser started");
				try {
					while (running.get()) {
						Service service = serviceTracker.getService();
						if (service != null) {
							String string = "count=" + counter.incrementAndGet();
							String usedString = service.useMe(string);
							System.out.println("service.useMe(\"" + string + "\") => \"" + usedString + "\"");
						} else {
							System.out.println("no service found");
						}
						Thread.sleep(2000);
					}
				} catch (InterruptedException e) {
					System.out.println("Interrupted");
				}
				System.out.println("ServiceUser stopped");
			}
		});
		thread.start();
	}

	public void stop() {
		System.out.println("ServiceUser stopping");
		running.set(false);
	}
}
