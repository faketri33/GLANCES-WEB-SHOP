<template>
  <div class="wrapper">
    <div v-show="isSmallWidth" class="card p-4" style="max-width: 16rem">
      <router-link
        :to="'/product/' + product.id"
        class="text-decoration-none text-center"
        style="color: black"
      >
        <img
          :src="'data:image/jpeg;base64,' + product.image[0].photo"
          class="card-img-top"
          :alt="getFullName"
          style="max-height: 206px; max-width: 174px"
        />
        <div class="card-body p-0">
          <h5 class="card-title">
            {{ getFullName }}
          </h5>
          <div class="d-flex justify-content-between">
            <p class="card-price" v-if="product.isPromoActive">
              <strong>{{ product.promoPrice }}</strong
              >руб.
            </p>
            <p
              class="card-price"
              :style="
                product.isPromoActive
                  ? 'text-decoration:line-through; color: grey;'
                  : 'text-decoration: none;'
              "
            >
              <strong>{{ product.price }}</strong
              >руб.
            </p>
          </div>
        </div>
      </router-link>
      <div class="stock d-flex justify-content-between align-items-center">
        <p class="in-stock text-primary m-0">В наличии</p>
        <button
          @click="liked = !liked"
          class="btn btn-outline-light shadow mb-2"
          title="Добавить в избраное"
        >
          <img :src="liked ? '/red.svg' : '/Vector.svg'" alt="" />
        </button>
      </div>
      <button class="btn btn-primary w-100">В корзину</button>
    </div>
    <!-- 
    TO FULL SIZE
  -->
    <div v-show="!isSmallWidth" class="row p-2 m-3 border rounded-1">
      <div class="img col-md-4 col-lg-3 text-center">
        <img
          :src="'data:image/jpeg;base64,' + product.image[0].photo"
          class="img"
          :alt="getFullName"
          style="max-height: 206px"
        />
      </div>
      <div class="card-body p-0 ms-3 col-md-3">
        <h5 class="card-title">
          <router-link
            :to="'/product/' + product.id"
            class="text-decoration-none"
            style="color: black"
          >
            {{ getFullName }}
          </router-link>
        </h5>
        <div class="rating mt-2">
          <p>
            Рейтинг товара - <strong>{{ getAverageRating }}</strong>
          </p>
        </div>
        <div class="stock d-flex align-items-center col">
          <p class="in-stock text-primary m-0">В наличии</p>
          <button
            @click="liked = !liked"
            class="btn btn-outline-light shadow mb-2 ms-5"
            title="Добавить в избраное"
          >
            <img :src="liked ? '/red.svg' : '/Vector.svg'" alt="" />
          </button>
        </div>
      </div>
      <div
        class="card-price-buy col-md-3 col-lg-2 text-center d-flex align-items-center"
      >
        <div class="wrapper w-100">
          <h5 class="price">
            <strong>{{ product.price }}руб.</strong>
          </h5>
          <button class="btn btn-primary w-100">В корзину</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    product: {
      id: Number,
      brand: {
        id: Number,
        name: String,
      },
      nameModel: String,
      categories: {
        id: Number,
        name: String,
      },
      image: {
        id: Number,
        photo: [],
      },
      rating: {
        id: Number,
        description: String,
        grade: Number,
        publishedOn: Date,
      },
      characteristics: {
        id: Number,
        name: String,
        value: String,
      },
      price: Number,
      isPromoActive: Boolean,
      promoPrice: Number,
      discount: Number,
      quantity: Number,
      quantitySold: Number,
    },
    isSmallWidth: Boolean,
  },
  data() {
    return {
      liked: false,
    };
  },
  created() {
    window.addEventListener("resize", () => {
      this.width = document.documentElement.clientWidth;
    });
  },
  computed: {
    getFullName() {
      return (
        this.product.categories.name.substring(
          0,
          this.product.categories.name.length - 1
        ) +
        " " +
        this.product.brand.name +
        " " +
        this.product.nameModel +
        " [" +
        Object.values(this.product.characteristics.slice(0, 10)).map(
          (element) => element.value
        ) +
        "]"
      );
    },
    getAverageRating() {
      if (this.product.length > 0) {
        return (
          this.product.rating.reduce((partialSum, a) => partialSum + a, 0) /
          this.product.rating.length
        );
      } else return 0;
    },
  },
};
</script>
