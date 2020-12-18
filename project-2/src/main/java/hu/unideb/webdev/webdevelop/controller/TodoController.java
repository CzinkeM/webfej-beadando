package hu.unideb.webdev.webdevelop.controller;

import hu.unideb.webdev.webdevelop.exception.ResourceNotFound;
import hu.unideb.webdev.webdevelop.model.Todo;
import hu.unideb.webdev.webdevelop.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    //GET All
    @GetMapping("/todos")
    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }
    //Create new todo
    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo){
        return todoRepository.save(todo);
    }
    //Get Todo by id
    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Todo not exist"));
        return ResponseEntity.ok(todo);
    }
    //Update to
    @PutMapping("/todos/{id}")
    @Transactional
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id,@RequestBody Todo _todo){
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFound("Todd not exist"));
        todo.setTitle(_todo.getTitle());
        todo.setDescription((_todo.getDescription()));
        Todo updatedTodo = todoRepository.save(todo);
        return ResponseEntity.ok(updatedTodo);
    }
    //delete
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTodo(@PathVariable Long id){
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFound("Todd not exist"));
        todoRepository.delete(todo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
