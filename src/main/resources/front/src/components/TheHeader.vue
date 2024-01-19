
<template>
    <header>
        <div class="container">
            <div class="nav">
                <div class="nav__item logo" style="text-align: center;">
                    <span class="logo__name">GLANCES</span>
                </div>
                <div class="nav__item search__bar">
                    <div class="search-container">
                        <input type="text" class="search-input" placeholder="Поиск">
                        <img src="@/assets/search.svg" alt="Лупа" class="search-icon">
                    </div>
                </div>
                <div class="nav__item tab__bar">
                    <router-link to="/catalog" class="tab__bar__item">
                        <img src="@/assets/catalog.svg" alt="">
                        <p>Каталог</p>
                    </router-link>
                    <router-link to="/basket" class="tab__bar__item">
                        <img src="@/assets/basket.svg" alt="">
                        <p>Корзина</p>
                    </router-link>
                    <router-link to="/profile" class="tab__bar__item">
                        <img src="@/assets/profil.svg" alt="">
                        <p>Профиль</p>
                    </router-link>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
    export default{
        name: "TheHeader"   
    }
</script>

<style scoped>
    header{
        border-bottom: 1px solid #C1C1C1;
        background: #F6F6F6;
        height: 80px;
        display: flex;
        align-items: center;
    }
    .container{
        width: 80%;
        margin: 0 auto;
    }
    .nav{
        display: flex;
        gap: 88px;
        flex-shrink: 0;   
        align-items: center;
        justify-content: space-between;
    }
    .logo__name{
        font-family: Disket Mono;
        font-size: 28px;
        font-style: normal;
        font-weight: 400;
        line-height: normal;

        background: linear-gradient(267deg, #260DC1 6.29%, #59006F 100%);
        background-clip: text;
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
    .search__bar{
        width: 90%;
    }
    .search-container {
        position: relative;
        display: inline-block;
        background-color: #C1C1C1;
        border-radius: 5px;
        padding: 5px;
        width: 100%;
    }
    .search-input {
        border: none;
        background-color: transparent;
        padding: 5px;
        padding-left: 30px;
        border-radius: 20px;
        outline: none;
        width: 90%;
    }
    .search-icon {
        position: absolute;
        top: 50%;
        left: 10px;
        transform: translateY(-50%);
        height: 16px;
        width: 16px;
    }
    .tab__bar{
        display: flex;
        justify-content: center;
        align-items: flex-end;
        gap: 32px;
    }
    .tab__bar__item{
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 8px;
    }
    p{
        margin: 0;
        color: black;
        text-decoration: none;
    }
    .router-link-exact-active {
        text-decoration: none;
    }

    @media screen and (max-width: 1024px) {
        .container{
            width: 100%;
            margin: 0px;
        }
        header{
            height: auto;
        }
        .nav{
            display: block;
        }
        .nav__item{
            margin-top: 5px;
        }
        .search__bar{
            margin: 0 auto  ;
        }
    }
</style>