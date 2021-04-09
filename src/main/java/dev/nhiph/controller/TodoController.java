package dev.nhiph.controller;

import dev.nhiph.model.Todo;
import dev.nhiph.model.dto.TodoDTO;
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
    public ResponseEntity<TodoDTO> getTodoList(@RequestBody TodoDTO dto) {
        todoService.addItem(dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/delete/{todoId}")
    public List<Todo> deleteTodo(@PathVariable(name = "todoId") Long todoId){

        todoService.deletelist(todoId);
        return todoService.getList();
    }

    @PutMapping("/put/{todoId}")
    public ResponseEntity<TodoDTO> updateEmployee(@PathVariable(value = "todoId") Long todoId,
                                                   @RequestBody TodoDTO todos) {
//        Todo todo = todoService.editTodo(todoId);
//        todo.setUsername(todos.getUsername());
//        todo.setName(todos.getName());
//        todo.setEmail(todos.getEmail());
//        todo.setStart_date(todos.getStart_date());
//        todo.setPosition(todos.getPosition());
//        todo.setSalary(todos.getSalary());
//        todo.setHang(todos.getHang());
//        final Todo updatedTodo = todoService.addItem(todo);
        return ResponseEntity.ok(null);
    }
}