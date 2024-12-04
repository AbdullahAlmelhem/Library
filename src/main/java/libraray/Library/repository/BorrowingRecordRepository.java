package libraray.Library.repository;

import libraray.Library.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    // تعديل الاستعلام ليعيد قائمة من السجلات بدلاً من Optional
    @Query("SELECT br FROM BorrowingRecord br WHERE br.book.id = :bookId AND br.patron.id = :patronId AND br.returnDate IS NULL")
    List<BorrowingRecord> findByBookIdAndPatronIdAndReturnDateIsNull(@Param("bookId") Long bookId, @Param("patronId") Long patronId);
}
