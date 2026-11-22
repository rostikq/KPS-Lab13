package io.github.rostikq.task01;

class Shipment {
    private final String id;
    private final ShipmentType type;
    private final boolean waybillFilled;
    private final boolean hasProhibitedItems;
    private final double weightKg;
    private final int maxDimensionCm;
    private final boolean hasCustomsDocs;
    private final boolean hasLithiumBattery;
    private boolean accepted = true;
    private String rejectionReason;

    public Shipment(String id,
                    ShipmentType type,
                    boolean waybillFilled,
                    boolean hasProhibitedItems,
                    double weightKg,
                    int maxDimensionCm,
                    boolean hasCustomsDocs,
                    boolean hasLithiumBattery) {
        this.id = id;
        this.type = type;
        this.waybillFilled = waybillFilled;
        this.hasProhibitedItems = hasProhibitedItems;
        this.weightKg = weightKg;
        this.maxDimensionCm = maxDimensionCm;
        this.hasCustomsDocs = hasCustomsDocs;
        this.hasLithiumBattery = hasLithiumBattery;
    }

    public String getId() { return id; }
    public ShipmentType getType() { return type; }
    public boolean isWaybillFilled() { return waybillFilled; }
    public boolean isHasProhibitedItems() { return hasProhibitedItems; }
    public double getWeightKg() { return weightKg; }
    public int getMaxDimensionCm() { return maxDimensionCm; }
    public boolean isHasCustomsDocs() { return hasCustomsDocs; }
    public boolean isHasLithiumBattery() { return hasLithiumBattery; }

    public void reject(String reason) {
        this.accepted = false;
        this.rejectionReason = reason;
    }

    public boolean isAccepted() { return accepted; }
    public String getRejectionReason() { return rejectionReason; }
}