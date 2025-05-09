package Education.OnlineLearningPlatform.repository;

import Education.OnlineLearningPlatform.entity.LessonProgress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonProgressRepository {

    List<LessonProgress> findByEnrollmentId(Long enrollmentId);


    Optional<LessonProgress> findByEnrollmentIdAndLessonId(Long enrollmentId, Long lessonId);


    @Query("SELECT COUNT(lp) FROM LessonProgress lp WHERE lp.enrollment.id = :enrollmentId AND lp.isCompleted = true ")
    Long countCompletedLessonsByEnrollmentId(Long enrollmentId);

    LessonProgress save(LessonProgress progress);
}
