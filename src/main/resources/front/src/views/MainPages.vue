
<template>
    <section class="main">
        <div class="container" style="margin-top: 50px;">
            <PromotionSlider v-bind:promotions="getPromotion"/>
            <h2>Каталог</h2>
            <div class="categories--card">
                <CategoriesCard v-bind:categories="getCategories"/>
            </div>
            <h2>Акции</h2>
            <div class="product" style="align-items: center; display: flex;">
                <ProductCard v-for="product in getProduct" :key="product.id" v-bind:product="product"/>
            </div>
        </div>
    </section>
</template>

<script>
    import ProductCard from '@/components/ProductCard.vue'
    import PromotionSlider from '@/components/PromotionSlider.vue'
    import CategoriesCard from '@/components/CategoriesCard.vue';

export default{
    name: 'MainPages',
    components:{
        ProductCard,
        PromotionSlider,
        CategoriesCard,
    },
    created() {
        this.$store.dispatch('product/fetchProducts');
        this.$store.dispatch('promotion/fetchPromotion');
        this.$store.dispatch('categories/fetchCategories');
    },
    computed:{
        getProduct() { return this.$store.getters['product/getProduct']; },
        getPromotion() { return this.$store.getters['promotion/getPromotion']; },
        getCategories() { return this.$store.getters['categories/getCategories']; }
    }
};

</script>

<style scoped>
    .container{
        width: 80%;
        margin: 0 auto;
    }
</style>