package com.schedguap.schedguap.Services;


import GetGraphQL.QueryParametersBuilder;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Entities.ListAnswer;
import com.schedguap.schedguap.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private PupilGroupRepository pupilGroupRepository;


    public ListAnswer<PupilGroup> getGroups(Map<String, String> params) throws NoSuchFieldException {

        QueryParametersBuilder<PupilGroup> queryBuilder = new QueryParametersBuilder<>(params, PupilGroup.class);
        Specification<PupilGroup> spc = queryBuilder.getSpecification(null);
        Pageable pageable = queryBuilder.getPage();

        Page<PupilGroup> page = pupilGroupRepository.findAll(spc, pageable);
        return new ListAnswer<>(page);
    }

    public PupilGroup getGroup(String id) throws UserException {
        Optional<PupilGroup> group = pupilGroupRepository.findById(id);
        if(group.isPresent()) {
            return group.get();
        } else {
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND);
        }
    }


}
