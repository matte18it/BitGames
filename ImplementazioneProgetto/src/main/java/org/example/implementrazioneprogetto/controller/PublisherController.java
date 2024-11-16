package org.example.implementrazioneprogetto.controller;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.example.implementrazioneprogetto.Pattern.Singleton.DBManager;
import org.example.implementrazioneprogetto.Pattern.Singleton.UserData;
import org.example.implementrazioneprogetto.persistenza.GiocoDaoPostgre;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PublisherController {
    // Attributi
    private final ResourceLoader resourceLoader;

    // Costruttore
    public PublisherController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    // Pagina di pubblicazione del gioco
    @GetMapping("/pubblicazioneGioco")
    public void pubblicazioneGioco() {
        // Metodo di mapping per la pagina di pubblicazione del gioco
    }

    // Metodo per gestire la pubblicazione del gioco
    @PostMapping("/publish")
    public String publish(@RequestParam("gameName") String gameName, @RequestParam("gameGenre") String gameGenre, @RequestParam("gameLanguage") String gameLanguage, @RequestParam("gameDescription") String gameDescription, @RequestParam("gameVersion") String gameVersion, @RequestParam("gameIcon") MultipartFile gameIcon, @RequestParam("gameImages[]") List<MultipartFile> gameImages, @RequestParam("gameFile") MultipartFile gameFile, @RequestParam(value = "mobileCommands", required = false) boolean mobileCommands, Model model) {
        // Creazione della cartella "Games" nella directory resources/static
        File gameDir = new File("src/main/resources/static/Games");
        if (!gameDir.exists()) {
            gameDir.mkdirs();
        }

        // Directory dove verranno salvate le immagini
        String uploadDirectory = "src/main/resources/static/Games/";

        // Salva l'icona del gioco
        String pathIcon = saveFile(gameIcon, uploadDirectory, "Icons/" + gameName, gameName + "_icon");

        // Salva le immagini del gioco
        List<String> pathImages = new ArrayList<>();
        for (MultipartFile image : gameImages) {
            pathImages.add(saveFile(image, uploadDirectory, "Images/" + gameName, gameName + "_image_" + gameImages.indexOf(image)));
        }

        // Estrai e salva i file dallo zip
        String indexPath = null;
        try {
            indexPath = extractZipFile(gameFile, uploadDirectory + "Files/", gameName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Salva nel database
        GiocoDaoPostgre giocoConcrete = new GiocoDaoPostgre(DBManager.getInstance().getConnection());
        giocoConcrete.pubblicaGioco(UserData.getInstance().getUsername(), gameName, gameDescription, gameGenre, gameLanguage, pathIcon, gameVersion, pathImages, indexPath, mobileCommands);

        // Indica il template che deve tornare
        return "pubblicazioneGioco";
    }

    // Funzione per salvare icona e immagini
    private String saveFile(MultipartFile file, String uploadDirectory, String subdirectory, String fileName) {
        Path filePath = null;
        try {
            // Crea la directory se non esiste
            Path directoryPath = Path.of(uploadDirectory, subdirectory);
            Files.createDirectories(directoryPath);

            // Estrai l'estensione del file originale
            String fileExtension = getFileExtension(file.getOriginalFilename());

            // Aggiungi l'estensione al nome del file
            String fileNameWithExtension = fileName + "." + fileExtension;

            // Salva il file nella directory
            filePath = Path.of(uploadDirectory, subdirectory, fileNameWithExtension);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath.toString();
    }

    // Metodo di supporto per ottenere l'estensione del file
    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex == -1) ? "" : filename.substring(dotIndex + 1);
    }

    // Metodo per estrarre il file zip tramite libreria zip4j
    public String extractZipFile(MultipartFile zipFile, String extractPath, String gameName) throws IOException {
        String indexPath = null;
        try {
            // Salva il file temporaneo su disco
            File tempFile = File.createTempFile("temp", ".zip");
            zipFile.transferTo(tempFile);

            // Crea l'oggetto ZipFile con il percorso del file temporaneo
            ZipFile zip = new ZipFile(tempFile);
            zip.extractAll(Path.of(extractPath, gameName).toString());

            // Ottieni il percorso completo del file index.html
            indexPath = Path.of(extractPath, gameName, "index.html").toString();

            // Elimina il file temporaneo dopo l'estrazione
            tempFile.delete();

            // Restituisci il percorso del file index.html
            return indexPath;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return indexPath;
    }
}

