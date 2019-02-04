package com.elitbet.live.scores.parser.service;

import com.elitbet.live.scores.parser.model.Request;
import com.elitbet.live.scores.parser.model.Priority;
import com.elitbet.live.scores.parser.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    List<Request> getEventsWithLowPriority(){
        return requestRepository.findAllByPriority(Priority.LOW);
    }

    List<Request> getEventsWithHighPriority(){
        return requestRepository.findAllByPriority(Priority.HIGH);
    }

    public void updateRequestList(List<Request> requestList) throws Exception{
        requestRepository.deleteAll();
        requestRepository.saveAll(requestList);
    }

}
