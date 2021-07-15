package com.schedguap.schedguap.Services;


import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Entities.Repositories.ProfessorsRepository;
import com.schedguap.schedguap.Entities.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private PupilGroupRepository pupilGroupRepository;


    public List<PupilGroup> getGroups() {
        return pupilGroupRepository.getAllBy();
    }

    public PupilGroup getGroup(String id) throws UserException {
        Optional<PupilGroup> group = pupilGroupRepository.findById(id);
        if(group.isPresent()) {
            return group.get();
        } else {
            throw new UserException(404, "404", "group doesn't exist", "");
        }
    }


}
