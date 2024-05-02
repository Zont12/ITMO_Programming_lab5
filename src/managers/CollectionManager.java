package managers;

import Models.Vehicle;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class CollectionManager { // класс для работы с коллекциями
    private PriorityQueue<Vehicle> collection;

    private final LocalDateTime creationDate;

    CommandManager commandManager;

    private boolean Flag;

    public CollectionManager(PriorityQueue<Vehicle> collection) {
        this.collection = collection;
        this.creationDate = LocalDateTime.now();
        this.Flag = true;
    }

    //возвращает коллекцию
    public PriorityQueue<Vehicle> getCollection() {
        return collection;
    }


    public Integer GemerateIds() {
        if (this.collection.isEmpty()) return 1;

        Set<Integer> existingIds = this.collection.stream()
                .map(Vehicle::getId)
                .collect(Collectors.toSet());

        int maxId = Collections.max(existingIds);
        for (int i = 1; i <= maxId; i++) {
            if (!((Set<?>) existingIds).contains(i)) {
                return i;
            }
        }
        return maxId + 1;

    }


    public static boolean isValid(PriorityQueue<Vehicle> collection) {
        Set<Integer> idSet = collection.stream().map(Vehicle::getId).collect(Collectors.toSet());

        return idSet.size() == collection.size();

    }
    public void cleanAttributesInCollection() {
        Iterator<Vehicle> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (!vehicle.validate()) {
                System.err.println("Транспорт с именем " + vehicle.getName() + " имеет не корректные атрибуты!");
                iterator.remove(); // Удалить транспортное средство, если оно не проходит валидацию
            }
        }
    }

    public void ClearCollection() {
        collection.clear();
    }


    public boolean WasNotChanged() {
        return this.Flag;
    }

    public void ChangeFlag() {
        this.Flag = false;
    }

    public void AddElementtoCollection(Vehicle vehicle) {
        collection.add(vehicle);
    }


    public Vehicle get_min_by_number_of_wheels() {
        return this.collection.stream()
                .min(Comparator.comparing(Vehicle::getNumberOfWheels)).orElseThrow(NoSuchElementException::new);

    }

    public Set<Integer> get_unique_engine_power() {
        Set<Integer> uniqenginepower = new HashSet<>();
        for (Vehicle vehicle : this.collection) {
            uniqenginepower.add(vehicle.getEnginePower());
        }
        return uniqenginepower;
    }

    public LocalDateTime getInitializationDate() {
        return creationDate;
    }

    public String getElementsType() {
        if (!collection.isEmpty()) {
            return String.valueOf(collection.iterator().next().getClass());
        } else {
            return null;
        }
    }

    public String getCollectionType() {
        return String.valueOf(PriorityQueue.class);
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public boolean containsId(Integer id) {
        if (this.collection.isEmpty()) return false;
        return this.collection.stream().anyMatch(worker -> worker.getId() == id);
    }


    public void removeById(Integer id) {
        this.collection.removeIf(worker -> worker.getId() == id);
    }

    public void RemoveFirstElemFromCollection() {
        collection.poll();
    }
}
