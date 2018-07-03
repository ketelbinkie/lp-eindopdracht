package nl.hanze.application.service;


import nl.hanze.application.domain.Response;

import java.util.List;

public interface ResponseService {

    List<Response> findDistinctByEnqueteId(String id);


}
