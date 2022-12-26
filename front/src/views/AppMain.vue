<template>
<v-app>
  <v-main class="blue-grey lighten-5">
  <v-carousel>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src">

    </v-carousel-item>
  </v-carousel>

  <h1 style="margin-top:20px; display:inline-block; ">당신이 찾는 집은 무엇인가요?</h1>

  <br/>
  <b-button plain
                x-large
                class="link mt-5"
                id="goHouse"
                :to="{ name: 'house' }"
                
                style="dackground-color: #C0C0C0;"> 집 보러가기 </b-button>
  
  
  <v-row class="mb-15 ml-5 mr-5">
          <v-col>
            <v-card class="mx-auto" max-width="500px" flat>
              <v-card-title class="justify-center m-4" style="font-size: 2em">
                <div 
                  class="justify-center mt-4"
                >
                  공지 사항
                </div>
              </v-card-title>

              <v-card-actions>
                <v-btn
                  class="link"
                  :to="{ name: 'notice' }"
                  color="primary" 
                  text
                > 
                  목록 
                </v-btn>

                <v-spacer></v-spacer>

                <v-btn 
                  icon 
                  @click="noticeShow = !noticeShow"
                >
                  <v-icon>
                    {{ noticeShow ? "mdi-chevron-up" : "mdi-chevron-down" }}
                  </v-icon>
                </v-btn>
              </v-card-actions>

              <v-expand-transition>
                <div v-show="noticeShow">
                  <v-divider></v-divider>

                  <v-list
                    v-for="notice in notices"
                    :key="notice.num"
                    :notice="notice"
                  >
                    <v-list-item style="border-bottom: 1px solid black;">
                      <v-list-item-content>
                        <v-list-item-title  
                        >
                        <router-link :to="{ name: 'noticeview', params: {num: notice.num} }" style="text-decoration:none;" >{{ notice.title }}</router-link>
                          
                        </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                </div>
              </v-expand-transition>
            </v-card>
          </v-col>

          <v-col>
            <v-card class="mx-auto" max-width="500px" flat>
              <v-card-title class="justify-center m-4" style="font-size: 2em">
                <div 
                  class="justify-center mt-4"
                >
                  뉴스
                </div>
              </v-card-title>

              <v-card-actions>
                <v-btn
                  href = "https://news.naver.com/main/list.naver?mode=LS2D&mid=shm&sid1=101&sid2=260"
                  target = "_blank"
                  color="primary" 
                  text
                > 
                  목록 
                </v-btn>

                <v-spacer></v-spacer>

                <v-btn 
                  icon 
                  @click="newsShow = !newsShow"
                >
                  <v-icon>
                    {{ newsShow ? "mdi-chevron-up" : "mdi-chevron-down" }}
                  </v-icon>
                </v-btn>
              </v-card-actions>

              <v-expand-transition>
                <div v-show="newsShow">
                  <v-divider></v-divider>

                  <v-list v-for="(item, i) in newsArr" :key="i">
                    <div class="a">
                    <v-list-item>
                      <v-list-item-content>
                        <v-list-item-title>
                          <a :href="item.link" target="_blank" style="text-decoration:none;">{{item.title | rmHTML}}</a>
                          <span class="b">{{item.description | rmHTML | descriptText}}...</span>
                        </v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                    </div>
                  </v-list>
                </div>
              </v-expand-transition>
            </v-card>
          </v-col>
        </v-row>
  
  </v-main>
</v-app>
</template>

<script>
import http from '@/api/http'
import axios from "axios";
  export default {
    name: "AppMain",
    data () {
      return {
        newsArr : [],
        RegText : "",
        noticeShow: false,
        newsShow: false,
        items: [
          {
            src: require('@/assets/unsplashHouse(2).jpg')
          },
          {
            src: require('@/assets/unsplashHouse(1).jpg')
          },
          {
            src: require('@/assets/unsplashHouse(3).jpg')
          },
          {
            src: require('@/assets/unsplashHouse(4).jpg')
          }
        ],
        notices: [],
      }
    },
    mounted() {
      this.getNews();
    },
    methods : {
      getNews() { // ==========================================================
        // axios.get(`http://70.12.50.89:9999/api/server/naver/집값`).
        axios.get(`http://localhost:9999/api/server/naver/집값`).
        then((response) => {
          this.newsArr = response.data.items;
          console.log("data", this.newsArr);
        });
      }, // ====================================================================

      moveNoticePage() {
        this.$router.push({ name: "join" });
      },
    },
    filters: {
      rmHTML : function(value) {
        let result = value.toString().replace(/<[^>]*>?/g, '').replace("&quot;",'"').replace("&quot;","''").replace("&apos;","''").replace("&apos;","''");
        //console.log(result);
        return result;
      }, 
      descriptText : function(value) {
        if (value.length < 15) {
          return;
        }

        return value.substr(0, 15);

      }
    },
  
  created() {
    http.get("/noticeApi/notice/1").then(({ data }) => {
      this.notices = data;
    });
  },


};
</script>

<style>
@font-face {
  font-family: "BAEMIN";
  src: local("BAEMIN"), url("@/assets/font/BMDOHYEON_ttf.ttf");
}

/* h1 {
  font-family: "BAEMIN";
} */

* {
  font-family: "BAEMIN";
}


  #mainImage {
    width: 100%;
    height: 100%;
    background-repeat: no-repeat;
  }
  #goHouse {
    color: white;
    font-size: 30px;
  }

.a {
  border-bottom: 1px solid black;
}


.a .b {
  visibility: hidden;
  width: 300px;
  background-color: black;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  

  position: absolute;
  z-index: 1;
  top: 80%;
  left: 40%;
}

.a:hover .b {
  visibility: visible;
}

</style>
