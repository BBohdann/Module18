package com.project.proj.note;

import com.project.proj.data.repository.NoteRepository;
import com.project.proj.service.exeptions.NoteNotFoundException;
import com.project.proj.service.mapper.NoteMapper;
import com.project.proj.service.note.NoteDto;
import com.project.proj.service.note.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteRepositoryImplTest {
    @Autowired
    private NoteService service;
    @Autowired
    private NoteMapper mapper;
    @Autowired
    private NoteRepository repository;

    @Test
    void testNotFoundNoteThrowsExp() {
        Assertions.assertThrows(NoteNotFoundException.class, () -> service.getById(41));
        Assertions.assertThrows(NoteNotFoundException.class, () -> service.deleteById(999999));
    }

    @Test
    void testNoteAddCorectly() {
        NoteDto note = new NoteDto();
        note.setTitle("TestTitle");
        note.setContent("Content");
        NoteDto added = service.add(note);
        Assertions.assertEquals(findLastNote(), added);
    }

    @Test
    void testNoteUpdate() {
        NoteDto note = new NoteDto();
        note.setTitle("Testt");
        note.setContent("TestContent");
        NoteDto noteToUpdate = service.add(note);
        Long idBeforeUpdate = noteToUpdate.getId();

        String updatedTitle = "TESTT";
        String updatedContent = "421842jdf";

        noteToUpdate.setTitle(updatedTitle);
        noteToUpdate.setContent(updatedContent);
        service.update(noteToUpdate);
        NoteDto afterUpdate = service.getById(noteToUpdate.getId());

        // Перевірка, що заголовок і зміст оновленої нотатки правильні
        Assertions.assertEquals(updatedTitle, afterUpdate.getTitle());
        Assertions.assertEquals(updatedContent, afterUpdate.getContent());
        // Перевірка, що ідентифікатори нотаток співпадають
        Assertions.assertEquals(idBeforeUpdate, afterUpdate.getId());
    }

    NoteDto findLastNote() {
        return mapper.toNoteDto(repository.findFirstByOrderByIdDesc());
    }
}