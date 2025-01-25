package com.progi.progi.web;

import com.progi.progi.model.Article;
import com.progi.progi.model.ArticleFront;
import com.progi.progi.model.Closet;
import com.progi.progi.model.Locatedat;
import com.progi.progi.model.Location;
import com.progi.progi.repository.ArticleRepository;
import com.progi.progi.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"https://closetly-721y.onrender.com", "http://localhost:5173"})
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private LocatedatService locatedatService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private OrmarService ormarService;

    //vraća 8 nasumičnih artikala iz baze
    @GetMapping("/getFeatured")
    public Iterable<ArrayList<String>> getArticles() {
        return articleService.getFeatured().entrySet().stream().map((el) -> {
            ArrayList<String> list = new ArrayList<>();
            list.add(el.getKey().getArticlename());
            list.add(el.getKey().getCategory());
            list.add(el.getKey().getFormality());
            list.add(el.getKey().getAvailability());
            list.add(el.getValue());
            list.add(el.getKey().getArticlepicture());
            return list;
        }).collect(Collectors.toList());
    }

    @GetMapping("/getArticle/{id}")
    public Article getArticle(@PathVariable Integer id) {
        return articleService.get(id);
    }

    @GetMapping(value = "/getImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable Integer id) throws IOException, URISyntaxException {
        return Files.readAllBytes(Path.of(Objects.requireNonNull(this.getClass().getResource("/static/images/" + id + ".jpg")).toURI()));
        // return Files.readAllBytes(new ClassPathResource("/static/images/" + id + ".jpg").getFile().toPath());
    }

//    @PostMapping("/addArticle")
//    public Article postArticle(@RequestBody Article article, @RequestBody String type) {
//        return articleService.add(article, type);
//    }

    @PostMapping("/addArticle")
    public Article postArticle(@RequestParam("articlePicture") MultipartFile file, @RequestParam("articleName") String articleName, @RequestParam("category") String category, @RequestParam("seasonality") String seasonality, @RequestParam("formality") String formality, @RequestParam("maincolor") String maincolor, @RequestParam("secondarycolor") String secondarycolor, @RequestParam("availability") String availability, @RequestParam("activeElement") String activeElement, @RequestParam("closetID") String id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            try {
                Article article = new Article();
                String imageUrl = cloudinaryService.uploadFile(file);
                article.setArticlename(articleName);
                article.setArticlepicture(imageUrl);
                article.setCategory(category);
                article.setSeasonality(seasonality);
                article.setFormality(formality);
                article.setMaincolor(maincolor);
                article.setSecondarycolor(secondarycolor);
                article.setAvailability(availability);
                article.setUserid(UserService.getUserFromSession(session));
                Article newArticle = articleService.add(article);
                Integer closetID = Integer.parseInt(id);
                Location location = locationService.findLocation(closetID, activeElement.split("-")[0], Integer.parseInt(activeElement.split("-")[1]) + 1);
                locatedatService.add(new Locatedat(newArticle.getId(), location.getId(), closetID));
                return newArticle;
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    @DeleteMapping("/removeArticle/{id}")
    public boolean removeArticle(@PathVariable Integer id) {
        return articleService.remove(id);
    }

    @GetMapping("/getUserArticles")
    public List<Article> getUserArticles(@RequestParam String activeElement, @RequestParam String closetID) {
        List<Article> articles = new ArrayList<>();
        Closet closet = ormarService.get(Integer.parseInt(closetID));
        String type = activeElement.split("-")[0];
        int locationNo = Integer.parseInt(activeElement.split("-")[1]);
        Location location = locationService.findLocation(Integer.parseInt(closetID), type, locationNo);
        List<Locatedat> locatedat = locatedatService.getByLocation(location.getId());
        for (Locatedat locatedat1 : locatedat) {
            int articleid = locatedat1.getArticleid();
            articles.add(articleService.get(articleid));
        }
        return articles;
    }

    @GetMapping("/search")
    public List<Article> search(@RequestParam("query") String query) {
        return articleService.startsWith(query);
    }

    @GetMapping("/searchUser")
    public List<Article> searchUser(@RequestParam("query") String query, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            return articleService.startsWithAndBelongsTo(UserService.getUserFromSession(session), query);
        }
        return null;
    }

    @GetMapping("/sellerArticles")
    public List<Article> sellerArticles() {
        return articleService.getAllSellerArticles();
    }

    @PostMapping("/sellerAddArticle")
    public Article postArticle(@RequestParam("articlePicture") MultipartFile file, @RequestParam("articleName") String articleName, @RequestParam("category") String category, @RequestParam("price") String price, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            try {
                Article article = new Article();
                String imageUrl = cloudinaryService.uploadFile(file);
                article.setArticlename(articleName);
                article.setArticlepicture(imageUrl);
                article.setCategory(category);
                article.setPrice(new BigDecimal(price));
                article.setUserid(UserService.getUserFromSession(session));
                return articleService.add(article);
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    @GetMapping("/getClosetArticles")
    public List<ArticleFront> getClosetArticles(@RequestParam Integer closetID) {
        List<ArticleFront> closetArticles = new ArrayList<ArticleFront>();
        List<Locatedat> locatedats = locatedatService.getByClosetID(closetID);
        for (Locatedat locatedat : locatedats) {
            ArticleFront closetArticle = new ArticleFront();
            Location location = locationService.get(locatedat.getLocationid());
            String element = location.getLocationtype().concat("-").concat(String.valueOf(location.getLocationnumber()-1));
            Article article = articleService.get(locatedat.getArticleid());
            closetArticle.setArticleName(article.getArticlename());
            closetArticle.setCategory(article.getCategory());
            closetArticle.setFormality(article.getFormality());
            closetArticle.setColor(article.getMaincolor());
            closetArticle.setSecondaryColor(article.getSecondarycolor());
            closetArticle.setClosetName(ormarService.get(closetID).getClosetname());
            closetArticle.setCondition(article.getAvailability());
            closetArticle.setElementName(element);
            closetArticle.setText(null);
            closetArticle.setImage(article.getArticlepicture());
            closetArticle.setForSharing(true);
            closetArticle.setSeason(article.getSeasonality());
            closetArticle.setDescription(null);
            closetArticles.add(closetArticle);
        }
        return closetArticles;
    }
}