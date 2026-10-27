package dev.sio.todoalt.repositories;

import dev.sio.todoalt.entities.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TodolistRepository extends JpaRepository<Todolist, UUID> {
    @Query("SELECT t from Todolist t where LOWER(t.name) like LOWER(:text) or LOWER(t.description) like LOWER(:text) order by t.name")
    List<Todolist> findByText(String text);

}
