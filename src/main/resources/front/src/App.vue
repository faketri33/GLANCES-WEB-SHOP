<template>
    <Header/>
    <RouterView></RouterView>
</template>

<script>
    import Header from '@/components/payload/header.vue';

    export default{
        components: { Header }
    }
</script>

<style>

</style>