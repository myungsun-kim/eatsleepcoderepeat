<template>
  <el-row class="height5">
    <el-col :span="24" class="test-border"> UPDATE MY PAGE 여백: </el-col>
  </el-row>
  <el-row class="height5">
    <el-col :span="7"></el-col>

    <el-col :span="17" class="test-border itemlist-title-left">
      마이페이지 수정
    </el-col>
  </el-row>
  <el-row class="height5"> </el-row>
  <el-row class="height85">
    <el-col :span="10" :offset="7" class="test-border flex-parent">
      <el-row class="test-border">
        <el-col :span="12" class="cover-pic test-border">
          <el-row class="height100">
            <el-col :span="20"
              ><div class="profile"><img class="previewImg" /></div
            ></el-col>
            <el-col :span="4"
              ><el-row class="height90"> </el-row
              ><el-row class="height10"
                ><el-upload :before-upload="beforeUpload">업로드</el-upload>
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
      <!-- 마이페이지 정보 깃~ -->
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> github </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Github </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> twitter </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text"></el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> facebook </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Facebook </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> baekjoon </el-col>
        <el-col :span="16" :offset="1" class="test-border"> Baekjoon </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> port </el-col>
        <el-col :span="13" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.port"> </el-input>
        </el-col>
        <el-col :span="3" class="test-border"
          ><el-upload :before-upload="beforeUpload">업로드</el-upload>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> url </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.portfolio_uri"> </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> Strong </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.expTechList"> </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> Knowledgeable </el-col>
        <el-col :span="16" :offset="1" class="test-border">
          <el-input type="text" v-model="state.form.beginTechList"> </el-input>
        </el-col>
      </el-row>
      <el-row class="test-border">
        <el-col :span="7" class="test-border"> 희망 포지션 </el-col>
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
        <el-button class="btn-ghost-round-blue" @click="goReadMyPage"
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
import { ref, computed, reactive } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import axios from 'axios';

export default {
  methods: {
    beforeUpload: function (file) {
      let formData = new FormData();
      formData.append('file', file);

      var reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = function (e) {
        // var image = document.createElement('img');
        var image = document.querySelector('.previewImg');
        image.src = e.target.result; //blob 매핑
        image.width = 250;
        image.height = 200;
        image.alt = 'here should be some image';
        // document.body.appendChild(image);
      };

      const res = axios.post('/api/file/uploadFile', formData, {
        headers: {
          // auth가 제대로 안넘어가면 401 error 발생
          // 이해가 안되면 최민수에게 문의
          Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
          'Content-Type': 'multipart/form-data',
        },
      });
      res.then((res) => {
        console.log('then');
        console.log(res.data);
        console.log(res.data.fileDownloadUri);
        // readURL(this.uploadImageFile);
        console.log('reader');
      });
      console.log('onfile');

      // this.onFileSelected(file);
      // console.log('res');
      // console.log(res);
      // console.log(res.data);
      // console.log(res.data.fileDownloadUri);
    },
    readURL: function (event) {
      console.log('selected');
      var reader = new FileReader();
      reader.onload = (e) => {
        console.log('onload');
        console.log(e.target.result);
        this.uploadImageFile = e.target.result;
      };

      console.log(this.uploadImageFile);
    },
  },

  setup() {
    const store = useStore();
    const router = useRouter();
    const user = computed(() => store.state.user);
    const state = reactive({
      form: {
        email: user.value.email,
        name: user.value.name,
        nickname: user.value.nickname,
        position: user.value.position,
        city: user.value.city,
        github: '',
        twitter: '',
        facebook: '',
        backjoon: '',
        port: user.value.port,
        protfolio_uri: user.value.protfolio_uri,
        expTechList: user.value.expTechList,
        beginTechList: user.value.beginTechList,
        dpositionList: user.value.dpositionList,
        bio: user.value.bio,
      },
    });

    console.log('확인');
    console.log(state.form.email);
    console.log('유저정보');
    console.log(user);
    console.log(user.value.email);

    const goReadMyPage = function () {
      router.push({ path: '/nosubheader/readmypage' });
    };

    return {
      store,
      state,
      router,
      goReadMyPage,
      inputName: ref(''),
      user,
    };
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
