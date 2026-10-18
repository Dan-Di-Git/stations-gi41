package by.bsut.staris.stations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Сверка справочника станций с документацией STATIONS.md. */
class StationsDocTest {

    private static final Path DOC = Path.of("STATIONS.md");

    /** Строки данных таблицы: строки, начинающиеся с «|», без заголовка и разделителя. */
    private static List<List<String>> tableRows() throws IOException {
        List<String> tableLines = Files.readAllLines(DOC, StandardCharsets.UTF_8).stream()
                .map(String::trim)
                .filter(line -> line.startsWith("|"))
                .toList();
        return tableLines.stream()
                .skip(2)
                .map(line -> Arrays.stream(line.substring(1, line.length() - 1).split("\\|"))
                        .map(String::trim)
                        .toList())
                .toList();
    }

    @Test
    @DisplayName("Число строк в STATIONS.md равно числу станций в справочнике")
    void rowCountMatchesRegistry() throws IOException {
        assertEquals(new StationRegistry().all().size(), tableRows().size(),
                "STATIONS.md не соответствует справочнику StationRegistry");
    }

    @Test
    @DisplayName("Каждая станция справочника описана в STATIONS.md с теми же атрибутами")
    void everyStationIsDocumented() throws IOException {
        List<List<String>> rows = tableRows();
        for (Station station : new StationRegistry().all()) {
            List<String> expected = List.of(
                    station.name(),
                    station.line().name(),
                    station.type().name(),
                    String.valueOf(station.distanceFromGomelKm()));
            assertTrue(rows.contains(expected),
                    "В STATIONS.md отсутствует или не совпадает строка: " + String.join(" | ", expected));
        }
    }
}
