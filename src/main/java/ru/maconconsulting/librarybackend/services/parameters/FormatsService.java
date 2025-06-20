package ru.maconconsulting.librarybackend.services.parameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maconconsulting.librarybackend.models.parameters.Format;
import ru.maconconsulting.librarybackend.repositories.parameters.FormatsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FormatsService implements CommonParametersService<Format> {

    private final FormatsRepository formatsRepository;

    @Autowired
    public FormatsService(FormatsRepository formatsRepository) {
        this.formatsRepository = formatsRepository;
    }

    @Override
    public List<Format> findAll() {
        return formatsRepository.findAll();
    }

    @Override
    public Optional<Format> findByName(String name) {
        return formatsRepository.findByName(name);
    }

    @Override
    @Transactional
    public void save(Format entity) {
        enrichParameterFieldEntity(entity);
        formatsRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(String name, Format updatedEntity) {
        Optional<Format> currentFormat = findByName(name);
        if (currentFormat.isPresent()) {
            updatedEntity.setId(currentFormat.get().getId());
            updatedEntity.setCreatedAt(currentFormat.get().getCreatedAt());
            updatedEntity.setProjects(currentFormat.get().getProjects());
        }
        formatsRepository.save(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(String name) {
        formatsRepository.deleteByName(name);
    }
}
