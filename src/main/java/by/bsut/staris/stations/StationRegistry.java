package by.bsut.staris.stations;

import java.util.List;

/** Справочник станций Гомельского отделения Белорусской железной дороги. */
public final class StationRegistry {

    private final List<Station> stations = List.of(
            new Station("Гомель-Пассажирский", Line.GOMEL_ZHLOBIN, StationType.PASSENGER, 0),
            new Station("Демехи", Line.GOMEL_KALINKOVICHI, StationType.INTERMEDIATE, 57)
        // новые станции добавляются в конец списка
    );

    public List<Station> all() {
        return stations;
    }



    public String toMarkdownTable() {
        StringBuilder table = new StringBuilder();
        table.append("| Станция | Направление | Тип | Расстояние от Гомеля, км |\n");
        table.append("| --- | --- | --- | --- |\n");
        for (Station station : stations) {
            table.append("| ").append(escapeCell(station.name()))
                    .append(" | ").append(escapeCell(station.line().name()))
                    .append(" | ").append(escapeCell(station.type().name()))
                    .append(" | ").append(station.distanceFromGomelKm())
                    .append(" |\n");
        }
        return table.toString();
    }

    static String escapeCell(String value) {
        return value.replace("\r", " ")
                .replace("\n", " ")
                .replace("|", "\\|");
    }

    // новые методы добавляются в конец класса
}

