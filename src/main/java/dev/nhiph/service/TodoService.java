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
        //Cuc entity ghi xuong
        Todo todo = new Todo();

        //Map dto to entity
        todo.setPosition(dto.getChucVu());

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

//    public int findIndex(List<Todo> todos, Long id){
//        int index = 0;
//        for(int i=0; i<todos.size(); i++){
//            if(todos.get(i).getId() == id){
//                index = i;
//            }
//        }
//        return index;
//    }

}