package ru.notes.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.notes.model.Note;
import ru.notes.service.NoteService;

import java.util.List;

@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping(path = "/getAllNotes")
    public ResponseEntity<List<Note>> getAllNotes () {
        return ResponseEntity.ok().body(noteService.getAllNotes());
    }
}
