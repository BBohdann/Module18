package com.project.proj.service.note;

import com.project.proj.data.entity.Note;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.project.proj.data.repository.PseudoRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final PseudoRepository repository;

    @Override
    public List<Note> listAll() {
        return repository.getNotes();
    }

    @Override
    public Note add(Note note) {
        return repository.createNote(note);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteNote(id);
    }

    @Override
    public void update(Note note) {
        List<Note> notes = listAll();
        int index = notes.indexOf(repository.findNoteById(note.getId()));
        notes.set(index, note);
    }

    @Override
    public Note getById(long id) {
        return repository.findNoteById(id);
    }
}
