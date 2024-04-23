package com.project.proj.note;

import com.project.proj.data.entity.Note;
import com.project.proj.exeptions.NoteNotFoundException;
import com.project.proj.service.note.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteRepositoryImplTest {
    @Autowired
    private NoteService service;

    @Test
    public void testNotFoundNoteThrowsExp(){
        Assertions.assertThrows(NoteNotFoundException.class, ()-> service.getById(41));
        Assertions.assertThrows(NoteNotFoundException.class, ()-> service.deleteById(999999));

    }
    @Test
    public void testNoteAddCorectly() {
        Note note = new Note();
        note.setTitle("TestTitle");
        note.setContent("Content");
        service.add(note);
        Assertions.assertEquals(findLastNote(), note);
    }

    @Test
    public void testNoteUpdate(){
        Note note = new Note();
        note.setTitle("Test");
        note.setContent("TestContent");
        Note beforeUpdateNote = service.add(note);

        String updatedTitle = "TESTT";
        String updatedContent = "421842jdf";

        note.setTitle(updatedTitle);
        note.setContent(updatedContent);
        service.update(note);

        Note updatedNote = service.getById(note.getId());

        // Перевірка, що заголовок і зміст оновленої нотатки правильні
        Assertions.assertEquals(updatedTitle, updatedNote.getTitle());
        Assertions.assertEquals(updatedContent, updatedNote.getContent());
        // Перевірка, що ідентифікатори нотаток співпадають
        Assertions.assertEquals(beforeUpdateNote.getId(), updatedNote.getId());
    }

    public Note findLastNote(){
        int size = service.listAll().size();
        return  service.listAll().get(size-1);
    }
}