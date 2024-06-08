drop table if exists basket cascade;
drop table if exists basket_products cascade;
drop table if exists brand cascade;
drop table if exists categories cascade;
drop table if exists characteristics cascade;
drop table if exists image cascade;
drop table if exists jwt_refresh cascade;
drop table if exists orders cascade;
drop table if exists orders_products cascade;
drop table if exists payment cascade;
drop table if exists product cascade;
drop table if exists product_characteristics cascade;
drop table if exists product_image cascade;
drop table if exists product_item cascade;
drop table if exists promotion cascade;
drop table if exists promotion_promotion_product_items cascade;
drop table if exists rating cascade;
drop table if exists user_role cascade;
drop table if exists users cascade;
drop table if exists users_favorite_product cascade;

create table basket (
    price bigint DEFAULT 0,
    id uuid not null,
    primary key (id)
);

create table basket_products (
    basket_id uuid not null,
    products_id uuid not null unique
);

create table brand (
    id uuid not null,
    name varchar(255),
    primary key (id)
);

create table categories (
    id uuid not null,
    image_id uuid unique,
    name varchar(255) unique,
    primary key (id)
);

create table characteristics (
    id uuid not null,
    name varchar(255),
    value varchar(255),
    primary key (id)
);

create table image (
    id uuid not null,
    path varchar(255),
    primary key (id)
);

create table jwt_refresh (
    date_of_create timestamp(6) DEFAULT NOW(),
    date_of_expiration timestamp(6),
    id uuid not null,
    user_id uuid,
    token varchar(255),
    primary key (id)
);

create table orders (
    price integer,
    status_order smallint check (status_order between 0 and 2),
    date_of_create timestamp(6) DEFAULT NOW(),
    date_of_release timestamp(6),
    id uuid not null,
    payment_id uuid unique,
    users_id uuid,
    primary key (id)
);

create table orders_products (
    orders_id uuid not null,
    products_id uuid not null
);

create table payment (
    payment_status smallint check (payment_status between 0 and 1),
    date_of_create timestamp(6) DEFAULT NOW(),
    date_of_payment timestamp(6),
    id uuid not null,
    user_id uuid,
    primary key (id)
);

create table product (
    discount smallint DEFAULT 0,
    is_promo_item boolean DEFAULT FALSE,
    price integer DEFAULT 100,
    promo_price integer DEFAULT 0,
    quantity integer not null check (quantity>=0),
    quantity_sold integer,
    brand_id uuid,
    categories_id uuid,
    id uuid not null,
    description varchar(3000),
    name_model varchar(255),
    primary key (id)
);

create table product_characteristics (
    characteristics_id uuid not null,
    products_id uuid not null,
    primary key (characteristics_id, products_id)
);

create table product_image (
    image_id uuid not null unique,
    product_id uuid not null,
    primary key (image_id, product_id)
);

create table product_item (
    quantity integer,
    id uuid not null,
    product_id uuid,
    primary key (id)
);

create table promotion (
    date_of_end date,
    date_of_start date,
    banner_id uuid unique,
    id uuid not null,
    description varchar(255),
    conditions varchar(2048),
    title varchar(255),
    primary key (id)
);

create table promotion_promotion_product_items (
    promotion_id UUID NOT NULL,
    promotion_product_items_id UUID NOT NULL,
    PRIMARY KEY (promotion_id, promotion_product_items_id)
);

create table rating (
    grade smallint,
    published_on timestamp(6),
    id uuid not null,
    product_id uuid,
    users_id uuid,
    description varchar(255),
    primary key (id)
);

create table user_role (
    role smallint check (role between 0 and 2),
    users_id uuid not null
);

create table users (
    date_of_birthday date,
    date_of_create timestamp(6) DEFAULT NOW(),
    basket_id uuid unique,
    id uuid not null,
    profile_image_id uuid unique,
    city varchar(255),
    email varchar(255) unique,
    login varchar(255) unique,
    name varchar(255),
    password varchar(255),
    surname varchar(255),
    primary key (id)
);

create table shop (
    id uuid not null,
    address varchar(255) not null unique,
    primary key (id)
);

create table users_favorite_product (
    favorite_product_id uuid not null,
    users_id uuid not null,
    primary key (favorite_product_id, users_id)
);

alter table if exists basket_products
   add constraint FKq5iwyqw65f6eam55vgft7nnqt
   foreign key (products_id)
   references product_item;

alter table if exists basket_products
   add constraint FKlf7fabaqrkluttrqcbdiknoci
   foreign key (basket_id)
   references basket;

alter table if exists categories
   add constraint FKl00b731pkot6w2dx83er3bjov
   foreign key (image_id)
   references image;

alter table if exists jwt_refresh
   add constraint FKe613glrwsi2pwmanhsjcmhaqj
   foreign key (user_id)
   references users;

alter table if exists orders
   add constraint FKag8ppnkjvx255gj7lm3m18wkj
   foreign key (payment_id)
   references payment;

alter table if exists orders
   add constraint FKe6k45xxoin4fylnwg2jkehwjf
   foreign key (users_id)
   references users;

alter table if exists orders_products
   add constraint FKgpeseu74ov3xrv3o64rbw7116
   foreign key (products_id)
   references product_item;

alter table if exists orders_products
   add constraint FKc8m93nag8x44ghu6u9eoxuqk3
   foreign key (orders_id)
   references orders;

alter table if exists payment
   add constraint FKmi2669nkjesvp7cd257fptl6f
   foreign key (user_id)
   references users;

alter table if exists product
   add constraint FKs6cydsualtsrprvlf2bb3lcam
   foreign key (brand_id)
   references brand;

alter table if exists product
   add constraint FK96ie9fvomwiok25wtg1qs6s1o
   foreign key (categories_id)
   references categories;

alter table if exists product_characteristics
   add constraint FK7nrhrxgropym9hxo1xnt9aklw
   foreign key (characteristics_id)
   references characteristics;

alter table if exists product_characteristics
   add constraint FKfmfpqa2ql6q4pnp1rqwa0g1ww
   foreign key (products_id)
   references product;

alter table if exists product_image
   add constraint FKbhddxsl8axd5io2wgkcoealn5
   foreign key (image_id)
   references image;

alter table if exists product_image
   add constraint FK6oo0cvcdtb6qmwsga468uuukk
   foreign key (product_id)
   references product;

alter table if exists product_item
   add constraint FKa9mjpi98ark8eovbtnnreygbb
   foreign key (product_id)
   references product;

alter table if exists promotion
   add constraint FKpr1h8oh901gu22ejcgnryisyw
   foreign key (banner_id)
   references image;

ALTER TABLE IF EXISTS promotion_promotion_product_items
    ADD CONSTRAINT FK5p4vw8uswjx357kna989lap52
    FOREIGN KEY (promotion_product_items_id)
    REFERENCES product (id);

ALTER TABLE IF EXISTS promotion_promotion_product_items
    ADD CONSTRAINT FKcvore0jon84olyfnfwbwy4nma
    FOREIGN KEY (promotion_id)
    REFERENCES promotion (id);

alter table if exists rating
   add constraint FKlkuwie0au2dru36asng9nywmh
   foreign key (product_id)
   references product;

alter table if exists rating
   add constraint FK669d2yxxse87xkrvhmd2i0tsq
   foreign key (users_id)
   references users;

alter table if exists user_role
   add constraint FKm3tmf7s11ilny7lvpiks60j0n
   foreign key (users_id)
   references users;

alter table if exists users
   add constraint FK7uvaosre9anv0okiwm4mx82bm
   foreign key (basket_id)
   references basket;

alter table if exists users
   add constraint FK1o6t3td59l5wb7ku28k57n72j
   foreign key (profile_image_id)
   references image;

alter table if exists users_favorite_product
   add constraint FK5slq2cek2oy5p0tsc1ljh2jw0
   foreign key (favorite_product_id)
   references product;

alter table if exists users_favorite_product
   add constraint FKtilfpyutfqc9cenusu2imxyy0
   foreign key (users_id)
   references users;
