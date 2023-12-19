<template>
    <div class="categoriesSlider myContainer">
        <h2>Каталог</h2>
        <div class="d-flex">
            <categoriesCard v-bind:categories="categories"/>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import categoriesCard from '@/components/categories/categories-card.vue';

    export default {
        components: { categoriesCard },
        data(){
            return{
                categories: this.loadCategories()
            }
        },
        mounted () {
            
        },
        methods: {
            async loadCategories() {
                    axios.get('http://localhost:8080/api/categories/')
                        .then(response => {
                            this.categories = response.data;
                        })
                        .catch(error => {
                            console.error(error);
                        });
            }
        }
    }
  

</script>

<style>
    .myContainer{
        width: 90%;
        margin: 0 auto;
    }
</style>