<template>
  <div class="box-wrap">
    <div class="box">
      <img
        class="poster"
        :src="rating.posterImg"
        alt="Image"
        @click="moveProgramDetail(rating.id)"
      />
      <div class="button">
        <button
          @click="ratingDelete(rating.ratingId)"
          style="border: none; background: none"
        >
          <img class="trash" src="@/assets/trash.png" style="width: 50%" />
        </button>
      </div>
      <div class="info">
        <div class="title">{{ rating.title }}</div>
      </div>
    </div>
  </div>
  <div style="text-align: center; margin-bottom: 18%; margin-top: 3%">
    <img
      v-show="!isTrue1"
      @click="setFlagTrue1"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue1"
      @click="setFlagFalse1"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue2"
      @click="setFlagTrue2"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue2"
      @click="setFlagFalse2"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue3"
      @click="setFlagTrue3"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue3"
      @click="setFlagFalse3"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue4"
      @click="setFlagTrue4"
      class="hearted"
      src="@/assets/grey_star_small.png"
    />
    <img
      v-show="isTrue4"
      @click="setFlagFalse4"
      class="hearted"
      src="@/assets/blue_star_small.png"
    />
    <img
      v-show="!isTrue5"
      @click="setFlagTrue5"
      class="hearted"
      src="@/assets/grey_star_small.png"
      style="margin-right: 5%"
    />
    <img
      v-show="isTrue5"
      @click="setFlagFalse5"
      class="hearted"
      src="@/assets/blue_star_small.png"
      style="margin-right: 5%"
    />
  </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import axios from "axios";
import { mapActions, mapState, mapMutations } from "vuex";

export default {
  name: "Star",
  props: ["rating"],
  data() {
    return {
      score: 0,
      isTrue1: false,
      isTrue2: false,
      isTrue3: false,
      isTrue4: false,
      isTrue5: false,
    };
  },
  computed: {
    ...mapState(["ratingList"]),
  },
  created() {
    this.getRating();
    if (this.rating.score >= 2) {
      this.isTrue1 = true;
    }
    if (this.rating.score >= 4) {
      this.isTrue2 = true;
    }
    if (this.rating.score >= 6) {
      this.isTrue3 = true;
    }
    if (this.rating.score >= 8) {
      this.isTrue4 = true;
    }
    if (this.rating.score >= 10) {
      this.isTrue5 = true;
    }
  },
  methods: {
    ...mapActions([
      "deleteRating",
      "getProgramDetail",
      "registRating",
      "getRating",
    ]),
    ...mapMutations(["SET_PROGRAM_ID"]),
    ratingDelete(ratingId) {
      if (confirm("평점을 삭제하시겠습니까?") == true || ratingId == null) {
        this.deleteRating(ratingId);
      } else {
        return;
      }
    },
    moveProgramDetail(programId) {
      this.SET_PROGRAM_ID(programId);
      window.location.replace(`/detail`);
    },
    setFlagTrue5() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = true;
      this.registRating({ programId: this.rating.id, score: 10 });
    },
    setFlagFalse5() {
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 8 });
    },
    setFlagTrue4() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.isTrue4 = true;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 8 });
    },
    setFlagFalse4() {
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 8 });
    },
    setFlagTrue3() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = true;
      this.registRating({ programId: this.rating.id, score: 6 });
    },
    setFlagFalse3() {
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 6 });
    },
    setFlagTrue2() {
      this.isTrue1 = true;
      this.isTrue2 = true;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 4 });
    },
    setFlagFalse2() {
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 4 });
    },
    setFlagTrue1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 2 });
    },
    setFlagFalse1() {
      this.isTrue1 = true;
      this.isTrue2 = false;
      this.isTrue3 = false;
      this.isTrue4 = false;
      this.isTrue5 = false;
      this.registRating({ programId: this.rating.id, score: 2 });
    },
  },
};
</script>

<style scoped>
.saveButton {
  background-color: #ffffff;
  padding: 3px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

.logo {
  padding-left: 70px;
  padding-top: 0.05%;
}

.poster {
  width: 14.5vw;
  height: 22vw;
  padding-right: 0.5%;
  padding-bottom: 0.5%;
  margin-bottom: 1.5%;
}

#Img {
  padding-right: 0.5%;
  padding-bottom: 2%;
  position: relative;
}

.flex {
  display: flex;
  flex-wrap: wrap;
  align-content: stretch;
}

.btnRed.btnBorder:hover {
  box-shadow: 0px 0px 0px 5px #823621;
}

.btnRed {
  background: #ae2b00;
}

a.button {
  padding: 1%;
  font-weight: 600;
  text-align: center;
  color: #fff;
  border-radius: 5px;
  text-decoration: none;
  transition: all 0.1s;
  padding-top: 0;
}

img:hover {
  filter: brightness(0.3);
}

.box-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-wrap: wrap;
  align-content: stretch;
}

.box {
  position: relative;
  width: 100%;
  border: none;
  cursor: pointer;
  overflow: hidden;
}

.box img {
  transition: all 0.3s ease-in-out;
}

.box .info {
  position: absolute;
  left: 5px;
  bottom: 10px;
  color: #fff;
  box-sizing: border-box;
  opacity: 0;
  transition: all 0.3s ease-in-out;
}
.box .info .title {
  font-size: 0.9vw;
  font-weight: 400;
  text-align: left;
  white-space: normal;
}
.box:hover .info {
  opacity: 1;
}

.box:hover img {
  opacity: 1;
}

.box:hover:before {
  width: 60px;
}

.box:hover:after {
  height: 60px;
}

@font-face {
  font-family: "GoyangIlsan";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GoyangIlsan.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}

body {
  background-color: #1a237e;
  font-family: "GoyangIlsan";
}

.button {
  display: none;
}

.poster:hover + .button,
.button:hover {
  display: inline-block;
  position: absolute;
  top: 1.3vw;
  right: 0;
}
</style>
