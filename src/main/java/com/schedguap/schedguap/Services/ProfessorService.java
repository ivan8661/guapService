package com.schedguap.schedguap.Services;


import GetGraphQL.QueryParametersBuilder;
import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.ListAnswer;
import com.schedguap.schedguap.Entities.Repositories.ProfessorsRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfessorService {


    private final ProfessorsRepository professorsRepository;

    @Autowired
    public ProfessorService(ProfessorsRepository professorsRepository) {
        this.professorsRepository = professorsRepository;
    }

    public ListAnswer<Professor> getProfessors(Map<String, String> params) throws NoSuchFieldException {

        QueryParametersBuilder<Professor> queryBuilder = new QueryParametersBuilder<>(params, Professor.class);
        Specification<Professor> spc = queryBuilder.getSpecification(null);
        Pageable pageable = queryBuilder.getPage();
        Page<Professor> page = professorsRepository.findAll(spc, pageable);
        return new ListAnswer(page);
    }

    public Professor getProfessor(String id) throws UserException {
      Optional<Professor> professor = professorsRepository.findById(id);
      if(professor.isPresent()) {
          return professor.get();
      } else {
          throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "professor doesn't exist", null);
      }
    }
}
