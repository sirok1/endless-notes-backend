package ru.notes.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.notes.entity.Note;
import ru.notes.repository.NotesRepository;

import java.util.List;

@Slf4j
@Service
public class NoteService {
    private final NotesRepository notesRepository;

    public NoteService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Transactional
    public List<Note> getAllNotes() {
        log.info("in service");
        return notesRepository.findAll();
    }
}
