package integration;

import com.vfl.mutirao_solidario.client.City;
import com.vfl.mutirao_solidario.client.impl.Back4App;
import com.vfl.mutirao_solidario.controller.CityController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    @InjectMocks
    private CityController cityController;

    @Mock
    private Back4App client;


    @Test
    void findCitiesWithSuccess() throws Exception {

        //GIVEN
        List<City> cities = List.of(City.builder().id("1").name("Pirapora").latitude(1D).longitude(1D).build());

        //WHEN
        when(client.findCitiesByName("Pira")).thenReturn(cities);
        List<City> response = cityController.findCity("Pira");

        //THEN
        assertThat(response.get(0).getId()).isEqualTo("1");
        assertThat(response.get(0).getName()).isEqualTo("Pirapora");

    }

}