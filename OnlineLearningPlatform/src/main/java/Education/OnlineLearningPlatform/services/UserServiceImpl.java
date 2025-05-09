package Education.OnlineLearningPlatform.services;

import Education.OnlineLearningPlatform.entity.Course;
import Education.OnlineLearningPlatform.entity.User;
import software.amazon.awssdk.services.s3.model.Progress;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public Optional<User> authenticateUser(String email, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User enrollInCourse(Long userId, Long courseId) {
        return null;
    }

    @Override
    public List<Course> getUserCourses(Long userId) {
        return List.of();
    }

    @Override
    public Progress updateProgress(Long userId, Long courseId, Progress progress) {
        return null;
    }

    @Override
    public Progress getProgress(Long userId, Long courseId) {
        return null;
    }
}
