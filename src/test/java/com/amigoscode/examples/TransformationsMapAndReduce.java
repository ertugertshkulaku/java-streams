package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.beans.PersonDTO;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;

public class TransformationsMapAndReduce {

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @Test
    void yourFirstTransformationWithMap() throws IOException {
        List<Person> people = MockData.getPeople();
       List<PersonDTO> personDTOS =  people
               .stream()
               .map(
               person -> new PersonDTO(person.getId(),
               person.getFirstName(),
               person.getAge()))
               .filter(personDTO -> personDTO.getAge() == 20)
               .collect(Collectors.toList());
      // assertThat(people.size()).isEqualTo(personDTOS.size());
       personDTOS.forEach(System.out::println);

    }

    @Test
    void configureDistinct() throws IOException{
        List<Car> cars = MockData.getCars();
        List<Car> carList = cars
                .stream()
                .filter(car -> car.getPrice() == 6)
                .filter(distinctByKey(car -> car.getMake() + car.getModel() + car.getColor() + car.getYear() + car.getPrice()))
                .collect(Collectors.toList());
        carList.forEach(System.out::println);
    }

    @Test
    void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
    }

    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
    }
}

