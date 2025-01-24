package com.progi.progi;

import com.progi.progi.model.Article;
import com.progi.progi.model.Footwear;
import com.progi.progi.model.Scrapper;
import com.progi.progi.repository.FootwearRepository;
import com.progi.progi.service.ArticleService;
import com.progi.progi.service.FootwearService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FootwearServiceTest {

    @Mock
    private FootwearRepository footwearRepository;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private FootwearService footwearService;

    // Koristimo MockedConstruction za mokiranje konstrukcije Scrapper objekta
    @Mock
    private Scrapper scrapperMock;

    private Article article1;
    private Article article2;

    private Footwear footwear1;
    private Footwear footwear2;

    @BeforeEach
    void setUp() {
        // Postavljanje artikala
        article1 = new Article();
        article1.setId(1);
        article1.setCategory("Boots");
        article1.setArticlename("Timberland");

        article2 = new Article();
        article2.setId(2);
        article2.setCategory("Sandals");
        article2.setArticlename("Birkenstock");

        // Postavljanje footwear objekata
        footwear1 = new Footwear();
        footwear1.setId(1);
        footwear1.setOpenness(null); // Prije dodavanja

        footwear2 = new Footwear();
        footwear2.setId(2);
        footwear2.setOpenness(null); // Prije dodavanja
    }

    @Test
    void testAddFootwear_OpenCoverage() {
        // Arrange
        when(articleService.get(1)).thenReturn(article1);
        when(footwearRepository.save(any(Footwear.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Mokiranje konstrukcije Scrapper i njegove metode getCoverage
        try (MockedConstruction<Scrapper> mocked = mockConstruction(Scrapper.class, (mock, context) -> {
            when(mock.getCoverage(eq("Boots"), anyList())).thenReturn("Open");
        })) {
            Footwear newFootwear = new Footwear();
            newFootwear.setId(1);
            newFootwear.setOpenness(null);

            // Act
            Footwear result = footwearService.add(newFootwear);

            // Assert
            assertNotNull(result);
            assertEquals("Open", result.getOpenness());

            // Verifikacija interakcija
            verify(articleService, times(1)).get(1);
            verify(footwearRepository, times(1)).save(newFootwear);

            // Verifikacija da je Scrapper konstrukcija pozvana
            assertEquals(1, mocked.constructed().size());
            Scrapper scrapperInstance = mocked.constructed().get(0);
            verify(scrapperInstance, times(1)).getCoverage(eq("Boots"), anyList());
        }
    }

    @Test
    void testAddFootwear_ClosedCoverage() {
        // Arrange
        when(articleService.get(2)).thenReturn(article2);
        when(footwearRepository.save(any(Footwear.class))).thenAnswer(invocation -> invocation.getArgument(0));

        try (MockedConstruction<Scrapper> mocked = mockConstruction(Scrapper.class, (mock, context) -> {
            when(mock.getCoverage(eq("Sandals"), anyList())).thenReturn("Closed");
        })) {
            Footwear newFootwear = new Footwear();
            newFootwear.setId(2);
            newFootwear.setOpenness(null);

            // Act
            Footwear result = footwearService.add(newFootwear);

            // Assert
            assertNotNull(result);
            assertEquals("Closed", result.getOpenness());

            // Verifikacija interakcija
            verify(articleService, times(1)).get(2);
            verify(footwearRepository, times(1)).save(newFootwear);

            // Verifikacija da je Scrapper konstrukcija pozvana
            assertEquals(1, mocked.constructed().size());
            Scrapper scrapperInstance = mocked.constructed().get(0);
            verify(scrapperInstance, times(1)).getCoverage(eq("Sandals"), anyList());
        }
    }

    @Test
    void testAddFootwear_RainCoverage() {
        // Arrange
        when(articleService.get(3)).thenReturn(null); // Članak ne postoji

        Footwear newFootwear = new Footwear();
        newFootwear.setId(3);
        newFootwear.setOpenness(null);

        // Act & Assert
        Exception exception = assertThrows(NullPointerException.class, () -> {
            footwearService.add(newFootwear);
        });

        assertNotNull(exception);
        verify(articleService, times(1)).get(3);
        verify(footwearRepository, never()).save(any(Footwear.class));

        // Verifikacija da Scrapper nije instanciran
        // Ovo je teško izvesti direktno, ali možete provjeriti broj konstrukcija
        // Međutim, u ovom slučaju, nije moguće koristiti try-with-resources blok
    }

    @Test
    void testAddFootwear_SnowCoverage() {
        // Pretpostavimo da postoji članak s id=4
        Article article4 = new Article();
        article4.setId(4);
        article4.setCategory("Slippers");
        article4.setArticlename("Crocs");

        when(articleService.get(4)).thenReturn(article4);
        when(footwearRepository.save(any(Footwear.class))).thenAnswer(invocation -> invocation.getArgument(0));

        try (MockedConstruction<Scrapper> mocked = mockConstruction(Scrapper.class, (mock, context) -> {
            when(mock.getCoverage(eq("Slippers"), anyList())).thenReturn("Snow");
        })) {
            Footwear newFootwear = new Footwear();
            newFootwear.setId(4);
            newFootwear.setOpenness(null);

            // Act
            Footwear result = footwearService.add(newFootwear);

            // Assert
            assertNotNull(result);
            assertEquals("Snow", result.getOpenness());

            // Verifikacija interakcija
            verify(articleService, times(1)).get(4);
            verify(footwearRepository, times(1)).save(newFootwear);

            // Verifikacija da je Scrapper konstrukcija pozvana
            assertEquals(1, mocked.constructed().size());
            Scrapper scrapperInstance = mocked.constructed().get(0);
            verify(scrapperInstance, times(1)).getCoverage(eq("Slippers"), anyList());
        }
    }

    @Test
    void testGetById_WhenFootwearExists() {
        // Arrange
        when(footwearRepository.findById(1)).thenReturn(Optional.of(footwear1));

        // Act
        Footwear result = footwearService.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertNull(result.getOpenness()); // Openness nije postavljen u ovom testu
        verify(footwearRepository, times(1)).findById(1);
    }

    @Test
    void testGetById_WhenFootwearDoesNotExist() {
        // Arrange
        when(footwearRepository.findById(5)).thenReturn(Optional.empty());

        // Act
        Footwear result = footwearService.getById(5);

        // Assert
        assertNull(result);
        verify(footwearRepository, times(1)).findById(5);
    }

    @Test
    void testGetAll() {
        // Arrange
        List<Footwear> footwearList = Arrays.asList(footwear1, footwear2);
        when(footwearRepository.findAll()).thenReturn(footwearList);

        // Act
        List<Footwear> result = footwearService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(footwear1));
        assertTrue(result.contains(footwear2));
        verify(footwearRepository, times(1)).findAll();
    }

    @Test
    void testDelete_WhenFootwearExists() {
        // Arrange
        int idToDelete = 1;
        when(footwearRepository.existsById(idToDelete)).thenReturn(true);
        when(articleService.remove(idToDelete)).thenReturn(true);

        // Act
        boolean result = footwearService.delete(idToDelete);

        // Assert
        assertTrue(result);
        verify(footwearRepository, times(1)).existsById(idToDelete);
        verify(articleService, times(1)).remove(idToDelete);
    }

    @Test
    void testDelete_WhenFootwearDoesNotExist() {
        // Arrange
        int idToDelete = 6;
        when(footwearRepository.existsById(idToDelete)).thenReturn(false);

        // Act
        boolean result = footwearService.delete(idToDelete);

        // Assert
        assertFalse(result);
        verify(footwearRepository, times(1)).existsById(idToDelete);
        verify(articleService, never()).remove(anyInt());
    }
}
