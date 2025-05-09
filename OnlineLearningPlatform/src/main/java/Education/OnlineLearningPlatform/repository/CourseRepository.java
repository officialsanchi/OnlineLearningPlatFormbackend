package Education.OnlineLearningPlatform.repository;

import Education.OnlineLearningPlatform.entity.Course;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByIsPublishedTrue(Pageable pageable);

    Page<Course> findByInstructorId(Long instructorId, Pageable pageable);

    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND " +
            "(LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Course> searchCourses(@Param("keyword") String keyword, Pageable pageable);

    Page<Course> findByCategoriesContainingAndIsPublishedTrue(Category category, Pageable pageable);

    @Query("SELECT c FROM Course c JOIN c.enrollments e WHERE e.user.id = :userId")
    Page<Course> findEnrolledCoursesByUserId(@Param("userId") Long userId, Pageable pageable);
}
