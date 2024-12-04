package libraray.Library.model;
    import jakarta.persistence.*;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import java.util.List;
    import jakarta.validation.constraints.*;

    @Entity
    public class Book {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotNull(message = "Title is required")
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        private String title;

        @NotNull(message = "Author is required")
        @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
        private String author;

        @Min(value = 1450, message = "Publication year must be later than 1450")
        @Max(value = 2024, message = "Publication year cannot be in the future")
        private int publicationYear;

        @NotNull(message = "ISBN is required")
        @Pattern(regexp = "^[0-9-]+$", message = "ISBN must be numeric and can contain dashes")
        private String isbn;

        // Rest of the code...


        // العلاقة مع سجل الإعارة: كتاب واحد يمكن أن يُستعار بواسطة عدة مستفيدين
        @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
        @JsonIgnore // تجاهل القائمة عند تحويل كائن Book إلى JSON
        private List<BorrowingRecord> borrowingRecords;

        // getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getPublicationYear() {
            return publicationYear;
        }

        public void setPublicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public List<BorrowingRecord> getBorrowingRecords() {
            return borrowingRecords;
        }

        public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
            this.borrowingRecords = borrowingRecords;
        }
    }
