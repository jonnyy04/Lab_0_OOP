import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File inputFile = new File("src/main/resources/input-test.json");
        JsonNode data = mapper.readTree(inputFile).get("data");

        Universe starWars = new Universe("starWars", new ArrayList<>());
        Universe hitchhikers = new Universe("hitchHiker", new ArrayList<>());
        Universe marvel = new Universe("marvel", new ArrayList<>());
        Universe rings = new Universe("rings", new ArrayList<>());

        Scanner scanner = new Scanner(System.in);

        for (JsonNode entry : data) {
            String entryAsString = entry.toString();
            System.out.println(entryAsString);
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    starWars.individuals().add(entry);
                    break;
                case "2":
                    hitchhikers.individuals().add(entry);
                    break;
                case "3":
                    marvel.individuals().add(entry);
                    break;
                case "4":
                    rings.individuals().add(entry);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }

        scanner.close();
        File outputDir = new File("src/output");

        mapper.writeValue(new File(outputDir, "starwars.json"), starWars);
        mapper.writeValue(new File(outputDir, "hitchhiker.json"), hitchhikers);
        mapper.writeValue(new File(outputDir, "rings.json"), rings);
        mapper.writeValue(new File(outputDir, "marvel.json"), marvel);

    }
}

record Universe(
        String name,
        List<JsonNode> individuals
) { }