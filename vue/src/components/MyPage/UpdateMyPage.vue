<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"></el-col>
  </el-row>
  <el-row class="height5">
    <el-col :span="7"></el-col>

    <el-col :span="17" class="test-border itemlist-title-left">
      마이페이지 수정
      <hr style="border: 1px color= black" width="100%" />
    </el-col>
  </el-row>
  <el-row class="height5"> </el-row>
  <el-row class="height85">
    <el-col :span="10" :offset="7" class="test-border flex-parent">
      <el-row class="test-border">
        <el-col :span="12" class="cover-pic test-border">
          <el-row class="height100">
            <el-col :span="20"
              ><div class="profile">
                <img class="previewImg" /></div
            ></el-col>
            <el-col :span="4"
              ><el-row class="height90"> </el-row
              ><el-row class="height10"
                ><el-upload :before-upload="beforeProfileUpload"
                  >업로드</el-upload
                >
              </el-row>
            </el-col>
          </el-row>

          <!-- <el-upload
            ref="upload"
            class="upload-demo"
            drag
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            :before-upload="beforeUpload"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              파일을 드래그 하거나
              <br />
              <em>클릭해서 업로드 하세요</em>
            </div>
          </el-upload> -->
        </el-col>

        <el-col :span="10" :offset="2" class="test-border">
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> ID </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              <el-input
                type="text"
                class="info"
                v-model="state.form.email"
                readonly
              >
              </el-input>
            </el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 이름 </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              <el-input type="text" v-model="state.form.name"> </el-input>
            </el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 닉넴 </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              <el-input type="text" v-model="state.form.nickname"> </el-input
            ></el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 역할 </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              <el-input type="text" v-model="state.form.position"> </el-input
            ></el-col>
          </el-row>
          <el-row class="test-border">
            <el-col :span="5" class="test-border"> 지역 </el-col>
            <el-col :span="15" :offset="4" class="test-border">
              <el-input type="text" v-model="state.form.city"> </el-input
            ></el-col>
          </el-row>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 새 비밀번호 </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="password" v-model="state.form.password"> </el-input>
        </el-col>
      </el-row>
      <!-- <el-row class="test-border">
        <el-col :span="7" class="test-border"> 새 비밀번호 확인 </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.password"> </el-input>
        </el-col>
      </el-row> -->
      <!-- 마이페이지 정보 깃~ -->
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> github </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.snsHashMap.github">
          </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> twitter </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.snsHashMap.twitter">
          </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> facebook </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.snsHashMap.facebook">
          </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> baekjoon </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.snsHashMap.backjoon">
          </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 포트폴리오 </el-col>
        <el-col :span="13" :offset="1" class="test-border">
          <a :href="`${state.form.portfolio}`"
            >포트폴리오{{ state.form.portfolio }}</a
          >
        </el-col>
        <el-col :span="3" class="test-border"
          ><el-upload :before-upload="beforePortUpload">업로드</el-upload>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> url </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.portfolio_uri"> </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> Experienced </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.expTechList"> </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> Beginner </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.beginTechList"> </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 세부 포지션 </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input
            type="text"
            class="info"
            v-model="state.form.depositionList"
          >
          </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 자기소개 </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" class="info" v-model="state.form.bio">
          </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border align-center">
        <el-button class="btn-ghost-round-blue" @click="updateMember"
          >수정
        </el-button>
        <el-button class="btn-ghost-round" @click="goReadMyPage"
          >취소
        </el-button>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import { ref, computed, reactive, onBeforeMount } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import axios from 'axios';

export default {
  setup() {
    const store = useStore();
    const router = useRouter();
    // const res = store.dispatch('member/readMyPage');

    // res.then((res) => {
    //   store.state.user = res.data;
    // });
    // const user = computed(() => store.getters['member/mypageGetter']);

    const res = store.dispatch('member/readMyPage');
    // res.then((res) => {
    //   store.state.user = res.data;
    // });
    const user = computed(() => store.getters['member/mypageGetter']);

    console.log('유저정보');
    console.log(user);
    console.log(user.value);
    console.log(user.value.email);
    console.log(user.value.portfolio);
    const state = reactive({
      form: {
        beginAddTechList: [], //추가할 beginner 기술 리스트
        beginDelTechList: [], //삭제할 beginner 기술 리스트
        expAddTechList: [], //추가할 Experienced 기술 리스트
        expDelTechList: [], //삭제할 Experienced 기술 리스트
        dpositionAddList: [], //추가할 세부 포지션
        dpositionDelList: [], //삭제할 세부 포지션
        cover_pic: user.value.cover_pic, //프로필 사진 url
        email: user.value.email, //아이디=메일
        name: user.value.name, //이름
        nickname: user.value.nickname, //별명
        position: user.value.position, //역할
        password: '', //비밀번호
        city: user.value.city, //거주지
        snsHashMap: {
          //sns 아이디
          github: '',
          twitter: '',
          facebook: '',
          backjoon: '',
        },
        portfolio: user.value.portfolio, //포트폴리오 다운로드 주소
        portfolio_uuid: '', //포트폴리오 id
        // portfolio_uri: user.value.portfolio_uri, //포트폴리오 주소
        expTechList: user.value.expTechList, //Experinced
        beginTechList: user.value.beginTechList, //beginner
        dpositionList: user.value.dpositionList, //세부 포지션
        bio: user.value.bio, // 자기소개
        tel: '', //연락처
      },
    });

    onBeforeMount(() => {
      console.log(user.value.portfolio);
      console.log(user.value.portfolio_uuid);
      console.log(user.value.name);
      console.log(user.value.cover_pic);

      // 유저 snsList에서 snsName에 따라 snsAccount 계정 설정
      console.log(user.value.snsList.length);
      for (var i = 0; i < user.value.snsList.length; i++) {
        if (user.value.snsList[i].snsName == 'github') {
          state.form.snsHashMap.github = user.value.snsList[i].snsAccount;
        } else if (user.value.snsList[i].snsName == 'twitter') {
          state.form.snsHashMap.twitter = user.value.snsList[i].snsAccount;
        } else if (user.value.snsList[i].snsName == 'facebook') {
          state.form.snsHashMap.facebook = user.value.snsList[i].snsAccount;
        } else if (user.value.snsList[i].snsName == 'backjoon') {
          state.form.snsHashMap.backjoon = user.value.snsList[i].snsAccount;
        }
      }
    });

    // 프로필 사진 업로드
    const beforeProfileUpload = (file) => {
      let formData = new FormData();
      formData.append('file', file);

      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        // var image = document.createElement('img');
        console.log('파일리더');
        console.log(e);
        var image = document.querySelector('.previewImg');
        console.log('이미지');
        console.log(image);
        console.log('blob');
        console.log(e.target.result);
        image.src = e.target.result; //blob 매핑
        image.width = 250;
        image.height = 200;
        image.alt = 'here should be some image';
        // document.body.appendChild(image);
      };

      console.log('ddd');
      console.log(typeof formData);
      const res = store.dispatch('uploadFile', formData);

      res.then((res) => {
        console.log('then');
        console.log(res.data);
        console.log(res.data.fileDownloadUri);
        // readURL(this.uploadImageFile);
        console.log('reader');
        state.form.cover_pic = res.data.id;
      });
      console.log('onfile');
    };

    // 포트폴리오 파일 업로드
    const beforePortUpload = (file) => {
      let formData = new FormData();
      formData.append('file', file);

      const res = store.dispatch('uploadFile', formData);

      res.then((res) => {
        console.log('vv');
        console.log(res.data.fileDownloadUri);
        console.log(res.data.id);
        state.form.portfolio = res.data.fileDownloadUri;
        // state.form.portfolio_uuid = res.data.fileDownloadUri;
        state.form.portfolio_uuid = res.data.id;
      });
    };
    const goReadMyPage = function () {
      router.push({ path: '/nosubheader/readmypage' });
    };

    const updateMember = function () {
      store.dispatch('member/updateMember', state.form).then((res) => {
        console.log(res);
      });
    };

    return {
      store,
      state,
      router,
      goReadMyPage,
      beforeProfileUpload,
      beforePortUpload,
      updateMember,
      user,
    };
  },

  methods: {
    // readURL: function (event) {
    //   console.log('selected');
    //   var reader = new FileReader();
    //   reader.onload = (e) => {
    //     console.log('onload');
    //     console.log(e.target.result);
    //     this.uploadImageFile = e.target.result;
    //   };
    //   console.log(this.uploadImageFile);
    // },
  },
};
</script>

<style scoped>
.test-border {
  margin-bottom: 3%;
}
.flex-parent {
  display: flex;
  flex-direction: column;
  text-align: left;
  justify-content: space-evenly;
}
.title {
  font-size: 24px;
}
.info-size {
  height: 32px;
}
.previewImg {
  max-width: 100%;
  max-height: 100%;
  object-fit: cover;
}
</style>
