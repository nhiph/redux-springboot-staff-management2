package dev.nhiph.service;

import dev.nhiph.model.Todo;
import dev.nhiph.model.TodoValidator;
import dev.nhiph.model.dto.TodoDTO;
import dev.nhiph.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoValidator validator;

    /**
     * Lấy ra danh sách List<Todo>
     *
     * @param limit - Giới hạn số lượng lấy ra
     *
     * @return Trả về danh sách List<Todo> dựa theo limit, nếu limit == null thì trả findAll()
     */
    public List<Todo> findAll(Integer limit) {
        return Optional.ofNullable(limit)
                       .map(value -> todoRepository.findAll(PageRequest.of(0, value)).getContent())
                       .orElseGet(() -> todoRepository.findAll());
    }

    public Todo addItem(TodoDTO dto) {
        Todo todo = new Todo();
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

        if (validator.isValid(todo)) {
            todoRepository.save(todo);
        }
        return todo;
    }

    public List<Todo> getList(){
        List<Todo> todos=todoRepository.findAll();
        return todos;
    }


    public void deletelist(Long id){
        todoRepository.deleteById(id);
    }

    public Todo editTodo(Long id){
        Todo todo= todoRepository.findById(id).orElse(new Todo());
        return todo;
    }

    public void saveTodo(Todo todo){
        todoRepository.save(todo);
    }
}