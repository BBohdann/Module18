package com.project.proj.controller;

import com.project.proj.service.note.NoteDto;
import com.project.proj.service.note.NoteService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteControllerV1 {
    private final NoteService service;

    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("notes/get-all-notes");
        return result.addObject("notes", service.listAll());
    }

    @GetMapping("/create")
    public ModelAndView getCreateNotePage() {
        return new ModelAndView("notes/create-note");
    }

    @PostMapping("/create")
    public ModelAndView createNote(
            @Length(min = 5, max = 150) @RequestParam(name = "title") String title,
            @NotBlank @RequestParam(value = "content") @NotBlank String content) {
        NoteDto noteDto = new NoteDto();
        noteDto.setContent(content);
        noteDto.setTitle(title);
        service.add(noteDto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/note/list");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteNote(@NonNull @RequestParam(name = "id") Long id) {
        service.deleteById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/note/list");
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView getUpdateNote(@RequestParam(name = "id") Long id) {
        ModelAndView result = new ModelAndView("notes/update-note");
        return result.addObject("id", id);
    }

    @PostMapping("/edit")
    public ModelAndView postUpdateNote(
            @NotNull @RequestParam(name = "id") Long id,
            @Length(min = 5, max = 150) @RequestParam(name = "title") String title,
            @NotBlank @RequestParam(name = "content") String content) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(id);
        noteDto.setTitle(title);
        noteDto.setContent(content);
        service.update(noteDto);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/note/list");
        return modelAndView;
    }
}
