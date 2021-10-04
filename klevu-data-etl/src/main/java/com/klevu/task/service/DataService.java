package com.klevu.task.service;

import com.klevu.task.helper.DataParseHelper;
import com.klevu.task.model.OrderItem;
import com.klevu.task.model.Product;
import com.klevu.task.model.ProductPair;
import com.klevu.task.repository.DataRepository;
import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.klevu.task.helper.PurchaseHelper.addPurchasePermutations;
import static com.klevu.task.helper.PurchaseHelper.purchaseFinished;

@Prototype
public class DataService {

    private static final Logger log = LoggerFactory.getLogger(DataService.class);

    @Inject DataRepository dataRepository;

    public void extractData(URI uri) {
        Path path = Paths.get(uri);

        Map<ProductPair, Integer> productsRecommendationsWithWeights = new HashMap<>();
        Set<Product> products = new HashSet<>();

        try (Stream<String> lines = Files.lines(path)) {

            List<OrderItem> samePurchase = new ArrayList<>();

            lines
                    .map(DataParseHelper::toOrderItem)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(orderItem -> {

                        products.add(orderItem.getProduct());

                        if (purchaseFinished(samePurchase, orderItem)) {
                            addPurchasePermutations(productsRecommendationsWithWeights, samePurchase);
                            samePurchase.clear();
                        }

                        samePurchase.add(orderItem);

                    });

            if (!samePurchase.isEmpty()) {
                addPurchasePermutations(productsRecommendationsWithWeights, samePurchase);
            }

        } catch (IOException e) {
            log.error("Unable to parse file", e);
        }

        dataRepository.saveProcessedRecommendations(productsRecommendationsWithWeights);
        dataRepository.saveProducts(products);
    }

}
