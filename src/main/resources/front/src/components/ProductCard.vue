
<template>
  <button class="card" style="background-color: transparent; border: none">
    <div class="image">
      <img
        :src="'data:image/jpeg;base64,' + product.image[0].photo"
        :alt="product.nameModel"
      />
      <span v-if="product.isPromoActive" class="discount"
        >-{{ product.discount }} %</span
      >
    </div>
    <div class="info">
      <h3>{{ product.brand.name + " " + product.nameModel }}</h3>
      <p v-if="product.isPromoActive" class="price">
        <span class="new"> {{ product.promoPrice }} ₽</span>
        <span class="old"> {{ product.price }} ₽</span>
      </p>
      <p v-else class="price">
        <span> {{ product.price }} </span>
      </p>
      <div style="display: flex; justify-content: space-between">
        <p class="stock">В наличии</p>
        <button class="favorite" >
          <img :src="this.liked ? '/Vector.svg' : '/red.svg'" alt="" />
        </button>
      </div>
    </div>
    <button class="add">В корзину</button>
  </button>
</template>

<script>
export default {
  props: {
    product: {
      brand: {
        id: Number,
        name: String,
      },
      nameModel: String,
      image: {
        id: Number,
        Image: [],
      },
      price: Number,
      promoProce: Number,
      isPromoActive: Boolean,
      discount: Number,
      rating: {
        id: Number,
        description: String,
        grade: Number,
        user: {
          id: Number,
          login: String,
        },
      },
    },
  },
  methods: {
    favoriteClick(liked) {
      this.liked = !liked;
    },
  },
};
</script>

<style scoped>
.card {
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 220px;
  border: 1px solid #ccc;
  gap: 20px;
}
.like {
  color: color;
}

.image {
  position: relative;
  width: 200px;
}

.image img {
  width: 170px;
  height: 220px;
}

.discount {
  position: absolute;
  top: 10px;
  right: 10px;
  background: #ebba1a;
  padding: 5px;
  font-weight: bold;
  border-radius: 5px;
}

.info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 10px;
}

.info h3 {
  font-size: 18px;
  margin: 0;
}

.price {
  display: flex;
  align-items: center;
}

.price .new {
  font-size: 24px;
  font-weight: bold;
  color: green;
  margin-right: 10px;
}

.price .old {
  font-size: 18px;
  text-decoration: line-through;
  color: red;
}

.stock {
  color: blue;
}

.favorite {
  border: none;
  -webkit-border-radius: 5;
  -moz-border-radius: 5;
  border-radius: 5px;
  -webkit-box-shadow: 1px 1px 18px #666666;
  -moz-box-shadow: 1px 1px 18px #666666;
  box-shadow: 1px 1px 18px #666666;
  font-family: Arial;
  padding: 8px 12px;
  color: #ffffff;
  text-decoration: none;
}

.add {
  background: blue;
  width: 100%;
  color: white;
  padding: 10px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}
</style>