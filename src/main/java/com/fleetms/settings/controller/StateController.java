package com.fleetms.settings.controller;

import com.fleetms.settings.model.Country;
import com.fleetms.settings.services.CountryService;
import com.fleetms.settings.services.StateService;
import com.fleetms.settings.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class StateController {

    @Autowired
    private StateService stateService;
    @Autowired
    private CountryService countryService;

    public Model addModelAttributes(Model model)
    {
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("countries", countryService.getAll());
        return model;
    }
   /* @GetMapping("/states")
    public String getAll(Model model, String keyword)
    {
        List<State> states ;
        if(keyword == null) {
            states = stateService.findAll();
        }else
        {
            states = stateService.getByKeyword(keyword);
        }

        model.addAttribute("states", states);
        return "parameters/states";
    }*/

   @GetMapping("/states")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/states/page/{pageNumber}")
    public String  getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<State> page = stateService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<State> states = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("states", states);

        return "/parameters/states";
    }

    @GetMapping("/states/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir) {

        Page<State> page = stateService.findAllWithSort(field, sortDir, currentPage);
        List<State> states = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("states", states);
        return "/parameters/states";
    }


    //Get All States
    /*@GetMapping("/states")
    public String findAll(Model model)
    {
        addModelAttributes(model);
        return "parameters/states";
    }*/

    @GetMapping("/parameters/stateAdd")
    public String addState(Model model)
    {
        addModelAttributes(model);
        return "/parameters/stateAdd";
    }

    @GetMapping("/parameters/state/{op}/{id}")
    public String editState(@PathVariable Integer id, @PathVariable String op, Model model)
    {
        addModelAttributes(model);
        model.addAttribute("state", stateService.findById(id));
        return "/parameters/state" +op;
    }

    //Add State
    @PostMapping("/parameters/states")
    public String addNew(State state)
    {
        stateService.save(state);
        return "redirect:/states";
    }

    @RequestMapping(value ="/parameters/states/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id) {
        stateService.delete(id);
        return "redirect:/states";
    }

}