package com.project.proj.service.mapper;

import com.project.proj.data.entity.Note;
import com.project.proj.service.note.NoteDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NoteMapper {

    public List<Note> toNoteEntities(Collection<NoteDto> dtos) {
        return dtos.stream()
                .map(this::toNoteEntity)
                .collect(Collectors.toList());
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
                .map(this::toNoteDto)
                .collect(Collectors.toList());
    }

    public NoteDto toNoteDto(Note entity) {
        NoteDto dto = new NoteDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        return dto;
    }

//    public List<NoteResponse> toNoteResponses(Collection<NoteDto> dtos) {
//        return dtos.stream()
//                .map(this::toNoteResponse)
//                .collect(Collectors.toList());
//    }
//
//    public NoteResponse toNoteResponse(NoteDto dto) {
//        NoteResponse response = new NoteResponse();
//        response.setId(dto.getId());
//        response.setTitle(dto.getTitle());
//        response.setContent(dto.getContent());
//        return response;
//    }
//
//    public List<NoteDto> requestsToNoteDtos(Collection<CreateNoteRequest> requests) {
//        return requests.stream()
//                .map(this::toNoteDto)
//                .collect(Collectors.toList());
//    }
//
//    public NoteDto toNoteDto(CreateNoteRequest request) {
//        NoteDto dto = new NoteDto();
//        dto.setTitle(request.getTitle());
//        dto.setContent(request.getContent());
//        return dto;
//    }
//
//    public NoteDto toNoteDto(UUID id, UpdateNoteRequest request) {
//        NoteDto dto = new NoteDto();
//        dto.setId(id);
//        dto.setTitle(request.getTitle());
//        dto.setContent(request.getContent());
//        return dto;
//    }
}
