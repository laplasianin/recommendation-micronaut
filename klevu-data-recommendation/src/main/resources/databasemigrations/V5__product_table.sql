CREATE TABLE "product" (
    id TEXT NOT NULL,
    name text NOT NULL,
    date_created timestamp without time zone NOT NULL DEFAULT now(),
    last_updated timestamp without time zone NOT NULL DEFAULT now()
);

ALTER TABLE "product"
    ADD CONSTRAINT "product_pkey" PRIMARY KEY (id);
