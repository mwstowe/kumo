/**
 *  Kumojs Device
 *
 *  Copyright 2019 Michael Stowe
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 */
metadata {
	definition (name: "Kumojs", namespace: "mwstowe", author: "Michael Stowe", cstHandler: true) {
        capability "Actuator"
        capability "Polling"
        capability "Temperature Measurement"
		capability "Thermostat Cooling Setpoint"
		capability "Thermostat Fan Mode"
		capability "Thermostat Heating Setpoint"
		capability "Thermostat Mode"
        capability "Fan Speed"
        capability "Filter Status"
        capability "Refresh"
        command "fanOnly"
        command "fanQuiet"
        command "fanLow"
        command "fanPowerful"
        command "fanSuperPowerful"
        command "fanAuto"
	}

    simulator {
		// TODO: define status and reply messages here
	}

    tiles(scale: 2) {
		multiAttributeTile(name:"thermostatMulti", type:"thermostat", width:6, height:4, canChangeIcon: true) {
			tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
				attributeState("temp", label:'${currentValue}')
			}
            tileAttribute("device.thermostatSetpoint", key: "VALUE_CONTROL") {
				attributeState("VALUE_UP", action: "increaseSetpoint")
                attributeState("VALUE_DOWN", action: "decreaseSetpoint")
			}
			tileAttribute("device.thermostatOperatingState", key: "OPERATING_STATE") {
				attributeState("idle", backgroundColor:"#44b621")
				attributeState("heating", backgroundColor:"#ffa81e")
                attributeState("cooling", backgroundColor:"#33ccff")
			}
			tileAttribute("device.thermostatMode", key: "THERMOSTAT_MODE") {
				attributeState("off", label:'${name}')
				attributeState("heat", label:'${name}')
				attributeState("eco", label:'${name}')
                attributeState("heat", label:'${name}')
                attributeState("cool", label:'${name}')
                attributeState("auto", label:'${name}')
			}
            tileAttribute("device.heatingSetpoint", key: "HEATING_SETPOINT") {
                attributeState("heatingSetpoint", label:'${currentValue}', unit:"F", defaultState: true)
            }
           tileAttribute("device.coolingSetpoint", key: "COOLING_SETPOINT") {
                attributeState("coolingSetpoint", label:'${currentValue}', unit:"F", defaultState: true)
            }
		}
        standardTile("mode", "device.thermostatMode", height: 1, width: 1, decoration: "flat") {
            state "default", label: "Mode:"
        }
        standardTile("modeHeat", "device.thermostatMode", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "st.thermostat.heat", action: "heat"
            state "heat", icon: "st.thermostat.heat", backgroundColor: "#ffa81e"
        }
        standardTile("modeCool", "device.thermostatMode", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "st.thermostat.cool", action: "cool"
            state "cool", icon: "st.thermostat.cool", backgroundColor: "#269bd2"
        }
        standardTile("modeOff", "device.thermostatMode", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "st.thermostat.heating-cooling-off", action: "off"
            state "off", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#efefef"
        }
        standardTile("modeAuto", "device.thermostatMode", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "st.thermostat.auto", action: "auto"
            state "auto", icon: "st.thermostat.auto", backgroundColor: "#44b621"
        }
        standardTile("modeFanOnly", "device.thermostatMode", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/fan-only@2x.png", action: "fanOnly"
            state "eco", icon: "http://www.michaelstowe.com/kumo/fan-only@2x.png", backgroundColor: "#efefef"
        }
        standardTile("fan_speed", "device.fanSpeed", height: 1, width: 1, decoration: "flat") {
            state "default", label: "Fan Speed:"
        }
        standardTile("fan-1", "device.fanSpeed", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/fan-1@2x.png", action: "fanQuiet"
            state "1", icon: "http://www.michaelstowe.com/kumo/fan-1@2x.png", backgroundColor: "#efefef"        
        }
        standardTile("fan-2", "device.fanSpeed", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/fan-2@2x.png", action: "fanLow"
            state "2", icon: "http://www.michaelstowe.com/kumo/fan-2@2x.png", backgroundColor: "#efefef"        
        }
        standardTile("fan-3", "device.fanSpeed", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/fan-3@2x.png", action: "fanPowerful"
            state "3", icon: "http://www.michaelstowe.com/kumo/fan-3@2x.png", backgroundColor: "#efefef"        
        }
        standardTile("fan-4", "device.fanSpeed", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/fan-4@2x.png", action: "fanSuperPowerful"
            state "4", icon: "http://www.michaelstowe.com/kumo/fan-4@2x.png", backgroundColor: "#efefef"        
        }
       standardTile("fan-0", "device.fanSpeed", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "st.thermostat.fan-auto", action: "fanAuto"
            state "0", icon: "st.thermostat.fan-auto", backgroundColor: "#efefef"        
        }
        standardTile("vent-angle", "device.thermostatMode", height: 1, width: 1, decoration: "flat") {
            state "default", label: "Vent:"
        }
        standardTile("vent-1", "state.Vent", height: 1, width: 1, decoration: "flat") {
            state "midpoint", icon: "http://www.michaelstowe.com/kumo/vent-middle@2x.png", action: "vent-middle"
            state "1", icon: "http://www.michaelstowe.com/kumo/vent-middle@2x.png", backgroundColor: "#efefef"        
        }
        standardTile("vent-2", "state.Vent", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/vent-low@2x.png", action: "vent-low"
            state "2", icon: "http://www.michaelstowe.com/kumo/vent-low@2x.png", backgroundColor: "#efefef"        
        }
        standardTile("vent-3", "state.Vent", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/vent-high@2x.png", action: "vent-high"
            state "3", icon: "http://www.michaelstowe.com/kumo/vent-high@2x.png", backgroundColor: "#efefef"        
        }
        standardTile("vent-4", "state.Vent", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/vent-swing@2x.png", action: "vent-swing"
            state "4", icon: "http://www.michaelstowe.com/kumo/vent-swing@2x.png", backgroundColor: "#efefef"        
        }
       standardTile("vent-0", "state.Vent", height: 1, width: 1, decoration: "flat") {
            state "default", icon: "http://www.michaelstowe.com/kumo/vent-auto@2x.png", action: "vent-auto"
            state "0", icon: "http://www.michaelstowe.com/kumo/vent-auto@2x.png", backgroundColor: "#efefef"        
        }
}


}


// parse events into attributes
def parse(String description) {

	log.debug "Parsing '${description}'"
    
    def status = new groovy.json.JsonSlurper().parseText(description)
    
    log.info status.r.indoorUnit.status.roomTemp
    
    def current = Math.round(celsiusToFahrenheit(status.r.indoorUnit.status.roomTemp))
    log.info "Current room temperature: " + current
    
    sendEvent(name: "temperature", value: current, unit: "F")
    
    def mode = status.r.indoorUnit.status.mode
    switch (mode) {
      case "dry":
      case "vent":
         mode = "eco"
         break
      case "autoCool":
         mode = "auto"
         }
         
    sendEvent(name: "thermostatMode", value: mode)
    
    log.info "Thermostat Mode: " + status.r.indoorUnit.status.mode
    
    def spCoolF = Math.round(celsiusToFahrenheit(status.r.indoorUnit.status.spCool))
    
    sendEvent(name: "coolingSetpoint", value: spCoolF, unit: "F")
    
    def spHeatF = Math.round(celsiusToFahrenheit(status.r.indoorUnit.status.spHeat))
    
    sendEvent(name: "heatingSetpoint", value: spHeatF, unit: "F")
    
    if (mode == "heat") {
       sendEvent(name: "thermostatSetpoint", value: spHeatF, unit: "F")
    } else {
       sendEvent(name: "thermostatSetpoint", value: spCoolF, unit: "F")
    }
    
    def thermoState = "idle"
    switch (mode) {
      case "heat":
         if (spHeatF > current) {
         	thermoState = "heating"
         } else {
            thermoState = "idle"
         }
         break
      case "cool":
         if (spCoolF < current) {
            thermoState = "cooling"
         } else {
            thermoState = "idle"
         }
         break
      case "eco":
         thermoState = "fan only"
         }
         
     log.info "ThermoStatOperatingState: " + thermoState
     sendEvent(name: "thermostatOperatingState", value: thermoState)
     
     def fanNumeric = 0
     switch (status.r.indoorUnit.status.fanSpeed) {
        case "quiet":
           fanNumeric = 1
           break
        case "low":
           fanNumeric = 2
           break
        case "powerful":
           fanNumeric = 3
           break
        case "superPowerful":
           fanNumeric = 4
           break
        case "auto":
           fanNumeric = 0
         }
     sendEvent(name: "fanSpeed", value: fanNumeric)
     
     if (fanNumeric == 0 ) {
       sendEvent(name: "thermostatFanMode", value: "auto")
     } else {
       sendEvent(name: "thermostatFanMode", value: "on")
     }
    
    state.Vent = "swing"
    state.Vent = status.r.indoorUnit.status.vaneDir
    
    log.debug "state.Vent: " + state.Vent
	// TODO: handle 'temperature' attribute
	// TODO: handle 'coolingSetpoint' attribute
	// TODO: handle 'thermostatFanMode' attribute
	// TODO: handle 'supportedThermostatFanModes' attribute
	// TODO: handle 'heatingSetpoint' attribute
	// TODO: handle 'thermostatMode' attribute
	// TODO: handle 'supportedThermostatModes' attribute

}

// handle commands
def fanQuiet() {
	log.debug "Executing 'fanQuiet'"
    parent.setFanSpeed(device,'quiet')
    pollDelay()
}

def fanLow() {
	log.debug "Executing 'fanLow'"
    parent.setFanSpeed(device,'low')
    pollDelay()
}

def fanPowerful() {
	log.debug "Executing 'fanPowerful'"
    parent.setFanSpeed(device,'powerful')
    pollDelay()
}

def fanSuperPowerful() {
	log.debug "Executing 'fanSuperPowerful'"
    parent.setFanSpeed(device,'superPowerful')
    pollDelay()
}

def fanAuto() {
	log.debug "Executing 'fanAuto'"
    parent.setFanSpeed(device,'auto')
    pollDelay()
}

def setCoolingSetpoint() {
	log.debug "Executing 'setCoolingSetpoint'"
	// TODO: handle 'setCoolingSetpoint' command
}

def fanOn() {
	log.debug "Executing 'fanOn'"
	// TODO: handle 'fanOn' command
}

def fanCirculate() {
	log.debug "Executing 'fanCirculate'"
	// TODO: handle 'fanCirculate' command
}

def setThermostatFanMode() {
	log.debug "Executing 'setThermostatFanMode'"
	// TODO: handle 'setThermostatFanMode' command
}

def setHeatingSetpoint() {
	log.debug "Executing 'setHeatingSetpoint'"
	// TODO: handle 'setHeatingSetpoint' command
}

def off() {
	log.debug "Executing 'off'"
	// TODO: handle 'off' command
    parent.set_mode(device,"off")
    pollDelay()
}

def heat() {
	log.debug "Executing 'heat'"
	// TODO: handle 'heat' command
    parent.set_mode(device,"heat")
    pollDelay()
}

def emergencyHeat() {
	log.debug "Executing 'emergencyHeat'"
	// TODO: this shouldn't be called
    parent.set_mode(device,"heat")
    pollDelay()
}

def cool() {
	log.debug "Executing 'cool'"
	// TODO: handle 'cool' command
    parent.set_mode(device,"cool")
    pollDelay()
}

def auto() {
	log.debug "Executing 'auto'"
	// TODO: handle 'auto' command
    parent.set_mode(device,"auto")
    pollDelay()
}

def fanOnly() {
	log.debug "Executing 'fanOnly'"
	// TODO: handle 'eco' command
    parent.set_mode(device,"vent")
    pollDelay()
}

def setThermostatMode() {
	log.debug "Executing 'setThermostatMode'"
	// TODO: handle 'setThermostatMode' command
}

def poll() {
    return pollCalled()
}

def pollCalled() {
	log.info "get_status:" + device
    log.info "device network id:" + device.getDeviceNetworkId()
	def status = parent.get_status(device)
    log.info status
    parse(status.toString())
}

def pollDelay() {
    runIn(4, pollCalled)
}

def refresh() {
    poll()
}
