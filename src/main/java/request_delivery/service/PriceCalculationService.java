package request_delivery.service;

import org.springframework.stereotype.Service;
import request_delivery.domain.ContractorEntity;

import java.math.BigDecimal;

@Service
/**
 * Service для расчета стоимости доставки,
 * Этот интерфейс позволяет реализовать стратегию расчета через Strategy pattern,
 * Это поддерживает Open/Closed принцип SOLID (открыт для расширения, закрыт для модификации).
 */
public interface PriceCalculationService {
    /**
     * Расчет стоимости доставки.
     *
     * @Param productVolume - объем продукции
     * @Param distanceKm - дистанция в километрах для расчета стоимости доставки.
     * Данный параметр заглушка т.к. нет сервиса для автоматического расчета расстояния от места отгрузки до места разгрузки.
     */

    BigDecimal calculateDeliveryPrice(
            Double productVolume,
            Integer distanceKm
    );

    /**
     *
     * Расчет стоимости продукции
     * @Param productVolume - объем продукции
     * @Param baseProductPrice - цена продукции за единицу измерения
     */
    BigDecimal calculateProductPrice(
            Double productVolume,
            BigDecimal baseProductPrice
    );


    /**
     * Расчет общей стоимости (продукция + доставка)
     */

    BigDecimal calculateTotalCost(
            BigDecimal productCost,
            BigDecimal deliveryCost
    );
}
