package com.example.jpawithh2.controller;

import com.example.jpawithh2.dao.AlienRepo;
import com.example.jpawithh2.model.Alien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    AlienRepo alienRepo;

    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @RequestMapping("/addAlien")
    public String addAlien(Alien alien){
        alienRepo.save(alien);
        return "home";
    }

    @RequestMapping("/getAlien")
    public ModelAndView getAlien(@RequestParam int aid){
        ModelAndView mv = new ModelAndView("showAlien");
        Alien alien = alienRepo.findById(aid).orElse(new Alien());

        System.out.println(alienRepo.findByTech("java"));
        System.out.println(alienRepo.findByAidGreaterThan(10));
        System.out.println(alienRepo.findByTechSortedByName("java"));

        mv.addObject(alien);
        return mv;
    }

    @RequestMapping("/aliens")
    @ResponseBody
    //@Response body is used to indicate that we return data in string not view name
    public String getAliens(){
        return alienRepo.findAll().toString();
    }
}
