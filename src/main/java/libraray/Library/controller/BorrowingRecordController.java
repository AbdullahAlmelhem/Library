package libraray.Library.controller;

import libraray.Library.model.BorrowingRecord;
import libraray.Library.service.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/borrowingRecords")
@Validated
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping
    public ResponseEntity<?> createBorrowingRecord(@Valid @RequestBody BorrowingRecord borrowingRecord) {
        try {
            BorrowingRecord createdRecord = borrowingRecordService.createBorrowingRecord(borrowingRecord);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<BorrowingRecord>> getAllBorrowingRecords() {
        List<BorrowingRecord> records = borrowingRecordService.getAllBorrowingRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBorrowingRecordById(@PathVariable Long id) {
        try {
            BorrowingRecord record = borrowingRecordService.getBorrowingRecordById(id);
            return ResponseEntity.ok(record);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBorrowingRecord(@PathVariable Long id, @Valid @RequestBody BorrowingRecord borrowingRecord) {
        try {
            BorrowingRecord updatedRecord = borrowingRecordService.updateBorrowingRecord(id, borrowingRecord);
            return ResponseEntity.ok(updatedRecord);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorrowingRecord(@PathVariable Long id) {
        try {
            borrowingRecordService.deleteBorrowingRecord(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }
    }
}
