package dev.nhiph.model;

import org.thymeleaf.util.StringUtils;

import java.util.Optional;

/*
Đối tượng này dùng để kiểm tra xem một Object Todo có hợp lệ không
 */
public class TodoValidator {

    /**
     * Kiểm tra một object Todo có hợp lệ không
     * @param todo
     * @return
     */
    public boolean isValid(Todo todo) {
        return Optional.ofNullable(todo)
                        .filter(t -> !StringUtils.isEmpty(t.getUsername())) // Kiểm tra title khác rỗng
                        .filter(t -> !StringUtils.isEmpty(t.getName())) // Kiểm tra detail khác rỗng
                        .filter(t -> !StringUtils.isEmpty(t.getEmail()))
                        .filter(t -> !StringUtils.isEmpty(t.getStartDate().toString()))
                        .filter(t -> !StringUtils.isEmpty(String.valueOf(t.getPosition())))
                        .filter(t -> !StringUtils.isEmpty(String.valueOf(t.getSalary())))
                        .filter(t -> !StringUtils.isEmpty(String.valueOf(t.getHang())))
                        .isPresent(); // Trả về true nếu hợp lệ, ngược lại false
    }
}