package com.progi.progi.model;

import com.progi.progi.repository.UserRepository;
import com.progi.progi.service.RegistereduserService;
import com.progi.progi.service.SellerService;
import com.progi.progi.service.UserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.text.Normalizer;
import java.util.regex.Pattern;

@Component
public class UsersGenerator {

    @Autowired
    private UserService userService;
    @Autowired
    private RegistereduserService regservice;
    @Autowired
    private SellerService sellerService;

    public UsersGenerator() {}

    public void generataeUsers() {
        List<String> names = getNames();
        List<String> surnames = getSurnames();
        List<String> logos = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            Random r = new Random();
            int index1 = r.nextInt(143);
            int index2 = r.nextInt(151);
            int num = r.nextInt(10);
            int selector = r.nextInt(10);
            String role = "";
            if (selector < 2) {
                role = "seller";
            } else {
                role = "registereduser";
            }
            String name = names.get(index1);
            String surname = surnames.get(index2);
            String mail = removeDiacritics(String.format("%s.%s%d@%s.com", name, surname, num, role).toLowerCase(Locale.ROOT));
            String password = removeDiacritics(String.format("password%s%d", surname, num));
            //dodavanje u bazu
            Users user = new Users();
            user.setUsername(removeDiacritics(String.format("%s.%s%d", name, surname, num).toLowerCase(Locale.ROOT)));
            user.setEmail(mail);
            user.setPassword(password);
            Users newUser = userService.add(user, role);

            if (role == "company") {
                String logo = getLogo();
                while (logos.contains(logo)) {
                    logo = getLogo();
                }
                Seller seller = new Seller();
                seller.setId(newUser.getId());
                seller.setLogo(logo);
                sellerService.add(seller);
            }
            else {
                Registereduser newReg = new Registereduser();
                newReg.setId(newUser.getId());
                newReg.setGeolocation(randomCoordinates());
                regservice.add(newReg);
            }
        }
    }

    public List<String> getNames() {
        return Arrays.asList(
                "Ana",
                "Ante",
                "Antonio",
                "Barbara",
                "Boris",
                "Bruno",
                "Darko",
                "David",
                "Domagoj",
                "Dora",
                "Duje",
                "Ela",
                "Elena",
                "Ema",
                "Filip",
                "Fran",
                "Frano",
                "Gabriel",
                "Helena",
                "Hrvoje",
                "Igor",
                "Iva",
                "Ivan",
                "Ivana",
                "Ivo",
                "Jakov",
                "Jana",
                "Josip",
                "Josipa",
                "Karlo",
                "Katarina",
                "Kristijan",
                "Kristina",
                "Lana",
                "Leo",
                "Lidija",
                "Luka",
                "Lucija",
                "Magdalena",
                "Maja",
                "Maksim",
                "Marija",
                "Marin",
                "Marina",
                "Mario",
                "Marko",
                "Marta",
                "Matej",
                "Mateja",
                "Matija",
                "Mia",
                "Mihael",
                "Mihaela",
                "Mislav",
                "Nataša",
                "Nika",
                "Nikola",
                "Nina",
                "Noa",
                "Patrik",
                "Paula",
                "Petar",
                "Petra",
                "Roko",
                "Sara",
                "Stjepan",
                "Tajana",
                "Tina",
                "Tomislav",
                "Valentina",
                "Vedran",
                "Viktor",
                "Viktorija",
                "Zlatan",
                "Zrinka",
                "Željka",
                "Željko",
                "Adriana",
                "Aleksandar",
                "Alen",
                "Andrea",
                "Andrej",
                "Andrija",
                "Anita",
                "Antonia",
                "Bernarda",
                "Damir",
                "Danijel",
                "Daniela",
                "Darija",
                "Dijana",
                "Dino",
                "Dorijan",
                "Dunja",
                "Emanuel",
                "Gabrijela",
                "Hrvoje",
                "Ivica",
                "Izabela",
                "Jakša",
                "Jasmina",
                "Josipa",
                "Kristijan",
                "Leon",
                "Lovro",
                "Lucian",
                "Magdalena",
                "Marijana",
                "Marino",
                "Martin",
                "Melita",
                "Mirna",
                "Nikolina",
                "Ognjen",
                "Petar",
                "Renata",
                "Sanja",
                "Snježana",
                "Stipe",
                "Suzana",
                "Tihana",
                "Toni",
                "Tonka",
                "Valerija",
                "Veronika",
                "Vladimir",
                "Vlatka",
                "Zdenko",
                "Zora",
                "Žana",
                "Žarko",
                "Željana",
                "Živko",
                "Živana",
                "Alberta",
                "Blaženka",
                "Borislav",
                "Darinko",
                "Dragan",
                "Đuro",
                "Ivanka",
                "Jerko",
                "Katica",
                "Krešimir",
                "Ljubica",
                "Marijeta",
                "Milena",
                "Miljenko",
                "Radmila",
                "Slavko",
                "Teodor",
                "Vesna",
                "Veselko",
                "Zdravko"
        );
    }

    public List<String> getSurnames() {
        return Arrays.asList(
                "Abramović",
                "Alilović",
                "Andrić",
                "Anić",
                "Antolić",
                "Babić",
                "Bagarić",
                "Balog",
                "Barić",
                "Bašić",
                "Baturina",
                "Benković",
                "Berket",
                "Bilić",
                "Boban",
                "Bogdan",
                "Bošnjak",
                "Božić",
                "Brkić",
                "Bulić",
                "Burazer",
                "Cindrić",
                "Ćosić",
                "Crnković",
                "Cvitanović",
                "Dadić",
                "Damjanović",
                "Đerek",
                "Džaja",
                "Erceg",
                "Filipović",
                "Franić",
                "Galić",
                "Goluža",
                "Grgić",
                "Grubišić",
                "Gušić",
                "Horvat",
                "Hrabar",
                "Ilić",
                "Ivanković",
                "Ivanović",
                "Janković",
                "Jelavić",
                "Jelić",
                "Jerković",
                "Jurčević",
                "Jurinić",
                "Kanižaj",
                "Kapetanović",
                "Karačić",
                "Karoglan",
                "Katarina",
                "Kelava",
                "Klarić",
                "Knežević",
                "Kovač",
                "Kovačević",
                "Kozina",
                "Kraljević",
                "Krčmar",
                "Križan",
                "Kučina",
                "Lacković",
                "Leko",
                "Lovrić",
                "Lukačević",
                "Ljubić",
                "Majstorović",
                "Marić",
                "Marković",
                "Martić",
                "Matić",
                "Matulić",
                "Mavrović",
                "Medak",
                "Medved",
                "Mihanović",
                "Mikulić",
                "Milas",
                "Milinović",
                "Milić",
                "Miljak",
                "Mlinarić",
                "Mujanović",
                "Muslim",
                "Novak",
                "Novosel",
                "Orešković",
                "Orlić",
                "Ostojić",
                "Pavlović",
                "Perić",
                "Petković",
                "Petrović",
                "Plavšić",
                "Pleša",
                "Posavec",
                "Pranjić",
                "Pušić",
                "Radić",
                "Rajković",
                "Rakić",
                "Rašić",
                "Rendulić",
                "Ribić",
                "Rimac",
                "Rogina",
                "Roso",
                "Sabljo",
                "Salopek",
                "Sertić",
                "Šarić",
                "Škaro",
                "Škobić",
                "Šoljić",
                "Špoljarić",
                "Stanić",
                "Stipić",
                "Štimac",
                "Šušnjara",
                "Tadić",
                "Tomas",
                "Tomić",
                "Topić",
                "Tukša",
                "Tuta",
                "Varga",
                "Vidović",
                "Vidaković",
                "Vidić",
                "Vinković",
                "Vlahović",
                "Vrban",
                "Vrdoljak",
                "Vučemil",
                "Vučetić",
                "Vujić",
                "Vukelić",
                "Vuko",
                "Zadro",
                "Zebić",
                "Zelenika",
                "Zlatar",
                "Zovko",
                "Zrnić",
                "Zubčić",
                "Žaja",
                "Živković",
                "Župan",
                "Žurić"
        );
    }

    public String getLogo() {
        String logo = "nologo";
        List<String> logos = new ArrayList<>();
        String urlSource = "http://logobook.com/";
        try {
            Document doc = Jsoup.connect(urlSource).get();
            Elements categories = doc.select(".homepage-logos");
            for (Element category : categories) {
                Elements logosElements = category.select(".logo-svg img");
                for (Element logoElement : logosElements) {
                    logos.add(logoElement.attr("src"));
                }
            }
            Random r = new Random();
            int index = r.nextInt(logos.size());
            logo = logos.get(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logo;
    }

    public String randomCoordinates() {
        Random r = new Random();
        int lat = r.nextInt(361) - 180;
        int lon = r.nextInt(361) - 180;
        return String.format("%d°N %d°W", lat, lon);
    }

    public static String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }
}