package Education.OnlineLearningPlatform.services;

import Education.OnlineLearningPlatform.entity.LessonProgress;

import java.util.List;

public interface LessonProgressServiceInterface {
    List<LessonProgress> getLessonProgressByEnrollment(Long enrollmentId);
    LessonProgress markLessonAsCompleted(Long enrollmentId, Long lessonId);
    LessonProgress updateLessonProgress(Long enrollmentId, Long lessonId, Integer timeSpentSeconds);
}

