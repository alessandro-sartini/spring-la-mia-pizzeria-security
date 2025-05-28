package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.OfferRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

    @Autowired
    private OfferRepository repository;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") SpecialOffer formOffer, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "offers/edit-create";
        }
        repository.save(formOffer);

        return "redirect:/pizze/" + formOffer.getPizza().getId();
    }

    // seleziona
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Integer id) {
        SpecialOffer offer = repository.findById(id).orElse(null);
        System.out.println("DEBUG startDate: " + offer.getStartDate());
        System.out.println("DEBUG finishDate: " + offer.getFinishDate());
        model.addAttribute("offer", offer);
        model.addAttribute("edit", true);
        return "offers/edit-create";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("offer") SpecialOffer formOffer, BindingResult bindingResult,
            Model model, @PathVariable Integer id) {

        if (bindingResult.hasErrors()) {
            return "offers/edit-create";
        }
        repository.save(formOffer);

        return "redirect:/pizze/" + formOffer.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        repository.deleteById(id);

        return "redirect:/pizze";
    }

}
