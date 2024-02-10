CREATE TABLE "product" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "brand_id" bigint NOT NULL,
  "name_model" varchar NOT NULL,
  "categories_id" bigint not null,
  "price" bigint NOT NULL, 
  "quantity" int not null,
  "quantity_sold" int not null,
  "is_promo_active" bit not null,
  "promotion_price" bigint,
  "discount" int not null default 0
);

CREATE TABLE "brand" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" varchar NOT NULL
);

CREATE TABLE "image" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "image" bytea
);

CREATE TABLE "characteristics" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" varchar NOT NULL,
  "value" varchar NOT NULL
);

CREATE TABLE "categories" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "name" varchar NOT NULL,
  "image" bytea not null
);

CREATE TABLE "rating" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "product_id" bigint NOT NULL,
  "user_id" bigint NOT NULL,
  "description" varchar NOT NULL,
  "grate" varchar NOT NULL
);

CREATE TABLE "users" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "email" varchar NOT NULL unique,
  "login" varchar NOT NULL unique,
  "password" varchar(3000) NOT NULL
);

CREATE TABLE "product_characteristics" (
  "product_id" bigint ,
  "characteristics_id" bigint
);

CREATE TABLE "product_order" (
  "order_id" bigint,
  "product_id" bigint
);

create table user_favorite_product(
       "id_user" bigint,
       "id_product" bigint 
);

create table promotion(
        id bigint primary key GENERATED ALWAYS AS IDENTITY,
        banner bytea not null, 
        title varchar not null,
        description varchar not null,
        date_of_start timestamp without time zone NOT NULL,
        date_of_end timestamp without time zone NOT NULL
);

create table promotion_product_item(
        promotion_id bigint,
        product_id bigint
);

create table product_image(
        product_id bigint,
        image_id bigint
);

CREATE TABLE "orders" (
  "id" bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  "user_id" bigint NOT NULL,
  "date_of_create" timestamp without time zone NOT NULL,
  "date_of_realese" timestamp without time zone NOT NULL,
  "price" bigint NOT NULL,
  "status" varchar NOT NULL
);

alter table product add foreign key ("categories_id") references categories("id");

alter table promotion_product_item add foreign key (promotion_id) references promotion(id);
alter table promotion_product_item add foreign key (product_id) references product(id);

ALTER TABLE "user_favorite_product" ADD FOREIGN KEY ("id_user") REFERENCES "users" ("id");
ALTER TABLE "user_favorite_product" ADD FOREIGN KEY ("id_product") REFERENCES "product" ("id");

ALTER TABLE "product" ADD FOREIGN KEY ("brand_id") REFERENCES "brand" ("id");

ALTER TABLE product_image ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE product_image ADD FOREIGN KEY ("image_id") REFERENCES "image" ("id");

ALTER TABLE "rating" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "rating" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "product_characteristics" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "product_characteristics" ADD FOREIGN KEY ("characteristics_id") REFERENCES "characteristics" ("id");

ALTER TABLE "product_order" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "product_order" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");
