package com.progi.progi;

import com.progi.progi.model.Closet;
import com.progi.progi.model.Location;
import com.progi.progi.repository.OrmarRepository;
import com.progi.progi.service.LocationService;
import com.progi.progi.service.OrmarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrmarServiceTest {

    @InjectMocks
    private OrmarService ormarService;

    @Mock
    private OrmarRepository ormarRepository;

    @Mock
    private LocationService locationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Helper metode za kreiranje Closet i Location objekata
    private Closet createCloset(int id, String name, Integer userId) {
        Closet closet = new Closet();
        closet.setId(id);
        closet.setClosetname(name);
        closet.setUserid(userId);
        return closet;
    }

    private Location createLocation(int id, int closetId, String type) {
        Location location = new Location();
        location.setId(id);
        location.setClosetid(closetId);
        location.setLocationtype(type);
        //location.setLocationnumber();
        return location;
    }

    // Test za get(int id) metodu
    @Test
    void testGet_ExistingCloset() {
        // Arrange
        int closetId = 1;
        Closet closet = createCloset(closetId, "Ormar 1", 10);
        when(ormarRepository.findById(closetId)).thenReturn(Optional.of(closet));

        // Act
        Closet result = ormarService.get(closetId);

        // Assert
        assertNotNull(result);
        assertEquals(closetId, result.getId());
        assertEquals("Ormar 1", result.getClosetname());
        assertEquals(10, result.getUserid());
        verify(ormarRepository, times(1)).findById(closetId);
    }

    @Test
    void testGet_NonExistingCloset() {
        // Arrange
        int closetId = 999;
        when(ormarRepository.findById(closetId)).thenReturn(Optional.empty());

        // Act
        Closet result = ormarService.get(closetId);

        // Assert
        assertNull(result);
        verify(ormarRepository, times(1)).findById(closetId);
    }

    // Test za getByUser(Integer userID) metodu
    @Test
    void testGetByUser_WithClosets() {
        // Arrange
        Integer userId = 10;
        List<Closet> ormari = Arrays.asList(
                createCloset(1, "Ormar 1", userId),
                createCloset(2, "Ormar 2", userId)
        );
        List<Location> locations1 = Arrays.asList(
                createLocation(100, 1, "Lokacija A"),
                createLocation(101, 1, "Lokacija B")
        );
        List<Location> locations2 = Collections.singletonList(
                createLocation(102, 2, "Lokacija C")
        );

        when(ormarRepository.findByUserID(userId)).thenReturn(ormari);
        when(locationService.getByClosetId(1)).thenReturn(locations1);
        when(locationService.getByClosetId(2)).thenReturn(locations2);

        // Act
        HashMap<String, List<Location>> result = ormarService.getByUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsKey("Ormar 1"));
        assertTrue(result.containsKey("Ormar 2"));
        assertEquals(locations1, result.get("Ormar 1"));
        assertEquals(locations2, result.get("Ormar 2"));

        verify(ormarRepository, times(1)).findByUserID(userId);
        verify(locationService, times(1)).getByClosetId(1);
        verify(locationService, times(1)).getByClosetId(2);
    }

    @Test
    void testGetByUser_NoClosets() {
        // Arrange
        Integer userId = 20;
        when(ormarRepository.findByUserID(userId)).thenReturn(Collections.emptyList());

        // Act
        HashMap<String, List<Location>> result = ormarService.getByUser(userId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(ormarRepository, times(1)).findByUserID(userId);
        verify(locationService, never()).getByClosetId(anyInt());
    }

    // Test za getAll() metodu
    @Test
    void testGetAll_WithClosets() {
        // Arrange
        List<Closet> ormari = Arrays.asList(
                createCloset(1, "Ormar 1", 10),
                createCloset(2, "Ormar 2", 20),
                createCloset(3, "Ormar 3", 30)
        );
        when(ormarRepository.findAll()).thenReturn(ormari);

        // Act
        List<Closet> result = ormarService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(ormari));
        verify(ormarRepository, times(1)).findAll();
    }

    @Test
    void testGetAll_NoClosets() {
        // Arrange
        when(ormarRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Closet> result = ormarService.getAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(ormarRepository, times(1)).findAll();
    }

    // Test za getByUserID(int userID) metodu
    @Test
    void testGetByUserID_WithClosets() {
        // Arrange
        int userId = 10;
        List<Closet> ormari = Arrays.asList(
                createCloset(1, "Ormar 1", userId),
                createCloset(2, "Ormar 2", userId)
        );
        when(ormarRepository.findByUserID(userId)).thenReturn(ormari);

        // Act
        List<Closet> result = ormarService.getByUserID(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsAll(ormari));
        verify(ormarRepository, times(1)).findByUserID(userId);
    }

    @Test
    void testGetByUserID_NoClosets() {
        // Arrange
        int userId = 20;
        when(ormarRepository.findByUserID(userId)).thenReturn(Collections.emptyList());

        // Act
        List<Closet> result = ormarService.getByUserID(userId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(ormarRepository, times(1)).findByUserID(userId);
    }

    // Test za add(Closet closet) metodu
    @Test
    void testAdd_Closet() {
        // Arrange
        Closet closet = createCloset(1, "Ormar 1", 10);
        when(ormarRepository.save(closet)).thenReturn(closet);

        // Act
        Closet result = ormarService.add(closet);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Ormar 1", result.getClosetname());
        assertEquals(10, result.getUserid());

        verify(ormarRepository, times(1)).save(closet);
    }

    @Test
    void testAdd_Closet_WithNullUserId() {
        // Arrange
        Closet closet = createCloset(2, "Ormar 2", null);
        when(ormarRepository.save(closet)).thenReturn(closet);

        // Act
        Closet result = ormarService.add(closet);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getId());
        assertEquals("Ormar 2", result.getClosetname());
        assertNull(result.getUserid());

        verify(ormarRepository, times(1)).save(closet);
    }

    // Test za add(String naziv) metodu
    @Test
    void testAdd_WithNaziv() {
        // Arrange
        String naziv = "Ormar 3";
        Closet closetToSave = createCloset(0, naziv, null); // id će biti ignoriran prilikom save
        Closet savedCloset = createCloset(3, naziv, null);
        when(ormarRepository.save(any(Closet.class))).thenReturn(savedCloset);

        // Act
        Closet result = ormarService.add(naziv);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals(naziv, result.getClosetname());
        assertNull(result.getUserid());

        ArgumentCaptor<Closet> closetCaptor = ArgumentCaptor.forClass(Closet.class);
        verify(ormarRepository, times(1)).save(closetCaptor.capture());
        Closet capturedCloset = closetCaptor.getValue();
        assertEquals(naziv, capturedCloset.getClosetname());
        assertNull(capturedCloset.getUserid());
    }

    // Test za delete(int id) metodu
    @Test
    void testDelete_WithLocations() {
        // Arrange
        int closetId = 1;
        Closet closet = createCloset(closetId, "Ormar 1", 10);
        List<Location> locations = Arrays.asList(
                createLocation(100, closetId, "Lokacija A"),
                createLocation(101, closetId, "Lokacija B")
        );

        when(ormarRepository.findById(closetId)).thenReturn(Optional.of(closet));
        when(locationService.getByClosetId(closetId)).thenReturn(locations);
        doNothing().when(locationService).delete(anyInt());
        doNothing().when(ormarRepository).delete(closet);

        // Act
        boolean result = ormarService.delete(closetId);

        // Assert
        assertTrue(result);
        verify(ormarRepository, times(1)).findById(closetId);
        verify(locationService, times(1)).getByClosetId(closetId);
        verify(locationService, times(1)).delete(100);
        verify(locationService, times(1)).delete(101);
        verify(ormarRepository, times(1)).delete(closet);
    }

    @Test
    void testDelete_NoLocations() {
        // Arrange
        int closetId = 2;
        Closet closet = createCloset(closetId, "Ormar 2", 20);
        List<Location> locations = Collections.emptyList();

        when(ormarRepository.findById(closetId)).thenReturn(Optional.of(closet));
        when(locationService.getByClosetId(closetId)).thenReturn(locations);
        doNothing().when(ormarRepository).delete(closet);

        // Act
        boolean result = ormarService.delete(closetId);

        // Assert
        assertTrue(result);
        verify(ormarRepository, times(1)).findById(closetId);
        verify(locationService, times(1)).getByClosetId(closetId);
        verify(locationService, never()).delete(anyInt());
        verify(ormarRepository, times(1)).delete(closet);
    }

    @Test
    void testDelete_NonExistingCloset() {
        // Arrange
        int closetId = 999;
        when(ormarRepository.findById(closetId)).thenReturn(Optional.empty());

        // Act
        boolean result = ormarService.delete(closetId);

        // Assert
        assertFalse(result);
        verify(ormarRepository, times(1)).findById(closetId);
        verify(locationService, never()).getByClosetId(anyInt());
        verify(locationService, never()).delete(anyInt());
        verify(ormarRepository, never()).delete(any(Closet.class));
    }

    @Test
    void testAdd_WithExistingClosetName() {

        String naziv = "Ormar 1";
        Closet savedCloset = createCloset(4, naziv, 30);
        when(ormarRepository.save(any(Closet.class))).thenReturn(savedCloset);

        // Act
        Closet result = ormarService.add(naziv);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.getId());
        assertEquals(naziv, result.getClosetname());
        assertNotNull(result.getUserid());

        verify(ormarRepository, times(1)).save(any(Closet.class));
    }

    @Test
    void testDelete_WithNullCloset() {
        // Arrange
        int closetId = 3;
        when(ormarRepository.findById(closetId)).thenReturn(Optional.ofNullable(null));

        // Act
        boolean result = ormarService.delete(closetId);

        // Assert
        assertFalse(result);
        verify(ormarRepository, times(1)).findById(closetId);
        verify(locationService, never()).getByClosetId(anyInt());
        verify(locationService, never()).delete(anyInt());
        verify(ormarRepository, never()).delete(any(Closet.class));
    }

    // Pretpostavimo da ClosetService ne baca izuzetke, ali ako to radi, možeš dodati testove za izuzetke
}
