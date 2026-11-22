package io.github.rostikq;

import io.github.rostikq.logger.EventLogger;
import io.github.rostikq.task01.*;
import io.github.rostikq.task02.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        runTask1ChainOfResponsibility();
        System.out.println("\n--------------------------------------------------\n");
        runTask2Command();
    }

    public static void runTask1ChainOfResponsibility() {
        EventLogger.log("Runner", "=== ЗАВДАННЯ 1: ПЕРЕВІРКА ВІДПРАВЛЕННЯ (Chain of Responsibility) ===");

        ShipmentCheckHandler internationalChain = new WaybillCheckHandler();
        internationalChain
                .setNext(new ProhibitedItemsCheckHandler())
                .setNext(new WeightAndSizeCheckHandler(30.0, 120))
                .setNext(new InternationalRequirementsHandler());

        Shipment intlOk = new Shipment("PKG-101", ShipmentType.INTERNATIONAL,
                true, false, 8.5, 60, true, false);
        internationalChain.handle(intlOk);

        Shipment heavyPkg = new Shipment("PKG-102", ShipmentType.INTERNATIONAL,
                true, false, 45.0, 50, true, false);
        internationalChain.handle(heavyPkg);


        ShipmentCheckHandler updatedChain = new WaybillCheckHandler();
        updatedChain
                .setNext(new ProhibitedItemsCheckHandler())
                .setNext(new LithiumBatteryCheckHandler())
                .setNext(new WeightAndSizeCheckHandler(30.0, 120))
                .setNext(new InternationalRequirementsHandler());

        Shipment batteryPkg = new Shipment("PKG-103", ShipmentType.INTERNATIONAL,
                true, false, 4.0, 40, true, true);
        updatedChain.handle(batteryPkg);
    }

    public static void runTask2Command() {
        EventLogger.log("Runner", "=== ЗАВДАННЯ 2: ОПЕРАЦІЇ НА СКЛАДІ (Command) ===");

        Parcel p1 = new Parcel("TRK-501", "A1");
        Parcel p2 = new Parcel("TRK-502", "A1");
        Parcel p3 = new Parcel("TRK-503", "B2");

        WarehouseScannerController controller = new WarehouseScannerController();

        controller.enqueueCommand(new MoveSectorCommand(p1, "B1"));
        controller.enqueueCommand(new MarkDamagedCommand(p2, "Розірвана плівка"));
        controller.enqueueCommand(new MoveSectorCommand(p3, "C3"));
        controller.enqueueCommand(new MoveSectorCommand(p1, "D4"));
        controller.enqueueCommand(new LoadVehicleCommand(p1, "VAN-07"));

        controller.processQueue();

        controller.undoLastCommand();
        EventLogger.log("Runner", "Стан посилки після скасування завантаження: " + p1);

        controller.printAuditJournal();
    }
}
