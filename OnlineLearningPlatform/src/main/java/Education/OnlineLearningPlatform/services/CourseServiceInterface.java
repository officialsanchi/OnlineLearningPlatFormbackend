package Education.OnlineLearningPlatform.services;

import Education.OnlineLearningPlatform.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CourseServiceInterface {
    Page<Course> getAllPublishedCourses(Pageable pageable);

    Page<Course> getCoursesByInstructor(Long instructorId, Pageable pageable);

    Optional<Course> getCourseById(Long id);

    Page<Course> searchCourses(String keyword, Pageable pageable);

    Page<Course> getCoursesByCategory(Object category, Pageable pageable); // If 'Category' is a custom entity, fix import

    Page<Course> getEnrolledCoursesByUser(Long userId, Pageable pageable);

    Course saveCourse(Course course);

    void deleteCourse(Long id);

    Course publishCourse(Long id);

    Course unpublishCourse(Long id);
}
