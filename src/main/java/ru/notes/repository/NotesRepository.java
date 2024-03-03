package ru.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.notes.model.Note;

import java.util.List;

public interface NotesRepository extends JpaRepository<Note, Integer> {

}
