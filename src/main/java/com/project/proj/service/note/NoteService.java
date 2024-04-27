package com.project.proj.service.note;

import java.util.List;

public interface NoteService {
     List<NoteDto> listAll();
     NoteDto add(NoteDto note);
     void deleteById(long id);
     void update(NoteDto note);
     NoteDto getById(long id);
}
