package org.abror.service.convertor;

import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nurislom
 * @see org.abror.service.convertor
 * @since 11/29/2024 10:59 PM
 */
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private static final String DELIMITER = ",";

    /**
     *
     * @param attribute  the entity attribute value to be converted
     * @return
     */
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return String.join(DELIMITER, attribute);
    }

    /**
     *
     * @param dbData  the data from the database column to be
     *                converted
     * @return
     */
    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(dbData.split(DELIMITER));
    }
}
