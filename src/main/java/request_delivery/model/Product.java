package request_delivery.model;

public class Product {
    private Long id;
    private ProductType productType;
    private Long sizeProduct;

    public Product() {
    }

    public Product(Long id, ProductType productType, Long sizeProduct) {
        this.id = id;
        this.productType = productType;
        this.sizeProduct = sizeProduct;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Long getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(Long sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productType=" + productType +
                ", sizeProduct=" + sizeProduct +
                '}';
    }
}
