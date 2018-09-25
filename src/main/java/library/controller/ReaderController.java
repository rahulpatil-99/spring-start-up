package library.controller;

import library.domain.Reader;
import library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")
@RestController
public class ReaderController {
    private ReaderService readerService;

    @Autowired
    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/{readerName}")
    public List<Reader> getReader(@PathVariable String readerName){
        return readerService.getByName(readerName);
    }

    @PostMapping(value = "/create")
    public void createReader(@RequestBody Reader reader){
        readerService.createReader(reader);
    }

    @DeleteMapping("/remove/{firstName}")
    public void deleteReader(@PathVariable String firstName) {
        readerService.removeByFirstName(firstName);
    }

    @PutMapping("/update")
    public void updateLastName(@RequestParam String firstName,@RequestParam String lastName) {
        readerService.updateLastNameBy(firstName,lastName);
    }
}
