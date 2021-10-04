CREATE UNIQUE INDEX CONCURRENTLY unq_pis_bw_id
    ON preprocessed_recommendations_by_product(product_id, buy_with_product_id);
