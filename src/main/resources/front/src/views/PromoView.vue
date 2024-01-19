<script>
    import ProductCard from '@/components/ProductCard.vue';
    
    
    export default{
        name: 'PromoView',
        components:{
            ProductCard
        },
        created(){
            this.$store.dispatch('promotion/fetchPromotionById', this.$route.params.id);
        },
        computed: {
            getPromotion(){
                return this.$store.getters['promotion/getPromotion'];
            }
        }
    }
</script>

<template>

    <div class="container">
        <h1 class="title"> {{ getPromotion.title }} </h1>
        <img :src="'data:image/jpeg;base64,'+ getPromotion.banner"/>
        <div class="content">
            <div class="center--block">
                <div class="promo--product">
                    <h3>Аукционный товары</h3>
                    <ProductCard v-bind:products="getPromotion.promotionItems"/>
                </div>
            </div>
            <div class="description">
                <p> {{ getPromotion.description }} </p>
            </div>
            <p> Срок проведения акции {{getPromotion.dateOfStart}} по {{ getPromotion.dateOfEnd }} </p>
        </div>
    </div>

</template>

<style scoped>
    .content{
        background-color: white;
        padding: 15px;
    }
    .container{
        width: 60%;
        margin: 0 auto;
    }
    img{
        width: 100%;
        height: 100%;
    }

    @media screen and (max-width: 900px) {
        .container {
            width: 90%;
        }
    }
</style>