package library.service;

import library.domain.Book;
import library.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private BookService bookService;

    @Before
    public void setUp() {
        this.bookService=new BookService(bookRepository);
    }

    @Test
    public void shouldReturnEmptyListForUnavailableBookName() {
        List<Book> books = bookService.getByTitle("some title");
        assertEquals(0,books.size());
    }

    @Test
    public void shouldReturnListForAvailableBookName() {
        Book book = new Book("some title", "abc", "123a");
        when(bookRepository.findByTitle("some title")).thenReturn(Collections.singletonList(book));
        List<Book> books = bookService.getByTitle("some title");
        assertEquals(1,books.size());
    }

    @Test
    public void shouldReturnEmptyListForUnavailableAuthorName() {
        List<Book> books = bookService.getByAuthor("some author");
        assertEquals(0,books.size());
    }

    @Test
    public void shouldReturnAvailableBooksForGivenAuthorName() {
        Book book = new Book("some title", "some author", "123a");
        when(bookRepository.findByAuthor("some author")).thenReturn(Collections.singletonList(book));
        List<Book> books = bookService.getByAuthor("some author");
        assertEquals(1,books.size());
    }

    @Test
    public void shouldReturnNullForUnavailableIsbn() {
        Book book = bookService.getByIsbn("a1234");
        assertEquals(null,book);
    }

    @Test
    public void shouldReturnBookForAvailableIsbn() {
        Book book = new Book("some title", "some author", "123a");
        when(bookRepository.findByIsbn("123a")).thenReturn(book);
        Book expectedBook = bookService.getByIsbn("123a");
        assertEquals(expectedBook,book);
    }
}