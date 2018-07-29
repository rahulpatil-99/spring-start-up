package library.controller;

import library.domain.Reader;
import library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public void createReader(@RequestBody Reader reader){
        readerService.createReader(reader);
    }

    @DeleteMapping("/remove/{firstName}")
    public void deleteReader(@PathVariable String firstName) {
        readerService.removeByFirstName(firstName);
    }
}
