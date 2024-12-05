package com.example.notePolyakovAM.service;

import com.example.notePolyakovAM.model.Note;
import com.example.notePolyakovAM.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> getAllNotes(String login) {
        return noteRepository.findAllByLogin(login);
    }

    public void save(Note note) {
        noteRepository.save(note);
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

}
