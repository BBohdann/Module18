package com.project.proj.service.note;

import com.project.proj.data.entity.Note;
import com.project.proj.data.repository.NoteRepository;
import com.project.proj.exeptions.NoteNotFoundException;
import com.project.proj.service.mapper.NoteMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository repository;
    private final NoteMapper mapper;

    @Override
    @Transactional
    public List<NoteDto> listAll() {
        return mapper.toNoteDtos(repository.findAll());
    }

    @Override
    public NoteDto add(NoteDto note) {
        Note entity = mapper.toNoteEntity(note);
        return mapper.toNoteDto(repository.save(entity));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (repository.findById(id).isEmpty()) {
            throw new NoteNotFoundException();
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(NoteDto note) {
        if (repository.findById(note.getId()).isEmpty()) {
            throw new NoteNotFoundException();
        }
        repository.save(mapper.toNoteEntity(note));
    }

    @Override
    public NoteDto getById(long id) {
        Optional<Note> note = repository.findById(id);
        if (note.isEmpty()) {
            throw new NoteNotFoundException();
        }
        return mapper.toNoteDto(note.get());
    }
}
