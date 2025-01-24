package com.progi.progi;

import com.progi.progi.model.*;
import com.progi.progi.repository.UserRepository;
import com.progi.progi.service.*;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrmarService ormarService;

    @Mock
    private ArticleService articleService;

    @Mock
    private RegistereduserService registereduserService;

    @Mock
    private SellerService sellerService;

    @InjectMocks
    private UserService userService;

    private Users user1;
    private Users user2;

    private Registereduser registeredUser1;
    private Seller seller1;

    @BeforeEach
    void setUp() {
        // Inicijalizacija Users objekata
        user1 = new Users();
        user1.setId(1);
        user1.setUsername("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password123");

        user2 = new Users();
        user2.setId(2);
        user2.setUsername("Jane Smith");
        user2.setEmail("jane.smith@example.com");
        user2.setPassword("password456");

        // Inicijalizacija Registereduser objekta
        registeredUser1 = new Registereduser();
        registeredUser1.setId(1);
        registeredUser1.setGeolocation("Europe");

        // Inicijalizacija Seller objekta
        seller1 = new Seller();
        seller1.setId(2);
        seller1.setLogo("seller_logo.png");
    }

    // Test za get(int id) kada korisnik postoji
    @Test
    void testGetUser_WhenUserExists() {
        // Arrange
        when(userRepository.findById(1)).thenReturn(Optional.of(user1));

        // Act
        Users result = userService.get(1);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getUsername());
        verify(userRepository, times(1)).findById(1);
    }

    // Test za get(int id) kada korisnik ne postoji
    @Test
    void testGetUser_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findById(3)).thenReturn(Optional.empty());

        // Act
        Users result = userService.get(3);

        // Assert
        assertNull(result);
        verify(userRepository, times(1)).findById(3);
    }

    // Test za getByEmail(String email) kada korisnici postoje
    @Test
    void testGetByEmail_WhenUsersExist() {
        // Arrange
        List<Users> users = Arrays.asList(user1, user2);
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Collections.singletonList(user1));
        when(userRepository.findByEmail("jane.smith@example.com")).thenReturn(Collections.singletonList(user2));

        // Act
        List<Users> result1 = userService.getByEmail("john.doe@example.com");
        List<Users> result2 = userService.getByEmail("jane.smith@example.com");

        // Assert
        assertNotNull(result1);
        assertEquals(1, result1.size());
        assertEquals("John Doe", result1.get(0).getUsername());

        assertNotNull(result2);
        assertEquals(1, result2.size());
        assertEquals("Jane Smith", result2.get(0).getUsername());

        verify(userRepository, times(1)).findByEmail("john.doe@example.com");
        verify(userRepository, times(1)).findByEmail("jane.smith@example.com");
    }

    // Test za getByEmail(String email) kada korisnici ne postoje
    @Test
    void testGetByEmail_WhenNoUsersExist() {
        // Arrange
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Collections.emptyList());

        // Act
        List<Users> result = userService.getByEmail("nonexistent@example.com");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findByEmail("nonexistent@example.com");
    }

    // Test za getAllUsers()
    @Test
    void testGetAllUsers() {
        // Arrange
        List<Users> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<Users> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
        verify(userRepository, times(1)).findAll();
    }

    // Test za add(Users user, String role) sa rolom "seller"
    @Test
    void testAddUser_SellerRole() {
        // Arrange
        Users newUser = new Users();
        newUser.setUsername("Alice Brown");
        newUser.setEmail("alice.brown@example.com");
        newUser.setPassword("password789");

        Users savedUser = new Users();
        savedUser.setId(3);
        savedUser.setUsername("Alice Brown");
        savedUser.setEmail("alice.brown@example.com");
        savedUser.setPassword("password789");

        when(userRepository.save(newUser)).thenReturn(savedUser);
        when(sellerService.add(any(Seller.class))).thenReturn(seller1);

        // Act
        Users result = userService.add(newUser, "seller");

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getId());
        assertEquals("Alice Brown", result.getUsername());

        verify(userRepository, times(1)).save(newUser);
        ArgumentCaptor<Seller> sellerCaptor = ArgumentCaptor.forClass(Seller.class);
        verify(sellerService, times(1)).add(sellerCaptor.capture());

        Seller capturedSeller = sellerCaptor.getValue();
        assertEquals(3, capturedSeller.getId());
    }

    // Test za add(Users user, String role) sa rolom "registereduser"
    @Test
    void testAddUser_RegisteredUserRole() {
        // Arrange
        Users newUser = new Users();
        newUser.setUsername("Bob Green");
        newUser.setEmail("bob.green@example.com");
        newUser.setPassword("password101");

        Users savedUser = new Users();
        savedUser.setId(4);
        savedUser.setUsername("Bob Green");
        savedUser.setEmail("bob.green@example.com");
        savedUser.setPassword("password101");

        when(userRepository.save(newUser)).thenReturn(savedUser);
        when(registereduserService.add(any(Registereduser.class))).thenReturn(registeredUser1);

        // Act
        Users result = userService.add(newUser, "registereduser");

        // Assert
        assertNotNull(result);
        assertEquals(4, result.getId());
        assertEquals("Bob Green", result.getUsername());

        verify(userRepository, times(1)).save(newUser);
        ArgumentCaptor<Registereduser> registeredUserCaptor = ArgumentCaptor.forClass(Registereduser.class);
        verify(registereduserService, times(1)).add(registeredUserCaptor.capture());

        Registereduser capturedRegisteredUser = registeredUserCaptor.getValue();
        assertEquals(4, capturedRegisteredUser.getId());
    }

    // Test za addRegistered(Users user, String geolocation)
    @Test
    void testAddRegisteredUser() {
        // Arrange
        Users newUser = new Users();
        newUser.setUsername("Charlie White");
        newUser.setEmail("charlie.white@example.com");
        newUser.setPassword("password202");

        Users savedUser = new Users();
        savedUser.setId(5);
        savedUser.setUsername("Charlie White");
        savedUser.setEmail("charlie.white@example.com");
        savedUser.setPassword("password202");

        Registereduser newRegisteredUser = new Registereduser();
        newRegisteredUser.setId(5);
        newRegisteredUser.setGeolocation("North America");

        when(userRepository.save(newUser)).thenReturn(savedUser);
        when(registereduserService.add(any(Registereduser.class))).thenReturn(newRegisteredUser);

        // Act
        Users result = userService.addRegistered(newUser, "North America");

        // Assert
        assertNotNull(result);
        assertEquals(5, result.getId());
        assertEquals("Charlie White", result.getUsername());

        verify(userRepository, times(1)).save(newUser);
        ArgumentCaptor<Registereduser> registeredUserCaptor = ArgumentCaptor.forClass(Registereduser.class);
        verify(registereduserService, times(1)).add(registeredUserCaptor.capture());

        Registereduser capturedRegisteredUser = registeredUserCaptor.getValue();
        assertEquals(5, capturedRegisteredUser.getId());
        assertEquals("North America", capturedRegisteredUser.getGeolocation());
    }

    // Test za addSeller(Users user, String image)
    @Test
    void testAddSeller() {
        // Arrange
        Users newUser = new Users();
        newUser.setUsername("Diana Black");
        newUser.setEmail("diana.black@example.com");
        newUser.setPassword("password303");

        Users savedUser = new Users();
        savedUser.setId(6);
        savedUser.setUsername("Diana Black");
        savedUser.setEmail("diana.black@example.com");
        savedUser.setPassword("password303");

        Seller newSeller = new Seller();
        newSeller.setId(6);
        newSeller.setLogo("seller_logo.png");

        when(userRepository.save(newUser)).thenReturn(savedUser);
        when(sellerService.add(any(Seller.class), eq("image.png"))).thenReturn(newSeller);

        // Act
        Users result = userService.addSeller(newUser, "image.png");

        // Assert
        assertNotNull(result);
        assertEquals(6, result.getId());
        assertEquals("Diana Black", result.getUsername());

        verify(userRepository, times(1)).save(newUser);
        verify(sellerService, times(1)).add(any(Seller.class), eq("image.png"));
    }



    // Test za delete(int id) kada korisnik postoji i ima povezane entitete
    @Test
    void testDeleteUser_WhenUserExists() {
        // Arrange
        int userId = 1;
        Closet closet1 = new Closet();
        closet1.setId(10);
        Closet closet2 = new Closet();
        closet2.setId(11);
        Article article1 = new Article();
        article1.setId(100);
        Article article2 = new Article();
        article2.setId(101);
        Seller seller = new Seller();
        seller.setId(userId);
        Registereduser registeredUser = new Registereduser();
        registeredUser.setId(userId);

        when(ormarService.getByUserID(userId)).thenReturn(Arrays.asList(closet1, closet2));
        when(articleService.getByUserID(userId)).thenReturn(Arrays.asList(article1, article2));
        when(sellerService.getById(userId)).thenReturn(seller);
        when(registereduserService.getById(userId)).thenReturn(registeredUser);

        when(ormarService.delete(closet1.getId())).thenReturn(true);
        when(ormarService.delete(closet2.getId())).thenReturn(true);
        when(articleService.remove(article1.getId())).thenReturn(true);
        when(articleService.remove(article2.getId())).thenReturn(true);
        doNothing().when(registereduserService).delete(userId);
        doNothing().when(sellerService).delete(userId);
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.delete(userId);

        // Assert
        verify(ormarService, times(1)).getByUserID(userId);
        verify(ormarService, times(1)).delete(closet1.getId());
        verify(ormarService, times(1)).delete(closet2.getId());
        verify(articleService, times(1)).getByUserID(userId);
        verify(articleService, times(1)).remove(article1.getId());
        verify(articleService, times(1)).remove(article2.getId());
        verify(sellerService, times(1)).getById(userId);
        verify(sellerService, times(1)).delete(userId);
        verify(registereduserService, times(1)).getById(userId);
        verify(registereduserService, times(1)).delete(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    // Test za delete(int id) kada korisnik ne postoji (bez povezanih entiteta)
    @Test
    void testDeleteUser_WhenUserDoesNotExist() {
        // Arrange
        int userId = 2;
        when(ormarService.getByUserID(userId)).thenReturn(Collections.emptyList());
        when(articleService.getByUserID(userId)).thenReturn(Collections.emptyList());
        when(sellerService.getById(userId)).thenReturn(null);
        when(registereduserService.getById(userId)).thenReturn(null);

        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.delete(userId);

        // Assert
        verify(ormarService, times(1)).getByUserID(userId);
        verify(ormarService, never()).delete(anyInt());
        verify(articleService, times(1)).getByUserID(userId);
        verify(articleService, never()).remove(anyInt());
        verify(sellerService, times(1)).getById(userId);
        verify(sellerService, never()).delete(anyInt());
        verify(registereduserService, times(1)).getById(userId);
        verify(registereduserService, never()).delete(anyInt());
        verify(userRepository, times(1)).deleteById(userId);
    }

    // Test za getUserFromSession(HttpSession session) kada session sadrži korisnički ID
    @Test
    void testGetUserFromSession_WithValidSession() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("sif_korisnika")).thenReturn(1);

        // Act
        Integer result = UserService.getUserFromSession(session);

        // Assert
        assertNotNull(result);
        assertEquals(1, result);
        verify(session, times(1)).getAttribute("sif_korisnika");
    }

    // Test za getUserFromSession(HttpSession session) kada session ne sadrži korisnički ID
    @Test
    void testGetUserFromSession_WithInvalidSession() {
        // Arrange
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("sif_korisnika")).thenReturn(null);

        // Act
        Integer result = UserService.getUserFromSession(session);

        // Assert
        assertNull(result);
        verify(session, times(1)).getAttribute("sif_korisnika");
    }

    // Test za getUserFromSession(HttpSession session) kada je session null
    @Test
    void testGetUserFromSession_WithNullSession() {
        // Act
        Integer result = UserService.getUserFromSession(null);

        // Assert
        assertNull(result);
    }

    // Dodatni testovi za edge slučajeve i specifične scenarije

    // Test za add(Users user, String role) sa nepoznatom rolom
    @Test
    void testAddUser_WithUnknownRole() {
        // Arrange
        Users newUser = new Users();
        newUser.setUsername("Eve White");
        newUser.setEmail("eve.white@example.com");
        newUser.setPassword("password404");

        Users savedUser = new Users();
        savedUser.setId(7);
        savedUser.setUsername("Eve White");
        savedUser.setEmail("eve.white@example.com");
        savedUser.setPassword("password404");

        when(userRepository.save(newUser)).thenReturn(savedUser);

        // Act
        Users result = userService.add(newUser, "unknown_role");

        // Assert
        assertNotNull(result);
        assertEquals(7, result.getId());
        assertEquals("Eve White", result.getUsername());

        verify(userRepository, times(1)).save(newUser);
        verify(sellerService, never()).add(any(Seller.class));
        verify(registereduserService, never()).add(any(Registereduser.class));
    }

    // Test za addRegistered(Users user, String geolocation) kada userRepository.save() baca izuzetak
    @Test
    void testAddRegisteredUser_WhenSaveThrowsException() {
        // Arrange
        Users newUser = new Users();
        newUser.setUsername("Frank Brown");
        newUser.setEmail("frank.brown@example.com");
        newUser.setPassword("password505");

        when(userRepository.save(newUser)).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.addRegistered(newUser, "Asia");
        });

        assertEquals("Database error", exception.getMessage());
        verify(userRepository, times(1)).save(newUser);
        verify(registereduserService, never()).add(any(Registereduser.class));
    }


    // Test za delete(int id) kada korisnik ima samo jedan tip entiteta (npr. samo Seller)
    @Test
    void testDeleteUser_WithOnlySeller() {
        // Arrange
        int userId = 3;
        List<Closet> closets = Collections.emptyList();
        List<Article> articles = Collections.emptyList();
        Seller seller = new Seller();
        seller.setId(userId);
        Registereduser registeredUser = null;

        when(ormarService.getByUserID(userId)).thenReturn(closets);
        when(articleService.getByUserID(userId)).thenReturn(articles);
        when(sellerService.getById(userId)).thenReturn(seller);
        when(registereduserService.getById(userId)).thenReturn(registeredUser);

        doNothing().when(sellerService).delete(userId);
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.delete(userId);

        // Assert
        verify(ormarService, times(1)).getByUserID(userId);
        verify(ormarService, never()).delete(anyInt());
        verify(articleService, times(1)).getByUserID(userId);
        verify(articleService, never()).remove(anyInt());
        verify(sellerService, times(1)).getById(userId);
        verify(sellerService, times(1)).delete(userId);
        verify(registereduserService, times(1)).getById(userId);
        verify(registereduserService, never()).delete(anyInt());
        verify(userRepository, times(1)).deleteById(userId);
    }

    // Test za delete(int id) kada korisnik ima samo jedan tip entiteta (npr. samo Registereduser)
    @Test
    void testDeleteUser_WithOnlyRegisteredUser() {
        // Arrange
        int userId = 4;
        List<Closet> closets = Collections.emptyList();
        List<Article> articles = Collections.emptyList();
        Seller seller = null;
        Registereduser registeredUser = new Registereduser();
        registeredUser.setId(userId);
        registeredUser.setGeolocation("Africa");

        when(ormarService.getByUserID(userId)).thenReturn(closets);
        when(articleService.getByUserID(userId)).thenReturn(articles);
        when(sellerService.getById(userId)).thenReturn(seller);
        when(registereduserService.getById(userId)).thenReturn(registeredUser);

        doNothing().when(registereduserService).delete(userId);
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.delete(userId);

        // Assert
        verify(ormarService, times(1)).getByUserID(userId);
        verify(ormarService, never()).delete(anyInt());
        verify(articleService, times(1)).getByUserID(userId);
        verify(articleService, never()).remove(anyInt());
        verify(sellerService, times(1)).getById(userId);
        verify(sellerService, never()).delete(anyInt());
        verify(registereduserService, times(1)).getById(userId);
        verify(registereduserService, times(1)).delete(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
