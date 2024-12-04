package libraray.Library.repository;

import libraray.Library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // يمكنك إضافة استعلامات مخصصة هنا إذا لزم الأمر
}
