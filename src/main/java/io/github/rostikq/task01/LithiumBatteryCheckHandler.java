package io.github.rostikq.task01;

import io.github.rostikq.logger.EventLogger;

public class LithiumBatteryCheckHandler extends ShipmentCheckHandler {
    public LithiumBatteryCheckHandler() {
        super("LithiumBatteryCheck");
    }

    @Override
    protected boolean check(Shipment shipment) {
        if (shipment.isHasLithiumBattery()) {
            EventLogger.log(handlerName,
                    "Виявлено літієву батарею у " + shipment.getId()
                            + " — застосовано спецмаркування UN3481");
        } else {
            EventLogger.log(handlerName,
                    "Літієвих батарей у " + shipment.getId() + " не виявлено");
        }
        return true;
    }
}