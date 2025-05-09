package Education.OnlineLearningPlatform.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LessonRepository {
    Long countByCourseId(Long courseId);

    Optional<Object> findById(Long lessonId);
}
