//package com.rooter.carportv8.unit.service;
//
//import com.querydsl.core.types.Predicate;
//import com.rooter.carportv8.dto.user.UpdateProfileDescription;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
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
//public class ProfileServiceImplTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @Spy
//    private ProfileMapper profileMapper = ProfileMapper.INSTANCE;
//
//    @InjectMocks
//    private ProfileServiceImpl profileService;
//
//    private static User user;
//
//    @BeforeAll
//    public static void setUpData(){
//        UserDescription userDescription = UserDescription
//                .builder()
//                .firstName("Arcadiy")
//                .lastName("Arcadievich")
//                .about("test")
//                .email("test@mail.com")
//                .age(20)
//                .build();
//        user = User
//                .builder()
//                .id(1L)
//                .username("testUser")
//                .userDescription(userDescription)
//                .build();
//    }
//
//    @Test
//    public void getProfile_UserExist_ReturnUser() {
//        given(userRepository.findOne(UserPredicates.hasId(1L))).willReturn(Optional.of(user));
//        profileService.getProfile(1L);
//        verify(userRepository).findOne(any(Predicate.class));
//    }
//
//    @Test
//    public void getProfile_UserNOTExists_ThrowNoSuchElementException() {
//        Assertions.assertThrows(NoSuchElementException.class, () -> profileService.getProfile(1L), "NoSuchElementException was expected");
//    }
//
//    @Test
//    public void delete_ProfileExists_DeletedFieldTrue(){
//        given(userRepository.findOne(UserPredicates.hasId(1L))).willReturn(Optional.of(user));
//        profileService.deleteProfile(user.getId());
//        verify(userRepository).findOne(any(Predicate.class));
//        Assertions.assertEquals(Boolean.TRUE,user.getDeleted());
//    }
//
//    @Test
//    public void delete_ProfileNotExists_ThrowNoSuchElementException(){
//        Assertions.assertThrows(NoSuchElementException.class, () -> profileService.getProfile(1L), "NoSuchElementException was expected");
//    }
//
//    @Test
//    public void update_ProfileExists_returnUpdatedUserDescription(){
//        given(userRepository.save(any(User.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
//        given(userRepository.findOne(UserPredicates.hasId(1L))).willReturn(Optional.of(user));
//        UpdateProfileDescription updateProfileDescription = new UpdateProfileDescription("Boris","Borisovich","newEmail@mailcom",30,  "new About");
//        User newUser = profileService.updateProfileDescription(user.getId(), updateProfileDescription);
//        verify(userRepository).findOne(any(Predicate.class));
//        Assertions.assertEquals(updateProfileDescription.getFirstName(),newUser.getUserDescription().getFirstName());
//        Assertions.assertEquals(updateProfileDescription.getLastName(),newUser.getUserDescription().getLastName());
//        Assertions.assertEquals(updateProfileDescription.getAbout(),newUser.getUserDescription().getAbout());
//        Assertions.assertEquals(updateProfileDescription.getAge(),newUser.getUserDescription().getAge());
//        Assertions.assertEquals(updateProfileDescription.getEmail(),newUser.getUserDescription().getEmail());
//    }
//
//    @Test
//    public void update_ProfileNotExists_ThrowNoSuchElementException(){
//        Assertions.assertThrows(NoSuchElementException.class, () -> profileService.getProfile(1L), "NoSuchElementException was expected");
//    }
//}
