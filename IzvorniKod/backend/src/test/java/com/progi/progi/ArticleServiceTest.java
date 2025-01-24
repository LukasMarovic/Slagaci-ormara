package com.progi.progi;

import com.progi.progi.model.Article;
import com.progi.progi.model.Clothes;
import com.progi.progi.model.Footwear;
import com.progi.progi.model.Locatedat;
import com.progi.progi.repository.ArticleRepository;
import com.progi.progi.repository.UserRepository;
import com.progi.progi.service.ArticleService;
import com.progi.progi.service.ClothesService;
import com.progi.progi.service.FootwearService;
import com.progi.progi.service.LocatedatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private FootwearService footwearService;

    @Mock
    private ClothesService clothesService;

    @Mock
    private LocatedatService locatedatService;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Postojeći Testovi

    @Test
    void testGet() {
        // Arrange
        Article article = new Article();
        article.setId(1);
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));

        // Act
        Article result = articleService.get(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(articleRepository, times(1)).findById(1);
    }

    @Test
    void testAdd_Article() {
        // Arrange
        Article article = new Article();
        article.setId(2);
        when(articleRepository.save(article)).thenReturn(article);

        // Act
        Article result = articleService.add(article);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getId());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    void testRemove() {
        // Arrange
        Integer articleId = 3;
        when(footwearService.getById(articleId)).thenReturn(null);
        when(clothesService.getById(articleId)).thenReturn(null);
        when(locatedatService.getByArticleID(articleId)).thenReturn(Collections.emptyList());
        doNothing().when(articleRepository).deleteById(articleId);

        // Act
        boolean result = articleService.remove(articleId);

        // Assert
        assertTrue(result);
        verify(locatedatService, times(1)).getByArticleID(articleId);
        verify(locatedatService, never()).delete(anyInt());
        verify(footwearService, times(1)).getById(articleId);
        verify(clothesService, times(1)).getById(articleId);
        verify(articleRepository, times(1)).deleteById(articleId);
    }

    // Dodatni Testovi

    @Test
    void testAdd_WithParameters_Footwear() {
        // Arrange
        String nazivArtikla = "Patike";
        String slikaArtikla = "patike.jpg";
        String opcaKategorija = "Obuća";
        String kategorijaGoddoba = "Ljeto";
        String kategorijaLezernosti = "Neformalno";
        String glavnaBoja = "Crna";
        String sporednaBoja = "Bijela";
        String stanjeArtikla = "Novo";
        Integer sifKorisnika = 1;
        String type = "footwear";

        Article savedArticle = new Article();
        savedArticle.setId(4);
        when(articleRepository.save(any(Article.class))).thenReturn(savedArticle);

        // Act
        Article result = articleService.add(nazivArtikla, slikaArtikla, opcaKategorija, kategorijaGoddoba,
                kategorijaLezernosti, glavnaBoja, sporednaBoja, stanjeArtikla, sifKorisnika, type);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.getId());
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        verify(articleRepository, times(1)).save(articleCaptor.capture());

        Article capturedArticle = articleCaptor.getValue();
        assertEquals(nazivArtikla, capturedArticle.getArticlename());
        assertEquals(slikaArtikla, capturedArticle.getArticlepicture());
        assertEquals(opcaKategorija, capturedArticle.getCategory());
        assertEquals(kategorijaGoddoba, capturedArticle.getSeasonality());
        assertEquals(kategorijaLezernosti, capturedArticle.getFormality());
        assertEquals(glavnaBoja, capturedArticle.getMaincolor());
        assertEquals(sporednaBoja, capturedArticle.getSecondarycolor());
        assertEquals(stanjeArtikla, capturedArticle.getAvailability());
        assertEquals(sifKorisnika, capturedArticle.getId());

        verify(footwearService, times(1)).add(any(Footwear.class));
        verify(clothesService, never()).add(any(Clothes.class));
    }

    @Test
    void testAdd_WithParameters_Clothes() {
        // Arrange
        String nazivArtikla = "Majica";
        String slikaArtikla = "majica.jpg";
        String opcaKategorija = "Odjeća";
        String kategorijaGoddoba = "Proljeće";
        String kategorijaLezernosti = "Formalno";
        String glavnaBoja = "Plava";
        String sporednaBoja = "Siva";
        String stanjeArtikla = "Polovno";
        Integer sifKorisnika = 2;
        String type = "clothes";

        Article savedArticle = new Article();
        savedArticle.setId(5);
        when(articleRepository.save(any(Article.class))).thenReturn(savedArticle);

        // Act
        Article result = articleService.add(nazivArtikla, slikaArtikla, opcaKategorija, kategorijaGoddoba,
                kategorijaLezernosti, glavnaBoja, sporednaBoja, stanjeArtikla, sifKorisnika, type);

        // Assert
        assertNotNull(result);
        assertEquals(5, result.getId());
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        verify(articleRepository, times(1)).save(articleCaptor.capture());

        Article capturedArticle = articleCaptor.getValue();
        assertEquals(nazivArtikla, capturedArticle.getArticlename());
        assertEquals(slikaArtikla, capturedArticle.getArticlepicture());
        assertEquals(opcaKategorija, capturedArticle.getCategory());
        assertEquals(kategorijaGoddoba, capturedArticle.getSeasonality());
        assertEquals(kategorijaLezernosti, capturedArticle.getFormality());
        assertEquals(glavnaBoja, capturedArticle.getMaincolor());
        assertEquals(sporednaBoja, capturedArticle.getSecondarycolor());
        assertEquals(stanjeArtikla, capturedArticle.getAvailability());
        assertEquals(sifKorisnika, capturedArticle.getId());

        verify(clothesService, times(1)).add(any(Clothes.class));
        verify(footwearService, never()).add(any(Footwear.class));
    }

    @Test
    void testAdd_ArticleWithType_Footwear() {
        // Arrange
        Article article = new Article();
        article.setId(6);
        String type = "footwear";
        when(articleRepository.save(article)).thenReturn(article);

        // Act
        Article result = articleService.add(article, type);

        // Assert
        assertNotNull(result);
        assertEquals(6, result.getId());
        verify(articleRepository, times(1)).save(article);
        verify(footwearService, times(1)).add(any(Footwear.class));
        verify(clothesService, never()).add(any(Clothes.class));
    }

    @Test
    void testAdd_ArticleWithType_Clothes() {
        // Arrange
        Article article = new Article();
        article.setId(7);
        String type = "clothes";
        when(articleRepository.save(article)).thenReturn(article);

        // Act
        Article result = articleService.add(article, type);

        // Assert
        assertNotNull(result);
        assertEquals(7, result.getId());
        verify(articleRepository, times(1)).save(article);
        verify(clothesService, times(1)).add(any(Clothes.class));
        verify(footwearService, never()).add(any(Footwear.class));
    }

    @Test
    void testGetAll() {
        // Arrange
        List<Article> articles = Arrays.asList(
                new Article(),
                new Article(),
                new Article()
        );
        when(articleRepository.findAll()).thenReturn(articles);

        // Act
        Iterable<Article> result = articleService.getAll();

        // Assert
        assertNotNull(result);
        assertTrue(result instanceof List);
        assertEquals(3, ((List<Article>) result).size());
        verify(articleRepository, times(1)).findAll();
    }

    @Test
    void testGetByUserID() {
        // Arrange
        Integer userId = 10;
        List<Article> articles = Arrays.asList(
                new Article(),
                new Article()
        );
        when(articleRepository.getByUserID(userId)).thenReturn(articles);

        // Act
        List<Article> result = articleService.getByUserID(userId);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(articleRepository, times(1)).getByUserID(userId);
    }

    @Test
    void testGetFeatured() {
        // Arrange
        List<Article> allArticles = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Article article = new Article();
            article.setId(i);
            article.setUserid(i);
            allArticles.add(article);
        }
        when(articleRepository.findAll()).thenReturn(allArticles);

        // Mocking userRepository.findById
        for (int i = 1; i <= 10; i++) {
            when(userRepository.findById(i)).thenReturn(Optional.of(new com.progi.progi.model.Users()));
        }

        // Act
        Map<Article, String> result = articleService.getFeatured();

        // Assert
        assertNotNull(result);
        assertEquals(8, result.size());

        for (Map.Entry<Article, String> entry : result.entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
            verify(userRepository, times(1)).findById(entry.getKey().getUserid());
        }

        verify(articleRepository, times(1)).findAll();
    }

    // Dodatni Testovi za Edge Cases

    @Test
    void testGet_NonExistingArticle() {
        // Arrange
        when(articleRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Article result = articleService.get(999);

        // Assert
        assertNull(result);
        verify(articleRepository, times(1)).findById(999);
    }

    @Test
    void testAdd_InvalidType() {
        // Arrange
        Article article = new Article();
        article.setId(8);
        String type = "invalid_type";
        when(articleRepository.save(article)).thenReturn(article);

        // Act
        Article result = articleService.add(article, type);

        // Assert
        assertNotNull(result);
        assertEquals(8, result.getId());
        verify(articleRepository, times(1)).save(article);
        verify(footwearService, never()).add(any(Footwear.class));
        verify(clothesService, never()).add(any(Clothes.class));
    }

    @Test
    void testRemove_WithLocatedatEntries() {
        // Arrange
        Integer articleId = 4;
        Locatedat locatedat1 = new Locatedat();
        locatedat1.setId(100);
        Locatedat locatedat2 = new Locatedat();
        locatedat2.setId(101);

        List<Locatedat> locatedatList = Arrays.asList(locatedat1, locatedat2);

        when(footwearService.getById(articleId)).thenReturn(null);
        when(clothesService.getById(articleId)).thenReturn(null);
        when(locatedatService.getByArticleID(articleId)).thenReturn(locatedatList);
        doNothing().when(locatedatService).delete(locatedat1.getId());
        doNothing().when(locatedatService).delete(locatedat2.getId());
        doNothing().when(articleRepository).deleteById(articleId);

        // Act
        boolean result = articleService.remove(articleId);

        // Assert
        assertTrue(result);
        verify(locatedatService, times(1)).getByArticleID(articleId);
        verify(locatedatService, times(1)).delete(locatedat1.getId());
        verify(locatedatService, times(1)).delete(locatedat2.getId());
        verify(articleRepository, times(1)).deleteById(articleId);
    }

    @Test
    void testAdd_WithNullType() {
        // Arrange
        Article article = new Article();
        article.setId(9);
        String type = null;
        when(articleRepository.save(article)).thenReturn(article);

        // Act
        Article result = articleService.add(article, type);

        // Assert
        assertNotNull(result);
        assertEquals(9, result.getId());
        verify(articleRepository, times(1)).save(article);
        verify(footwearService, never()).add(any(Footwear.class));
        verify(clothesService, never()).add(any(Clothes.class));
    }

    @Test
    void testAdd_WithEmptyType() {
        // Arrange
        Article article = new Article();
        article.setId(10);
        String type = "";
        when(articleRepository.save(article)).thenReturn(article);

        // Act
        Article result = articleService.add(article, type);

        // Assert
        assertNotNull(result);
        assertEquals(10, result.getId());
        verify(articleRepository, times(1)).save(article);
        verify(footwearService, never()).add(any(Footwear.class));
        verify(clothesService, never()).add(any(Clothes.class));
    }

    @Test
    void testAdd_WithFeaturedArticlesLessThan8() {
        // Arrange
        List<Article> allArticles = Arrays.asList(
                new Article(),
                new Article(),
                new Article()
        );
        when(articleRepository.findAll()).thenReturn(allArticles);

        // Act
        Map<Article, String> result = articleService.getFeatured();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        verify(articleRepository, times(1)).findAll();
    }
}
