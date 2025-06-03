package cz.czechitas.lekce09.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.czechitas.java1.kockamyssyr.api.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Uloziste {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private Cat cat;
    private Mouse mouse;


    public void nacistPlochuZeSouboru(Path file) throws IOException {
        UlozenaPlocha ulozenaPlocha = objectMapper.readValue(file.toFile(), UlozenaPlocha.class);
        cat = new Cat(ulozenaPlocha.getCat());
        mouse = new Mouse(ulozenaPlocha.getMouse());
        for (Point treePoint : ulozenaPlocha.getTrees()) {
            new Tree(treePoint);
        }
        new Cheese(ulozenaPlocha.getCheese());
        new Meat(ulozenaPlocha.getMeat());
    }

    public void nacistPlochuZeSouboru() throws IOException {
        nacistPlochuZeSouboru(Paths.get("data/level-01.json"));
    }

    /**
     * Načte objekt UlozenyStav pomocí objectMapper.readValue(file, UlozenyStav.class).
     * Získá z UlozenyStav souřadnice kočky a myši.
     * Zapíše tyto souřadnice do objektů kočky a myši pomocí setLocation().
     */
    public void nacistStavZeSouboru(Path path)  throws IOException {
        UlozenyStav ulozenyStav = objectMapper.readValue(path.toFile(), UlozenyStav.class);

//        Point souradniceKocky = ulozenyStav.getCat();
//        Point souradniceMysi = ulozenyStav.getMouse();

        cat.setLocation(ulozenyStav.getCat());
        mouse.setLocation(ulozenyStav.getMouse());
    }

    public void nacistStavZeSouboru() throws IOException {
        nacistStavZeSouboru(Paths.get("data/stav.json"));
    }

    /**
     * Vytvoří objekt UlozenyStav.
     * Uloží do něj souřadnice kočky a myši – souřadnice získáte voláním getLocation().
     * Uloží objekt UlozenyStav do souboru pomocí objectMapper.writeValue(file, object).
     */
    public void ulozitStavDoSouboru(Path path)  throws IOException {
        UlozenyStav ulozenyStav = new UlozenyStav();

        ulozenyStav.setCat(cat.getLocation());
        ulozenyStav.setMouse(mouse.getLocation());

        objectMapper.writeValue(path.toFile(), ulozenyStav);
    }

    public void ulozitStavDoSouboru() throws IOException {
        ulozitStavDoSouboru(Paths.get("data/stav.json"));
    }

    public Cat getCat() {
        return cat;
    }

    public Mouse getMouse() {
        return mouse;
    }


}
