package Education.OnlineLearningPlatform.services;

import Education.OnlineLearningPlatform.entity.Course;
import Education.OnlineLearningPlatform.repository.CourseRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseServiceInterface{
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {

        this.courseRepository = courseRepository;
    }

    public Page<Course> getAllPublishedCourses(Pageable pageable) {
        return courseRepository.findByIsPublishedTrue(pageable);
    }

    public Page<Course> getCoursesByInstructor(Long instructorId, Pageable pageable) {
        return courseRepository.findByInstructorId(instructorId, pageable);
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Page<Course> searchCourses(String keyword, Pageable pageable) {
        return courseRepository.searchCourses(keyword, pageable);
    }

    @Override
    public Page<Course> getCoursesByCategory(Object category, Pageable pageable) {
        return null;
    }

    public Page<Course> getCoursesByCategory(Category category, Pageable pageable) {
        return courseRepository.findByCategoriesContainingAndIsPublishedTrue(category, pageable);
    }

    public Page<Course> getEnrolledCoursesByUser(Long userId, Pageable pageable) {
        return courseRepository.findEnrolledCoursesByUserId(userId, pageable);
    }

    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public Course publishCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setPublished(true);
        return courseRepository.save(course);
    }

    @Transactional
    public Course unpublishCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setPublished(false);
        return courseRepository.save(course);
    }
}