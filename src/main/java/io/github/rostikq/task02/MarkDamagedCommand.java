package io.github.rostikq.task02;

import io.github.rostikq.logger.EventLogger;

public class MarkDamagedCommand implements WarehouseCommand {
    private final Parcel parcel;
    private final String reason;
    private boolean previousDamaged;
    private String previousNote;

    public MarkDamagedCommand(Parcel parcel, String reason) {
        this.parcel = parcel;
        this.reason = reason;
    }

    @Override
    public String getName() {
        return "MarkDamaged(" + parcel.getTrackNumber() + ")";
    }

    @Override
    public void execute() {
        this.previousDamaged = parcel.isDamaged();
        this.previousNote = parcel.getDamageNote();
        parcel.setDamaged(true);
        parcel.setDamageNote(reason);
        EventLogger.log(getClass().getSimpleName(),
                "Виконано: посилку " + parcel.getTrackNumber()
                        + " позначено як пошкоджену (" + reason + ")");
    }

    @Override
    public void undo() {
        parcel.setDamaged(previousDamaged);
        parcel.setDamageNote(previousNote);
        EventLogger.log(getClass().getSimpleName(),
                "Скасовано: знято позначку пошкодження з посилки " + parcel.getTrackNumber());
    }
}
