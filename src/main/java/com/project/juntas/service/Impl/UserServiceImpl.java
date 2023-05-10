package com.project.juntas.service.Impl;

import com.project.juntas.dto.user.LoginRequestDto;
import com.project.juntas.dto.user.LoginResponseDto;
import com.project.juntas.dto.user.RegisterRequestDto;
import com.project.juntas.dto.user.RegisterResponseDto;
import com.project.juntas.exception.ResourceAlreadyExistsException;
import com.project.juntas.exception.ResourceNotFoundException;
import com.project.juntas.mapper.GenericMapper;
import com.project.juntas.model.User;
import com.project.juntas.model.enums.Provider;
import com.project.juntas.model.enums.Role;
import com.project.juntas.repository.UserRepository;
import com.project.juntas.security.JwtUtil;
import com.project.juntas.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

import static com.project.juntas.model.enums.MessageCode.*;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final GenericMapper mapper;
    private final MessageSource messenger;
   // private final OAuth2AuthorizedClientService authorizedClientService;

    @Override
  public RegisterResponseDto oauthLogin(OAuth2AuthenticationToken authenticate) {
//        OAuth2AuthorizedClient client = authorizedClientService
//                .loadAuthorizedClient(
//                        authenticate.getAuthorizedClientRegistrationId(),
//                        authenticate.getName());
//
//        String email = authenticate.getPrincipal().getAttribute("email");
//
//        //Login side
//        if (existsByEmail(email)) {
//            User user = findByEmail(email);
//            RegisterResponseDto response = mapper.map(user, RegisterResponseDto.class);
//            response.setToken(JwtUtil.generateToken(user));
//            return response;
//        } else {
//            //Dto and db candidate builder
//            RegisterResponseDto response = new RegisterResponseDto();
//
//            response.setEmail(authenticate.getPrincipal().getAttribute("email"));
//            response.setName(authenticate.getPrincipal().getAttribute("name"));
//            response.setProfileImage(authenticate.getPrincipal().getAttribute("picture"));
//
//            User userDb = mapper.map(response, User.class);
//            userDb.setRole(Role.USER);
//            userDb.setProvider(Provider.GOOGLE);
//            repository.save(userDb);
//            response.setToken(JwtUtil.generateToken(userDb));
//            response.setId(userDb.getId());
//
          return null;
  //    }
  }
    @Override
    public RegisterResponseDto register(RegisterRequestDto dto) {

        if(repository.existsByEmail(dto.getEmail()) || repository.existsByDni(dto.getDni())) {
            throw new ResourceAlreadyExistsException(messenger.getMessage(RESOURCE_ALREADY_EXIST.name(),
                    new Object[]{User.class.getSimpleName()}, Locale.getDefault()));
        }
        User user = mapper.map(dto, User.class);
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(Role.USER);

        user.setProvider(Provider.LOCAL);

        RegisterResponseDto response = mapper.map(repository.save(user), RegisterResponseDto.class);
        response.setToken(JwtUtil.generateToken(user));

        return response;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException(messenger.getMessage(RESOURCE_NOT_FOUND.name(),
                        new Object[]{User.class.getSimpleName(), dto.getEmail()}, Locale.getDefault())));

        //If the user is not local
        if (!user.getProvider().equals(Provider.LOCAL)) {
            throw new RuntimeException("The user has been registered by external services");
        }
        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(messenger.getMessage(BAD_CREDENTIALS.name(),
                    new Object[]{},Locale.getDefault()));
        }
        LoginResponseDto response = mapper.map(user, LoginResponseDto.class);
        response.setToken(JwtUtil.generateToken(user));

        return response;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(messenger.getMessage(RESOURCE_NOT_FOUND.name(),
                        new Object[]{User.class.getSimpleName(), email}, Locale.getDefault())));

        var grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(grantedAuthority));
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.repository.findByEmail(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException(messenger.getMessage(RESOURCE_NOT_FOUND.name(),
                        new Object[]{User.class.getName()}, Locale.getDefault())));
    }
}
