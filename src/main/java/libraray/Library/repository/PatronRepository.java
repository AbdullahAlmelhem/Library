package libraray.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import libraray.Library.model.Patron;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    // يمكن إضافة استعلامات مخصصة هنا إذا لزم الأمر
}
