package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Ingredient;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model moedl) {

        moedl.addAttribute("ingredients", ingredientRepository.findAll());

        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {

        model.addAttribute(
                "ingredient", ingredientRepository.findById(id).get());

        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ingredient", new Ingredient());

        return "ingredients/edit-create";
    }

    @PostMapping("/create")
    public String strore(@Valid @ModelAttribute("ingredient") Ingredient formIngredient,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "ingredients/edit-create";
        }

        ingredientRepository.save(formIngredient);
        return "redirect:/ingredient";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).get());
        model.addAttribute("edit", true);

        return "ingredients/edit-create";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/edit-create";
        }

        ingredientRepository.save(formIngredient);
        return "redirect:/ingredient";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Ingredient ingredientToDelate= ingredientRepository.findById(id).get();

        for (Pizza pizzaToDelate : ingredientToDelate.getPizzas()) {
            pizzaToDelate.getIngredients().remove(ingredientToDelate);
        }
        ingredientRepository.delete(ingredientToDelate);

        return "redirect:/ingredient";
    }
    


}
