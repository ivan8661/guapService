package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.GroupService;
import com.schedguap.schedguap.Services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ControllerAdvice
public class MemberController {

    private final ProfessorService professorService;

    private final GroupService groupService;

    @Autowired
    public MemberController(ProfessorService professorService, GroupService groupService) {
        this.professorService = professorService;
        this.groupService = groupService;
    }

    @GetMapping("/professors")
    public ResponseEntity<List<Professor>> professors() {
        return ResponseEntity.ok().body(professorService.getProfessors());
    }

    @GetMapping("/professors/{professorId}")
    public ResponseEntity<Professor> professors(@PathVariable("professorId") String professorId) throws UserException {
        return ResponseEntity.ok().body(professorService.getProfessor(professorId));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<PupilGroup>> groups() {
        return ResponseEntity.ok().body(groupService.getGroups());
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<PupilGroup> group(@PathVariable("groupId") String groupId) throws UserException {
        return ResponseEntity.ok().body(groupService.getGroup(groupId));
    }





}
