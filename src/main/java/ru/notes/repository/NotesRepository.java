package ru.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.notes.entity.Note;

public interface NotesRepository extends JpaRepository<Note, String> {}
