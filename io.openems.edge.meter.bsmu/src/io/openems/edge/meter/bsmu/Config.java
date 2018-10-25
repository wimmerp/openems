package io.openems.edge.meter.bsmu;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;


@ObjectClassDefinition( 
		name = "Meter BSMU", //
		description = "Implements the Battery Stack Management Unit.")
@interface Config {
	String service_pid(); 

	String id() default "ess"; 

	boolean enabled() default true; 

	@AttributeDefinition(name = "Modbus-ID", description = "ID of Modbus brige.")
	String modbus_id(); 
	
	@AttributeDefinition(name = "Modbus Unit-ID", description = "The Unit-ID of the Modbus device.")
	int modbusUnitId() default 5;
	
	@AttributeDefinition(name = "Battery state", description = "Switches the battery into the given state")
	BatteryState batteryState() default BatteryState.ON;
	

	@AttributeDefinition(name = "Modbus target filter", description = "This is auto-generated by 'Modbus-ID'.")
	String Modbus_target() default ""; 

	@AttributeDefinition(name = "Minimum Ever Active Power", description = "This is automatically updated.")
	int minActivePower(); 

	@AttributeDefinition(name = "Maximum Ever Active Power", description = "This is automatically updated.")
	int maxActivePower(); 
	
	@AttributeDefinition(name = "Watchdog", description = "Sets the watchdog timer interval in seconds, 0=disable")
	int watchdoginterval() default 0;

	String webconsole_configurationFactory_nameHint() default "Battery Stack Management Unit [{id}]"; 
}

