package org.abror.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.abror.domain.enumeration.ProductType;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link org.abror.domain.Product} entity. This class is used
 * in {@link org.abror.web.rest.ProductResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /products?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductCriteria implements Serializable, Criteria {

    /**
     * Class for filtering ProductType
     */
    public static class ProductTypeFilter extends Filter<ProductType> {

        public ProductTypeFilter() {}

        public ProductTypeFilter(ProductTypeFilter filter) {
            super(filter);
        }

        @Override
        public ProductTypeFilter copy() {
            return new ProductTypeFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter description;

    private DoubleFilter price;

    private StringFilter imageKey;

    private IntegerFilter age;

    private FloatFilter height;

    private FloatFilter weight;

    private StringFilter gram;

    private StringFilter calories;

    private ProductTypeFilter type;

    private BooleanFilter isDiet;

    private Boolean distinct;

    public ProductCriteria() {}

    public ProductCriteria(ProductCriteria other) {
        this.id = other.optionalId().map(LongFilter::copy).orElse(null);
        this.name = other.optionalName().map(StringFilter::copy).orElse(null);
        this.description = other.optionalDescription().map(StringFilter::copy).orElse(null);
        this.price = other.optionalPrice().map(DoubleFilter::copy).orElse(null);
        this.imageKey = other.optionalImageKey().map(StringFilter::copy).orElse(null);
        this.age = other.optionalAge().map(IntegerFilter::copy).orElse(null);
        this.height = other.optionalHeight().map(FloatFilter::copy).orElse(null);
        this.weight = other.optionalWeight().map(FloatFilter::copy).orElse(null);
        this.gram = other.optionalGram().map(StringFilter::copy).orElse(null);
        this.calories = other.optionalCalories().map(StringFilter::copy).orElse(null);
        this.type = other.optionalType().map(ProductTypeFilter::copy).orElse(null);
        this.isDiet = other.optionalIsDiet().map(BooleanFilter::copy).orElse(null);
        this.distinct = other.distinct;
    }

    @Override
    public ProductCriteria copy() {
        return new ProductCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public Optional<LongFilter> optionalId() {
        return Optional.ofNullable(id);
    }

    public LongFilter id() {
        if (id == null) {
            setId(new LongFilter());
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public Optional<StringFilter> optionalName() {
        return Optional.ofNullable(name);
    }

    public StringFilter name() {
        if (name == null) {
            setName(new StringFilter());
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getDescription() {
        return description;
    }

    public Optional<StringFilter> optionalDescription() {
        return Optional.ofNullable(description);
    }

    public StringFilter description() {
        if (description == null) {
            setDescription(new StringFilter());
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public DoubleFilter getPrice() {
        return price;
    }

    public Optional<DoubleFilter> optionalPrice() {
        return Optional.ofNullable(price);
    }

    public DoubleFilter price() {
        if (price == null) {
            setPrice(new DoubleFilter());
        }
        return price;
    }

    public void setPrice(DoubleFilter price) {
        this.price = price;
    }

    public StringFilter getImageKey() {
        return imageKey;
    }

    public Optional<StringFilter> optionalImageKey() {
        return Optional.ofNullable(imageKey);
    }

    public StringFilter imageKey() {
        if (imageKey == null) {
            setImageKey(new StringFilter());
        }
        return imageKey;
    }

    public void setImageKey(StringFilter imageKey) {
        this.imageKey = imageKey;
    }

    public Optional<IntegerFilter> optionalAge() {
        return Optional.ofNullable(age);
    }

    public IntegerFilter getAge() {
        return age;
    }

    public void setAge(IntegerFilter age) {
        this.age = age;
    }

    public Optional<FloatFilter> optionalHeight() {
        return Optional.ofNullable(height);
    }

    public FloatFilter getHeight() {
        return height;
    }

    public void setHeight(FloatFilter height) {
        this.height = height;
    }

    public Optional<FloatFilter> optionalWeight() {
        return Optional.ofNullable(weight);
    }

    public FloatFilter getWeight() {
        return weight;
    }

    public void setWeight(FloatFilter weight) {
        this.weight = weight;
    }

    public StringFilter getGram() {
        return gram;
    }

    public Optional<StringFilter> optionalGram() {
        return Optional.ofNullable(gram);
    }

    public StringFilter gram() {
        if (gram == null) {
            setGram(new StringFilter());
        }
        return gram;
    }

    public void setGram(StringFilter gram) {
        this.gram = gram;
    }

    public StringFilter getCalories() {
        return calories;
    }

    public Optional<StringFilter> optionalCalories() {
        return Optional.ofNullable(calories);
    }

    public StringFilter calories() {
        if (calories == null) {
            setCalories(new StringFilter());
        }
        return calories;
    }

    public void setCalories(StringFilter calories) {
        this.calories = calories;
    }

    public ProductTypeFilter getType() {
        return type;
    }

    public Optional<ProductTypeFilter> optionalType() {
        return Optional.ofNullable(type);
    }

    public ProductTypeFilter type() {
        if (type == null) {
            setType(new ProductTypeFilter());
        }
        return type;
    }

    public void setType(ProductTypeFilter type) {
        this.type = type;
    }

    public BooleanFilter getIsDiet() {
        return isDiet;
    }

    public Optional<BooleanFilter> optionalIsDiet() {
        return Optional.ofNullable(isDiet);
    }

    public BooleanFilter isDiet() {
        if (isDiet == null) {
            setIsDiet(new BooleanFilter());
        }
        return isDiet;
    }

    public void setIsDiet(BooleanFilter isDiet) {
        this.isDiet = isDiet;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public Optional<Boolean> optionalDistinct() {
        return Optional.ofNullable(distinct);
    }

    public Boolean distinct() {
        if (distinct == null) {
            setDistinct(true);
        }
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProductCriteria that = (ProductCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(description, that.description) &&
            Objects.equals(price, that.price) &&
            Objects.equals(imageKey, that.imageKey) &&
            Objects.equals(age, that.age) &&
            Objects.equals(height, that.height) &&
            Objects.equals(weight, that.weight) &&
            Objects.equals(gram, that.gram) &&
            Objects.equals(calories, that.calories) &&
            Objects.equals(type, that.type) &&
            Objects.equals(isDiet, that.isDiet) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, imageKey, age, height, weight, gram, calories, type, isDiet, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductCriteria{" +
            optionalId().map(f -> "id=" + f + ", ").orElse("") +
            optionalName().map(f -> "name=" + f + ", ").orElse("") +
            optionalDescription().map(f -> "description=" + f + ", ").orElse("") +
            optionalPrice().map(f -> "price=" + f + ", ").orElse("") +
            optionalImageKey().map(f -> "imageKey=" + f + ", ").orElse("") +
            optionalAge().map(f -> "age=" + f + ", ").orElse("") +
            optionalHeight().map(f -> "height=" + f + ", ").orElse("") +
            optionalWeight().map(f -> "weight=" + f + ", ").orElse("") +
            optionalGram().map(f -> "gram=" + f + ", ").orElse("") +
            optionalCalories().map(f -> "calories=" + f + ", ").orElse("") +
            optionalType().map(f -> "type=" + f + ", ").orElse("") +
            optionalIsDiet().map(f -> "isDiet=" + f + ", ").orElse("") +
            optionalDistinct().map(f -> "distinct=" + f + ", ").orElse("") +
        "}";
    }
}
