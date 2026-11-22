package io.github.rostikq.task01;

import io.github.rostikq.logger.EventLogger;

abstract class ShipmentCheckHandler {
    private ShipmentCheckHandler next;
    protected final String handlerName;

    protected ShipmentCheckHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public ShipmentCheckHandler setNext(ShipmentCheckHandler next) {
        this.next = next;
        return next;
    }

    public final void handle(Shipment shipment) {
        EventLogger.log("ReceptionDesk",
                "Відправлення " + shipment.getId() + " надійшло на перевірку [" + handlerName + "]");
        boolean passed = check(shipment);
        if (!passed) {
            EventLogger.log(handlerName,
                    "ВІДХИЛЕНО відправлення " + shipment.getId() + ". Причина: " + shipment.getRejectionReason());
            return;
        }
        if (next != null) {
            EventLogger.log(handlerName,
                    "Передано відправлення " + shipment.getId() + " далі по ланцюгу");
            next.handle(shipment);
        } else {
            EventLogger.log(handlerName,
                    "ПРИЙНЯТО відправлення " + shipment.getId() + " (усі перевірки пройдено)");
        }
    }

    protected abstract boolean check(Shipment shipment);
}
