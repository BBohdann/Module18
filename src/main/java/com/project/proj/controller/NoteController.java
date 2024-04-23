package com.project.proj.controller;

import com.project.proj.service.mapper.NoteMapper;
import com.project.proj.service.note.NoteDto;
import com.project.proj.service.note.NoteService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;
    private final NoteMapper mapper;

    @GetMapping("/list")
    public ModelAndView getAllNotes(){
        ModelAndView result = new ModelAndView("notes/get-all-notes");
        return result.addObject("notes", service.listAll());
    }

    @GetMapping("/create")
    public ModelAndView getCreateNotePage() {
        ModelAndView result = new ModelAndView("notes/create-note");
        return result;
    }

    @PostMapping("/create")
    public ModelAndView createNote(
            @Size(min = 5, max = 150) @RequestParam(name = "title") String title,
            @NotBlank @RequestParam(value="content") @NotBlank String content){
        NoteDto noteDto = new NoteDto();
        noteDto.setContent(content);
        noteDto.setTitle(title);
        service.add(mapper.toNoteEntity(noteDto));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/note/list");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteNote(@NonNull @RequestParam(name = "id") Long id){
        service.deleteById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/note/list");
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView getUpdateNote(@RequestParam(name = "id") Long id){
        ModelAndView result = new ModelAndView("notes/update-note");
        return result.addObject("id", id);
    }

    @PostMapping("/edit")
    public ModelAndView postUpdateNote(
            @NotNull @RequestParam(name = "id") Long id,
            @Size(min = 5, max = 150) @RequestParam(name = "title") String title,
            @NotBlank @RequestParam(name = "content") String content){
        NoteDto noteDto = new NoteDto();
        noteDto.setId(id);
        noteDto.setTitle(title);
        noteDto.setContent(content);
        service.update(mapper.toNoteEntity(noteDto));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/note/list");
        return modelAndView;
    }
}
