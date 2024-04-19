package com.project.proj.repository;

import com.project.proj.exeptions.NoteNotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import com.project.proj.note.Note;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Repository
public class PseudoRepository {
    private List<Note> notes = new ArrayList<>();

    public Note createNote(Note note){
        note.setId((long) (new Random().nextInt(1000000) + 1));
        this.notes.add(note);
        return note;
    }

    public Note findNoteById(Long id) {
        return notes.stream()
                .filter(note -> note.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoteNotFoundException("Id " + id + " not found"));
    }

    public boolean deleteNote(Long id){
        return notes.remove(findNoteById(id));
    }
}
