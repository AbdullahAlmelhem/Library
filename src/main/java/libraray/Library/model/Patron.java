package libraray.Library.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;

    private String email; // الحقل الجديد

    // العلاقة مع سجل الإعارة: مستفيد واحد يمكنه استعارة عدة كتب
    @OneToMany(mappedBy = "patron", cascade = CascadeType.ALL)
    @JsonIgnore // تجاهل القائمة عند تحويل كائن Patron إلى JSON
    private List<BorrowingRecord> borrowingRecords;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getEmail() { // Getter للحقل email
        return email;
    }

    public void setEmail(String email) { // Setter للحقل email
        this.email = email;
    }

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}
