/*******************************************************************************
 * OpenEMS - Open Source Energy Management System
 * Copyright (c) 2016 FENECON GmbH and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Contributors:
 *   FENECON GmbH - initial API and implementation and initial documentation
 *******************************************************************************/
package io.openems.api.scheduler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.openems.api.controller.Controller;
import io.openems.api.exception.ConfigException;
import io.openems.api.exception.InjectionException;
import io.openems.api.thing.Thing;
import io.openems.core.databus.Databus;
import io.openems.core.utilities.AbstractWorker;
import io.openems.core.utilities.ControllerFactory;

public abstract class Scheduler extends AbstractWorker implements Thing {
	public final static String THINGID_PREFIX = "_scheduler";
	private static int instanceCounter = 0;
	protected final List<Controller> controllers = new CopyOnWriteArrayList<>();
	protected final Databus databus;

	public Scheduler(Databus databus) {
		super(THINGID_PREFIX + instanceCounter++);
		this.databus = databus;
	}

	public void addController(Controller controller) throws InjectionException, ConfigException {
		ControllerFactory.generateMappings(controller, databus);
		controllers.add(controller);
	}
}