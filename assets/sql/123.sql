
select p.*, b.name as brand_name, c.name as categories_name, c.image as categories_image,i.id as image_id, i.image, ch.id as characteristics_id, 
ch.name as characteristics_name, ch.value as characteristics_value 
from product p 
left join brand b on b.id = p.brand_id 
left join categories c on c.id = p.categories_id 
left join product_image pi on pi.product_id = p.id 
left join image i on i.id = pi.image_id 
left join product_characteristics pc on pc.product_id = p.id 
left join characteristics ch on ch.id = pc.characteristics_id  
WHERE p.id in ( 
        select p1.id from product p1
        left join product_characteristics pc1 on pc1.product_id = p1.id
        where p1.categories_id = 1 
        and pc1.characteristics_id = 1
        and pc1.characteristics_id = 3
        LIMIT 2 OFFSET 0
); 


delete  from categories;
delete from brand;
delete from image;
delete from product;
delete from product_image;
delete from product_characteristics;
delete from characteristics;

select * from characteristics
select * from product_characteristics;
 

select * from characteristics p where p.categories_id = 2

select distinct c1_0.id,c1_0.name,c1_0.value 
from characteristics c1_0 
left join (characteristics_products p1_0 join product p1_1 on p1_1.id=p1_0.products_id) on c1_0.id = p1_0.characteristics_id 
where p1_1.categories_id=2