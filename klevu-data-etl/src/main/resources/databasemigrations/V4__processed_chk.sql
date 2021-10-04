ALTER TABLE preprocessed_recommendations_by_product
    ADD CONSTRAINT not_same_products_preprocessed_recommendations_by_product
        CHECK (product_id != buy_with_product_id);