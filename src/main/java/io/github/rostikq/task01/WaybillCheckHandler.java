package io.github.rostikq.task01;

import io.github.rostikq.logger.EventLogger;

class WaybillCheckHandler extends ShipmentCheckHandler {
    public WaybillCheckHandler() {
        super("WaybillCheck");
    }

    @Override
    protected boolean check(Shipment shipment) {
        if (!shipment.isWaybillFilled()) {
            shipment.reject("Накладна не заповнена");
            return false;
        }
        EventLogger.log(handlerName, "Накладна для " + shipment.getId() + " коректна");
        return true;
    }
}