package io.github.rostikq.task02;

import io.github.rostikq.logger.EventLogger;

public class LoadVehicleCommand implements WarehouseCommand {
    private final Parcel parcel;
    private final String vehicleId;
    private String previousVehicle;
    private String previousSector;

    public LoadVehicleCommand(Parcel parcel, String vehicleId) {
        this.parcel = parcel;
        this.vehicleId = vehicleId;
    }

    @Override
    public String getName() {
        return "LoadVehicle(" + parcel.getTrackNumber() + " -> " + vehicleId + ")";
    }

    @Override
    public void execute() {
        this.previousVehicle = parcel.getLoadedVehicle();
        this.previousSector = parcel.getSector();
        parcel.setLoadedVehicle(vehicleId);
        parcel.setSector("LOADING_BAY_" + vehicleId);
        EventLogger.log(getClass().getSimpleName(),
                "Виконано: посилку " + parcel.getTrackNumber()
                        + " завантажено в машину '" + vehicleId + "'");
    }

    @Override
    public void undo() {
        parcel.setLoadedVehicle(previousVehicle);
        parcel.setSector(previousSector);
        EventLogger.log(getClass().getSimpleName(),
                "Скасовано завантаження: посилку " + parcel.getTrackNumber()
                        + " вивантажено з '" + vehicleId + "', повернуто у сектор '" + previousSector + "'");
    }
}
