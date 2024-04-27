package com.project.proj.service.mapper;

import com.project.proj.data.entity.Note;
import com.project.proj.service.note.NoteDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class NoteMapper {

    public List<Note> toNoteEntities(Collection<NoteDto> dtos) {
        return dtos.stream()
                .map(this::toNoteEntity)
                .toList();
    }

    public Note toNoteEntity(NoteDto dto) {
        Note entity = new Note();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        return entity;
    }

    public List<NoteDto> toNoteDtos(Collection<Note> entities) {
        return entities.stream()
                .map(this::toNoteDto).toList();
    }

    public NoteDto toNoteDto(Note entity) {
        NoteDto dto = new NoteDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        return dto;
    }
}
