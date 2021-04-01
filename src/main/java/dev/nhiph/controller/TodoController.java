package dev.nhiph.controller;

import dev.nhiph.model.Todo;
import dev.nhiph.repository.TodoRepository;
import dev.nhiph.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@CrossOrigin("*")
@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

//    @Autowired
//    private TodoRepository todoRepository;

    private List<Todo> todoList = new CopyOnWriteArrayList<>();

    @GetMapping("/getListTodo")
    public List<Todo> getTodoList() {
       return todoService.getList();
    }

    @GetMapping("/getListTodo/{todoId}")
    public Todo getTodoId(@PathVariable(name = "todoId") Long todoId){
        return todoService.editTodo(todoId);
    }

    @PostMapping("/listTodo")
    public ResponseEntity<Todo> getTodoList(@RequestBody Todo todo) {
        todoService.addItem(todo);
        return ResponseEntity.ok().body(todo);
    }

    @DeleteMapping("/delete/{todoId}")
    public List<Todo> deleteTodo(@PathVariable(name = "todoId") Long todoId){

        todoService.deletelist(todoId);
        return todoService.getList();
    }

    @PutMapping("/put/{todoId}")
    public ResponseEntity<Todo> updateEmployee(@PathVariable(value = "todoId") Long todoId,
                                                   @RequestBody Todo todos) {
        Todo todo = todoService.editTodo(todoId);
        todo.setTitle(todos.getTitle());
        todo.setEmail(todos.getEmail());
        todo.setPhone(todos.getPhone());
        todo.setGender(todos.getGender());
        todo.setDetail(todos.getDetail());
        todo.setHang(todos.getHang());
        final Todo updatedTodo = todoService.addItem(todo);
        return ResponseEntity.ok(updatedTodo);
    }
}