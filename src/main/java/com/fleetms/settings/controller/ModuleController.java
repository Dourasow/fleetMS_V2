package com.fleetms.settings.controller;

import com.fleetms.settings.model.Module;
import com.fleetms.settings.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    //This Method inherit the getAll method from the service class
    @GetMapping("/modules")
    public String getAll(Model model)
    {
        List<Module> modules =  moduleService.getAll();
        model.addAttribute("modules", modules);
        return "parameters/modules";
    }

    //The Get Country By Id
    @GetMapping("/parameters/module/{id}")
    @ResponseBody
    public Module getModuleById(@PathVariable Integer id){
       return moduleService.getBYId(id);
    }

    @GetMapping("/moduleAdd")
    public String addModule()
    {
        return "/parameters/moduleAdd";
    }

    @GetMapping("/moduleEdit/{id}")
    public String editModule(@PathVariable Integer id, Model model)
    {
        Module module = moduleService.getBYId(id);
        model.addAttribute("module", module);
        return "/parameters/moduleEdit";
    }


    @GetMapping("/moduleDetails/{id}")
    public String detailsModule(@PathVariable Integer id, Model model)
    {
        Module module = moduleService.getBYId(id);
        model.addAttribute("module", module);
        return "/parameters/moduleDetails";
    }

    @PostMapping("/modules")
    public String save(Module module)
    {
        moduleService.save(module);
        return "redirect:/modules";
    }

    @RequestMapping(value ="/modules/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id)
    {
        moduleService.delete(id);
        return "redirect:/modules";
    }

    @RequestMapping(value ="/modules/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Module module)
    {
        moduleService.save(module);
        return "redirect:/modules";
    }
}
