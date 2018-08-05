package com.edutilos.controller;


import com.edutilos.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.edutilos.dao.WorkerDAO;
import com.edutilos.dao.WorkerDAOJPAImpl;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {
    private WorkerDAO dao;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());
    public WorkerController() {
        dao = new WorkerDAOJPAImpl();
    }
    @RequestMapping(value="/insert/{id}/{name}/{age}/{wage}/{active}", method = RequestMethod.GET, produces = "application/json")
    public ModelAndView insert(@PathVariable long id, @PathVariable String name,
                       @PathVariable int age, @PathVariable double wage,
                       @PathVariable boolean active) {
        dao.insert(new Worker(id, name, age, wage, active));
        return new ModelAndView("redirect:/worker/findAll");
    }

    @PostMapping("/insert")
    public ModelAndView insert(@ModelAttribute Worker worker) {
        try {
            logger.info(worker.toString());
            dao.insert(worker);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return new ModelAndView("redirect:/worker/findAll");
    }


    @RequestMapping(value="/findAll", method=RequestMethod.GET, produces="application/json")
    public ModelAndView findAll() {
        List<Worker> all = dao.findAll();
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("workers", all);
        return mav;
    }
}
