package dev.sio.todoalt.repositories;

import dev.sio.todoalt.entities.Todolist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodolistRepository extends JpaRepository<Todolist, UUID> {
}
