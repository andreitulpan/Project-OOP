package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class Gift {
    private String productName;
    private Double price;
    private String category;
    @JsonIgnore
    private Integer quantity;

    public Gift(final GiftBuilder builder) {
        this.productName = builder.productName;
        this.price = builder.price;
        this.category = builder.category;
        this.quantity = builder.quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    public static final class GiftBuilder {
        private final String productName;
        private final Double price;
        private final String category;
        private Integer quantity;

        public GiftBuilder(final String productName, final Double price, final String category) {
            this.productName = productName;
            this.price = price;
            this.category = category;
            this.quantity = 0;
        }

        /**
         * Seteaza cantitatea prin
         * intermediul pattern-ului builder
         *
         * @param receivedQuantity cantitatea ce va fi setata
         * @return instanta obiectului curent
         */
        public GiftBuilder quantity(final Integer receivedQuantity) {
            this.quantity = receivedQuantity;
            return this;
        }

        /**
         * Intoarce o instanta nou
         * creata a obiectului Gift
         *
         * @return instanta creata
         */
        public Gift build() {
            return new Gift(this);
        }
    }

}
