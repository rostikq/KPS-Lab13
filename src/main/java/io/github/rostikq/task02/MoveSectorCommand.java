package io.github.rostikq.task02;

import io.github.rostikq.logger.EventLogger;

public class MoveSectorCommand implements WarehouseCommand {
    private final Parcel parcel;
    private final String targetSector;
    private String previousSector;

    public MoveSectorCommand(Parcel parcel, String targetSector) {
        this.parcel = parcel;
        this.targetSector = targetSector;
    }

    @Override
    public String getName() {
        return "MoveSector(" + parcel.getTrackNumber() + " -> " + targetSector + ")";
    }

    @Override
    public void execute() {
        this.previousSector = parcel.getSector();
        parcel.setSector(targetSector);
        EventLogger.log(getClass().getSimpleName(),
                "Виконано: посилку " + parcel.getTrackNumber()
                        + " переміщено з сектора '" + previousSector + "' у '" + targetSector + "'");
    }

    @Override
    public void undo() {
        parcel.setSector(previousSector);
        EventLogger.log(getClass().getSimpleName(),
                "Скасовано: посилку " + parcel.getTrackNumber()
                        + " повернуто у сектор '" + previousSector + "'");
    }
}
