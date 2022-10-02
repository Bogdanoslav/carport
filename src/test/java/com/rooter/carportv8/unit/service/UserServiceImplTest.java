//package com.rooter.carportv8.unit.service;
//
//import com.querydsl.core.types.Predicate;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Test
//    public void getById_UserExist_ReturnUser() {
//        User user = User
//                .builder()
//                .id(1L)
//                .username("Arcadiy")
//                .build();
//        given(userRepository.findOne(UserPredicates.hasId(1L))).willReturn(Optional.of(user));
//        userService.getById(1L);
//        verify(userRepository).findOne(any(Predicate.class));
//    }
//
//    @Test
//    public void getById_TripNOTExists_ThrowNoSuchElementException() {
//        Assertions.assertThrows(NoSuchElementException.class, () -> userService.getById(1L), "NoSuchElementException was expected");
//    }
//}
