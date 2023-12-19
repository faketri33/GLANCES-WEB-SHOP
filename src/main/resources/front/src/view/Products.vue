<template>
    Найдено товаров - {{ this.products.length }}
    <productCard v-bind:products="this.products"/>
</template>

<script>
    import productCard from '@/components/product/product-card.vue';
    import axios from 'axios'

    export default {
        components: { productCard },

        data(){
            return{
                products: this.loadProduct()
            }
        },
        props: {
            id: null,
            name: null
        },
        methods: {
            async loadProduct(){
                axios.get('http://localhost:8080/api/product/categories/' + this.id)
                        .then(response => {
                            this.products = response.data;
                        })
                        .catch(error => {
                            console.error(error);
                        });
            }
        }
    }
</script>

<style>

</style>
