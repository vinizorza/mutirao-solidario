package unit;

import com.vfl.mutirao_solidario.controller.dto.RegistrationRequest;
import com.vfl.mutirao_solidario.controller.dto.RegistrationResponse;
import com.vfl.mutirao_solidario.model.Event;
import com.vfl.mutirao_solidario.model.Registration;
import com.vfl.mutirao_solidario.model.User;
import com.vfl.mutirao_solidario.repository.RegistrationRepository;

import com.vfl.mutirao_solidario.service.AuthenticationService;
import com.vfl.mutirao_solidario.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private RegistrationService registrationService;

    @Captor
    private ArgumentCaptor<Registration> registrationCaptor;

    private static User getUser(Long userId){
        return User.builder()
                .id(userId)
                .build();
    }

    private static Event getEvent(Long eventId){
        return Event.builder()
                .id(eventId)
                .build();
    }

    @Test
    void joinEventWithSucess() {
        Long userId = 1L;
        Long eventId = 2L;
        RegistrationRequest registrationRequest = new RegistrationRequest(eventId, userId);

        doNothing().when(authenticationService).validateUser(userId);

        registrationService.joinEvent(registrationRequest);

        verify(authenticationService, times(1)).validateUser(userId);
        verify(registrationRepository, times(1)).save(registrationCaptor.capture());

        Registration savedRegistration = registrationCaptor.getValue();
        assertThat(savedRegistration.getEvent().getId()).isEqualTo(eventId);
        assertThat(savedRegistration.getUser().getId()).isEqualTo(userId);
    }

    @Test
    public void joinEventThrowsExceptionWhenUserNotHavePermission() {
        Long userId = 1L;
        Long eventId = 2L;
        RegistrationRequest registrationRequest = new RegistrationRequest(eventId, userId);

        doThrow(new RuntimeException("User does not have the permission")).when(authenticationService).validateUser(userId);

        assertThatThrownBy(() -> registrationService.joinEvent(registrationRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("User does not have the permission");

        verify(authenticationService, times(1)).validateUser(userId);
        verifyNoInteractions(registrationRepository);
    }

    @Test
    void deleteRegistration() {
        Long registrationId = 1L;

        registrationService.delete(registrationId);

        verify(registrationRepository, times(1)).deleteById(registrationId);
    }

    @Test
    void getRegistrationByUserId() {
        User user1 = getUser(1L);
        Event event1 = getEvent(1L);
        Event event2 = getEvent(2L);

        Registration registration1 = new Registration(1L,event1, user1);
        Registration registration2 = new Registration(2L, event2, user1);

        List<Registration> registrationList = Arrays.asList(registration1, registration2);

        when(registrationRepository.findByUserId(user1.getId())).thenReturn(registrationList);

        List<RegistrationResponse> responses = registrationService.getRegistrationByUserId(user1.getId());

        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).userId()).isEqualTo(user1.getId());
        assertThat(responses.get(0).eventId()).isEqualTo(1L);
        assertThat(responses.get(1).eventId()).isEqualTo(2L);

        verify(registrationRepository, times(1)).findByUserId(user1.getId());

    }

    @Test
    void getRegistrationByEventId() {
        User user1 = getUser(1L);
        User user2 = getUser(2L);
        Event event1 = getEvent(2L);

        Registration registration1 = new Registration(1L,event1, user1);
        Registration registration2 = new Registration(2L, event1, user2);

        List<Registration> registrationList = Arrays.asList(registration1, registration2);

        when(registrationRepository.findByEventId(event1.getId())).thenReturn(registrationList);

        List<RegistrationResponse> responses = registrationService.getRegistrationByEventId(event1.getId());

        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).eventId()).isEqualTo(event1.getId());
        assertThat(responses.get(0).userId()).isEqualTo(1L);
        assertThat(responses.get(1).userId()).isEqualTo(2L);

        verify(registrationRepository, times(1)).findByEventId(event1.getId());
    }
}