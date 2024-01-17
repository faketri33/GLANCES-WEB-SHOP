<script>

    export default{
        name: 'PromoView',
        data: () => ({
            promotion: []
        }),
        mounted(){  
        },
        created(){
            this.$store.dispatch('promotion/fetchPromotionById', this.$route.params.id);
        },
        methods: {
            async getPromotion(){
                this.promotion = await this.$store.dispatch('promotion/fetchPromotionById', this.$route.params.id);
            }
        }
    }
</script>

<template>

    <div>
        <h1> Promo View {{  this.$route.params.id }}</h1>
        <h1> {{  promotion.description }}  ss</h1>
    </div>

</template>

<style scoped>

</style>