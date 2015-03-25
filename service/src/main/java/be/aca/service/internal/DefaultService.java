package be.aca.service.internal;

import be.aca.service.api.Service;

public class DefaultService implements Service {

	@Override
	public String useMe(String arg) {
		return arg.toUpperCase();
	}
}
