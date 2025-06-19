package ru.maconconsulting.librarybackend.utils.validators.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maconconsulting.librarybackend.dto.parameters.FormatDTO;
import ru.maconconsulting.librarybackend.models.parameters.Format;
import ru.maconconsulting.librarybackend.services.parameters.FormatsService;

@Component
public class FormatValidator implements Validator {

    private final FormatsService formatsService;

    @Autowired
    public FormatValidator(FormatsService formatsService) {
        this.formatsService = formatsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Format.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormatDTO formatDTO = (FormatDTO) target;
        if (formatsService.findByName(formatDTO.getName()).isPresent()) {
            errors.rejectValue("name", "404", "Такой формат отчета уже существует!");
        }
    }
}
