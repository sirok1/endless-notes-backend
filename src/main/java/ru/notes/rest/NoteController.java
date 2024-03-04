package ru.notes.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.notes.entity.Note;
import ru.notes.service.NoteService;
import ru.notes.service.UserService;

import java.util.List;

@Tag(name = "Notes", description = "Notes API")
@RestController
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;}

    @GetMapping(path = "/getAllNotes")
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok().body(noteService.getAllNotes());
    }
}
