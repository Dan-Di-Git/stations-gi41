package by.bsut.staris.stations;

import java.util.List;

/** Справочник станций Гомельского отделения Белорусской железной дороги. */
public final class StationRegistry {

<<<<<<< Updated upstream
    private final List<Station> stations = List.of(
        new Station("Гомель-Пассажирский", Line.GOMEL_ZHLOBIN, StationType.PASSENGER, 0),
        new Station("Гомель-Сортировочный", Line.GOMEL_ZHLOBIN, StationType.MARSHALLING, 5)
        // новые станции добавляются в конец списка
    );
=======
    private final List<Station> stations;

    public StationRegistry() {
        this.stations = List.of(
                new Station("Гомель-Пассажирский", Line.GOMEL_ZHLOBIN, StationType.PASSENGER, 0),
                new Station("Демехи", Line.GOMEL_KALINKOVICHI, StationType.INTERMEDIATE, 57),
                new Station("Житковичи", Line.GOMEL_LUNINETS, StationType.INTERMEDIATE, 238),
                new Station("Калинковичи", Line.GOMEL_KALINKOVICHI, StationType.DIVISIONAL, 125),
                new Station("Василевичи", Line.GOMEL_KALINKOVICHI, StationType.INTERMEDIATE, 86),
                new Station("Тереховка", Line.GOMEL_BAKHMACH, StationType.INTERMEDIATE, 43),
                new Station("Уза", Line.GOMEL_ZHLOBIN, StationType.INTERMEDIATE, 23),
                new Station("Гомель-Сортировочный", Line.GOMEL_ZHLOBIN, StationType.MARSHALLING, 6)
                // новые станции добавляются в конец списка
        );
    }

    public StationRegistry(List<Station> stations) {
        this.stations = List.copyOf(stations);
    }
>>>>>>> Stashed changes

    /** Возвращает все станции справочника. */
    public List<Station> all() {
        return stations;
    }

    // новые методы добавляются в конец класса
}
