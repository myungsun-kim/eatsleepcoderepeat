<template>
  <el-row>
    <el-col :span="5"></el-col>
    <el-col :span="13">
      <el-row class="author-font"> 공지사항 </el-row>
      <el-row id="article-title"> 금주 모임 시간 변경 안내 </el-row>
      <el-row class="author-font"> Minsu </el-row>
      <el-row>
        <el-col :span="4" class="gray-font">21.09.08 13:49</el-col>
        <el-col :span="16"></el-col>
        <el-col :span="2">
          <a @click="goUpdateNotice" class="grayLittle">수정</a>
        </el-col>
        <el-col :span="2"> <ArticleDeleteModal /></el-col>
      </el-row>
      <el-row class="gray-font"
        >본문 Section 1.10.32 of "de Finibus Bonorum et Malorum", written by
        Cicero in 45 BC "Sed ut perspiciatis unde omnis iste natus error sit
        voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
        ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
        dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
        aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
        qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui
        dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia
        non numquam eius modi tempora incidunt ut labore et dolore magnam
        aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum
        exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea
        commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea
        voluptate velit esse quam nihil molestiae consequatur, vel illum qui
        dolorem eum fugiat quo voluptas nulla pariatur?" 1914 translation by H.
        Rackham "But I must explain to you how all this mistaken idea of
        denouncing pleasure and praising pain was born and I will give you a
        complete account of the system, and expound the actual teachings of the
        great explorer of the truth, the master-builder of human happiness. No
        one rejects, dislikes, or avoids pleasure itself, because it is
        pleasure, but because those who do not know how to pursue pleasure
        rationally encounter consequences that are extremely painful. Nor again
        is there anyone who loves or pursues or desires to obtain pain of
        itself, because it is pain, but because occasionally circumstances occur
        in which toil and pain can procure him some great pleasure. To take a
        trivial example, which of us ever undertakes laborious physical
        exercise, except to obtain some advantage from it? But who has any right
        to find fault with a man who chooses to enjoy a pleasure that has no
        annoying consequences, or one who avoids a pain that produces no
        resultant pleasure?"
      </el-row>
      <el-row>
        <el-col :span="21"></el-col>
        <el-col :span="2">
          <el-button class="btn-ghost" @click="goReadNotice">목록</el-button>
        </el-col>
        <el-col :span="1"></el-col>
      </el-row>
      <el-row>
        <el-col :span="2">댓글</el-col>
        <el-col :span="22"></el-col>
      </el-row>
      <el-row>
        <el-col :span="24"><textarea>등록버튼</textarea></el-col>
      </el-row>
      <el-row>
        <el-col :span="1"></el-col>
        <el-col :span="22"></el-col>
        <el-row>
          <el-col :span="1">사진</el-col>
          <el-col :span="21">닉네임<br />3시간전</el-col
          ><el-col :span="1"><button>수정</button></el-col
          ><el-col :span="1"><button>삭제</button></el-col>
        </el-row>
        <el-row>
          <el-col :span="2"><button>답글쓰기</button></el-col>
          <el-col :span="22"></el-col>
        </el-row>

        <el-col :span="1"></el-col>
      </el-row>
      <el-row>
        <el-col :span="24"><textarea>등록버튼</textarea></el-col>
      </el-row>
    </el-col>
    <el-col :span="6"></el-col>
  </el-row>
</template>
<script>
import ArticleDeleteModal from '../../Modal/ArticleDeleteModal.vue';
// import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  components: { ArticleDeleteModal },
  setup() {
    // const store = useStore();
    const router = useRouter();

    const goUpdateNotice = function () {
      router.push({ path: '/subheader/notice/update' });
    };
    const goReadNotice = function () {
      router.push({ path: '/subheader/notice/read' });
    };

    return {
      goUpdateNotice,
      goReadNotice,
    };
  },
};
</script>
<style scoped>
.grayLittle {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: 500;
  font-size: 16px;
  line-height: 16px;
  text-align: center;
  color: #999999;
}
#article-title {
  font-family: Inter;
  font-style: normal;
  font-weight: bold;
  font-size: 45px;
}
.author-font {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: bold;
  font-size: 18px;
  line-height: 22px;
}
.gray-font {
  font-family: Noto Sans KR;
  font-style: normal;
  font-weight: normal;
  font-size: 16px;
  line-height: 19px;
  color: #718096;
}
</style>
