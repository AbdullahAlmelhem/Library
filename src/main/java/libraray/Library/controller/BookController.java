package libraray.Library.controller;
import jakarta.validation.Valid;

import libraray.Library.model.Book;
import libraray.Library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // إضافة كتاب جديد
    @PostMapping
    public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
        try {
            Book savedBook = bookRepository.save(book);
            return ResponseEntity.status(201).body(savedBook);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error occurred while saving the book: " + ex.getMessage());
        }
    }


    // استرداد قائمة بجميع الكتب
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return ResponseEntity.ok(books);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error occurred while fetching books: " + ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                return ResponseEntity.ok(bookOptional.get());
            } else {
                return ResponseEntity.status(404).body("Book not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error occurred while fetching the book: " + ex.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @Valid @RequestBody Book updatedBook) {
        try {
            Optional<Book> existingBookOptional = bookRepository.findById(id);
            if (existingBookOptional.isPresent()) {
                Book existingBook = existingBookOptional.get();
                existingBook.setTitle(updatedBook.getTitle());
                existingBook.setAuthor(updatedBook.getAuthor());
                existingBook.setPublicationYear(updatedBook.getPublicationYear());
                existingBook.setIsbn(updatedBook.getIsbn());

                Book savedBook = bookRepository.save(existingBook);
                return ResponseEntity.ok(savedBook);
            } else {
                return ResponseEntity.status(404).body("Book not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error occurred while updating the book: " + ex.getMessage());
        }
    }


    // حذف كتاب باستخدام المعرف
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                bookRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(404).body("Book not found");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error occurred while deleting the book: " + ex.getMessage());
        }
    }

}
