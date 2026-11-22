package io.github.rostikq.task01;

import io.github.rostikq.logger.EventLogger;

class ProhibitedItemsCheckHandler extends ShipmentCheckHandler {
    public ProhibitedItemsCheckHandler() {
        super("ProhibitedItemsCheck");
    }

    @Override
    protected boolean check(Shipment shipment) {
        if (shipment.isHasProhibitedItems()) {
            shipment.reject("Виявлено заборонені предмети");
            return false;
        }
        EventLogger.log(handlerName, "Заборонених предметів у " + shipment.getId() + " немає");
        return true;
    }
}
