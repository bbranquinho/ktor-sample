package org.wpattern.ktor.product;

import org.wpattern.ktor.event.Event;

public class ProductEvent implements Event {
    private String name;

    private Money price;

    private String storeId;

    public ProductEvent() {
    }

    public ProductEvent(String name, Money price, String storeId) {
        this.name = name;
        this.price = price;
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductEvent that = (ProductEvent) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (price != null ? !price.equals(that.price) : that.price != null) {
            return false;
        }
        return storeId != null ? storeId.equals(that.storeId) : that.storeId == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
