package Education.OnlineLearningPlatform.services;

import Education.OnlineLearningPlatform.entity.Course;
import Education.OnlineLearningPlatform.entity.Enrollment;
import Education.OnlineLearningPlatform.entity.User;
import Education.OnlineLearningPlatform.repository.CourseRepository;
import Education.OnlineLearningPlatform.repository.EnrollmentRepository;
import Education.OnlineLearningPlatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentServiceImpl implements EnrollmentServiceInterface {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            UserRepository userRepository,
            CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrollment> getEnrollmentsByUser(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public Optional<Enrollment> getEnrollment(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId);
    }

    public Optional<Enrollment> getEnrollmentByUserAndCourse(Long userId, Long courseId) {
        return enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
    }

    @Transactional
    public Enrollment enrollUserInCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));


        Optional<Enrollment> existingEnrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        if (existingEnrollment.isPresent()) {
            return existingEnrollment.get();
        }


        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(ZonedDateTime.now());
        enrollment.setCompleted(false);
        enrollment.setProgressPercentage(0);

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment updateEnrollmentProgress(Long enrollmentId, Integer progressPercentage) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setProgressPercentage(progressPercentage);

        // Mark as completed if 100%
        if (progressPercentage >= 100) {
            enrollment.setCompleted(true);
            enrollment.setCompletionDate(ZonedDateTime.now());
        }

        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public void unEnrollUserFromCourse(Long userId, Long courseId) {
        Optional<Enrollment> enrollment = enrollmentRepository.findByUserIdAndCourseId(userId, courseId);
        enrollment.ifPresent(e -> enrollmentRepository.deleteById(e.getId()));
    }

    public Long getCourseEnrollmentCount(Long courseId) {
        return enrollmentRepository.countByCourseId(courseId);
    }

    public Double getAverageCourseProgress(Long courseId) {
        return enrollmentRepository.averageProgressByCourseId(courseId);
    }
}
