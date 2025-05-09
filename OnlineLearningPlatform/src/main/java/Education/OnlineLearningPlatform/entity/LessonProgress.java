package Education.OnlineLearningPlatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.Objects;

//@Getter
//@Setter
@Entity
@Table(name = "progresses")
public class LessonProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false)
    private Enrollment enrollment;

    @Column(name = "module_name")
    private String moduleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public ZonedDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(ZonedDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "is_completed")
    private boolean isCompleted = false;

    @ManyToOne
    @JoinColumn(name = "lesson_lesson_id")
    private Lesson lesson;

    @Column(name = "completed_at")
    private ZonedDateTime completedAt;

    public void markCompleted() {
        isCompleted = true;
        completedAt = ZonedDateTime.now();
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LessonProgress)) return false;
        LessonProgress progress = (LessonProgress) o;
        return id != null && id.equals(progress.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void setLastAccessed(ZonedDateTime now) {
    }

    public void setTimeSpentSeconds(int i) {
    }

    public int getTimeSpentSeconds() {
        return 0;
    }

    // Getters and Setters
}
