package com.elitbet.service;

import com.elitbet.model.Request;
import com.elitbet.model.Priority;
import com.elitbet.repository.RequestRepository;
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
