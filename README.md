# Our prototype

## Bundles

### service

Defines a service

	Service#useMe(String)

and an implementation

	DefaultService

that capitalizes the passed-in string.

This DefaultService is registered using an Activator (i.e. no Blueprint here).

### service-user

Uses a ServiceTracker on the Service, calls it every two seconds and prints.

## Subsystems

* service-subsystem contains service
* service-user-subsystem contains service-user and depends on service-subsystem

## runner

This is a minimal runner that has aries support, gogo shell (also the subsystem extension) and logging.

To use it, run

	pax:provision

Use the shell to install subsystems. E.g.

	subsystem:install file:///D:/workspace/osgi-subsystem-support/service-subsystem/target/service-subsystem-0.0.1-SNAPSHOT.esa
	subsystem:install file:///D:/workspace/osgi-subsystem-support/service-user-subsystem/target/service-user-subsystem-0.0.1-SNAPSHOT.esa
	subsystem:list
	subsystem:start 1
	subsystem:start 2

# Stuff that works

## Esa's referencing other esa's

If you first install service-subsystem and then service-user-subsystem, then everything works fine.
We manually removed the inner esa file from service-user-subsystem.

# Stuff that doesn't work

## Inner esa's

**tag=inneresa**

When we build a service-user-subsystem that contains service-subsystem (as esa) and deploy this, then we get:

	SubsystemException: org.osgi.service.resolver.ResolutionException:
	  Unable to resolve /D:/isabel/WORKSP~1/tmp/inputStreamExtract9189435371719284552.zip/service-user-0.0.1-SNAPSHOT.jar:
	  missing requirement org.apache.aries.subsystem.core.archive.ImportPackageRequirement:
	  namespace=osgi.wiring.package, attributes={}, directives={filter=(&(osgi.wiring.package=be.aca.service.api)(&(version>=0.0.0)(!(version>=1.0.0))))},
	  resource=/D:/isabel/WORKSP~1/tmp/inputStreamExtract9189435371719284552.zip/service-user-0.0.1-SNAPSHOT.jar

When, on the other hand, we first deploy service-subsystem, then it does work.