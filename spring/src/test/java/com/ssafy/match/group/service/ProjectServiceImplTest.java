package com.ssafy.match.group.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ssafy.match.db.entity.Authority;
import com.ssafy.match.db.entity.City;
import com.ssafy.match.db.entity.Member;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.db.repository.MemberProjectRepository;
import com.ssafy.match.db.repository.MemberRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.group.dto.ProjectCreateRequestDto;
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
import org.springframework.test.annotation.Rollback;
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
    void setUp() {
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

        projectServiceImpl.setDBFile(project, dto.getUuid());
        projectServiceImpl.setClub(project, dto.getClubId());
        projectServiceImpl.changeRole(project, "디자이너");

        projectRepository.save(project);

        projectServiceImpl.createTechstack(project.getId());
        projectServiceImpl.addTechstack(project.getId(), dto.getTechList());
        projectServiceImpl.addMember(project.getId(), dto.getHostId(), dto.getHostRole());

    }

    @Test
    @Disabled
    void create() {
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

        projectServiceImpl.setDBFile(project, dto.getUuid());
        projectServiceImpl.setClub(project, dto.getClubId());
        projectServiceImpl.changeRole(project, "디자이너");

        projectRepository.save(project);

        projectServiceImpl.createTechstack(project.getId());
        projectServiceImpl.addTechstack(project.getId(), dto.getTechList());
        projectServiceImpl.addMember(project.getId(), dto.getHostId(), dto.getHostRole());

        List<ProjectTechstack> list = projectTechstackRepository.findByProjectTechstack(project);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).isActive() + " ");
        }
        List<MemberProject> memberProjects = memberProjectRepository.findMemberWithProject(project);
        for (int i = 0; i < memberProjects.size(); i++) {
            System.out.print(memberProjects.get(i).getRole());
        }
        assertEquals(project.getMember().getName(), "박범진");
        assertEquals(project.getDesignerCount(), 1);
        assertEquals(project.getCity(), City.부천);

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void projectInfo() {
    }

    @Test
    void projectMember() {
    }

    @Test
    void roleInfo() {
    }

    @Test
    void createTechstack() {
    }

    @Test
    void addTechstack() {
    }

    @Test
    void removeTechstack() {
    }



}