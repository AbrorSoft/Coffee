package org.abror.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

import org.abror.domain.enumeration.ProductType;
import org.abror.service.convertor.StringListConverter;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image_key")
    private String imageKey;

    @NotNull
    @Column(name = "gram", nullable = false)
    private String gram;

    @Column(name = "calories")
    private String calories;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;

    @NotNull
    @Column(name = "is_diet", nullable = false)
    private Boolean isDiet;

    @Convert(converter = StringListConverter.class)
    private List<String> ingredients;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return this.price;
    }

    public Product price(Double price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageKey() {
        return this.imageKey;
    }

    public Product imageKey(String imageKey) {
        this.setImageKey(imageKey);
        return this;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getGram() {
        return this.gram;
    }

    public Product gram(String gram) {
        this.setGram(gram);
        return this;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }

    public String getCalories() {
        return this.calories;
    }

    public Product calories(String calories) {
        this.setCalories(calories);
        return this;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public ProductType getType() {
        return this.type;
    }

    public Product type(ProductType type) {
        this.setType(type);
        return this;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Boolean getIsDiet() {
        return this.isDiet;
    }

    public Product isDiet(Boolean isDiet) {
        this.setIsDiet(isDiet);
        return this;
    }

    public void setIsDiet(Boolean isDiet) {
        this.isDiet = isDiet;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", imageKey='" + getImageKey() + "'" +
            ", gram='" + getGram() + "'" +
            ", calories='" + getCalories() + "'" +
            ", type='" + getType() + "'" +
            ", isDiet='" + getIsDiet() + "'" +
            ", ingredients='" + getIngredients() + "'" +
            "}";
    }
}
