package dev.nhiph.controller;

import dev.nhiph.model.Todo;
import dev.nhiph.model.TodoValidator;
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

    @Autowired
    private TodoValidator validator;

    @Autowired
    private TodoRepository todoRepository;

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
                                                   @RequestBody TodoDTO dto) {
        Todo todo = todoService.editTodo(todoId);
        todo.setUsername(dto.getAccount());
        todo.setName(dto.getName());
        todo.setEmail(dto.getEmail());
        todo.setStartDate(dto.getStartDate());

        if(dto.getPosition()==1) {
            todo.setSalary(dto.getSalary()*3);
            todo.setPosition("Sếp");
        }
        else if(dto.getPosition()==2) {
            todo.setSalary(dto.getSalary()*2);
            todo.setPosition("Trưởng phòng");
        }
        else {
            todo.setSalary(dto.getSalary());
            todo.setPosition("Nhân viên");
        }

        if(dto.getHours()>=192) todo.setHang("Xuất sắc");
        else if(dto.getHours()>=176) todo.setHang("Giỏi");
        else if(dto.getHours()>=160) todo.setHang("Khá");
        else todo.setHang("Trung bình");
        todoService.saveTodo(todo);
        return ResponseEntity.ok(null);
    }

}