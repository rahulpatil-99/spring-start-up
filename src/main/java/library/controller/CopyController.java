package library.controller;

import library.domain.Copy;
import library.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/copy")
@RestController
public class CopyController {
    private CopyService service;

    @Autowired
    public CopyController(CopyService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public void addCopy(@RequestBody Copy copy) {
        service.addCopy(copy);
    }

    @GetMapping("/{copyId}")
    public Copy getCopy(@PathVariable String copyId) {
        return service.getByCopyId(copyId);
    }

    @GetMapping("/copies")
    public List<Copy> getCopies(@RequestParam("isbn") String isbn) {
        return service.getByIsbn(isbn);
    }

    @GetMapping("/availableCopies")
    public List<Copy> getAvailableCopies() {
        return service.getAvailableCopies();
    }

    @GetMapping("/availableCopiesFor")
    public List<Copy> getAvailableCopiesFor(@RequestParam("isbn") String isbn) {
        return service.getAvailableCopiesFor(isbn);
    }

    @PutMapping("/makeAvailable")
    public void makeAvailable(@RequestBody String copyId) {
        service.makeAvailable(copyId);
    }

    @PutMapping("/makeUnAvailable")
    public void makeUnAvailable(@RequestBody String copyId) {
        service.makeUnAvailable(copyId);
    }
}
