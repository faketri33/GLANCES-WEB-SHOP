<template>
    <div class="slider">
        <router-link v-for="promotion in promotions" 
            :key="promotion.id" :to="'/promo/' + promotion.id">
            <img :src="'data:image/jpeg;base64,'+promotion.banner" 
            :class="{ active: currentIndex === promotion.id }">
        </router-link>
            
        <div class="prev" @click="goToPrev">&#8249;</div>
        <div class="next" @click="goToNext">&#8250;</div>
    </div>
</template>

<script>
    export default{
        props:{
            promotions: {
                type: Array,
                require: true
            }
        },
        data(){
            return{
                currentIndex: 0
            }
        }
    ,
    methods: {
        goToPrev() {
            this.currentIndex--;
            if (this.currentIndex < 0) {
                this.currentIndex = this.promotions.length - 1;
            }
        },
        goToNext() {
            this.currentIndex++;
            if (this.currentIndex >= this.promotions.length) {
                this.currentIndex = 0;
            }
        }
        }
    }
</script>

<style scoped>
/* Стили для слайдера */
.slider {
  position: relative;
  width: 100%;
  height: 300px;
  overflow: hidden;
  border-radius: 15px;
}

.slider img {
  width: 100%;
  height: 100%;
  cursor: pointer;
  background-repeat: no-repeat;
  background-size: cover;
}

.slider img.active {
  display: block;
}

.slider .prev,
.slider .next {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: rgba(0, 0, 0, 0.5);
  color: #fff;
  font-size: 24px;
  text-align: center;
  line-height: 40px;
  cursor: pointer;
}

.slider .prev {
  left: 10px;
}

.slider .next {
  right: 10px;
}
</style>