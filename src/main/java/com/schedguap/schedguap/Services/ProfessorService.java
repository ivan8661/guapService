package com.schedguap.schedguap.Services;


import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.Repositories.ProfessorsRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {


    @Autowired
    private ProfessorsRepository professorsRepository;


    public List<Professor> getProfessors() {
        return professorsRepository.getAllBy();
    }

    public Professor getProfessor(String id) throws UserException {
      Optional<Professor> professor = professorsRepository.findById(id);
      if(professor.isPresent()) {
          return professor.get();
      } else {
          throw new UserException(404, "404", "professor doesn't exist", "");
      }
    }








}
