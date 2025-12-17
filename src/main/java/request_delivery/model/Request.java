package request_delivery.model;


import java.time.LocalDateTime;

public class Request {
    private Long id;
    private Client client;
    private Product product;
    private Contractor contractor;
    private LocalDateTime requestDate;
    private Long price;
    private RequsetStatus status;


    //Конструкторы
    public Request() {
    }

    public Request(Long price, Contractor contractor,
                   Product product, Client client, Long id, RequsetStatus status) {
        this.price = price;
        this.requestDate = LocalDateTime.now();
        this.contractor = contractor;
        this.product = product;
        this.client = client;
        this.id = id;
        this.status = status;
    }

    //Геттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public Long getPrice() {
        return price;
    }

    //Сеттеры

    public void setPrice(Long price) {
        this.price = price;
    }

    public RequsetStatus getStatus() {
        return status;
    }

    public void setStatus(RequsetStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                ", contractor=" + contractor +
                ", requestDate=" + requestDate +
                ", price=" + price +
                ", status=" + status +
                '}';
    }


}
