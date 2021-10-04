ALTER TABLE preprocessed_recommendations_by_product
    ADD CONSTRAINT unique_product_and_buy_with
        UNIQUE USING INDEX unq_pis_bw_id;