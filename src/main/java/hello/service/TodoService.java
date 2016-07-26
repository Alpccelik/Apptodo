package hello.service;

import hello.persistance.CheckItem;
import hello.persistance.Todo;
import hello.persistance.User;
import hello.repository.CheckItemDao;
import hello.repository.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.*;

@Service
public class TodoService {
    private TodoDao todoDao;
    private CheckItemDao checkItemDao;

    @Autowired
    public TodoService(TodoDao todoDao, CheckItemDao checkItemDao) {
        this.todoDao = todoDao;
        this.checkItemDao = checkItemDao;
    }

    public Todo create() {
        return todoDao.save(new Todo("New Todo", Collections.emptyList(), Collections.emptySet()));
    }

    public Todo update(Todo todo) {
        if (todoDao.findOne(todo.getId()) != null) {
            return todoDao.save(todo);
        }
        throw new InvalidParameterException();
    }

    public Set<Todo> list(User user) {
        return todoDao.findByCreatedByOrSharingWith(user, user);
    }

    public void addNewItem(Long todoId) {
        Todo todo = todoDao.getOne(todoId);
        if (todo != null) {
            CheckItem newItem = checkItemDao.save(new CheckItem("New Item", false));
            if (newItem != null) {
                List<CheckItem> items = todo.getItems();
                items.add(newItem);
                todoDao.save(todo);
            }
        } else {
            throw new InvalidParameterException();
        }
    }

    public void updateTodoName(Long todoId, String name) {
        Todo todo = todoDao.findOne(todoId);
        if (todo != null) {
            todo.setName(name);
            todoDao.save(todo);
        } else {
            throw new InvalidParameterException();
        }
    }

    public void updateItem(Long itemId, boolean checked) {
        CheckItem item = checkItemDao.findOne(itemId);
        if (item != null) {
            item.setChecked(checked);
            checkItemDao.save(item);
        } else {
            throw new InvalidParameterException();
        }
    }

    public void updateItem(Long itemId, String content) {
        CheckItem item = checkItemDao.findOne(itemId);
        if (item != null) {
            item.setContent(content);
            checkItemDao.save(item);
        } else {
            throw new InvalidParameterException();
        }
    }

    public void deleteTodo(Long todoId) {
        Todo todo = todoDao.findOne(todoId);
        List<CheckItem> items = todo.getItems();
        if (todo != null) {
            todo.getItems().remove(items);
            todoDao.delete(todo);
        }
    }
    public void deleteItem(Long itemId) {
        CheckItem item = checkItemDao.findOne(itemId);

        if (item != null) {
            Todo todo = todoDao.findByItems(item);
            todo.getItems().remove(item);
            todoDao.save(todo);
            checkItemDao.delete(item);
        } else {
            throw new InvalidParameterException();
        }
    }

}
