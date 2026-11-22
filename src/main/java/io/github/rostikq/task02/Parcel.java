package io.github.rostikq.task02;

public class Parcel {
    private final String trackNumber;
    private String sector;
    private String loadedVehicle;
    private boolean damaged;
    private String damageNote;

    public Parcel(String trackNumber, String sector) {
        this.trackNumber = trackNumber;
        this.sector = sector;
    }

    public String getTrackNumber() { return trackNumber; }
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
    public String getLoadedVehicle() { return loadedVehicle; }
    public void setLoadedVehicle(String loadedVehicle) { this.loadedVehicle = loadedVehicle; }
    public boolean isDamaged() { return damaged; }
    public void setDamaged(boolean damaged) { this.damaged = damaged; }
    public String getDamageNote() { return damageNote; }
    public void setDamageNote(String damageNote) { this.damageNote = damageNote; }

    @Override
    public String toString() {
        return "Parcel{" + trackNumber + ", sector='" + sector + '\''
                + ", vehicle=" + loadedVehicle + ", damaged=" + damaged + '}';
    }
}