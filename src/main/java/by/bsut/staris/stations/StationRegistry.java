package by.bsut.staris.stations;

import java.util.List;
import java.util.Comparator;
import java.util.Optional;

/** Справочник станций Гомельского отделения Белорусской железной дороги. */
public final class StationRegistry {

    private final List<Station> stations;

    public StationRegistry() {
        this.stations = List.of(
                new Station("Гомель-Пассажирский", Line.GOMEL_ZHLOBIN, StationType.PASSENGER, 0),
                new Station("Демехи", Line.GOMEL_KALINKOVICHI, StationType.INTERMEDIATE, 57),
                new Station("Житковичи", Line.GOMEL_LUNINETS, StationType.INTERMEDIATE, 238)
                // новые станции добавляются в конец списка
        );
    }

    public StationRegistry(List<Station> stations) {
        this.stations = List.copyOf(stations);
    }

    /** Возвращает все станции справочника. */
    public List<Station> all() {
        return stations;
    }

    /** Возвращает самую удалённую от Гомеля станцию. */
    public Optional<Station> farthest() {
        return stations.stream()
                .max(Comparator.comparingInt(Station::distanceFromGomelKm));
    }
    // новые методы добавляются в конец класса
}
