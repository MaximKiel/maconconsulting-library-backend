package ru.maconconsulting.librarybackend.utils.validators.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maconconsulting.librarybackend.dto.parameters.TypeDTO;
import ru.maconconsulting.librarybackend.models.parameters.Type;
import ru.maconconsulting.librarybackend.services.parameters.TypesService;

@Component
public class TypeValidator implements Validator {

    private final TypesService typesService;

    @Autowired
    public TypeValidator(TypesService typesService) {
        this.typesService = typesService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Type.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TypeDTO typeDTO = (TypeDTO) target;
        if (typesService.findByName(typeDTO.getName()).isPresent()) {
            errors.rejectValue("name", "404", "Такой тип исследования уже существует!");
        }
    }
}
