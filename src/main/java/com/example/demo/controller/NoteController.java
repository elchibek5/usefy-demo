package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    public NoteController(NoteService service) { this.service = service; }

    @GetMapping
    public List<Note> all() { return service.getAllNotes(); }

    @PostMapping
    public Note create(@RequestBody Note n) { return service.createNote(n); }

    @GetMapping("/{id}")
    public ResponseEntity<Note> one(@PathVariable Long id) {
        return service.getNoteById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
