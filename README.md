# OnlineMarket

Online store of electronic equipment.
Technology stack -
<ul>
  <li>Java 17</li>
  <li>Spring boot (Spring Web, JDBC Template, Spring Security)</li>
  <li>PostgreSQL</li>
  <li>Docker</li>
  <li>Git</li>
  <li>HTML</li>
  <li>CSS</li>
  <li>Vue.js</li>
</ul>
<h2>
  DataBase Diagram
</h2>

<img src="https://github.com/faketri/OnlineMarket/blob/master/assets/%D0%94%D0%B8%D0%B0%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%B0.png">

# Controller 

# Product /api/product
<h2>GET</h2>  
<ul>
  <li> Path = / </li>
  <p>find all product. Return - List<Product>. MediaType.APPLICATION_JSON_VALUE</p>
    <li> Path = /categories/{categoriesId} </li>
  <p>find all product in categories. Return - List<Product>. MediaType.APPLICATION_JSON_VALUE</p>
    <li> Path = /page?number=1?size=20 </li>
    <p>number - number pages</p>
    <p>size - count products on page</p>
  <p>find all product by pages. Return - Pages<Product>. MediaType.APPLICATION_JSON_VALUE</p>
    <li> Path = /{productId} </li>
    <p> productId - id product </p>
  <p>Find product by id. Return - Product. MediaType.APPLICATION_JSON_VALUE</p>
</ul>
