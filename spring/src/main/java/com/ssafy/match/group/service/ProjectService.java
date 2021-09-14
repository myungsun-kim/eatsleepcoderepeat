package com.ssafy.match.group.service;

import com.ssafy.match.group.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    // (아이디어가 생각이 안나서 임시로 If문 사용 조언 구함)
    public void changeRole(Project project, String role) { // 역할 수정했을 때 프로젝트 인원 변화

        String now = project.getHost_role(); // 현재 프로젝트에서의 역할

        if (now.isBlank()) { // 이제 생성된 프로젝트이거나 이제 가입한 경우
            if (role.equals("디자이너")) {
                project.plusDesigner();
            } else if (role.equals("개발자")) {
                project.plusDeveloper();
            } else if (role.equals("기획자")) {
                project.plusPlanner();
            }
        } else if (now.equals(role)) { // 변경사항이 없는 경우
            return;
        } else {
            if (now.equals("디자이너")) {
                project.minusDesigner();

                if (role.equals("개발자")) {
                    project.plusDeveloper();
                } else if (role.equals("기획자")) {
                    project.plusPlanner();
                }
            } else if (now.equals("개발자")) {
                project.minusDeveloper();

                if (role.equals("기획자")) {
                    project.plusPlanner();
                } else if (role.equals("디자이너")) {
                    project.plusDesigner();
                }
            } else if (now.equals("기획자")) {
                project.minusPlanner();

                if (role.equals("개발자")) {
                    project.plusDeveloper();
                } else if (role.equals("디자이너")) {
                    project.plusDesigner();
                }
            }
        }

        project.setHost_role(role);
    }
}
