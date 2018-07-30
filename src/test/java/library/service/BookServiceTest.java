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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    private BookService bookService;
    @Mock
    private Book book;

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
        when(bookRepository.findByIsbn("123a")).thenReturn(book);
        Book expectedBook = bookService.getByIsbn("123a");
        assertEquals(expectedBook,book);
    }

    @Test
    public void shouldReturnNullForUnavailableBookId() {
        Book book = bookService.getByBookId("1233");
        assertEquals(null,book);
    }

    @Test
    public void shouldReturnBookForAvailableBookId() {
        when(bookRepository.findByBookId("1233")).thenReturn(book);
        Book book1 = bookService.getByBookId("1233");
        assertEquals(book,book1);
    }

    @Test
    public void shouldCallBookRepoSave() {
        bookService.addBook(book);
        verify(bookRepository,times(1)).save(book);
    }
}