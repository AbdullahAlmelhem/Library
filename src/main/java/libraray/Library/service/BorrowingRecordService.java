package libraray.Library.service;

import libraray.Library.model.BorrowingRecord;
import libraray.Library.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public BorrowingRecord createBorrowingRecord(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    public BorrowingRecord getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found with ID: " + id));
    }

    public BorrowingRecord updateBorrowingRecord(Long id, BorrowingRecord updatedRecord) {
        BorrowingRecord existingRecord = borrowingRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found with ID: " + id));
        existingRecord.setBorrowDate(updatedRecord.getBorrowDate());
        existingRecord.setReturnDate(updatedRecord.getReturnDate());
        existingRecord.setBook(updatedRecord.getBook());
        existingRecord.setPatron(updatedRecord.getPatron());
        return borrowingRecordRepository.save(existingRecord);
    }

    public void deleteBorrowingRecord(Long id) {
        if (!borrowingRecordRepository.existsById(id)) {
            throw new RuntimeException("Borrowing record not found with ID: " + id);
        }
        borrowingRecordRepository.deleteById(id);
    }
}
