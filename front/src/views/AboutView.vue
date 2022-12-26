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
  <h1 class="text-h4 font-weight-thin" style="opacity: 0.9; display:inline-block; ">당신이 찾는 집은 무엇인가요?</h1>
  <br/>
  <b-button plain
                x-large
                class="link mt-5"
                id="goHouse"
                :to="{ name: 'house' }"
                
                style="dackground-color: #C0C0C0;"> 집 보러가기 </b-button>
  
  <h1>안녕하세요</h1>
  <v-row class="mb-15">
          <v-col>
            <v-card class="mx-auto" max-width="500px">
              <v-card-title class="justify-center m-4" style="font-size: 2em">
                <!-- <v-avart color="indigo">
                  <v-icon>
                    mdi-volume-off
                  </v-icon>
                </v-avart> -->
                <b-icon
                  icon="bell-fill"
                  class="rounded-circle bg-black p-2 m-2"
                  variant="light"
                  style="width: 50px; height: 50px"
                ></b-icon>
                <div 
                  class="justify-center"
                >
                  공지 사항
                </div>
              </v-card-title>

              <v-card-subtitle> 1,000 miles of wonder </v-card-subtitle>

              <v-card-actions>
                <v-btn 
                  color="primary" 
                  text
                > 
                  Explore 
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

                  <v-list>
                    <v-list-item>
                      <v-list-item-content>
                        <v-list-item-title
                        >news1</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-content>
                        <v-list-item-title
                        >news2</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                </div>
              </v-expand-transition>
            </v-card>
          </v-col>

          <v-col>
            <v-card class="mx-auto" max-width="500px">
              <v-card-title class="justify-center m-4" style="font-size: 2em">
                <!-- <v-avart color="indigo">
                  <v-icon>
                    mdi-volume-off
                  </v-icon>
                </v-avart> -->
                <b-icon
                  icon="bell-fill"
                  class="rounded-circle bg-black p-2 m-2"
                  variant="light"
                  style="width: 50px; height: 50px"
                ></b-icon>
                <div 
                  class="justify-center"
                >
                  뉴스
                </div>
              </v-card-title>

              <v-card-subtitle> 1,000 miles of wonder </v-card-subtitle>

              <v-card-actions>
                <v-btn 
                  color="primary" 
                  text
                > 
                  Explore 
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

                  <v-list>
                    <v-list-item>
                      <v-list-item-content>
                        <v-list-item-title
                        >news1</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                    <v-list-item>
                      <v-list-item-content>
                        <v-list-item-title
                        >news2</v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
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
export default {};
</script>

<script>
  export default {
    data () {
      return {
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
        ]
      }
    }
  }
</script>

<style> 




</style>
