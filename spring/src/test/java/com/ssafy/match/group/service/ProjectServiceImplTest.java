package com.ssafy.match.group.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ssafy.match.db.entity.Authority;
import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.group.dto.project.ProjectInfoResponseDto;
import com.ssafy.match.group.dto.project.ProjectUpdateRequestDto;
import com.ssafy.match.group.repository.MemberProjectRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.group.dto.project.ProjectCreateRequestDto;
import com.ssafy.match.group.entity.Club;
import com.ssafy.match.group.entity.MemberProject;
import com.ssafy.match.group.entity.Project;
import com.ssafy.match.group.entity.ProjectTechstack;
import com.ssafy.match.group.repository.ClubRepository;
import com.ssafy.match.group.repository.ProjectRepository;
import com.ssafy.match.group.repository.ProjectTechstackRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProjectServiceImplTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClubRepository clubRepository;
    @Autowired
    TechstackRepository techstackRepository;
    @Autowired
    ProjectTechstackRepository projectTechstackRepository;
    @Autowired
    ProjectServiceImpl projectServiceImpl;
    @Autowired
    MemberProjectRepository memberProjectRepository;

    @BeforeEach
    void setUp() throws Exception {
        Member member1 = Member.builder()
            .create_date(LocalDateTime.now())
            .email("qjawlsqjacks@naver.com")
            .name("박범진")
            .password("1234")
            .nickname("BJP")
            .tel("01028732329")
            .bio("테스트 멤버 1")
            .city("부천")
            .banned(false)
            .position("개발자")
            .is_active(true)
            .authority(Authority.ROLE_USER)
            .dbFile(null)
            .build();

        memberRepository.save(member1);

        Member member2 = Member.builder()
            .create_date(LocalDateTime.now())
            .email("qjawlsqjacks@naver.com")
            .name("박범찬")
            .password("1234")
            .nickname("BCP")
            .tel("01028732329")
            .bio("테스트 멤버 2")
            .city("구미")
            .banned(false)
            .position("기획자")
            .is_active(true)
            .authority(Authority.ROLE_USER)
            .dbFile(null)
            .build();
        memberRepository.save(member2);

        Club club1 = new Club("헬스", member1, LocalDateTime.now(), "냠", City.구미, 1,  6, true, true, null);
        clubRepository.save(club1);

        Techstack techstack1 = new Techstack("Java");
        techstackRepository.save(techstack1);
        Techstack techstack2 = new Techstack("Spring");
        techstackRepository.save(techstack2);
        Techstack techstack3 = new Techstack("Python");
        techstackRepository.save(techstack3);

        List<String> techList = new ArrayList<>();
        techList.add("Java");
        techList.add("Python");

        ProjectCreateRequestDto dto = ProjectCreateRequestDto.builder()
            .techList(techList)
            .name("프로젝트")
            .hostId(1L)
            .schedule("매주 화 6시")
            .period(7)
            .bio("매칭 프로젝트")
            .developerMaxCount(3)
            .plannerMaxCount(3)
            .designerMaxCount(3)
            .city("부천")
            .isPublic(false)
            .clubId(null)
            .uuid(null)
            .hostRole("디자이너")
            .build();

        Project project = Project.builder()
            .name(dto.getName())
            .member(member1)
            .createDate(LocalDateTime.now())
            .modifyDate(LocalDateTime.now())
            .schedule(dto.getSchedule())
            .period(dto.getPeriod())
            .bio(dto.getBio())
            .developerCount(0)
            .developerMaxCount(dto.getDeveloperMaxCount())
            .plannerCount(0)
            .plannerMaxCount(dto.getPlannerMaxCount())
            .designerCount(0)
            .designerMaxCount(dto.getDesignerMaxCount())
            .city(City.from(dto.getCity()))
            .status(Status.모집중)
            .isActive(true)
            .isPublic(dto.isPublic())
            .isParticipate(true)
            .build();

        projectRepository.save(project);

        projectServiceImpl.setDBFile(project.getId(), dto.getUuid());
        projectServiceImpl.setClub(project.getId(), dto.getClubId());
        projectServiceImpl.createTechstack(project.getId());
        projectServiceImpl.addTechstack(project.getId(), dto.getTechList());
        projectServiceImpl.addMember(project.getId(), dto.getHostId(), dto.getHostRole());

        projectServiceImpl.addMember(1L, 2L, "기획자");

    }

    @Test
    @Disabled
    void create() throws Exception {
        Member member1 = memberRepository.findById(1L)
            .orElseThrow(() -> new NullPointerException("없으"));

        List<String> techList = new ArrayList<>();
        techList.add("Java");
        techList.add("Python");

        ProjectCreateRequestDto dto = ProjectCreateRequestDto.builder()
            .techList(techList)
            .name("프로젝트")
            .hostId(1L)
            .schedule("매주 화 6시")
            .period(7)
            .bio("매칭 프로젝트")
            .developerMaxCount(3)
            .plannerMaxCount(3)
            .designerMaxCount(3)
            .city("부천")
            .isPublic(false)
            .clubId(null)
            .uuid(null)
            .hostRole("디자이너")
            .build();

        Project project = Project.builder()
            .name(dto.getName())
            .member(member1)
            .createDate(LocalDateTime.now())
            .modifyDate(LocalDateTime.now())
            .schedule(dto.getSchedule())
            .period(dto.getPeriod())
            .bio(dto.getBio())
            .developerCount(0)
            .developerMaxCount(dto.getDeveloperMaxCount())
            .plannerCount(0)
            .plannerMaxCount(dto.getPlannerMaxCount())
            .designerCount(0)
            .designerMaxCount(dto.getDesignerMaxCount())
            .city(City.from(dto.getCity()))
            .status(Status.모집중)
            .isActive(true)
            .isPublic(dto.isPublic())
            .isParticipate(true)
            .build();

        projectRepository.save(project);

        projectServiceImpl.setDBFile(project.getId(), dto.getUuid());
        projectServiceImpl.setClub(project.getId(), dto.getClubId());
        projectServiceImpl.createTechstack(project.getId());
        projectServiceImpl.addTechstack(project.getId(), dto.getTechList());
        projectServiceImpl.addMember(project.getId(), dto.getHostId(), dto.getHostRole());

        List<ProjectTechstack> list = projectTechstackRepository.findByProjectTechstack(project);
        System.out.println("=========================");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).isActive() + " ");
        }
        System.out.println("\n" + "=========================");
        List<MemberProject> memberProjects = memberProjectRepository.findMemberWithProject(project);
        System.out.println("=========================");
        for (int i = 0; i < memberProjects.size(); i++) {
            System.out.print(memberProjects.get(i).getRole());
        }
        System.out.println("\n" + "=========================");
        System.out.println(project.getDesignerCount());
        System.out.println(project.getHostRole());

        assertEquals("박범진", project.getMember().getName());
        assertEquals(1, project.getDesignerCount());
        assertEquals(City.부천, project.getCity());

    }

    @Test
    void update() throws Exception {
        Member member1 = projectServiceImpl.findMember(1L);
        Project project = projectServiceImpl.findProject(1L);

        if(project.getMember().getId() != 1L) {
            new Exception("권한이 없습니다.");
        }

        List<String> addStackList = new ArrayList<>();
        addStackList.add("Spring");
        List<String> removeStackList = new ArrayList<>();
        removeStackList.add("Java");
        removeStackList.add("Python");

        ProjectUpdateRequestDto dto = ProjectUpdateRequestDto.builder()
            .addStackList(addStackList)
            .removeStackList(removeStackList)
            .name("업데이트 프로젝트")
            .hostId(2L)
            .schedule("야미")
            .bio("아 졸려")
            .period(1)
            .developerMaxCount(4)
            .designerMaxCount(4)
            .plannerMaxCount(4)
            .city("광주")
            .status(Status.종료됨)
            .isPublic(false)
            .isParticipate(false)
            .clubId(null)
            .uuid(null)
            .hostRole("기획자")
            .build();

        project.setName(dto.getName());
//        project.setMember(member2);
        project.setSchedule(dto.getSchedule());
        project.setBio(dto.getBio());
        project.setPeriod(dto.getPeriod());
        project.setModifyDate(LocalDateTime.now());
        project.setDeveloperMaxCount(dto.getDeveloperMaxCount());
        project.setDesignerMaxCount(dto.getDesignerMaxCount());
        project.setPlannerMaxCount(dto.getPlannerMaxCount());
        project.setCity(City.from(dto.getCity()));
        project.setStatus(dto.getStatus());
        project.setPublic(dto.isPublic());
        project.setParticipate(dto.isParticipate());
        projectRepository.save(project);

        projectServiceImpl.setDBFile(project.getId(), dto.getUuid());
        projectServiceImpl.setClub(project.getId(), dto.getClubId());
        projectServiceImpl.addTechstack(project.getId(), dto.getAddStackList());
        projectServiceImpl.removeTechstack(project.getId(), dto.getRemoveStackList());
        projectServiceImpl.changeRole(project.getId(), member1.getId(), dto.getHostRole());

        List<ProjectTechstack> list = projectTechstackRepository.findByProjectTechstack(project);
        System.out.println("=========================");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).isActive() + " ");
        }
        System.out.println("\n" + "=========================");

        List<MemberProject> memberProjects = memberProjectRepository.findMemberWithProject(project);
        System.out.println("=========================");
        for (int i = 0; i < memberProjects.size(); i++) {
            System.out.print(memberProjects.get(i).getRole() + " ");
        }
        System.out.println("\n" + "=========================");

        Project project2 = projectServiceImpl.findProject(1L);
        Member member2 = projectServiceImpl.findMember(2L);
        assertEquals("기획자", project2.getHostRole());
        assertEquals("기획자", memberProjectRepository.findMemberProject(project2, member2).getRole());
        assertEquals(0, project2.getDesignerCount());
        assertEquals(2, project2.getPlannerCount());
    }

    @Test
    void delete() throws Exception {
        Project project = projectServiceImpl.findProject(1L);

        if(project.getMember().getId() != 1L) {
            new Exception("권한이 없습니다.");
        }

        List<MemberProject> memberProjects = memberProjectRepository.findMemberWithProject(project);
        for (MemberProject mem: memberProjects) {
            mem.deactivation();
            memberProjectRepository.save(mem);
        }

        project.setActive(false);
        projectRepository.save(project);

        System.out.println("==============");
        for (MemberProject mem: memberProjects) {
            System.out.println(mem.isActive() + " ");
        }
        System.out.println("=============");
        assertEquals(false, project.isActive());
    }

    @Test
    void projectInfo() throws Exception {
        ProjectInfoResponseDto dto = projectServiceImpl.projectInfo(1L);
        System.out.println(dto);
    }

    @Test
    void projectMember() {
    }

    @Test
    void memberNicknames() throws Exception {
        List<String> develop = projectServiceImpl.memberNicknames(1L, "디자이너");
        for (String str: develop) {
            System.out.println("===============");
            System.out.println(str);
        }
        assertEquals("BJP", develop.get(0));
        List<String> planner = projectServiceImpl.memberNicknames(1L, "기획자");
        for (String str: planner) {
            System.out.println("===============");
            System.out.println(str);
        }
        assertEquals("BCP", planner.get(0));
    }

    @Test
    void projectInMember() throws Exception {
        List<Project> list = projectServiceImpl.projectInMember(1L);
        for (Project pro: list) {
            System.out.println("========");
            System.out.println(pro.getName());
        }
        assertEquals("디자이너", list.get(0).getHostRole());
    }

    @Test
    void createTechstack() {
    }

    @Test
    void addTechstack() {
    }

    @Test
    void addMember() throws Exception {
        projectServiceImpl.addMember(1L, 2L, "기획자");
        Member member = projectServiceImpl.findMember(2L);
        Project project = projectServiceImpl.findProject(1L);

        assertEquals("디자이너", project.getHostRole());
        assertEquals("기획자", memberProjectRepository.findMemberProject(project, member).getRole());
        assertEquals(1, project.getDesignerCount());
        assertEquals(1, project.getPlannerCount());
    }

    @Test
    void removeTechstack() {
    }

    @Test
    void removeMember(){

    }

    @Test
    void setClub(){

    }

    @Test
    void setDBFile(){

    }

    @Test
    void changeRole() throws Exception {
        projectServiceImpl.changeRole(1L, 1L, "개발자");
        Member member = projectServiceImpl.findMember(1L);
        Project project = projectServiceImpl.findProject(1L);

        assertEquals("개발자", project.getHostRole());
        assertEquals("개발자", memberProjectRepository.findMemberProject(project, member).getRole());
        assertEquals(0, project.getDesignerCount());
        assertEquals(1, project.getDeveloperCount());
    }

}