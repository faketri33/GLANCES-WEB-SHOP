<template>
  <div class="container mt-5">
    <h1>Подтверждение заказа</h1>

    <div class="mb-3">
      <h3>Выбор оплаты заказа</h3>
      <div>
        <input
          type="radio"
          id="online"
          value="online"
          v-model="paymentMethod"
        />
        <label for="online">Онлайн</label>
      </div>
      <div>
        <input
          type="radio"
          id="upon-receipt"
          value="upon-receipt"
          v-model="paymentMethod"
        />
        <label for="upon-receipt">При получении</label>
      </div>
    </div>

    <div class="mb-3">
      <h3>Список товаров</h3>
      <ul class="list-group">
        <li
          v-for="item in orderItems"
          :key="item.id"
          class="list-group-item d-flex justify-content-between align-items-center"
        >
          {{ item.name }}
          <span>{{ item.price }} руб.</span>
        </li>
      </ul>
      <div class="mt-3">
        <strong>Общая стоимость: {{ totalCost }} руб.</strong>
      </div>
    </div>

    <div class="mb-3">
      <h3>Выбор получения заказа</h3>
      <div>
        <input
          type="radio"
          id="pickup"
          value="pickup"
          v-model="deliveryMethod"
        />
        <label for="pickup">Самовывоз</label>
      </div>
      <div>
        <input type="radio" id="cdek" value="cdek" v-model="deliveryMethod" />
        <label for="cdek">СДЭК</label>
      </div>
    </div>

    <div v-if="deliveryMethod === 'cdek'" class="mb-3">
      <h4>Данные для доставки СДЭК</h4>
      <div class="mb-3">
        <label for="name" class="form-label">ФИО</label>
        <input
          type="text"
          class="form-control"
          id="name"
          v-model="cdekData.name"
        />
      </div>
      <div class="mb-3">
        <label for="phone" class="form-label">Номер телефона</label>
        <input
          type="text"
          class="form-control"
          id="phone"
          v-model="cdekData.phone"
        />
      </div>
      <div class="mb-3">
        <label for="address" class="form-label">Адрес</label>
        <input
          type="text"
          class="form-control"
          id="address"
          v-model="cdekData.address"
        />
      </div>
    </div>

    <div v-if="paymentMethod === 'online'" class="mb-3">
      <h4>Данные карты</h4>
      <div class="mb-3">
        <label for="cardNumber" class="form-label">Номер карты</label>
        <input
          type="text"
          class="form-control"
          id="cardNumber"
          v-model="cardData.cardNumber"
        />
      </div>
      <div class="mb-3">
        <label for="cardExpiry" class="form-label">Срок действия</label>
        <input
          type="text"
          class="form-control"
          id="cardExpiry"
          v-model="cardData.cardExpiry"
        />
      </div>
      <div class="mb-3">
        <label for="cardCvc" class="form-label">CVC</label>
        <input
          type="text"
          class="form-control"
          id="cardCvc"
          v-model="cardData.cardCvc"
        />
      </div>
    </div>

    <button class="btn btn-primary" @click="submitOrder">Оформить заказ</button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      paymentMethod: "",
      deliveryMethod: "",
      cdekData: {
        name: "",
        phone: "",
        address: "",
      },
      cardData: {
        cardNumber: "",
        cardExpiry: "",
        cardCvc: "",
      },
      orderItems: [
        { id: 1, name: "Товар 1", price: 1000 },
        { id: 2, name: "Товар 2", price: 2000 },
        { id: 3, name: "Товар 3", price: 1500 },
      ],
    };
  },
  computed: {
    totalCost() {
      return this.orderItems.reduce((total, item) => total + item.price, 0);
    },
  },
  methods: {
    submitOrder() {
      const orderData = {
        paymentMethod: this.paymentMethod,
        deliveryMethod: this.deliveryMethod,
        cdekData: this.deliveryMethod === "cdek" ? this.cdekData : null,
        cardData: this.paymentMethod === "online" ? this.cardData : null,
        orderItems: this.orderItems,
      };

      // Отправка данных на сервер
      console.log("Submitting order:", orderData);
      // Вы можете использовать axios или fetch для отправки данных на сервер
    },
  },
};
</script>

<style scoped>
.container {
  max-width: 600px;
}
</style>
