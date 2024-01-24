<template>
  <div class="container">
    <input type="login" name="login" id="username" v-model="login" />
    <input type="email" name="email" id="email" v-model="email" />
    <input type="password" name="password" id="password" v-model="password" />
    <button @click="registerUser">Register</button>
  </div>
  <div>
    <CategoriesCard :categories="getCategories"></CategoriesCard>
  </div>
</template>

<script>
import axios from "../axiosConfig";
import CategoriesCard from "@/components/CategoriesCard.vue";
export default {
  components: {
    CategoriesCard,
  },
  created() {
    this.$store.dispatch("categories/fetchCategories");
  },
  methods: {
    registerUser() {
      axios
        .post("http://localhost:8080/api/auth/authenticate", {
          login: this.login,
          password: this.password,
        })
        .then((response) => {
          localStorage.setItem("token", response.data.token);
        })
        .catch((error) => {
          console.error(error);
        });
      console.log(localStorage.getItem("token"));
    },
  },
  data() {
    return {
      login: "",
      password: "",
      email: "",
    };
  },
  computed: {
    getCategories() {
      return this.$store.getters["categories/getCategories"];
    },
  },
};
</script>

<style scoped>
.container {
  width: 50%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 24px;
  margin-top: 50px;
}
</style>