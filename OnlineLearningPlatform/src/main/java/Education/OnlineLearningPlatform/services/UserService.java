package Education.OnlineLearningPlatform.services;

import Education.OnlineLearningPlatform.entity.Course;
import Education.OnlineLearningPlatform.entity.User;
import software.amazon.awssdk.services.s3.model.Progress;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> authenticateUser(String email, String password);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User enrollInCourse(Long userId, Long courseId);
    List<Course> getUserCourses(Long userId);
    Progress updateProgress(Long userId, Long courseId, Progress progress);
    Progress getProgress(Long userId, Long courseId);
}
