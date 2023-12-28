<template>
    <RouterLink :to="{name: 'ProductCategories', params: {id: categorie.id, name: categorie.name}}" v-for="categorie in categories">
        <div class="product-card">
            <div class="image-container">
                <img src="@/assets/image 1.png" alt="Product Image">
            </div>
            <div class="title-container">
                <h4> {{ categorie.name }} </h4>
            </div>
        </div>
    </RouterLink>
 </template>
 
<script>
    export default{
        props:{
            categories:{
                type: Array,
                required: true
            }
        }
    }
</script>

 <style scoped>
    a{
        text-decoration: none;
        color: black;
    }
    a:hover{
        text-decoration: none;
        color: black;
        box-shadow: 4px 4px 8px 0px rgba(34, 60, 80, 0.2);
        border-radius: 5px;
    }
     .product-card {
         width: 12.5rem;
         padding: 10px;
     }
 
     .image-container {
         background-color: #BCC5FF;
         text-align: center;
         padding: 10px;
         border-radius: 5px;
     }
 
     img {
         max-width: 100%;
         height: auto;
     }
 
     .title-container {
         text-align: center;
         margin-top: 10px;
     }
 </style>