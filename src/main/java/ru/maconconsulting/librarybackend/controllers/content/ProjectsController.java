package ru.maconconsulting.librarybackend.controllers.content;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maconconsulting.librarybackend.dto.content.ProjectDTO;
import ru.maconconsulting.librarybackend.dto.parameters.*;
import ru.maconconsulting.librarybackend.models.content.Project;
import ru.maconconsulting.librarybackend.models.parameters.*;
import ru.maconconsulting.librarybackend.services.content.ProjectsService;
import ru.maconconsulting.librarybackend.services.parameters.*;
import ru.maconconsulting.librarybackend.utils.exceptions.content.ProjectNotFoundException;
import ru.maconconsulting.librarybackend.utils.search.SearchProject;
import ru.maconconsulting.librarybackend.utils.validators.content.ProjectValidator;

import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    public static final Logger log = LoggerFactory.getLogger(ProjectsController.class);
    public static final String YANDEX_DISK_LINK = "https://disk.yandex.ru/client/disk/MRG/Проекты";
    private final ProjectsService projectsService;
    private final TypesService typesService;
    private final SegmentsService segmentsService;
    private final FormatsService formatsService;
    private final ModelMapper modelMapper;
    private final ProjectValidator projectValidator;

    @Autowired
    public ProjectsController(ProjectsService projectsService, TypesService typesService, SegmentsService segmentsService, FormatsService formatsService, ModelMapper modelMapper, ProjectValidator projectValidator) {
        this.projectsService = projectsService;
        this.typesService = typesService;
        this.segmentsService = segmentsService;
        this.formatsService = formatsService;
        this.modelMapper = modelMapper;
        this.projectValidator = projectValidator;
    }

    @GetMapping
    public String getAll() {
        log.info("Show manage projects view - call ProjectsController method getAll()");
        return "content/projects/manage";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Project project = projectsService.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Проект с ID=" + id + " не найден"));
        model.addAttribute("project", convertToProjectDTO(project));
        model.addAttribute("link", YANDEX_DISK_LINK + "/" + project.getYear()
                + ". Проекты/" + project.getTitle());
        log.info("Show project with ID=" + id + " page view - call ProjectsController method show()");
        return "content/projects/show";
    }

    @GetMapping("/new")
    public String newProject(@ModelAttribute("project") ProjectDTO projectDTO, Model model) {
        addParametersToModelAttribute(model);
        log.info("Show view for create new project - call ProjectsController method newProject()");
        return "content/projects/new";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("project") @Valid ProjectDTO projectDTO, BindingResult bindingResult, Model model) {
        projectValidator.validate(projectDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            addParametersToModelAttribute(model);
            checkNotNullParameters(projectDTO);
            log.info("Catch error for create new project - recall ProjectsController method newProject()");
            return "content/projects/new";
        }
        projectsService.save(convertToProject(projectDTO));
        Project project = projectsService.findByTitle(projectDTO.getTitle())
                .orElseThrow(() -> new ProjectNotFoundException("Проект " + projectDTO.getTitle() + " не найден"));
        log.info("Create new project with ID=" + project.getId() + " - redirect to ProjectsController method show()");
        return "redirect:/projects/" + project.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("project", convertToProjectDTO(projectsService.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Проект с ID=" + id + " не найден"))));
//        Use DTO parameters because they store into ProjectDTO for update
        addParametersDTOToModelAttribute(model);
        log.info("Show view for edit project with ID=" + id + " - call ProjectsController method edit()");
        return "content/projects/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("project") @Valid ProjectDTO projectDTO, BindingResult bindingResult,
                         @PathVariable("id") Integer id, Model model) {
        projectValidator.checkUniqueForUpdate(projectDTO, bindingResult);
        if (bindingResult.hasErrors()) {
//          Use DTO parameters because they store into ProjectDTO for update
            addParametersDTOToModelAttribute(model);
            checkNotNullParameters(projectDTO);
            log.info("Catch error for update project with ID=" + id + " - recall ProjectsController method edit()");
            return "content/projects/edit";
        }

        projectsService.update(id, convertToProject(projectDTO));
        log.info("Update project with ID=" + id + " - redirect to ProjectsController method show()");
        return "redirect:/projects/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        projectsService.delete(id);
        log.info("Delete project with ID=" + id + " - redirect to ProjectsController method getAll()");
        return "redirect:/projects";
    }

    @GetMapping("/search-project")
    public String searchProject(@ModelAttribute("searchProject") SearchProject searchProject, Model model) {
        addParametersToModelAttribute(model);
        log.info("Show view for search project - call ProjectsController method searchProject()");
        return "content/projects/search_project";
    }

    @PostMapping("/search-project-result")
    public String showSearchProjectResult(Model model, @ModelAttribute("searchProject") SearchProject searchProject,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Catch error for search project - recall ProjectsController method searchProject()");
            return "content/projects/search_project";
        }
        model.addAttribute("result", projectsService.searchProject(searchProject));
        log.info("Show view for search project result - call ProjectsController method showSearchProjectResult()");
        return "content/projects/result_project";
    }

    @GetMapping("/search-methodology")
    public String searchMethodology(@ModelAttribute("searchProject") SearchProject searchProject, Model model) {
        addParametersToModelAttribute(model);
        log.info("Show view for search methodology - call ProjectsController method searchMethodology()");
        return "content/projects/search_methodology";
    }

    @PostMapping("/search-methodology-result")
    public String showSearchMethodologyResult(Model model, @ModelAttribute("searchProject") SearchProject searchProject,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Catch error for search methodology - recall ProjectsController method searchMethodology()");
            return "content/projects/search_methodology";
        }
        model.addAttribute("result", projectsService.searchMethodology(searchProject));
        log.info("Show view for search methodology result- call ProjectsController method showSearchMethodologyResult()");
        return "content/projects/result_methodology";
    }

    @ExceptionHandler
    private String handleException(ProjectNotFoundException e) {
        log.info("Catch ProjectNotFoundException: " + e.getMessage());
        return "content/projects/not_found";
    }

//    For new, create and search methods
    private void addParametersToModelAttribute(Model model) {
        model.addAttribute("types", typesService.findAll().stream().sorted(Comparator.comparing(Type::getName)).collect(Collectors.toList()));
        model.addAttribute("segments", segmentsService.findAll().stream().sorted(Comparator.comparing(Segment::getName)).collect(Collectors.toList()));
        model.addAttribute("formats", formatsService.findAll().stream().sorted(Comparator.comparing(Format::getName)).collect(Collectors.toList()));
    }

//    For edit and update methods
    private void addParametersDTOToModelAttribute(Model model) {
            model.addAttribute("types", typesService.findAll().stream().sorted(Comparator.comparing(Type::getName)).map(this::convertToTypeDTO).collect(Collectors.toList()));
            model.addAttribute("segments", segmentsService.findAll().stream().sorted(Comparator.comparing(Segment::getName)).map(this::convertToSegmentDTO).collect(Collectors.toList()));
            model.addAttribute("formats", formatsService.findAll().stream().sorted(Comparator.comparing(Format::getName)).map(this::convertToFormatDTO).collect(Collectors.toList()));
    }

    private void checkNotNullParameters(ProjectDTO projectDTO) {
        if (projectDTO.getTypes() == null) {
            projectDTO.setTypes(new HashSet<>());
        }
        if (projectDTO.getSegments() == null) {
            projectDTO.setSegments(new HashSet<>());
        }
        if (projectDTO.getFormats() == null) {
            projectDTO.setFormats(new HashSet<>());
        }
    }

    private Project convertToProject(ProjectDTO projectDTO) {
        return modelMapper.map(projectDTO, Project.class);
    }

    private ProjectDTO convertToProjectDTO(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    private TypeDTO convertToTypeDTO(Type type) {
        return modelMapper.map(type, TypeDTO.class);
    }

    private SegmentDTO convertToSegmentDTO(Segment segment) {
        return modelMapper.map(segment, SegmentDTO.class);
    }

    private FormatDTO convertToFormatDTO(Format format) {
        return modelMapper.map(format, FormatDTO.class);
    }
}
