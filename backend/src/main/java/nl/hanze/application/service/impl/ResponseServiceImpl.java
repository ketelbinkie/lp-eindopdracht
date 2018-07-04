package nl.hanze.application.service.impl;


import nl.hanze.application.domain.Response;
import nl.hanze.application.repositories.ResponseRepository;
import nl.hanze.application.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("responseService")
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public List<Response> findDistinctByEnqueteId(String id) {
        return responseRepository.findDistinctByEnqueteId(id);
    }

}

