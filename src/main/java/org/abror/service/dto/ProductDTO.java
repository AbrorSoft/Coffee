package org.abror.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.abror.domain.enumeration.ProductType;

/**
 * A DTO for the {@link org.abror.domain.Product} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0")
    private Double price;

    private String imageKey;

    private byte[] imageFile;

    @NotNull
    private String gram;

    private String calories;

    private ProductType type;

    @NotNull
    private Boolean isDiet;

    private List<String> ingredients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public String getGram() {
        return gram;
    }

    public void setGram(String gram) {
        this.gram = gram;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Boolean getIsDiet() {
        return isDiet;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
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
