package com.progi.progi.model;

import com.progi.progi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scala.actors.threadpool.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class ClosetGenerator {
    @Autowired
    private OrmarService ormarService;
    @Autowired
    private RegistereduserService registereduserService;
    @Autowired
    private UserService userService;
    @Autowired
    private ClothesService clothesService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private FootwearService footwearService;
    @Autowired
    private LocatedatService locatedatService;
    @Autowired
    private ArticleService articleService;

    public ClosetGenerator() {
    }

    public void generateClosets() {
        List<Registereduser> registeredusers = registereduserService.getAll();
        List<Users> users = userService.getAllUsers();
        List<Clothes> clothes = clothesService.getAll();
        List<Footwear> footwear = footwearService.getAll();
        List<String> locationTypes = Arrays.asList("drawer hanger shelf".split(" "));
        Random rand = new Random();
        for (Registereduser registereduser : registeredusers) {
            List<Closet> closets = new ArrayList<>();
            List<Location> locations = new ArrayList<>();
            int nOfClosets = rand.nextInt(6) + 1;
            int userid = registereduser.getId();
            //stvori ormare
            for (int i = 0; i < nOfClosets; i++) {
                Closet closet = new Closet();
                Users user = userService.get(userid);
                closet.setClosetname(user.getUsername() + "'s closet number " + (i + 1));
                closet.setUserid(userid);
                closets.add(ormarService.add(closet));
            }
            //popuni ormare lokacijama, ali ne iznad granice za svaki tip lokacije
            for (Closet closet : closets) {
                Location sshelf = new Location();
                Location hhanger = new Location();
                sshelf.setLocationtype("shelf");
                sshelf.setClosetid(closet.getId());
                hhanger.setLocationtype("hanger");
                hhanger.setClosetid(closet.getId());
                locationService.add(sshelf);
                locationService.add(hhanger);
                int drawerCount = 0;
                boolean drawerLimit = false;
                int hangerCount = 1;
                boolean hangerLimit = false;
                int shelfCount = 1;
                boolean shelfLimit = false;
                int noOfLocations = rand.nextInt(6) + 1;
                for (int i = 0; i < noOfLocations; i++) {
                    Location location = new Location();
                    location.setClosetid(closet.getId());
                    int randLocation = rand.nextInt(locationTypes.size());
                    location.setLocationtype(locationTypes.get(randLocation));
                    switch (location.getLocationtype()) {
                        case "drawer":
                            drawerCount++;
                            break;
                        case "hanger":
                            hangerCount++;
                            break;
                        case "shelf":
                            shelfCount++;
                            break;
                    }
                    if (drawerCount == 3) {
                        drawerLimit = true;
                    }
                    if (hangerCount == 2) {
                        hangerLimit = true;
                    }
                    if (shelfCount == 5) {
                        shelfLimit = true;
                    }
                    if (
                            (drawerLimit && location.getLocationtype() == "drawer")
                                    || (hangerLimit && location.getLocationtype() == "hanger")
                                    || (shelfLimit && location.getLocationtype() == "shelf")
                    ) {
                        i -= 1;
                        continue;
                    }
                    Location newLocation = locationService.add(location);
                    locations.add(newLocation);
                }
            }
            //popuni lokacije artiklima
            for (Location location : locations) {
                int noOfArticles = rand.nextInt(10) + 1;
                for (int i = 0; i < noOfArticles; i++) {
                    Locatedat locatedat = new Locatedat();
                    locatedat.setLocationid(location.getId());
                    locatedat.setClosetid(location.getClosetid());
                    switch (location.getLocationtype()) {
                        case "drawer":
                            Clothes attire = clothes.get(rand.nextInt(clothes.size()));
                            Article article = articleService.get(attire.getId());
                            if (article.getCategory() != "Jackets" && article.getCategory() != "Coats") {
                                locatedat.setClosetid(attire.getId());
                            }
                            break;
                        case "hanger":
                            Clothes articleC = clothes.get(rand.nextInt(clothes.size()));
                            locatedat.setArticleid(articleC.getId());
                            break;
                        case "shelf":
                            Footwear articleF = footwear.get(rand.nextInt(footwear.size()));
                            locatedat.setArticleid(articleF.getId());
                            break;
                    }
                    if (locatedat.getArticleid() != null && locatedat.getClosetid() != null && locatedat.getLocationid() != null) {
                        locatedatService.add(locatedat);
                    }
                }
            }

        }
    }
}
