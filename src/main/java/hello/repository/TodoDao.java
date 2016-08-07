package hello.repository;

import hello.persistance.CheckItem;
import hello.persistance.Todo;
import hello.persistance.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TodoDao extends JpaRepository<Todo, Long> {
     Set<Todo> findByCreatedByOrSharingWith(User createdBy, User sharingWith);
     Todo findByItems(CheckItem item);
     Todo getTodoById(long id);

}