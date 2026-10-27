package dev.sio.todoalt.services;

import dev.sio.todoalt.entities.User;
import dev.sio.todoalt.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository uRepo;
    private final PasswordEncoder pEncoder;

    public UserService(UserRepository uRepo, PasswordEncoder pEncoder) {
        this.uRepo = uRepo;
        this.pEncoder = pEncoder;
    }

    public User createUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        encodePassword(user);
        return uRepo.save(user);
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return uRepo.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur inconnu"));
    }

    public void encodePassword(User user) {
        user.setPassword(pEncoder.encode(user.getPassword()));
    }
}
