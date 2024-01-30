<template>
  <div class="main">
    <input type="checkbox" id="chk" aria-hidden="true" />

    <div class="signup">
      <form>
        <label for="chk" aria-hidden="true">Sign up</label>
        <input type="text" name="txt" placeholder="User name" required="" />
        <input type="email" name="email" placeholder="Email" required="" />
        <input type="password" name="pswd" placeholder="Password" required="" />
        <button @click="singUp">Sign up</button>
      </form>
    </div>

    <div class="login">
      <form>
        <label for="chk" aria-hidden="true">Login</label>
        <input type="email" name="email" placeholder="Email" required="" />
        <input type="password" name="pswd" placeholder="Password" required="" />
        <button @click="singIn">Login</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  created() {},
  methods: {
    singUp() {
      const user = {
        login: this.login,
        email: this.email,
        password: this.password,
      };

      this.$store.commit("user/setUser", user);
      this.$store.dispatch("user/fetchSignUp");
    },
    singIn() {
      const user = {
        login: this.login,
        password: this.password,
      };

      this.$store.commit("user/setUser", user);
      this.$store.dispatch("user/fetchSignIn");
    }
  },
  data() {
    return {
      login: "",
      password: "",
      email: "",
    };
  },
};
</script>

<style scoped>
.main {
  margin: 0 auto;
  margin-top: 10%;
  width: 350px;
  height: 500px;
  background: linear-gradient(to right, blue, pink);
  overflow: hidden;
  border-radius: 10px;
  box-shadow: 5px 20px 50px #000;
}
#chk {
  display: none;
}
.signup {
  position: relative;
  width: 100%;
  height: 100%;
}
label {
  color: #fff;
  font-size: 2.3em;
  justify-content: center;
  display: flex;
  margin: 60px;
  font-weight: bold;
  cursor: pointer;
  transition: 0.5s ease-in-out;
}
input {
  width: 60%;
  height: 20px;
  background: #e0dede;
  justify-content: center;
  display: flex;
  margin: 20px auto;
  padding: 10px;
  border: none;
  outline: none;
  border-radius: 5px;
}
button {
  width: 60%;
  height: 40px;
  margin: 10px auto;
  justify-content: center;
  display: block;
  color: #fff;
  background-color: #573b8a;
  font-size: 1em;
  font-weight: bold;
  margin-top: 20px;
  outline: none;
  border: none;
  border-radius: 5px;
  transition: 0.2s ease-in;
  cursor: pointer;
}
button:hover {
  background-color: #6d44b8;
}
.login {
  height: 460px;
  background: #eee;
  border-radius: 60% / 10%;
  transform: translateY(-180px);
  transition: 0.8s ease-in-out;
}
.login label {
  color: #573b8a;
  transform: scale(0.6);
}

#chk:checked ~ .login {
  transform: translateY(-500px);
}
#chk:checked ~ .login label {
  transform: scale(1);
}
#chk:checked ~ .signup label {
  transform: scale(0.6);
}
</style>