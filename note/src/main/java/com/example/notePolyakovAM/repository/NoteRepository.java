package com.example.notePolyakovAM.repository;

import com.example.notePolyakovAM.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByLogin(String login);
}
