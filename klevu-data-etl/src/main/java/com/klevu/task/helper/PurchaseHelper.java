package com.klevu.task.helper;

import com.klevu.task.model.OrderItem;
import com.klevu.task.model.Product;
import com.klevu.task.model.ProductPair;
import com.xiantrimble.combinatorics.Combinatoric;
import com.xiantrimble.combinatorics.CombinatoricFactory;
import com.xiantrimble.combinatorics.CombinatoricFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PurchaseHelper {

    private PurchaseHelper() {}

    private static final Logger log = LoggerFactory.getLogger(PurchaseHelper.class);

    public static boolean purchaseFinished(List<OrderItem> samePurchase, OrderItem orderItem) {
        return !samePurchase.isEmpty() &&
                !samePurchase.get(samePurchase.size() - 1).getIp().equals(orderItem.getIp());
    }

    public static void addPurchasePermutations(Map<ProductPair, Integer> data, List<OrderItem> purchase) {

        List<String> productsInPurchase = purchase.stream()
                .map(OrderItem::getProduct)
                .map(Product::getId)
                .collect(Collectors.toList());

        String[] productsAsArray = new String[productsInPurchase.size()];
        productsInPurchase.toArray(productsAsArray);

        CombinatoricFactory factory = new CombinatoricFactoryImpl();
        Combinatoric<String> permutations = factory.createPermutations(2, productsAsArray);

        permutations.forEach(a -> {

            if (!sameProductsInPermutation(a)) {

                ProductPair productPair = new ProductPair(a[0], a[1]);

                data.putIfAbsent(productPair, 0);
                data.computeIfPresent(productPair, (l, i) -> i + 1);

            }

        });

    }

    private static boolean sameProductsInPermutation(String[] a) {
        return a[0].equals(a[1]);
    }

}
