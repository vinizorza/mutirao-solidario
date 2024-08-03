package com.vfl.mutirao_solidario.repository;

import com.vfl.mutirao_solidario.controller.dto.RegistrationResponse;
import com.vfl.mutirao_solidario.enums.Status;
import com.vfl.mutirao_solidario.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUserId(Long userId);

    List<Registration> findByEventId(Long eventId);
}
