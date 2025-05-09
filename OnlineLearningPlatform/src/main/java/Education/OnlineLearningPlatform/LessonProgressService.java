package Education.OnlineLearningPlatform;

import Education.OnlineLearningPlatform.entity.Enrollment;
import Education.OnlineLearningPlatform.entity.Lesson;
import Education.OnlineLearningPlatform.entity.LessonProgress;
import Education.OnlineLearningPlatform.repository.EnrollmentRepository;
import Education.OnlineLearningPlatform.repository.LessonProgressRepository;
import Education.OnlineLearningPlatform.repository.LessonRepository;
import Education.OnlineLearningPlatform.services.EnrollmentServiceImpl;
import Education.OnlineLearningPlatform.services.LessonProgressServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.List;


@Service
public class LessonProgressService implements LessonProgressServiceInterface {
    private final LessonProgressRepository lessonProgressRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LessonRepository lessonRepository;
    private final EnrollmentServiceImpl enrollmentService;
    private final LessonProgress lessonProgress;

    @Autowired
    public LessonProgressService(LessonProgressRepository lessonProgressRepository,
                                 EnrollmentRepository enrollmentRepository,
                                 LessonRepository lessonRepository,
                                 EnrollmentServiceImpl enrollmentService,
                                 LessonProgress lessonProgress) {
        this.lessonProgressRepository = lessonProgressRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.lessonRepository = lessonRepository;
        this.enrollmentService = enrollmentService;


        this.lessonProgress = lessonProgress;
    }

    public List<LessonProgress> getLessonProgressByEnrollment(Long enrollmentId) {
        return lessonProgressRepository.findByEnrollmentId(enrollmentId);
    }

    @Transactional
    public LessonProgress markLessonAsCompleted(Long enrollmentId, Long lessonId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Lesson lesson = (Lesson) lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        LessonProgress progress = lessonProgressRepository.findByEnrollmentIdAndLessonId(enrollmentId, lessonId)
                .orElseGet(() -> {
                    LessonProgress newProgress = new LessonProgress();
                    newProgress.setEnrollment(enrollment);
                    newProgress.setLesson(lesson);
                    return newProgress;
                });

        progress.setCompleted(true);
        progress.setLastAccessed(ZonedDateTime.now());
        LessonProgress savedProgress = lessonProgressRepository.save(progress);


        updateCourseProgress(enrollmentId);

        return savedProgress;
    }

    @Transactional
    public LessonProgress updateLessonProgress(Long enrollmentId, Long lessonId, Integer timeSpentSeconds) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Lesson lesson = (Lesson) lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        LessonProgress progress = lessonProgressRepository.findByEnrollmentIdAndLessonId(enrollmentId, lessonId)
                .orElseGet(() -> {
                    LessonProgress newProgress = new LessonProgress();
                    newProgress.setEnrollment(enrollment);
                    newProgress.setLesson(lesson);
                    newProgress.setTimeSpentSeconds(0);
                    return newProgress;
                });

        progress.setTimeSpentSeconds(progress.getTimeSpentSeconds() + timeSpentSeconds);
        progress.setLastAccessed(ZonedDateTime.now());

        return lessonProgressRepository.save(progress);
    }

    private void updateCourseProgress(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));


        Long courseId = enrollment.getCourse().getId();
        Long totalLessons = lessonRepository.countByCourseId(courseId);


        Long completedLessons = lessonProgressRepository.countCompletedLessonsByEnrollmentId(enrollmentId);


        int progressPercentage = 0;
        if (totalLessons > 0) {
            progressPercentage = (int) ((completedLessons * 100) / totalLessons);
        }


        enrollmentService.updateEnrollmentProgress(enrollmentId, progressPercentage);
    }
}
