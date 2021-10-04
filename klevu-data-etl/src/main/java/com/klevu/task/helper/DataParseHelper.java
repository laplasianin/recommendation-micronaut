package com.klevu.task.helper;

import com.klevu.task.model.OrderItem;
import com.klevu.task.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public final class DataParseHelper {

    private DataParseHelper() {
    }

    private static final Logger log = LoggerFactory.getLogger(DataParseHelper.class);

    public static final int COLS_NUMBER = 3;
    public static final String COLS_SEPARATOR = "\t";

    public static Optional<OrderItem> toOrderItem(String line) {
        String[] unparsedLine = line.split(COLS_SEPARATOR);

        if (unparsedLine.length != COLS_NUMBER) {
            log.error("Invalid data: incorrect number of cols. Expected: {}, Actual: {}", COLS_NUMBER, unparsedLine.length);
            return Optional.empty();
        }

        String customerIp = unparsedLine[1];
        String productId = unparsedLine[0];
        String productName = unparsedLine[2];

        return Optional.of(new OrderItem(
                customerIp,
                new Product(
                        productId,
                        productName
                )
        ));
    }

}
