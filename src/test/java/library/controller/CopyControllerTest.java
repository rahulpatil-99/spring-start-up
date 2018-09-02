package library.controller;

import library.domain.Copy;
import library.service.CopyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CopyControllerTest {

    private CopyController controller;
    @Mock
    private CopyService service;
    @Before
    public void setUp() {
        controller = new CopyController(service);
    }

    @Test
    public void shouldCallCopyServiceAddCopyMethodToSaveCopy() {
        Copy copy = new Copy("1-1","1234",true);
        controller.addCopy(copy);
        verify(service,times(1)).addCopy(copy);
    }

    @Test
    public void shouldCallCopyServiceGetByCopyIdToGetCopy() {
        controller.getCopy("1-1");
        verify(service,times(1)).getByCopyId("1-1");
    }

    @Test
    public void shouldCallCopyServiceGetByIsbnToGetListOfCopies() {
         controller.getCopies("12345");
         verify(service,times(1)).getByIsbn("12345");
    }

    @Test
    public void shouldCallCopyServiceGetAvailableCopiesToSeeAvailableCopies() {
        controller.getAvailableCopies();
        verify(service,times(1)).getAvailableCopies();
    }

    @Test
    public void shouldCallCopyServiceGetAvailableCopiesForIsbn() {
        controller.getAvailableCopiesFor("12345");
        verify(service,times(1)).getAvailableCopiesFor("12345");
    }

    @Test
    public void shouldCallCopyServiceMakeAvailableWhenUserReturnBook() {
        controller.makeAvailable("1-1");
        verify(service,times(1)).makeAvailable("1-1");
    }

    @Test
    public void shouldCallCopyServiceMakeUnAvailableWhenUserBorrowBook() {
        controller.makeUnAvailable("1-1");
        verify(service,times(1)).makeUnAvailable("1-1");
    }
}