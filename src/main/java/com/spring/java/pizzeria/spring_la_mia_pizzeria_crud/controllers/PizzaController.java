package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.Pizza;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.services.PizzaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzaList = pizzaService.findAll();

        model.addAttribute("pizzaList", pizzaList);

        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        Pizza result = pizzaService.getByid(id);

        model.addAttribute("pizza", result);
        return "pizzas/show";
    }

    @GetMapping("/searchByName")
    public String searchIndex(Model model, @RequestParam(name = "query") String query) {

        List<Pizza> pizzaList = pizzaService.findByNameOrDescription(query);

        model.addAttribute("pizzaList", pizzaList);

        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("pizza", new Pizza());
        return "pizzas/edit-create";

    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            // return "pizzas/edit";
            model.addAttribute("ingredients", ingredientRepository.findAll());

            return "pizzas/edit-create";
        }
        pizzaService.create(formPizza);
        return "redirect:/pizze";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("ingredients", ingredientRepository.findAll());

        model.addAttribute("pizza", pizzaService.getByid(id));
        model.addAttribute("edit", true);

        return "pizzas/edit-create";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());

            return "pizzas/edit-create";
        }
        pizzaService.create(formPizza);
        return "redirect:/pizze";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        // Pizza pizza = pizzaService.getByid(id);

        pizzaService.delateById(id);
        return "redirect:/pizze";
    }

    @GetMapping("/offers/{id}")
    public String offers(@PathVariable Integer id, Model model) {

        SpecialOffer offer = new SpecialOffer();
        offer.setPizza(pizzaService.getByid(id));
        model.addAttribute("offer", offer);
        return "offers/edit-create";
    }

}
// @Autowired
// private PizzaRepository repository;

// @Autowired
// private OfferRepository offerRepository;

// @GetMapping("/{id}")
// public String show(Model model, @PathVariable("id") int id) {
// Optional<Pizza> result = repository.findById(id);

// model.addAttribute("pizza", result.orElse(null));
// return "pizzas/show";
// }

// @GetMapping("/searchByName")
// public String searchIndex(Model model, @RequestParam(name = "name") String
// name) {

// List<Pizza> pizzaList = repository.findByNameContaining(name);

// model.addAttribute("pizzaList", pizzaList);

// return "pizzas/index";

// }
// @PostMapping("/delete/{id}")
// public String delete(@PathVariable("id") Integer id) {
// Pizza pizza = repository.findById(id).get();

// for (SpecialOffer offer : pizza.getOffers()) {
// offerRepository.delete(offer);
// }

// repository.deleteById(id);
// return "redirect:/pizze";
// }