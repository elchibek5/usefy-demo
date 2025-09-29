package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository repo;

    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public List<Note> getAllNotes() {
        return repo.findAll();
    }

    public Note createNote(Note n) {
        return repo.save(n);
    }

    public Optional<Note> getNoteById(Long id) {
        return repo.findById(id);
    }
}
