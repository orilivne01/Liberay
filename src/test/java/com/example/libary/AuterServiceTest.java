package com.example.libary;

import com.example.libary.dao.Auter;
import com.example.libary.exception.AuterNotFoundException;
import com.example.libary.repo.AuterGildRepo;
import com.example.libary.repo.AuterRepo;
import com.example.libary.repo.BooksRepo;
import com.example.libary.service.AuterService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AuterServiceTest {
    @Mock
    AuterRepo auterRepo;
    @Mock
    BooksRepo booksRepo;
    @Mock
    AuterGildRepo auterGildRepo;
    @InjectMocks
    static AuterService service;

    @BeforeClass
    public static void setUp(){
        service = new AuterService();
    }
    @Test
    public void testAddAuterSuccses(){
        Auter auter = new Auter();
        auter.setFirstName("firstName");
        auter.setLastName("lastName");

        PowerMockito.when(auterRepo.save(ArgumentMatchers.any())).thenReturn(auter);
        assertEquals(service.addAuter("firstName","lastName").getFirstName(),auter.getFirstName());

    }
    @Test
    public void testfindAuterByFirstNameAndLastNameSuccses(){
        Auter auter = new Auter();
        auter.setFirstName("firstName");
        auter.setLastName("lastName");
        Optional<Auter> optinalAuter = Optional.of(auter);

        PowerMockito.when(auterRepo.findAuterByFirstNameAndLastName(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(optinalAuter);
        assertEquals(optinalAuter.get(),service.findAuterByFirstNameAndLastName("firstName","lastName"));
    }
    @Test
    public void testfindAuterByFirstNameAndLastNameFail(){
        Optional<Auter> optinalAuter = Optional.empty();

        PowerMockito.when(auterRepo.findAuterByFirstNameAndLastName(ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(optinalAuter);
        try{
            service.findAuterByFirstNameAndLastName("firstName","lastName");
            assertEquals(0,1);
        }
        catch (Exception ex){
            assertEquals(ex.getClass(),AuterNotFoundException.class);
            assertEquals(ex.getMessage(),"Auter with first name = firstName last name = lastName not found");
        }
    }
}
