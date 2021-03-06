package com.ssafy.match.group.service;

import com.ssafy.match.db.entity.City;
import com.ssafy.match.group.clubboard.board.repository.ClubBoardRepository;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.entity.club.Club;
import com.ssafy.match.group.clubboard.board.entity.ClubBoard;
import com.ssafy.match.member.entity.Member;
import com.ssafy.match.member.entity.MemberSns;
import com.ssafy.match.db.entity.Status;
import com.ssafy.match.db.entity.Techstack;
import com.ssafy.match.member.repository.MemberBeginnerTechstackRepository;
import com.ssafy.match.group.repository.club.MemberClubRepository;
import com.ssafy.match.member.repository.MemberExperiencedTechstackRepository;
import com.ssafy.match.member.repository.MemberRepository;
import com.ssafy.match.member.repository.MemberSnsRepository;
import com.ssafy.match.db.repository.TechstackRepository;
import com.ssafy.match.file.entity.DBFile;
import com.ssafy.match.file.repository.DBFileRepository;
import com.ssafy.match.group.dto.MemberDto;
import com.ssafy.match.group.dto.club.ClubDto;
import com.ssafy.match.group.dto.project.request.ProjectApplicationRequestDto;
import com.ssafy.match.group.dto.project.request.ProjectCreateRequestDto;
import com.ssafy.match.group.dto.project.request.ProjectUpdateRequestDto;
import com.ssafy.match.group.dto.project.response.InfoForApplyProjectFormResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectFormInfoResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectInfoForCreateResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectInfoForUpdateResponseDto;
import com.ssafy.match.group.dto.project.response.ProjectInfoResponseDto;
import com.ssafy.match.group.entity.project.CompositeMemberProject;
import com.ssafy.match.group.entity.project.CompositeProjectTechstack;
import com.ssafy.match.group.entity.project.MemberProject;
import com.ssafy.match.group.entity.project.Project;
import com.ssafy.match.group.entity.project.ProjectApplicationForm;
import com.ssafy.match.group.entity.project.ProjectTechstack;
import com.ssafy.match.group.repository.club.ClubRepository;
import com.ssafy.match.group.repository.project.MemberProjectRepository;
import com.ssafy.match.group.repository.project.ProjectApplicationFormRepository;
import com.ssafy.match.group.repository.project.ProjectRepository;
import com.ssafy.match.group.repository.project.ProjectTechstackRepository;
import com.ssafy.match.util.SecurityUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final ClubRepository clubRepository;
    private final MemberClubRepository memberClubRepository;
    private final MemberProjectRepository memberProjectRepository;
    private final ProjectApplicationFormRepository projectApplicationFormRepository;
    private final TechstackRepository techstackRepository;
    private final ProjectTechstackRepository projectTechstackRepository;
    private final DBFileRepository dbFileRepository;
    private final MemberExperiencedTechstackRepository memberExperiencedTechstackRepository;
    private final MemberBeginnerTechstackRepository memberBeginnerTechstackRepository;
    private final MemberSnsRepository memberSnsRepository;
//    private final ProjectBoardRepository projectBoardRepository;

    public ProjectInfoForCreateResponseDto getInfoForCreate() throws Exception {
        return ProjectInfoForCreateResponseDto.builder()
            .hostClub(makeClubDtos(memberClubRepository.
                findClubByMember(findMember(SecurityUtil.getCurrentMemberId()))))
            .build();
    }

    @Transactional
    public Long create(ProjectCreateRequestDto dto) throws Exception {
        validCity(dto.getCity());
        validTechstack(dto.getTechList());

        Project project = new Project(dto, findClub(dto.getClubId()), findDBFile(dto.getUuid()));
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        project.setMember(member);

        projectRepository.save(project);

//        makeBasicBoards(project);
        addTechstack(project, dto.getTechList());
        addMember(project, member, dto.getHostRole());

        return project.getId();
    }

    @Transactional
    public HttpStatus update(Long projectId, ProjectUpdateRequestDto dto) throws Exception {
        validCity(dto.getCity());
        validTechstack(dto.getAddStackList());
        validTechstack(dto.getRemoveStackList());
        validStatus(dto.getStatus());

        Project project = findProject(projectId);
        Long currentMemberId = SecurityUtil.getCurrentMemberId();

        if (!project.getMember().getId().equals(currentMemberId)) {
            throw new Exception("????????? ????????????.");
        }

        project.update(dto, findClub(dto.getClubId()), findDBFile(dto.getUuid()));
        project.setMember(findMember(dto.getHostId()));
        addTechstack(project, dto.getAddStackList());
        removeTechstack(project, dto.getRemoveStackList());

        return HttpStatus.OK;
    }

    @Transactional
    public HttpStatus delete(Long projectId) throws Exception {
        Project project = findProject(projectId);

        if (project.getMember().getId() != SecurityUtil.getCurrentMemberId()) {
            throw new Exception("????????? ????????????.");
        }

        List<MemberProject> memberProjects = memberProjectRepository.findMemberRelationInProject(project);
        for (MemberProject mem : memberProjects) {
            mem.deactivation();
        }

        List<ProjectTechstack> pts = projectTechstackRepository.findProjectTechstackByProject(project);
        for (ProjectTechstack pt: pts) {
            projectTechstackRepository.delete(pt);
        }

        project.setIsActive(false);

        return HttpStatus.OK;
    }

    public Page<ProjectInfoResponseDto> getAllProject(Pageable pageable) {
        Page<ProjectInfoResponseDto> projectInfoResponseDtos = projectRepository.findByIsActiveAndIsPublicAndStatusIsNot(Boolean.TRUE, Boolean.TRUE, Status.??????, pageable)
                .map(ProjectInfoResponseDto::of);
        for (ProjectInfoResponseDto projectInfoResponseDto: projectInfoResponseDtos.getContent()) {
            projectInfoResponseDto.setMemberDtos(makeMemberDtos(memberProjectRepository.findMemberByProjectId(projectInfoResponseDto.getId())));
        }
        return projectInfoResponseDtos;
    }

    // ?????? ???????????? ?????? ?????? ?????????
    public List<Member> findMemberInProject(Project project) {
        return memberProjectRepository.findMemberInProject(project);
    }

    // ?????? ????????? ?????? ????????? ?????? ?????????
    public List<String> projectTechstackName(Project project) {
        return projectTechstackRepository.findByProjectTechstackName(project);
    }

    // ?????? ???????????? ?????? ??????
    public ProjectInfoResponseDto getOneProject(Long projectId) throws Exception {
        Project project = findProject(projectId);
        if (!SecurityUtil.getCurrentMemberId().equals(project.getMember().getId())
            && !project.getIsPublic()) {
            throw new Exception("???????????? ?????????????????????.");
        }
        ProjectInfoResponseDto projectInfoResponseDto = projectRepository.findById(projectId).map(ProjectInfoResponseDto::of).orElseThrow(() -> new NullPointerException("??????????????? ???????????? ????????????."));
        projectInfoResponseDto.setMemberDtos(makeMemberDtos(memberProjectRepository.findMemberByProjectId(projectId)));
        projectInfoResponseDto.setDeveloperNicknames(memberNicknames(projectId, "?????????"));
        projectInfoResponseDto.setDesignerNicknames(memberNicknames(projectId, "????????????"));
        projectInfoResponseDto.setPlannerNicknames(memberNicknames(projectId, "?????????"));
        projectInfoResponseDto.setTechList(projectTechstackName(project));

        return projectInfoResponseDto;
    }

    public ProjectInfoForUpdateResponseDto getInfoForUpdateProject(Long projectId) throws Exception {
        Project project = findProject(projectId);
        if (!SecurityUtil.getCurrentMemberId().equals(project.getMember().getId())) {
            throw new Exception("????????? ????????????");
        }

        ProjectInfoForUpdateResponseDto dto = new ProjectInfoForUpdateResponseDto(project);
        dto.setHost(new MemberDto(project.getMember()));
        dto.setMemberDtos(makeMemberDtos(findMemberInProject(project)));
        dto.setProjectTechstack(projectTechstackName(project));
        dto.setClubList(makeClubDtos(memberClubRepository.findClubByMember(project.getMember())));
        dto.setMemberDtos(makeMemberDtos(findMemberInProject(project)));

        return dto;
    }

    // ?????? ??????????????? ?????? ????????? ??????????????? ?????? ????????? ??????
    public List<Member> memberInProject(Project project) {
        return memberProjectRepository.findMemberInProject(project);
    }

    // ?????? ??????????????? ?????? ????????? ????????? ????????? ?????????
    public List<String> memberNicknames(Long projectId, String role) throws Exception {
        return memberProjectRepository.findMemberNickname(findProject(projectId), role);
    }

    // ?????? ????????? ????????? ???????????? ?????????
    public List<ProjectInfoResponseDto> projectInMember(Long memberId) throws Exception {
//        List<ProjectInfoResponseDto> projectInfoResponseDtos = new ArrayList<>();
        System.out.println(memberProjectRepository.getProjectsByMemberAndStatus(findMember(memberId), Status.??????));
        List<Project> projects = memberProjectRepository.getProjectsByMemberAndStatus(findMember(memberId), Status.??????);
        System.out.println("!!!!!!!!!!!!");
        System.out.println(projects);
//        projects.ma
        List<ProjectInfoResponseDto> projectInfoResponseDtos = projects.stream().map(ProjectInfoResponseDto::of).collect(Collectors.toList());
        System.out.println("here!!!!!!!!!!!!!!!");
        System.out.println(projectInfoResponseDtos);
        for (ProjectInfoResponseDto projectInfoResponseDto: projectInfoResponseDtos) {
            projectInfoResponseDto.setMemberDtos(makeMemberDtos(memberProjectRepository.findMemberByProjectId(projectInfoResponseDto.getId())));
        }
//        for (Project project: memberProjectRepository.projectInMember(findMember(memberId))) {
//            if(project.getStatus().equals(Status.??????)) continue;
//            ProjectInfoResponseDto dto = new ProjectInfoResponseDto(project);
//            dto.setHost(new MemberDto(project.getMember()));
//            dto.setMemberDtos(makeMemberDtos(findMemberInProject(project)));
//            projectInfoResponseDtos.add(dto);
//        }
        return projectInfoResponseDtos;
    }

    // ?????? ??????????????? ?????? ?????????
    public List<String> allTechstackName() {
        return techstackRepository.findAllName();
    }

    @Transactional
    public void addTechstack(Project project, List<String> techName) {
        for (String name : techName) {
            Techstack techstack = findTechstack(name);
            CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
                techstack, project);

            projectTechstackRepository.save(new ProjectTechstack(compositeProjectTechstack));

        }
    }

    @Transactional
    public void removeTechstack(Project project, List<String> techName) {
        for (String name : techName) {
            Techstack techstack = findTechstack(name);
            CompositeProjectTechstack compositeProjectTechstack = new CompositeProjectTechstack(
                techstack, project);

            ProjectTechstack projectTechstack = projectTechstackRepository
                .findById(compositeProjectTechstack)
                .orElseThrow(() -> new NullPointerException("?????? ?????? ????????? ????????????????????????."));

            projectTechstackRepository.delete(projectTechstack);
        }
    }

    @Transactional
    public void addMember(Project project, Member member, String role) {
        CompositeMemberProject compositeMemberProject = new CompositeMemberProject(member, project);
        // DB??? ?????? ?????? ????????? ????????? ?????? ??????
        MemberProject memberProject = memberProjectRepository
            .findById(compositeMemberProject)
            .orElseGet(() -> MemberProject.builder()
                .compositeMemberProject(compositeMemberProject)
                .registerDate(LocalDateTime.now())
                .build());

        memberProject.activation();
        memberProjectRepository.save(memberProject);

        changeRole(project, member, role);
    }

    @Transactional
    public void removeMember(Long projectId) throws Exception {
        Project project = findProject(projectId);
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new NullPointerException("????????? ????????? ?????????.(????????? ??????)"));
        if(project.getMember().getId().equals(member.getId())){
            throw new Exception("?????????????????? ????????? ??? ????????????.");
        }
        CompositeMemberProject compositeMemberProject = new CompositeMemberProject(member, project);
        // DB??? ?????? ?????? ????????? ????????? ?????? ??????
        MemberProject memberProject = memberProjectRepository.findById(compositeMemberProject)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ???????????????."));
        memberProject.deactivation();
        changeRole(project, member, "");
    }

//    @Transactional
//    public void makeBasicBoards(Club club){
//        projectBoardRepository.save(new ProjectBoard("????????????", project));
//        projectBoardRepository.save(new ProjectBoard("?????????", project));
//    }

    public Project findProject(Long projectId) throws Exception {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new NullPointerException("???????????? ????????? ????????????."));

        if (!project.getIsActive()) {
            throw new Exception("????????? ?????????????????????.");
        }

        return project;
    }

    public Member findMember(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));

        if (member.getIs_active() == false) {
            throw new Exception("????????? ???????????????.");
        }

        return member;
    }

    public Techstack findTechstack(String techName) {
        return techstackRepository.findByName(techName)
            .orElseThrow(() -> new NullPointerException("?????? ?????? ????????? ????????????."));
    }

    public Club findClub(Long clubId) {
        if (clubId == null) {
            return null;
        }
        return clubRepository.findById(clubId)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));
    }

    public DBFile findDBFile(String uuid) {
        if (uuid == null) {
            return null;
        }
        return dbFileRepository.findById(uuid)
            .orElseThrow(() -> new NullPointerException("?????? ????????? ????????????."));
    }

    public void validCity(String city) throws Exception {
        if(!Stream.of(City.values()).map(Enum::name)
            .collect(Collectors.toList()).contains(city)){
            throw new Exception("???????????? ?????? ???????????????");
        }
    }
    public void validStatus(String status) throws Exception {
        if(!Stream.of(Status.values()).map(Enum::name)
            .collect(Collectors.toList()).contains(status)){
            throw new Exception("???????????? ?????? ???????????????");
        }
    }
    public void validTechstack(List<String> techstacks) throws Exception {
        for (String inTech: techstacks) {
            if(!techstackRepository.findAllName().contains(inTech)){
                throw new Exception("???????????? ?????? ?????? ???????????????");
            }
        }
    }

    public List<ClubDto> makeClubDtos(List<Club> hostClub) {
        List<ClubDto> clubDtos = new ArrayList<>();

        for (Club club : hostClub) {
            clubDtos.add(new ClubDto(club));
        }

        return clubDtos;
    }

    public List<MemberDto> makeMemberDtos(List<Member> members) {
        List<MemberDto> memberDtos = new ArrayList<>();

        for (Member member : members) {
            memberDtos.add(new MemberDto(member));
        }

        return memberDtos;
    }

    // (??????????????? ????????? ????????? ????????? If??? ?????? ?????? ??????)
    @Transactional
    public void changeRole(Project project, Member member, String role) {
        MemberProject memberProject = memberProjectRepository.findMemberProject(project, member);

        String now = memberProject.getRole();

        if (now == null) { // ?????? ????????? ????????????????????? ?????? ????????? ??????
            if (role.equals("????????????")) {
                project.plusDesigner();
                memberProject.setRole("????????????");
            } else if (role.equals("?????????")) {
                project.plusDeveloper();
                memberProject.setRole("?????????");
            } else if (role.equals("?????????")) {
                project.plusPlanner();
                memberProject.setRole("?????????");
            }
        } else if (now.equals(role)) { // ??????????????? ?????? ??????
            return;
        } else {
            if (now.equals("????????????")) {
                project.minusDesigner();

                if (role.equals("?????????")) {
                    project.plusDeveloper();
                    memberProject.setRole("?????????");
                } else if (role.equals("?????????")) {
                    project.plusPlanner();
                    memberProject.setRole("?????????");
                }
            } else if (now.equals("?????????")) {
                project.minusDeveloper();

                if (role.equals("?????????")) {
                    project.plusPlanner();
                    memberProject.setRole("?????????");
                } else if (role.equals("????????????")) {
                    project.plusDesigner();
                    memberProject.setRole("????????????");
                }
            } else if (now.equals("?????????")) {
                project.minusPlanner();

                if (role.equals("?????????")) {
                    project.plusDeveloper();
                    memberProject.setRole("?????????");
                } else if (role.equals("????????????")) {
                    project.plusDesigner();
                    memberProject.setRole("????????????");
                }
            }
        }

        if (project.getMember().getId().equals(member.getId())) {
            project.setHostRole(role);
        }

    }

    // ?????? ?????? ????????? ?????? ?????? ??? ?????? ??????
    public InfoForApplyProjectFormResponseDto getInfoForApply(Long projectId) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Project project = findProject(projectId);

        List<Member> memberList = memberInProject(project);
        for (Member mem: memberList) {
            if(SecurityUtil.getCurrentMemberId() == mem.getId()){
                throw new Exception("?????? ????????? ???????????????.");
            }
        }

        if(!project.getIsParticipate()){
            throw new Exception("?????? ???????????? ?????????????????????.");
        }

        InfoForApplyProjectFormResponseDto dto = InfoForApplyProjectFormResponseDto.builder()
            .nickname(member.getNickname())
            .position(member.getPosition()) // ????????? ?????????????????????
            .strong(memberExperiencedTechstackRepository.findTechstackByMemberName(member))
            .knowledgeable(memberBeginnerTechstackRepository.findTechstackByMemberName(member))
            .build();

        Optional<MemberSns> git = memberSnsRepository.findByMemberAndSnsName(member, "github");
        Optional<MemberSns> twitter = memberSnsRepository.findByMemberAndSnsName(member, "twitter");
        Optional<MemberSns> facebook = memberSnsRepository
            .findByMemberAndSnsName(member, "facebook");
        Optional<MemberSns> backjoon = memberSnsRepository
            .findByMemberAndSnsName(member, "backjoon");

        if (git.isPresent()) {
            dto.setGit(git.get().getSnsAccount());
        }
        if (twitter.isPresent()) {
            dto.setTwitter(twitter.get().getSnsAccount());
        }
        if (facebook.isPresent()) {
            dto.setFacebook(facebook.get().getSnsAccount());
        }
        if (backjoon.isPresent()) {
            dto.setBackjoon(backjoon.get().getSnsAccount());
        }

        return dto;
    }

    @Transactional
    public HttpStatus applyProject(Long projectId, ProjectApplicationRequestDto dto) throws Exception {
        Member member = findMember(SecurityUtil.getCurrentMemberId());
        Project project = findProject(projectId);

        CompositeMemberProject cmp = new CompositeMemberProject(member, project);

        Optional<ProjectApplicationForm> form = projectApplicationFormRepository.findById(cmp);
        if (form.isPresent()) {
            throw new Exception("????????? ????????? ???????????????.");
        }

        ProjectApplicationForm projectApplicationForm = new ProjectApplicationForm(cmp, dto);

        projectApplicationForm.setDbFile(findDBFile(dto.getUuid()));

        projectApplicationFormRepository.save(projectApplicationForm);
        return HttpStatus.OK;
    }

    // ?????? ????????? ????????? ?????? ???????????? ??????
    public List<ProjectFormInfoResponseDto> allProjectForm(Long projectId) throws Exception {
        Project project = findProject(projectId);

        if(SecurityUtil.getCurrentMemberId() != project.getMember().getId()){
            throw new Exception("?????? ????????? ????????????.");
        }

        List<ProjectApplicationForm> forms = projectApplicationFormRepository.formByProjectId(project);
        List<ProjectFormInfoResponseDto> projectFormInfoResponseDtos = new ArrayList<>();

        for (ProjectApplicationForm form: forms) {
            projectFormInfoResponseDtos.add(ProjectFormInfoResponseDto.builder()
                .form(form)
                .strong(memberExperiencedTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberProject().getMember()))
                .knowledgeable(memberBeginnerTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberProject().getMember()))
                .build());
        }

        return projectFormInfoResponseDtos;
    }

    // ??????????????? ????????? ??????
    public List<ProjectFormInfoResponseDto> allFormByProjectNickname(Long projectId, String nickname) throws Exception{
        Project project = findProject(projectId);

        if(SecurityUtil.getCurrentMemberId() != project.getMember().getId()){
            throw new Exception("?????? ????????? ????????????.");
        }

        List<ProjectApplicationForm> forms = projectApplicationFormRepository.formByProjectId(project);
        List<ProjectFormInfoResponseDto> projectFormInfoResponseDtos = new ArrayList<>();

        for (ProjectApplicationForm form: forms) {
            projectFormInfoResponseDtos.add(ProjectFormInfoResponseDto.builder()
                .form(form)
                .strong(memberExperiencedTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberProject().getMember()))
                .knowledgeable(memberBeginnerTechstackRepository
                    .findTechstackByMemberName(form.getCompositeMemberProject().getMember()))
                .build());
        }

        return projectFormInfoResponseDtos;
    }

    // ????????? ????????? ?????? ???????????? ????????? ?????? ????????? ???????????? (????????? ????????? ?????? ???????????? ??? ??????)
    public ProjectFormInfoResponseDto oneProjectForm(Long projectId, Long memberId) throws Exception {
        CompositeMemberProject cmp = new CompositeMemberProject(findMember(memberId), findProject(projectId));

        ProjectApplicationForm form = projectApplicationFormRepository.oneFormById(cmp)
            .orElseThrow(()-> new NullPointerException("???????????? ?????? ??????????????????"));

        return ProjectFormInfoResponseDto.builder()
            .form(form)
            .strong(memberExperiencedTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberProject().getMember()))
            .knowledgeable(memberBeginnerTechstackRepository
                .findTechstackByMemberName(form.getCompositeMemberProject().getMember()))
            .build();
    }

    // ?????? ??????
    @Transactional
    public HttpStatus approval(Long projectId, Long memberId) throws Exception {
        Project project = findProject(projectId);
        List<Member> members = memberInProject(project);
        Member member = findMember(memberId);

        for (Member mem: members) {
            if(mem.equals(member)){
                throw new Exception("?????? ?????????????????? ???????????????.");
            }
        }

        ProjectApplicationForm form = projectApplicationFormRepository
            .findById(new CompositeMemberProject(findMember(memberId), findProject(projectId)))
            .orElseThrow(() -> new NullPointerException("???????????? ?????? ??????????????????."));

        addMember(project, member, form.getRole());
        reject(projectId, memberId);

        return HttpStatus.OK;
    }

    // ????????? ??????
    @Transactional
    public HttpStatus reject(Long projectId, Long memberId) throws Exception {
        projectApplicationFormRepository.delete(projectApplicationFormRepository
            .findById(new CompositeMemberProject(findMember(memberId), findProject(projectId)))
            .orElseThrow(() -> new NullPointerException("???????????? ?????? ??????????????????.")));

        return HttpStatus.OK;
    }
}
