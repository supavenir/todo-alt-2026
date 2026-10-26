package dev.sio.todoalt.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Todolist {
	@Id
	private UUID id = UUID.randomUUID();

	@Column(nullable = false)
	private String name;

	@Column(length = 65000)
	private String description;

	private LocalDateTime createdAt = LocalDateTime.now();

}
