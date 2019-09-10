package com.stackroute.teammanagementservice.controller;
import com.stackroute.teammanagementservice.domain.Idea;
import com.stackroute.teammanagementservice.exception.IdeaTitleAlreadyExistException;
import com.stackroute.teammanagementservice.service.TeamManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
public class TeamManagementController {
    private TeamManagementService teamManagementService;

    @Autowired
    public TeamManagementController(TeamManagementService teamManagementService) {
        this.teamManagementService = teamManagementService;
    }

    @PostMapping("idea")
    public ResponseEntity<?> postIdea(@RequestBody Idea idea) throws IdeaTitleAlreadyExistException {
        ResponseEntity responseEntity;
        teamManagementService.saveIdea(idea);
        responseEntity = new ResponseEntity<String>("sucessfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("selectedTeam")
    public ResponseEntity<?> updateIdea(@RequestBody Idea idea)  {
        Idea updatedSelectedTeam = teamManagementService.updateSelectedTeam(idea);
        return new ResponseEntity<>(updatedSelectedTeam, HttpStatus.OK);
    }

    @PutMapping("appliedTeam")
    public ResponseEntity<?> updateAppliedTeam(@RequestBody Idea idea){
        Idea updatedAppliedTeam = teamManagementService.updateAppliedTeam(idea);
        return new ResponseEntity<>(updatedAppliedTeam, HttpStatus.OK);
    }

    @PutMapping("invitedTeam")
    public ResponseEntity<?> updateInvitedTeam(@RequestBody Idea idea){
        Idea updatedInvitedTeam = teamManagementService.updateInvitedTeam(idea);
        return new ResponseEntity<>(updatedInvitedTeam, HttpStatus.OK);
    }

//    @PutMapping("acceptssp")
//    public ResponseEntity<?> acceptServiceProvider(@RequestBody Idea idea){
//        Idea acceptedsp = teamManagementService.acceptssp(idea);
//        return new ResponseEntity<>(acceptedsp, HttpStatus.OK);
//
//    }



}
