package com.project.proj.controller;

import com.project.proj.service.note.NoteDto;
import com.project.proj.service.note.NoteService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/V2/notes")
@RequiredArgsConstructor
public class NoteControllerV2 {
    private final NoteService service;

    @GetMapping("/list")
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll());
    }

    @PostMapping("/create")
    public ResponseEntity<NoteDto> createNote(
            @Length(min = 5, max = 150) @RequestParam(name = "title") String title,
            @NotBlank @RequestParam(value = "content") @NotBlank String content) {
        NoteDto noteDto = new NoteDto();
        noteDto.setContent(content);
        noteDto.setTitle(title);

        return ResponseEntity.status(HttpStatus.OK).body(service.add(noteDto));
    }

    @PostMapping("/delete")
    public ResponseEntity<NoteDto> deleteNote(@NotNull @RequestParam(name = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/edit")
    public ResponseEntity<NoteDto> postUpdateNote(
            @NotNull @RequestParam(name = "id") Long id,
            @Length(min = 5, max = 150) @RequestParam(name = "title") String title,
            @NotBlank @RequestParam(name = "content") String content) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(id);
        noteDto.setTitle(title);
        noteDto.setContent(content);
        service.update(noteDto);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}