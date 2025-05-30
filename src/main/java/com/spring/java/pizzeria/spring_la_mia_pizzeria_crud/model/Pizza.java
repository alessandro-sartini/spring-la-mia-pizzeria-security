package com.spring.java.pizzeria.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 20, message = "name must have min: 4 char and max: 20")
    @Column(nullable = false, length = 20)
    @NotBlank
    private String name;

    // ! large Object
    @Lob
    @NotBlank
    private String description;

    @NotBlank(message = "the description not be null/empty")
    private String urlPhoto;

    @NotNull(message = "Il prezzo non può essere nullo.")
    @Min(value = 4, message = "Il prezzo deve essere almeno 4.")
    private BigDecimal price;

    // Per evitare  RICORSIONEEEE
    // @JsonManagedReference
    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> offers;

    // @JsonManagedReference
    @ManyToMany
    @JoinTable( 
        name = "ingredient_pizza",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name="ingredient_id")
    )
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<SpecialOffer> getOffers() {
        return this.offers;
    }

    public void setOffers(List<SpecialOffer> offers) {
        this.offers = offers;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPhoto() {
        return this.urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
