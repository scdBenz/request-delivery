package request_delivery.domain;

public enum RequestStatus {
    DRAFT,       //Черновик
    SUBMITTED,   //Заявка подана
    CONFIRMED,   //Заявка подтверждена
    IN_PROGRESS, //Заявка в процессе выполнения
    COMPLETED,   //Заявка выполнена
    CANCELLED;   //Заявка отменена



}
