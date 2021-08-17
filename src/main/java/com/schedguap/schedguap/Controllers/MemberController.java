package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.GroupService;
import com.schedguap.schedguap.Services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Professor>> professors(@RequestParam Map<String, String> params) throws NoSuchFieldException {
        return ResponseEntity.ok().body(professorService.getProfessors(params));
    }

    @GetMapping("/professors/{professorId}")
    public ResponseEntity<Professor> professors(@PathVariable("professorId") String professorId) throws UserException {
        return ResponseEntity.ok().body(professorService.getProfessor(professorId));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<PupilGroup>> groups(@RequestParam Map<String, String> params) throws NoSuchFieldException {
        return ResponseEntity.ok().body(groupService.getGroups(params));
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<PupilGroup> group(@PathVariable("groupId") String groupId) throws UserException {
        return ResponseEntity.ok().body(groupService.getGroup(groupId));
    }





}
