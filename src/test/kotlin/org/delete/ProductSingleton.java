package org.delete;

public class ProductSingleton {
    private static ProductSingleton instance;

    // ....

    private ProductSingleton() { }

    public static ProductSingleton getInstance() {
        if (instance == null) {
            instance = new ProductSingleton();
        }

        return instance;
    }

}
