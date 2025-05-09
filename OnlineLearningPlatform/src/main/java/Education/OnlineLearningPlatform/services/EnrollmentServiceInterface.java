package Education.OnlineLearningPlatform.services;

import Education.OnlineLearningPlatform.entity.Enrollment;
import java.util.List;
import java.util.Optional;

public interface EnrollmentServiceInterface {

    List<Enrollment> getEnrollmentsByUser(Long userId);

    List<Enrollment> getEnrollmentsByCourse(Long courseId);

    Optional<Enrollment> getEnrollment(Long enrollmentId);

    Optional<Enrollment> getEnrollmentByUserAndCourse(Long userId, Long courseId);

    Enrollment enrollUserInCourse(Long userId, Long courseId);

    Enrollment updateEnrollmentProgress(Long enrollmentId, Integer progressPercentage);

    void unEnrollUserFromCourse(Long userId, Long courseId);

    Long getCourseEnrollmentCount(Long courseId);

    Double getAverageCourseProgress(Long courseId);
}
