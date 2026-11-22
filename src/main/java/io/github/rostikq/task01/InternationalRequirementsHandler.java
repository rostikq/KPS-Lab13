package io.github.rostikq.task01;

import io.github.rostikq.logger.EventLogger;

class InternationalRequirementsHandler extends ShipmentCheckHandler {
    public InternationalRequirementsHandler() {
        super("InternationalCheck");
    }

    @Override
    protected boolean check(Shipment shipment) {
        if (shipment.getType() == ShipmentType.INTERNATIONAL && !shipment.isHasCustomsDocs()) {
            shipment.reject("Відсутні митні документи для міжнародного відправлення");
            return false;
        }
        EventLogger.log(handlerName, "Міжнародні вимоги для " + shipment.getId() + " виконано");
        return true;
    }
}