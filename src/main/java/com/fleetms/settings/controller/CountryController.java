package com.fleetms.settings.controller;

import com.fleetms.settings.services.CountryService;
import com.fleetms.settings.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    //This Method inherit the getAll method from the service class
  /*  @GetMapping("/countries")
    public String getAll(Model model, String keyword)
    {
        List<Country> countries ;
          if(keyword == null) {
             countries = countryService.getAll();
          }else
          {
             countries = countryService.getByKeyword(keyword);
          }

        model.addAttribute("countries", countries);
        return "parameters/countryList";
    }*/

    @GetMapping("/countries")
    public String getAllPages(Model model){
        return getOnePage(model, 1);
    }

    @GetMapping("/countries/page/{pageNumber}")
    public String  getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Country> page = countryService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Country> countries = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("countries", countries);

        return "/parameters/countryList";
    }

    @GetMapping("/countries/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir) {

        Page<Country> page = countryService.findAllWithSort(field, sortDir, currentPage);
        List<Country> countries = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("countries", countries);
        return "/parameters/countryList";
    }

   /* @GetMapping("/countries/{field}")
    public String getAllWithSort(Model model, @PathVariable("field") String field, @PathParam("sortDir") String sortDir)
    {
        List<Country> countries ;
            countries = countryService.findAllWithSort(field, sortDir);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc": "asc");
        model.addAttribute("countries", countries);
        return "parameters/countryList";
    }*/

    //The Get Country By Id
    @GetMapping("/parameters/country/{id}")
    @ResponseBody
    public Country getCountry(@PathVariable Integer id){
       return countryService.getBYId(id);
    }

    @GetMapping("/countryAdd")
    public String addCountry()
    {
        return "/parameters/countryAdd";
    }

    @GetMapping("/countryEdit/{id}")
    public String editCountry(@PathVariable Integer id, Model model)
    {
        Country country = countryService.getBYId(id);
        model.addAttribute("country", country);
        return "/parameters/countryEdit";
    }


    @GetMapping("/countryDetails/{id}")
    public String detailsCountry(@PathVariable Integer id, Model model)
    {
        Country country = countryService.getBYId(id);
        model.addAttribute("country", country);
        return "/parameters/countryDetails";
    }
    @PostMapping("/countries")
    public String save(Country country)
    {
        countryService.save(country);
        return "redirect:/countries";
    }

    @RequestMapping(value ="/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id)
    {
        countryService.delete(id);
        return "redirect:/countries";
    }

    @RequestMapping(value ="/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Country country)
    {
        countryService.save(country);
        return "redirect:/countries";
    }
}
