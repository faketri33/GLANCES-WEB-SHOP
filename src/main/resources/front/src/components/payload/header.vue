<template>
    <header class="header">
        <div class="container">
            <div class="row">
                <div class="header--item col-2">
                    <h2 class="logo">GLANCES</h2>
                </div>
                
                <div class="header--item col-6"> 
                    <div class="search-container">
                        <input type="text" class="search-input" placeholder="Поиск">
                        <img src="@/assets/search.svg" alt="Лупа" class="search-icon">
                    </div>
                </div>

                <div class="header--item col">
                    <div class="tap--bar">
                            <a href="" class="tap--bar--item">
                                <img src="@/assets/catalog.svg" alt="">
                                <p>Каталог</p>
                            </a>
                            <a href="" class="tap--bar--item">
                                <img src="@/assets/basket.svg" alt="">
                                <p>Корзина</p>
                            </a>
                            <a href="" class="tap--bar--item">
                                <img src="@/assets/profil.svg" alt="">
                                <p>Профиль</p>
                            </a>
                    </div>
                </div>
            </div>
        </div>
    </header>
</template>

<style scoped>

    a{
        text-decoration: none;
        color: #454545;
    }
    .logo{
        background: linear-gradient(267deg, #260DC1 6.29%, #59006F 100%);
        background-clip: text;
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
    .tap--bar{
        display: flex;
    }
    .tap--bar--item{
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 0.5rem;
        margin-left: 2.75rem;
    }
    .header{
        border-bottom: 1px solid #C1C1C1;
        height: 90px;
        padding: 25px;
    }
    .tap--bar--item p { margin: 0; color: #454545; text-decoration: none;}
    
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
        width: 100%;
    }
    .search-icon {
        position: absolute;
        top: 50%;
        left: 10px;
        transform: translateY(-50%);
        height: 16px;
        width: 16px;
    }
</style>
