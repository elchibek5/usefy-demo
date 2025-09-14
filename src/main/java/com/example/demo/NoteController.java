package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteRepository repo;

    public NoteController(NoteRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Note> all() { return repo.findAll(); }

    @PostMapping
    public Note create(@RequestBody Note n) { return repo.save(n); }

    @GetMapping("/{id}")
    public ResponseEntity<Note> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
