package com.example.notePolyakovAM.controller;

import com.example.notePolyakovAM.client.LogClient;
import com.example.notePolyakovAM.client.ValidatorClient;
import com.example.notePolyakovAM.model.Log;
import com.example.notePolyakovAM.model.Note;
import com.example.notePolyakovAM.model.ValidateBody;
import com.example.notePolyakovAM.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final ValidatorClient validatorClient;
    private final LogClient logClient;
    @Value("${redirect_url}")
    private String redirectUrl;
    @GetMapping
    public String listNotes(Model model, @CookieValue(value = "id", required = false) String id,
                            @CookieValue(value = "login", required = false) String login) {
        model.addAttribute("redirect_url", redirectUrl);
        if (id == null || login == null) {
            return "notes/notes";
        }
        if (!validatorClient.validate(new ValidateBody(login, id))) {
            return "notes/notes";
        }
        String cookie = "id=" + id + "; login=" + login;
        logClient.save(new Log("getNotes", "GET", login), cookie);
        model.addAttribute("notes", noteService.getAllNotes(login));
        return "notes/notes";
    }

    @GetMapping("/new")
    public String createNoteForm(Model model, @CookieValue(value = "id", required = false) String id,
                                 @CookieValue(value = "login", required = false) String login) {
        model.addAttribute("redirect_url", redirectUrl);
        if (id == null || login == null) {
            return "notes/error";
        }
        if (!validatorClient.validate(new ValidateBody(login, id))) {
            return "notes/error";
        }
        String cookie = "id=" + id + "; login=" + login;
        logClient.save(new Log("getNoteForm", "GET", login), cookie);
        model.addAttribute("note", new Note());
        return "notes/form";
    }

    @PostMapping("/save")
    public String saveNote(@ModelAttribute Note note,
                           @CookieValue(value = "id", required = false) String id,
                           @CookieValue(value = "login", required = false) String login,
                           Model model) {
        model.addAttribute("redirect_url", redirectUrl);
        if (id == null || login == null) {
            return "notes/error";
        }
        if (!validatorClient.validate(new ValidateBody(login, id))) {
            return "notes/error";
        }
        String cookie = "id=" + id + "; login=" + login;
        logClient.save(new Log("saveNote", "POST", login), cookie);
        note.setLogin(login);
        noteService.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String editNoteForm(@PathVariable("id") Long noteId, Model model,
                               @CookieValue(value = "id", required = false) String id,
                               @CookieValue(value = "login", required = false) String login) {
        model.addAttribute("redirect_url", redirectUrl);
        if (id == null || login == null) {
            return "notes/error";
        }
        if (!validatorClient.validate(new ValidateBody(login, id))) {
            return "notes/error";
        }
        String cookie = "id=" + id + "; login=" + login;
        logClient.save(new Log("getNoteForm", "GET", login), cookie);
        Optional<Note> note = noteService.getNoteById(noteId);
        note.ifPresent(value -> model.addAttribute("note", value));
        return "notes/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteNote(@PathVariable("id") Long noteId, Model model,
                             @CookieValue(value = "id", required = false) String id,
                             @CookieValue(value = "login") String login) {
        model.addAttribute("redirect_url", redirectUrl);
        if (id == null || login == null) {
            return "notes/error";
        }
        if (!validatorClient.validate(new ValidateBody(login, id))) {
            return "notes/error";
        }
        String cookie = "id=" + id + "; login=" + login;
        logClient.save(new Log("deleteNote", "DELETE", login), cookie);
        noteService.delete(noteId);
        return "redirect:/notes";
    }

}
