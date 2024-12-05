package com.example.notePolyakovAM;

import com.example.notePolyakovAM.model.Note;
import com.example.notePolyakovAM.repository.NoteRepository;
import com.example.notePolyakovAM.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllNotes() {
        String login = "testUser";
        List<Note> notes = new ArrayList<>();
        notes.add(new Note());

        when(noteRepository.findAllByLogin(login)).thenReturn(notes);

        List<Note> result = noteService.getAllNotes(login);

        assertEquals(1, result.size());
        verify(noteRepository, times(1)).findAllByLogin(login);
    }

    @Test
    public void testSaveNote() {
        Note note = new Note();

        noteService.save(note);

        verify(noteRepository, times(1)).save(note);
    }

    @Test
    public void testGetNoteById() {
        Long id = 1L;
        Note note = new Note();

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        Optional<Note> result = noteService.getNoteById(id);

        assertTrue(result.isPresent());
        assertEquals(note, result.get());
        verify(noteRepository, times(1)).findById(id);
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        noteService.delete(id);

        verify(noteRepository, times(1)).deleteById(id);
    }
}