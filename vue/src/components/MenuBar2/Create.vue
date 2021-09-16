<template>
  <el-row class="height5">
    <el-col :span="24" :offset="0" class="test-border">
      CREATE - study, project, club 5% 여백. 입력 줄 간 간격을 넣을 것인지
      말것인지 결정해야함
    </el-col>
  </el-row>

  <el-row class="test-border height100">
    <el-col :span="5" :offset="0" class="test-border"></el-col>
    <el-col :span="14" :offset="0" class="test-border">
      <el-row class="test-border height5">[스터디 생성]</el-row>
      <el-row class="test-border height5">스터디 이름</el-row>
      <el-row class="test-border height5">
        <el-input
          placeholder="이름을 입력해주세요"
          v-model="temp"
          size="mini"
        ></el-input>
      </el-row>
      <el-row class="test-border height5">기술 스택</el-row>
      <el-row class="test-border height5">
        <el-input
          placeholder="사용하는 기술 스택을 입력해주세요"
          v-model="temp"
          size="mini"
        ></el-input>
      </el-row>
      <el-row class="test-border">
        <el-col :span="11" :offset="0" class="test-border">
          <el-row> 일정 (?) </el-row>
          <el-row>
            <el-input
              placeholder="스터디 일정을 입력해주세요"
              v-model="temp"
              size="mini"
            ></el-input>
          </el-row>
          <el-row> 스터디 기간(단위: 주) </el-row>
          <el-row>
            <el-input
              placeholder="(숫자만 입력하세요)"
              v-model="temp"
              size="mini"
            ></el-input>
          </el-row>
          <el-row> 인원 </el-row>
          <el-row>
            <el-input
              placeholder="(숫자만 입력하세요)"
              v-model="temp"
              size="mini"
            ></el-input>
          </el-row>
        </el-col>
        <el-col :span="2" :offset="0" class="test-border"></el-col>
        <el-col :span="11" :offset="0" class="test-border"
          >프로필사진 등록</el-col
        >
      </el-row>
      <el-row class="test-border height5">비공개 스터디 ✔</el-row>
      <el-row class="test-border height5"></el-row>
      <el-row class="test-border height5">지역</el-row>
      <el-row class="test-border height5">
        <el-input
          placeholder="지역을 선택하세요"
          v-model="temp"
          size="mini"
        ></el-input>
      </el-row>
      <el-row class="test-border height5"></el-row>
      <el-row class="test-border height5">소속 클럽</el-row>
      <el-row class="test-border height5">
        <el-input
          placeholder="소속된 클럽을 선택하세요"
          v-model="temp"
          size="mini"
        ></el-input>
      </el-row>
      <el-row class="test-border height5"></el-row>
      <el-row class="test-border height5">소개</el-row>
      <el-row class="test-border height5">
        <el-input
          placeholder="해당 스터디에 대해 소개해주세요"
          v-model="temp"
          size="mini"
        ></el-input>
      </el-row>
      <el-row class="test-border height5"></el-row>
      <el-row class="test-border height5">
        <el-col :span="10" :offset="0" class="test-border"></el-col>
        <el-col :span="2" :offset="0" class="test-border">생성</el-col>
        <el-col :span="2" :offset="0" class="test-border">취소</el-col>
        <el-col :span="10" :offset="0" class="test-border"></el-col>
      </el-row>
    </el-col>
    <el-col :span="5" :offset="0" class="test-border"></el-col>
  </el-row>
</template>
