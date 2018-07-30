package library.controller;

import library.domain.Book;
import library.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;
    private BookController controller;
    @Mock
    private Book book;

    @Before
    public void setUp() {
        controller = new BookController(bookService);
    }

    @Test
    public void shouldCallBookServiceGetBookById() {
        controller.getBookById("12344");
        verify(bookService,times(1)).getByBookId("12344");
    }

    @Test
    public void shouldCallBookServiceAddBook() {
        controller.addBook(book);
        verify(bookService,times(1)).addBook(book);
    }
}
