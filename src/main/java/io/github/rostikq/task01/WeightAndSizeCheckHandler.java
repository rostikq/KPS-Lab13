package io.github.rostikq.task01;

import io.github.rostikq.logger.EventLogger;

public class WeightAndSizeCheckHandler extends ShipmentCheckHandler {
    private final double maxWeightKg;
    private final int maxDimensionCm;

    public WeightAndSizeCheckHandler(double maxWeightKg, int maxDimensionCm) {
        super("WeightAndSizeCheck");
        this.maxWeightKg = maxWeightKg;
        this.maxDimensionCm = maxDimensionCm;
    }

    @Override
    protected boolean check(Shipment shipment) {
        if (shipment.getWeightKg() > maxWeightKg) {
            shipment.reject("Перевищено допустиму вагу (" + shipment.getWeightKg()
                    + " кг > " + maxWeightKg + " кг)");
            return false;
        }
        if (shipment.getMaxDimensionCm() > maxDimensionCm) {
            shipment.reject("Перевищено допустимі габарити (" + shipment.getMaxDimensionCm()
                    + " см > " + maxDimensionCm + " см)");
            return false;
        }
        EventLogger.log(handlerName, "Вага (" + shipment.getWeightKg()
                + " кг) та габарити (" + shipment.getMaxDimensionCm() + " см) у нормі");
        return true;
    }
}
