package com.progi.progi.service;

import com.progi.progi.model.Clothes;
import com.progi.progi.repository.ClothesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClothesServiceTest {

    @Mock
    private ClothesRepository clothesRepository;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ClothesService clothesService;

    private Clothes clothes1;
    private Clothes clothes2;

    @BeforeEach
    void setUp() {
        clothes1 = new Clothes();
        clothes1.setId(1);

        clothes2 = new Clothes();
        clothes2.setId(2);
    }

    @Test
    void testGetById_WhenClothesExists() {
        // Arrange
        when(clothesRepository.findById(1)).thenReturn(Optional.of(clothes1));

        // Act
        Clothes result = clothesService.getById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(clothesRepository, times(1)).findById(1);
    }

    @Test
    void testGetById_WhenClothesDoesNotExist() {
        // Arrange
        when(clothesRepository.findById(3)).thenReturn(Optional.empty());

        // Act
        Clothes result = clothesService.getById(3);

        // Assert
        assertNull(result);
        verify(clothesRepository, times(1)).findById(3);
    }

    @Test
    void testGetAll() {
        // Arrange
        List<Clothes> clothesList = Arrays.asList(clothes1, clothes2);
        when(clothesRepository.findAll()).thenReturn(clothesList);

        // Act
        List<Clothes> result = clothesService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(clothes1));
        assertTrue(result.contains(clothes2));
        verify(clothesRepository, times(1)).findAll();
    }

    @Test
    void testAdd() {
        // Arrange
        Clothes newClothes = new Clothes();
        newClothes.setId(3);
        when(clothesRepository.save(newClothes)).thenReturn(newClothes);

        // Act
        Clothes result = clothesService.add(newClothes);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getId());
        verify(clothesRepository, times(1)).save(newClothes);
    }

    @Test
    void testUpdate() {
        // Arrange
        Clothes updatedClothes = new Clothes();
        updatedClothes.setId(1);
        when(clothesRepository.save(updatedClothes)).thenReturn(updatedClothes);

        // Act
        Clothes result = clothesService.update(updatedClothes);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(clothesRepository, times(1)).save(updatedClothes);
    }

    @Test
    void testDelete_WhenClothesExists() {
        // Arrange
        int idToDelete = 1;
        when(clothesRepository.existsById(idToDelete)).thenReturn(true);
        doNothing().when(clothesRepository).deleteById(idToDelete);
        when(articleService.remove(idToDelete)).thenReturn(true); // Assuming remove returns boolean

        // Act
        boolean result = clothesService.delete(idToDelete);

        // Assert
        assertTrue(result);
        verify(clothesRepository, times(1)).existsById(idToDelete);
        verify(clothesRepository, times(1)).deleteById(idToDelete);
        verify(articleService, times(1)).remove(idToDelete);
    }

    @Test
    void testDelete_WhenClothesDoesNotExist() {
        // Arrange
        int idToDelete = 3;
        when(clothesRepository.existsById(idToDelete)).thenReturn(false);

        // Act
        boolean result = clothesService.delete(idToDelete);

        // Assert
        assertFalse(result);
        verify(clothesRepository, times(1)).existsById(idToDelete);
        verify(clothesRepository, never()).deleteById(anyInt());
        verify(articleService, never()).remove(anyInt());
    }

    @Test
    void testAdd_WhenRepositoryThrowsException() {
        // Arrange
        Clothes newClothes = new Clothes();
        newClothes.setId(3);
        when(clothesRepository.save(newClothes)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            clothesService.add(newClothes);
        });

        assertEquals("Database error", exception.getMessage());
        verify(clothesRepository, times(1)).save(newClothes);
    }

    @Test
    void testDelete_WhenArticleServiceThrowsException() {
        // Arrange
        int idToDelete = 1;
        when(clothesRepository.existsById(idToDelete)).thenReturn(true);
        doNothing().when(clothesRepository).deleteById(idToDelete);
        when(articleService.remove(idToDelete)).thenThrow(new RuntimeException("Removal error"));

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            clothesService.delete(idToDelete);
        });

        assertEquals("Removal error", exception.getMessage());
        verify(clothesRepository, times(1)).existsById(idToDelete);
        verify(clothesRepository, times(1)).deleteById(idToDelete);
        verify(articleService, times(1)).remove(idToDelete);
    }
}
