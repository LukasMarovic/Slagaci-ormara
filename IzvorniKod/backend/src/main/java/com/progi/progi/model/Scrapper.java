package com.progi.progi.model;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.Arrays;
import java.util.Random;

import com.progi.progi.service.ArticleService;
import com.progi.progi.service.UserService;
import com.progi.progi.web.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
//import scala.actors.threadpool.Arrays;

public class Scrapper {
    private List<String> urls = new LinkedList<>();

    public Scrapper() {
        List<String> urlSource = new LinkedList<>();
        urlSource.add("https://www.nabava.net/odjeca-muska");
        urlSource.add("https://www.nabava.net/odjeca-zenska");
        urlSource.add("https://www.nabava.net/obuca-muska");
        urlSource.add("https://www.nabava.net/obuca-zenska");
        try {
            for (String url : urlSource) {
                Document doc = Jsoup.connect(url).get();
                Elements productList = doc.select(".product-list--grid-style a");
                for (Element product : productList) {
                    urls.add("https://www.nabava.net" + product.attr("href"));
                    System.out.println("https://www.nabava.net" + product.attr("href"));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getUrls() {
        return urls;
    }

    public List<Article> getItems() throws IOException {
        List<Article> items = new LinkedList<>();
        String price = "";
        for (String url : getUrls()) {
            try {
                Document document = Jsoup.connect(url).get();
                Elements products = document.select(".product__data");
                for (Element product : products) {
                    Element nameElement = product.selectFirst(".product__name-and-price .product__name");
                    Element priceElement = product.selectFirst(".product__name-and-price .product__price");
                    String picture = "https://www.nabava.net" + product.selectFirst(".product__image").attr("src");
                    if (nameElement != null && priceElement != null) {
                        String name = nameElement.text(); //ime artikla
                        price = priceElement.text(); //cijena artikla
                        if (price.substring(0, 2).equals("od")) {
                            price = price.substring(3);
                        }

                        //analiza naziva artikla
                        String[] nameParts = name.toLowerCase().split("\\s+");
                        List<String> namePartsList = Arrays.asList(nameParts);

                        //svrstavanje u kategorije
                        String color = getColor(namePartsList); //boja artikla
                        String category = getCategory(namePartsList); //opća kategorija
                        String formality = getFormality(category, namePartsList);
                        String coverage = getCoverage(category, namePartsList);
                        String season = getSeason(category, namePartsList);

                        List<String> obuca = new ArrayList<>(Arrays.asList("Boots", "Trainers", "Sandals", "Slippers", "Heels"));

                        Article item = new Article();
                        item.setMaincolor(color.split("/")[0]);
                        item.setSecondarycolor(color.split("/")[1]);
                        item.setFormality(formality);
                        item.setCategory(category);
                        item.setArticlename(name);
                        item.setPrice(new BigDecimal(price.split(" ")[0]));
                        item.setAvailability("available");
                        item.setSeasonality(season);
                        item.setArticlepicture(picture);

                        RegistereduserController registereduserController = new RegistereduserController();

                        List<Registereduser> users = registereduserController.getAllRegistered();
                        List<Integer> ids = new ArrayList<>();

                        for (Registereduser user : users) {
                            ids.add(user.getId());
                        }

                        Random rand =  new Random();
                        item.setUserid(ids.get(rand.nextInt(ids.size())));

                        ArticleController articleController = new ArticleController();
                        Article newArticle = articleController.postArticle(item);
                        int id = newArticle.getId();
                        items.add(newArticle);

                        if (obuca.contains(category)) {
                            Footwear footwear = new Footwear();
                            footwear.setId(id);
                            footwear.setOpenness(coverage);
                            FootwearController footwearController = new FootwearController();
                            footwearController.addFootwear(footwear);
                        }
                        else {
                            Clothes clothes = new Clothes();
                            clothes.setId(id);
                            ClothesController clothesController = new ClothesController();
                            clothesController.add(clothes);
                        }

                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return items;
    }

    // slobodno dodati još caseova za različite boje, pazi samo jer "boja" ne može učitati "boja,", popravi to ako ti se da
    // također nekada se treba napisati npr "crvena" i "crveno" i "crveni", ali za neke rijetke
    // boje to je minimalno korisno jer se doda malo artikala naspram njih 2000 sto se ucitaju samo s ovima
    public String getColor(List<String> words) {
        String[] color = new String[]{"", ""};
        for (String word : words) {
            switch (word) {
                case "crna":
                case "crna,":
                case "crno":
                case "crno,":
                case "crni":
                    if (color[0].equals("")) {
                        color[0] = "Black";
                    } else {
                        color[1] = "Black";
                    }
                    break;
                case "bijela":
                case "bijela,":
                case "bijelo":
                case "bijelo,":
                case "bijeli":
                    if (color[0].equals("")) {
                        color[0] = "White";
                    } else {
                        color[1] = "White";
                    }
                    break;
                case "crvena":
                case "crvena,":
                case "crveno":
                case "crveni":
                    if (color[0].equals("")) {
                        color[0] = "Red";
                    } else {
                        color[1] = "Red";
                    }
                    break;
                case "plava":
                case "plava,":
                case "plavi":
                case "plavo":
                case "tirkizna":
                case "tirkizna,":
                case "tirkizno":
                case "tirkizni":
                    if (color[0].equals("")) {
                        color[0] = "Blue";
                    } else {
                        color[1] = "Blue";
                    }
                    break;
                case "siva":
                case "siva,":
                case "sivi":
                case "sivo":
                case "tamnosiva":
                case "tamnosiva,":
                case "tamnosivi":
                case "tamnosivo":
                case "svjetlosiva":
                case "svjetlosiva,":
                case "svjetlosivi":
                case "svjetlosivo":
                    if (color[0].equals("")) {
                        color[0] = "Grey";
                    } else {
                        color[1] = "Grey";
                    }
                    break;
                case "smeđa":
                case "smeđa,":
                case "smeđi":
                case "smeđe":
                    if (color[0].equals("")) {
                        color[0] = "Brown";
                    } else {
                        color[1] = "Brown";
                    }
                    break;
                case "zelena":
                case "zelena,":
                case "zeleni":
                case "zeleno":
                case "maslinasto":
                case "maslinasti":
                case "maslinasta":
                    if (color[0].equals("")) {
                        color[0] = "Green";
                    } else {
                        color[1] = "Green";
                    }
                    break;
                case "roza":
                case "roza,":
                case "rozi":
                case "rozo":
                    if (color[0].equals("")) {
                        color[0] = "Pink";
                    } else {
                        color[1] = "Pink";
                    }
                    break;
                case "žuta":
                case "žuta,":
                case "žuti":
                case "žuto":
                case "zlatna":
                case "zlatna,":
                case "zlatni":
                case "zlatno":
                    if (color[0].equals("")) {
                        color[0] = "Yellow";
                    } else {
                        color[1] = "Yellow";
                    }
                    break;
                case "narančasta":
                case "narančasta,":
                case "narančasto":
                case "narančast":
                case "narančasti":
                    if (color[0].equals("")) {
                        color[0] = "Orange";
                    } else {
                        color[1] = "Orange";
                    }
                    break;
                case "ljubičasta":
                case "ljubičasta,":
                case "ljubičasti":
                case "ljubičasto":
                case "ružičasta":
                case "ružičasta,":
                    if (color[0].equals("")) {
                        color[0] = "Purple";
                    } else {
                        color[1] = "Purple";
                    }
                    break;
                case "bež":
                case "bež,":
                case "kaki":
                case "kaki,":
                    if (color[0].equals("")) {
                        color[0] = "Beige";
                    } else {
                        color[1] = "Beige";
                    }
                    break;
            }
        }
        String colors = "";
        if (color[0] == "") {
            colors = "unsorted/unsorted";
        } else if (color[0] != "") {
            if (color[1] != "") {
                colors = color[0] + "/" + color[1];
            } else {
                colors = color[0];
            }
        }
        return colors;
    }

    public String getCategory(List<String> words) {
        String category = "";
        // T-shirts, sweatshirts and hoodies
        if (
                words.contains("t-shirt") ||
                        (words.contains("polo") && !words.contains("dugih")) ||
                        words.contains("kratkih") ||
                        words.contains("short") ||
                        words.contains("top")
        ) {
            category = "T-shirt";
        } else if (
                words.contains("dugih") ||
                        words.contains("sweatshirt") ||
                        words.contains("hoodie") ||
                        words.contains("hudica")
        ) {
            category = "Sweatshirts & hoodies";
        } else if (
                words.contains("sweater") ||
                        words.contains("knitwear") ||
                        words.contains("pulover") ||
                        words.contains("pullover") ||
                        words.contains("jumper")
        ) {
            category = "Sweaters & knitwear";
        }
        // pants
        if (
                words.contains("hlače") ||
                        words.contains("traperice") ||
                        words.contains("hlaÄ�e") ||
                        words.contains("trenirke") ||
                        words.contains("trenirka") ||
                        words.contains("tajice")
        ) {
            category = "Pants";
        }
        // jackets and coats
        if (
                words.contains("jakna") ||
                        words.contains("prsluk") ||
                        words.contains("jacket")
        ) {
            category = "Jackets";
        } else if (
                words.contains("kaput") ||
                        words.contains("coat")
        ) {
            category = "Coats";
        }
        // suits
        if (
                words.contains("suit") ||
                        words.contains("odijelo") ||
                        words.contains("sako")
        ) {
            category = "Suits";
        }
        // shirts
        if (
                words.contains("košulja") ||
                        words.contains("koÅ¡ulja") ||
                        words.contains("tunika") ||
                        words.contains("bluza")
        ) {
            category = "Shirts";
        }
        // dresses
        if (
                words.contains("haljina") ||
                        words.contains("dress")
        ) {
            category = "Dresses";
        }
        // skirts
        if (
                words.contains("suknja") ||
                        words.contains("skirt")
        ) {
            category = "Skirts";
        }
        // shoes
        if (
                words.contains("cipele")
        ) {
            category = "Business shoes";
        }
        // trainers
        if (
                words.contains("sneakers") ||
                        words.contains("tenisice") ||
                        words.contains("kopačke")
        ) {
            category = "Trainers";
        }
        // slippers
        if (
                words.contains("papuče") ||
                        words.contains("natikače") ||
                        words.contains("japanke") ||
                        words.contains("crocs")
        ) {
            category = "Slippers";
        }
        // sandals
        if (
                words.contains("sandale") ||
                        words.contains("sandals")
        ) {
            category = "Sandals";
        }
        // boots
        if (
                words.contains("boots") ||
                        words.contains("čizme") ||
                        words.contains("gležnjače")
        ) {
            category = "Boots";
        }
        // heels
        if (
                words.contains("mokasinke") ||
                        words.contains("štikle") ||
                        words.contains("espadrile") ||
                        words.contains("potpeticom") ||
                        words.contains("salonke") ||
                        words.contains("loaferice")
        ) {

        }
        // other
        if (category == "") {
            category = "unsorted";
        }
        return category;
    }

    public String getFormality(String category, List<String> words) {
        String formality = "";
        switch (category) {
            case "T-shirt":
            case "Shirts":
            case "Jackets":
            case "Skirts":
            case "Sweatshirts & hoodies":
            case "Sweaters & knitwear":
            case "Boots":
            case "Sandals":
            case "Pants":
                formality = "Casual";
                break;
            case "Suits":
            case "Coats":
            case "Dresses":
            case "Heels":
                formality = "Formal";
                break;
            case "Business shoes":
                formality = "Business";
                break;
            case "Trainers":
                formality = "Activewear";
                break;
            case "Slippers":
                formality = "Homewear";
                break;
        }

        if (
                words.contains("trenirka") ||
                        words.contains("trenirke") ||
                        words.contains("tajice") ||
                        words.contains("tenisice") ||
                        words.contains("kopačke")
        ) {
            formality = "Activewear";
        }
        if (
                words.contains("crocs")
        ) {
            formality = "Homewear";
        }
        return formality;
    }

    public String getCoverage(String category, List<String> words) {
        String coverage = "";
        List<String> notApplicable = new ArrayList<>(Arrays.asList("T-shirts", "Shirts", "Pants", "Jackets", "Suits", "Coats", "Dresses", "Skirts", "Sweatshirts & hoodies", "Sweaters & knitwear"));
        if (notApplicable.contains(category)) {
            return "N/A";
        }
        List<String> Open = new ArrayList<>(Arrays.asList("Sandals", "Heels"));
        List<String> Closed = new ArrayList<>(Arrays.asList("Sandals", "Slippers", "Trainers", "Business shoes", "Boots"));
        if (Open.contains(category)) {
            category = "Open";
        } else if (Closed.contains(category)) {
            category = "Closed";
        } else {
            category = "Rain";
        }
        if (
                words.contains("vodootporne") ||
                        words.contains("vodootporan") ||
                        words.contains("gležnjače") ||
                        words.contains("planinarske")
        ) {
            category = "Rain";
        }
        if (
                words.contains("zimske") ||
                        words.contains("snijeg")
        ) {
            category = "Snow";
        }
        return coverage;
    }

    public String getSeason(String category, List<String> words) {
        String season = "";
        switch (category) {
            case "T-shirts":
            case "Skirts":
            case "Slippers":
            case "Sandals":
                season = "Summer";
                break;
            case "Coats":
            case "Boots":
                season = "Winter";
                break;
            case "Dresses":
            case "Shirts":
            case "Pants":
            case "Trainers":
            case "Heels":
                season = "Spring";
                break;
            case "Jackets":
            case "Suits":
            case "Sweaters & knitwear":
            case "Sweatshirts & hoodies":
            case "Business shoes":
                season = "Fall";
                break;
        }

        if (
                words.contains("zimske") ||
                        words.contains("snijeg") ||
                        words.contains("zmiski") ||
                        words.contains("zimska")
        ) {
            season = "Winter";
        } else if (
                words.contains("proljetna") ||
                        words.contains("proljetne") ||
                        words.contains("trening") ||
                        words.contains("treniranje")
        ) {
            season = "Spring";
        } else if (
                words.contains("jesenska") ||
                        words.contains("jesenske") ||
                        words.contains("jesenski") ||
                        words.contains("vodootporna") ||
                        words.contains("vodootporan")
        ) {
            season = "Fall";
        } else if (
                words.contains("ljetna") ||
                        words.contains("ljetni") ||
                        words.contains("ljetne")
        ) {
            season = "Summer";
        }
        return season;
    }
}