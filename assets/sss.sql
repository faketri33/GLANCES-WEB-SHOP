select p.*, b.name as brand_name, c.name as categories_name, 
        i.id as image_id, i.image, ch.id as characteristics_id, ch.name as characteristics_name, ch.value as characteristics_value
from product p 
left join brand b on b.id = p.brand_id 
left join categories c on c.id = p.categories_id 
left join product_image pi on pi.product_id = p.id
left join image i on i.id = pi.image_id
left join product_characteristics pc on pc.product_id = p.id
left join characteristics ch on ch.id = pc.characteristics_id

alter table product
add column quantity_sold int not null default 0

alter table product
drop column is_promo_active;
alter table product
add column is_promo_active boolean default false

select * from product
insert into product(brand_id, name_model, price, quantity, quantity_sold, is_promo_active, promotion_price, discount, categories_id)
values(1,'',1,1,1,'0',0,0,1)

update product 
set brand_id = :brand_id, 
name_model = :name_model, 
price = :price, 
quantity = :quantity, 
quantity_sold = :quantity_sold, 
is_promo_active = :is_promo_active, 
promotion_price = :promotion_price, 
discount = :discount, 
categories_id = :categories_id
where product.id = :id

select * from product_characteristics

alter table product_characteristics
drop column product_id;
alter table product_characteristics
add column product_id bigint;
ALTER TABLE "product_characteristics" ADD FOREIGN KEY ("product_id") REFERENCES "product" ("id");