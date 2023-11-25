CREATE TABLE "product" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "brand_id" bigint,
  "name_model" varchar,
  "price" money
);

CREATE TABLE "brand" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" varchar
);

CREATE TABLE "image" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "image" bytea
);

CREATE TABLE "characteristics" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" varchar,
  "value" varchar
);

CREATE TABLE "categories" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" varchar,
  "value" varchar
);

CREATE TABLE "rating" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "product_id" bigint,
  "user_id" bigint,
  "description" varchar,
  "grate" varchar
);

CREATE TABLE "user" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "email" varchar,
  "login" varchar,
  "password" varchar(3000)
);

CREATE TABLE "order" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "user_id" bigint,
  "date_of_create" timestamp without time zone,
  "date_of_realese" timestamp without time zone,
  "price" money,
  "status" varchar
);

CREATE TABLE "product_image" (
  "product_id" bigint PRIMARY KEY,
  "image_id" bigint
);

CREATE TABLE "product_categories" (
  "product_id" bigint PRIMARY KEY,
  "categories_id" bigint
);

CREATE TABLE "product_characteristics" (
  "product_id" bigint PRIMARY KEY,
  "characteristics_id" bigint
);

CREATE TABLE "product_order" (
  "order_id" bigint PRIMARY KEY,
  "product_id" bigint
);

create table user_favorite_product(
       id_user bigint,
       id_product bigint 
);

ALTER TABLE "user_favorite_product" ADD FOREIGN KEY ("id_user") REFERENCES "user" ("id");
ALTER TABLE "user_favorite_product" ADD FOREIGN KEY ("id_product") REFERENCES "product" ("id");

ALTER TABLE "product" ADD FOREIGN KEY ("brand_id") REFERENCES "brand" ("id");

ALTER TABLE "product_image" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "product_image" ADD FOREIGN KEY ("image_id") REFERENCES "image" ("id");

ALTER TABLE "rating" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "rating" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");

ALTER TABLE "product_categories" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "product_categories" ADD FOREIGN KEY ("categories_id") REFERENCES "categories" ("id");

ALTER TABLE "product_characteristics" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "product_characteristics" ADD FOREIGN KEY ("characteristics_id") REFERENCES "characteristics" ("id");

ALTER TABLE "product_order" ADD FOREIGN KEY ("order_id") REFERENCES "order" ("id");

ALTER TABLE "product_order" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "order" ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");
