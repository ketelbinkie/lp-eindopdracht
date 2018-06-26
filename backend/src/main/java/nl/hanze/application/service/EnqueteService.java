package nl.hanze.application.service;


import nl.hanze.application.domain.Enquete;
import nl.hanze.application.domain.PersonEnquete;;

import java.util.List;

public interface EnqueteService {

    Enquete save(Enquete enquete);
    List<Enquete> findAll();
    Enquete findById(int id);

}
