package com.schedguap.schedguap.Services.ScheduleUser;

import com.schedguap.schedguap.Database.Entities.Professor;
import com.schedguap.schedguap.Database.Entities.PupilGroup;
import com.schedguap.schedguap.Database.Repositories.ProfessorsRepository;
import com.schedguap.schedguap.Database.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ScheduleUserService {


    private final PupilGroupRepository pupilGroupRepository;

    private final ProfessorsRepository professorsRepository;


    @Autowired
    public ScheduleUserService(PupilGroupRepository pupilGroupRepository,
                               ProfessorsRepository professorsRepository) {
        this.professorsRepository = professorsRepository;
        this.pupilGroupRepository = pupilGroupRepository;
    }

    public ScheduleUser getScheduleUser(String id) throws UserException {
        Optional<PupilGroup> pupilGroup = pupilGroupRepository.findById(id);
        Optional<Professor> professor = professorsRepository.getById(id);
        if(pupilGroup.isEmpty() && professor.isEmpty()) {
            throw new UserException(404, "404", "ScheduleUser doesn't exist", "check your scheduser id!!!");
        }

        return pupilGroup.map(
                group -> new ScheduleUser(group.getId(), group.getName()))
                .orElseGet(() -> new ScheduleUser(professor.get().getId(), professor.get().getFullName())
                );

    }


}
