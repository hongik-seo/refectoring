package com.freeboard.freeboard_jpa.controller;

import java.util.HashMap;
import java.util.Map;

import com.freeboard.freeboard_jpa.domain.Entity;
import com.freeboard.freeboard_jpa.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/free")
public class Controller {

    @Autowired
    private Service freeBoardService;

    @GetMapping("/main")
    public ModelAndView helloWorld(Model model,@PageableDefault(size = 10)  Pageable pageable) {
        Page<Entity> list=freeBoardService.mainList(pageable);
        model.addAttribute("boardList",list);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("", model);
        return modelAndView;
    }


    @GetMapping("/insert")
    public ModelAndView insert() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("insert");

        return modelAndView;
    }


    @GetMapping("/insertPro")
    @ResponseBody
    public Map<String,Object> insertPro(Model model,
                                        @RequestParam(defaultValue = "") String title,
                                        @RequestParam(defaultValue = "")String content ,
                                        @RequestParam(defaultValue = "") String name,
                                        @RequestParam(defaultValue = "") String codeType
    ) {
        Map<String,Object> map=new HashMap<String, Object>();
        Entity insert=freeBoardService.insertList(title,content,name,codeType);
        model.addAttribute(insert);
        map.put("insert", model);
        return map;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam(name = "num", value="num", required = false) Long num,Model model) {

        Entity detailEntity=freeBoardService.detail(num);
        model.addAttribute("detail",detailEntity);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("detail");
        modelAndView.addObject("", model);
        return modelAndView;
    }

    @PutMapping(value = "/update/{num}" )
    public Map<String,Entity> update(@RequestBody Map<String,Object> updateMap) {
        Map<String,Entity> returnMap=new HashMap<String, Entity>();
        Entity updateEnt=freeBoardService.update(updateMap);
        returnMap.put("update", updateEnt);
        return returnMap;
    }



    @GetMapping("/delete/{num}")
    @ResponseBody
    public Map<String,Long> delete(@PathVariable Long num) {
        Map<String,Long> map=new HashMap<String, Long>();
        freeBoardService.delete(num);
        map.put("delete", num);
        return map;
    }

    @GetMapping("/search")
    @ResponseBody
    public Page<Entity> search(
            @RequestParam(name ="option",value="option", required = false) String option,
            @RequestParam(name="keyword",value="keyword", required = false) String keyword,
            @PageableDefault(size=10) Pageable pageable)
    {
        Page<Entity> list=freeBoardService.search(option, keyword,pageable);
        return list;
    }


}
