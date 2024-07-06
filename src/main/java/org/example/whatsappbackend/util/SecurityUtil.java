package org.example.whatsappbackend.util;

import lombok.RequiredArgsConstructor;
import org.example.whatsappbackend.exception.ApplicationException;
import org.example.whatsappbackend.model.entity.User;
import org.example.whatsappbackend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUtil {
    private final UserRepository userRepository;

    public User getCurrentLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<String> emailOptional = Optional.ofNullable(auth.getPrincipal())
                .map(String.class::cast);

        if (emailOptional.isPresent()) {
            String email = emailOptional.get();
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new ApplicationException(
                            "USER_NOT_FOUND", String.format("No user found by eamil: %s", email)
                    ));
        }

        throw new ApplicationException("USER_NOT_FOUND", "No user found");
    }
}
