package by.bsut.staris.stations;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StationRegistryTest {

    private final StationRegistry registry = new StationRegistry();

    private Station station(String name) {
        return registry.all().stream()
                .filter(station -> station.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Станция не найдена: " + name));
    }

    @Test
    @DisplayName("Справочник содержит станцию Гомель-Пассажирский с нулевым расстоянием")
    void containsGomelPassenger() {
        Station gomel = station("Гомель-Пассажирский");
        assertEquals(StationType.PASSENGER, gomel.type());
        assertEquals(0, gomel.distanceFromGomelKm());
    }

    @Test
    @DisplayName("Наименования станций не повторяются")
    void namesAreUnique() {
        long unique = registry.all().stream().map(Station::name).distinct().count();
        assertEquals(registry.all().size(), unique);
    }

    @Test
    @DisplayName("Расстояния неотрицательны")
    void distancesAreNotNegative() {
        assertTrue(registry.all().stream().allMatch(station -> station.distanceFromGomelKm() >= 0));
    }

    @Test
    @DisplayName("Справочник содержит станцию Демехи с корректными атрибутами")
    void containsDemekhi() {
        Station demekhi = station("Демехи");
        assertEquals(Line.GOMEL_KALINKOVICHI, demekhi.line());
        assertEquals(StationType.INTERMEDIATE, demekhi.type());
        assertEquals(57, demekhi.distanceFromGomelKm());
    }


    @Test
    @DisplayName("Возвращается самая удалённая станция")
    void returnsFarthestStation() {
        Station farthest = registry.farthest().orElseThrow();

        assertEquals("Житковичи", farthest.name());
        assertEquals(238, farthest.distanceFromGomelKm());
    }

    @Test
    @DisplayName("Пустой справочник возвращает пустой результат")
    void returnsEmptyWhenRegistryIsEmpty() {
        StationRegistry emptyRegistry = new StationRegistry(List.of());

        assertTrue(emptyRegistry.farthest().isEmpty());
    }

    @Test
    @DisplayName("Справочник с одной станцией возвращает её")
    void returnsOnlyStationWhenRegistryHasOneStation() {
        Station gomel = new Station(
                "Гомель-Пассажирский",
                Line.GOMEL_ZHLOBIN,
                StationType.PASSENGER,
                0
        );

        StationRegistry singleStationRegistry =
                new StationRegistry(List.of(gomel));

        assertEquals(gomel, singleStationRegistry.farthest().orElseThrow());
    }

    @Test
    @DisplayName("Справочник содержит станцию Житковичи с корректными атрибутами")
    void containsZhitkovichi() {
        Station zhitkovichi = station("Житковичи");
        assertEquals(Line.GOMEL_LUNINETS, zhitkovichi.line());
        assertEquals(StationType.INTERMEDIATE, zhitkovichi.type());
        assertEquals(238, zhitkovichi.distanceFromGomelKm());
    }
    // тесты новых станций и методов добавляются в конец класса
}
