package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.OfferRepository;
import com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.repo.PizzaRepository;

@Service
public class SpecialOfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    PizzaRepository pizzaRepository;

    public List<SpecialOffer> findaAll() {
        return offerRepository.findAll();
    }

    public List<SpecialOffer> findaAllSortedName() {

        return offerRepository.findAll(Sort.by("name"));

    }

    public SpecialOffer getById(Integer id) {

        Optional<SpecialOffer> offerOptional = offerRepository.findById(id);

        return offerOptional.get();
    }

    public Optional<SpecialOffer> findById(Integer id) {
        return offerRepository.findById(id);
    }

    public SpecialOffer create(SpecialOffer offer) {

        return offerRepository.save(offer);

    }

    public SpecialOffer updateOffer(SpecialOffer offer) {
        return offerRepository.save(offer);

    }

    public void delete(SpecialOffer offer) {

        offerRepository.delete(offer);
    }

    public void deleteById(Integer id) {
        SpecialOffer offertoDelete = getById(id);
        offerRepository.delete(offertoDelete);
    }

    public boolean existById(Integer id) {
        return offerRepository.existsById(id);
    }

    public boolean existOffer(SpecialOffer offer) {
        return offerRepository.existsById(offer.getId());
    }

}
