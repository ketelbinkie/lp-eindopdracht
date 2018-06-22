package nl.hanze.application.service.impl;


import nl.hanze.application.domain.Enquete;
import nl.hanze.application.repositories.EnqueteRepository;
import nl.hanze.application.service.EnqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("enqueteService")
public class EnqueteServiceImpl implements EnqueteService {

    private final EnqueteRepository enqueteRepository;

    @Autowired
    public EnqueteServiceImpl(EnqueteRepository enqueteRepository) {
        this.enqueteRepository = enqueteRepository;
    }

    public Enquete save(Enquete enquete) {return enqueteRepository.save(enquete);}
    public List<Enquete> findAll() {return enqueteRepository.findAll();}
    public Enquete findById(int id) {return enqueteRepository.findById(id);}

}

