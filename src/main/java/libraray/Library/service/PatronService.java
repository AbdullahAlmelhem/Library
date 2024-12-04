package libraray.Library.service;

import libraray.Library.model.Patron;
import libraray.Library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public Patron createPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patron not found with ID: " + id));
    }

    public Patron updatePatron(Long id, Patron updatedPatron) {
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patron not found with ID: " + id));
        existingPatron.setName(updatedPatron.getName());
        existingPatron.setEmail(updatedPatron.getEmail());
        return patronRepository.save(existingPatron);
    }

    public void deletePatron(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new RuntimeException("Patron not found with ID: " + id);
        }
        patronRepository.deleteById(id);
    }
}
