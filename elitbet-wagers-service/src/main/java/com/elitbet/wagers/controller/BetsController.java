package com.elitbet.wagers.controller;

import com.elitbet.wagers.model.dto.WagerDTO;
import com.elitbet.wagers.model.entities.Wager;
import com.elitbet.wagers.services.WagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 * @author Roman Malyarchuk
 *
 * GET /{id} Returns wager by id given
 * GET /
 * POST /
 * DELETE /{id}
 * PATCH /{id}
 *
 *
 *
 *
 *
 */
@RestController
@RequestMapping(value = "/bets")
public class BetsController {
    private final static Logger logger = Logger.getLogger(BetsController.class.getName());

    @Autowired
    WagerService wagerService;

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public WagerDTO getById(@PathVariable long id){
        return new WagerDTO(wagerService.getByIdForCurrentUser(id));
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<WagerDTO> getAllForCurrentUser(@RequestParam(name = "page", required = false,defaultValue = "0") int page,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "20") int pageSize,
                                  @RequestParam(name = "sort", required = false, defaultValue = "desc") String sort){
        Sort.Direction direction = ("desc".equals(sort.toLowerCase()))? Sort.Direction.DESC: Sort.Direction.ASC;

        return wagerService.
                getAllForCurrentUser(PageRequest
                        .of(page, pageSize, new Sort(direction,"wagerId")))
                .stream()
                .map(WagerDTO::new)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = {"","/"}, method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public List<WagerDTO> postWagers(@RequestBody WagerDTO[] wagerDTOS){
        List<WagerDTO> wagerDTOSresponse = new LinkedList<>();

        for(WagerDTO wagerDTO:wagerDTOS){
            Wager wager = wagerDTO.toEntity();
            wagerDTOSresponse.add(new WagerDTO(wagerService.saveWager(wager)));
        }

        return wagerDTOSresponse;
    }

    /*@PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWager(@PathVariable long id){
        wagerService.deleteById(id);
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteAll(@RequestBody WagerDTO[] wagerDTOS){
        for(WagerDTO wagerDTO:wagerDTOS){
            wagerService.deleteById(wagerDTO.getWagerId());
        }
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public WagerDTO patchWager(@RequestBody WagerDTO wagerDTO, @PathVariable long id){
        wagerDTO.setWagerId(id);
        Wager wager = wagerDTO.toEntity();

        return new WagerDTO(wagerService.updateWager(wager));
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.PATCH, consumes = "application/json")
    @Transactional
    public List<WagerDTO> patchWagers(@RequestBody WagerDTO[] wagerDTOS){
        List<WagerDTO> wagerDTOSresponse = new LinkedList<>();
        for(WagerDTO wagerDTO:wagerDTOS){
            Wager wager = wagerDTO.toEntity();
            wagerDTOSresponse.add(new WagerDTO(wagerService.updateWager(wager)));
        }
        return wagerDTOSresponse;
    }*/


}
