<template>
    <a href="" v-for="product in products">
        <div class="product-card d-flex">
            <img v-bind:src="'data:image/jpeg;base64,' + product.image[0].photo" alt=""/>
            <div class="product-title"> {{product.brand.name +' '+ product.name_model }} </div>
            <div class="product-price"> {{product.price + 'р'}} </div>
            <button v-if="product.quantity > 0" > BUY</button>
        </div>
    </a>
</template>

<script>
    export default{
        props: {    
            products: {
                type : Array,
                require: true
            }
        }
    }
</script>

<style scoped>
    a{
        text-decoration: none;
        color: black;
    }
    .product-card{
        margin: 10px;
        border: 3px solid grey;
    }
    img{
        width: 80px;
        height: 80px;
    }
</style>