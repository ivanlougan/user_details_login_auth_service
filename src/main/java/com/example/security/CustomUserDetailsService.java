package com.example.security;

import com.example.user.UserService;
import com.example.user.dto.UserCredentialsDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Interfejs UserDetailsService posiada tylko jedną metodę abstrakcyjną loadUserByUsername(),
    // którą musimy zaimplementować. Jej zadaniem jest wyszukanie informacji o użytkowniku w bazie danych
    // i zwrócenie ich w postaci obiektu UserDetails, w którym będzie nazwa użytkownika, hash hasła i role.
    // Jeżeli użytkownik nie zostanie odnaleziony, to metoda powinna rzucić wyjątek UsernameNotFoundException.
    //
    //Dla czytelności tworzenie obiektu UserDetails na podstawie obiektu UserCredentialsDto
    // wydzieliłem do osobnej metody. Obiekt UserDetails najwygodniej jest utworzyć, korzystając z UserBuildera,
    // w którym nazwę użytkownika ustawiamy przez metodę username(), hasło przez metodę password(),
    // a role możemy przekazać w postaci tablicy String[] do metody roles().
    // Metoda ta doda do każdej przekazanej roli dodatkowy prefix ROLE_.
    // Jeżeli Twoje role miałyby już dodany prefix ROLE_ w bazie danych, to należałoby,
    // zamiast tego wykorzystać metodę authorities(). Zwróć uwagę na to,
    // żeby przy wywoływaniu metody builder() odwoływać się do klasy User pochodzącej ze Spring Security,
    // a nie klasy User, którą sami stworzyliśmy. Nasza klasa User ma specyfikator domyślny, więc pomyłka nam nie grozi.

    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findCredentialsByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User with email %s not found".formatted(username)));
    }

    private UserDetails createUserDetails(UserCredentialsDto credentials) {
        return User.builder()
                .username(credentials.getEmail())
                .password(credentials.getPassword())
                .roles(credentials.getRoles().toArray(String[]::new))
                .build();
    }
}
