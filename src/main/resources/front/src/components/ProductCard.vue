
<template>
   
    <button v-for="product in products" v-bind:key="product.id">
        <div class="product-card">
            <img v-bind:src="'data:image/jpeg;base64,' + product.image[0].photo" alt=""/>
            <div class="product-title"><h1>{{product.brand.name +' '+ product.nameModel }} </h1></div>
            <div class="product-price"> <h1>{{product.price + '₽'}} </h1></div>
            <div class="in-availability"><span>В наличии</span></div>
            <button class="btn-buy" v-if="product.quantity > 0" > В корзину</button>
        </div>
    </button>

</template>

<script>
    export default{
        props:{
            products:{
                type: Array,
                require: true
            }
        }
    }
</script>

<style scoped>
    *{
        font-family: 'Courier New', Courier, monospace;   
    }
    button{
        background-color: transparent;
        border: none;
        cursor: pointer;
    }
    button,
    button:active,
    button:focus  {
        border:none;
    }
    .btn-buy {
        margin: 0 auto;
        background-color:#091D9E;
        border-radius:8px;
        border:1px solid #272f8f;
        cursor:pointer;
        color:#ffffff;
        font-size:14px;
        font-weight:bold;
        padding:16px 31px;
        text-decoration:none;
    }
    .btn-buy:hover {
        background-color:#0040ff;
    }
    .btn-buy:active {
        position:relative;
        top:1px;
    }
    .product-card{
        width: 180px;
        height: 530px;
        margin: 10px;
        text-align: left;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        gap: 20px;
    }
    img{
        width: 180px;
        height: 220px;
    }
    .product-title{
        color: #0C0C0C;
        /* h1/20 regular 32 */
        font-size: 20px;
        font-style: normal;
    }
    h1{
        margin: 0;
    }
    .in-availability{
        color: #169B00;
        /* h3/14 light auto */
        font-size: 14px;
        font-style: normal;
        font-weight: 300;
        line-height: normal;
    }
</style>