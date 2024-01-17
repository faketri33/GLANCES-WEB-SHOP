
<template>
    <section class="main">
        <div class="container" style="margin-top: 50px;">
            <PromotionSlider v-bind:promotions="getPromotion"/>
            <ProductCard v-bind:products="getProduct"/>
        </div>
    </section>

</template>

<script>
    import ProductCard from '@/components/ProductCard.vue'
    import PromotionSlider from '@/components/PromotionSlider.vue'

export default{
    name: 'MainPages',
    components:{
        ProductCard,
        PromotionSlider
    },
    created() {
        this.$store.dispatch('product/fetchProducts');
        this.$store.dispatch('promotion/fetchPromotion');
    },
    computed:{
        getProduct() { return this.$store.getters['product/getProduct']; },
        getPromotion() { return this.$store.getters['promotion/getPromotion']; }
    }
};

</script>

<style scoped>
    .container{
        width: 80%;
        margin: 0 auto;
    }
</style>