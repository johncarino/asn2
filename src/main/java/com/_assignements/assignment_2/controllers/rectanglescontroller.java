package com._assignements.assignment_2.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com._assignements.assignment_2.models.rectangle;
import com._assignements.assignment_2.models.rectanglerepo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class rectanglescontroller {
    @Autowired
    private rectanglerepo recRepo;
  
    @GetMapping("/objects/view")
    public String getAllRectangles(Model model) {
      System.out.println("Getting all rectangles");
      List<rectangle> rectangles = recRepo.findAll();
      model.addAttribute("rectangles", rectangles);
      return "showAll";
    }
  
    @PostMapping("/objects/delete/{uId}")
    public String deleteRectangle(@PathVariable int uId) {
      recRepo.deleteById(uId);
      return "redirect:/objects/view";
    }
  
    @PostMapping("/objects/update/{uId}")
    public String updateRectangle(@PathVariable int uId, @RequestParam Map<String, String> updatedRectangle) {
      String newName = updatedRectangle.get("name");
      int newWidth = Integer.parseInt(updatedRectangle.get("width"));
      int newHeight = Integer.parseInt(updatedRectangle.get("height"));
  
      Optional<rectangle> rectangleOptional = recRepo.findById(uId);
      if (rectangleOptional.isPresent()){
        rectangle rectangle = rectangleOptional.get();
        rectangle.setName(newName);
        rectangle.setWidth(newWidth);
        rectangle.setHeight(newHeight);
        recRepo.save(rectangle);
      }
      return "redirect:/objects/view";
    }
  
    @GetMapping("/objects/detail/{uId}")
    public String showRectangleDetail(@PathVariable int uId, Model model) {
      Optional<rectangle> rectangle = recRepo.findById(uId);
      if (rectangle.isPresent()){
        model.addAttribute("rectangle", rectangle.get());
        return "recDetail";
      } else {
        return "redirect:/objects/view";
      }
    }
  
    @PostMapping("/objects/add")
    public String addRectangle(@RequestParam Map<String, String> newRectangle, HttpServletResponse response) {
      System.out.println("ADD rectangle");
      String newName = newRectangle.get("name");
      String newColour = newRectangle.get("colour");
      int newWidth = Integer.parseInt(newRectangle.get("width"));
      int newHeight = Integer.parseInt(newRectangle.get("height"));
      recRepo.save(new rectangle(newName, newColour, newWidth, newHeight));
      response.setStatus(201);
      return "redirect:/objects/view";
    }
}
