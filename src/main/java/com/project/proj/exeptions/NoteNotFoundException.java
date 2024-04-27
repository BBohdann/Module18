package com.project.proj.exeptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String message) {
        super(message);
    }
}
