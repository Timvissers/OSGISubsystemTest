# How to build

	mvn clean install

# How to run

There is a minimal runner that has aries support, gogo shell (also the subsystem extension) and logging.

To use it, in the runner directory, run

	mvn pax:provision

Use the shell to install subsystems. E.g.

	subsystem:install file:../../service-user-subsystem/target/service-user-subsystem-0.0.1-SNAPSHOT.esa

# Project Setup

There are 2 bundles:

* service: exports be.aca.service.api and an implementation.
* service-user: imports be.aca.service.api and uses the service every 2 seconds.

There are 2 subsystems:

* service-subsystem: includes the service bundle.
* service-user-subsystem: include the service-user bundle and the service-subsystem. Its structure is
	
		-- service-user-subsystem
		   -- service-subsystem
		      -- service bundle
		   -- service-user bundle
		
