package request_delivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PriceCalculationServiceimpl implements PriceCalculationService {


    @Value("${delivery.base-cost-per-km:200}")
    private BigDecimal baseCostPerKm;

    @Value("${delivery.machine-capacity:5.0}")
    private Double machineCapacity;

    @Value("${delivery.min-delivery-cost:3000}")
    private BigDecimal minDeliveryCost;

    private static final Logger log = LoggerFactory.getLogger(PriceCalculationServiceimpl.class);


    @Override
    public BigDecimal calculateDeliveryPrice(Double productVolume,
                                             Integer distanceKm
    ) {
        log.debug("Calculating price for delivery");

        if(productVolume == null || productVolume <= 0){
            throw new IllegalArgumentException("Product volume must be greater than zero");
        }


        int numberOfMachines = calculateNumberOfmachines(productVolume);

        BigDecimal deliveryCostPerKm = baseCostPerKm    //Базовая стоимость за доставку одной машиной до 5 кубов
                .multiply(BigDecimal.valueOf(distanceKm))
                .setScale(2, RoundingMode.HALF_DOWN);

        BigDecimal deliveryPrice = deliveryCostPerKm
                .multiply(BigDecimal.valueOf(numberOfMachines))
                .setScale(2, RoundingMode.HALF_DOWN);
        if (deliveryPrice.compareTo(minDeliveryCost) < 0) {
            log.debug("Применена минимальная стоимость доставки");
            deliveryPrice = minDeliveryCost;
        }
        return deliveryPrice;
    }

    private int calculateNumberOfmachines(Double productVolume){
        return (int) Math.ceil(productVolume/machineCapacity);
    }

    @Override
    public BigDecimal calculateProductPrice(Double productVolume,
                                            BigDecimal baseProductPrice
    ) {
        log.debug("Calculating price for product");

        if(baseProductPrice == null || baseProductPrice.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Base product price must be greater than zero");
        }

        BigDecimal productPrice = baseProductPrice
                .multiply(BigDecimal.valueOf(productVolume))
                .setScale(2, RoundingMode.HALF_DOWN);

        return productPrice;
    }

    @Override
    public BigDecimal calculateTotalCost(BigDecimal productCost,
                                         BigDecimal deliveryCost
    ) {
        log.debug("Calculating total cost for delivery");

        BigDecimal totalCost = productCost
                .add(deliveryCost)
                .setScale(2, RoundingMode.HALF_DOWN);

        return totalCost;
    }
}
