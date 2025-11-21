package com.example.taskmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    // 1. LISTAR TODAS (GET)
    @GetMapping
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // 2. CRIAR (POST)
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    // 3. ATUALIZAR TAREFA (PUT)
    // Recebe o ID na URL e os dados novos no Corpo (JSON)
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return repository.findById(id)
                .map(task -> {
                    // Atualiza os dados do objeto encontrado
                    task.setDescription(taskDetails.getDescription());
                    task.setCompleted(taskDetails.isCompleted());
                    // Salva no banco
                    Task updated = repository.save(task);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build()); // Se não achar o ID, retorna 404
    }

    // 4. DELETAR TAREFA (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // Retorna 204 (Sucesso sem conteúdo)
        }
        return ResponseEntity.notFound().build(); // Retorna 404 se o ID não existir
    }
}