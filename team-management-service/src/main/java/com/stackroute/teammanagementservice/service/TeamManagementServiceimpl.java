package com.stackroute.teammanagementservice.service;

import com.stackroute.teammanagementservice.domain.Idea;
import com.stackroute.teammanagementservice.domain.ServiceProvider;
import com.stackroute.teammanagementservice.dto.IdeaDto;
import com.stackroute.teammanagementservice.exception.IdeaTitleAlreadyExistException;
import com.stackroute.teammanagementservice.repository.TeamManagementRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.properties")
@Service

public class TeamManagementServiceimpl implements TeamManagementService {
    private TeamManagementRepository teamManagementRepository;

    @Autowired
    public TeamManagementServiceimpl(TeamManagementRepository teamManagementRepository) {
        this.teamManagementRepository = teamManagementRepository;
    }

    @Override
    public Idea saveIdea(Idea idea) throws IdeaTitleAlreadyExistException {
        if(teamManagementRepository.findByTitle(idea.getTitle())!=null){
            throw new IdeaTitleAlreadyExistException("Title Already Exists");
        }
        Idea savedIdea = teamManagementRepository.save(idea);
        return savedIdea;
    }

    @Override
    public Idea updateSelectedTeam(Idea idea) {
        Idea retrievedIdea = teamManagementRepository.findByTitle(idea.getTitle());
        List<ServiceProvider> serviceProviders = new ArrayList<>();
        serviceProviders = idea.getSelectedTeam();
        retrievedIdea.setSelectedTeam(serviceProviders);
        return teamManagementRepository.save(retrievedIdea);
    }

    @Override
    public Idea updateAppliedTeam(Idea idea) {
        Idea retrievedIdea = teamManagementRepository.findByTitle(idea.getTitle());
        List<ServiceProvider> serviceProviders;
        if (retrievedIdea.getAppliedTeam() == null) {
            serviceProviders = new ArrayList<>();
        } else {
            serviceProviders = retrievedIdea.getAppliedTeam();
        }
        serviceProviders.add(idea.getAppliedTeam().get(0));
        return teamManagementRepository.save(retrievedIdea);
    }

    @Override
    public Idea updateInvitedTeam(Idea idea) {
        Idea retrievedIdea = teamManagementRepository.findByTitle(idea.getTitle());
        List<ServiceProvider> serviceProviders;
        if(retrievedIdea.getInvitedTeam()==null) {
            serviceProviders = new ArrayList<>();
        }else {
            serviceProviders = retrievedIdea.getInvitedTeam();
        }
        serviceProviders.add(idea.getInvitedTeam().get(0));
        return teamManagementRepository.save(retrievedIdea);
    }

//    @Override
//    public Idea acceptssp(Idea idea) {
//        Idea retrievedIdea = teamManagementRepository.findByTitle(idea.getTitle());
//    }

    @RabbitListener(queues = "${idea.rabbitmq.queue}")
    public void receive(IdeaDto ideaDTO) {

        Idea idea = new Idea();
        idea.setTitle(ideaDTO.getTitle());
        idea.setDescription(ideaDTO.getDescription());
        idea.setDuration(ideaDTO.getDuration());
        idea.setDomain(ideaDTO.getDomain());
        idea.setCost(ideaDTO.getCost());
        idea.setRole(ideaDTO.getRole());
        idea.setStatus(ideaDTO.getStatus());
        idea.setLocation(ideaDTO.getLocation());
        teamManagementRepository.save(idea);
        System.out.println(ideaDTO);
    }

}
