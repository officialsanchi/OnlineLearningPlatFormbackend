package Education.OnlineLearningPlatform.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ZonedDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(ZonedDateTime enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public ZonedDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(ZonedDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Integer getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(Integer progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Set<LessonProgress> getLessonProgresses() {
        return lessonProgresses;
    }

    public void setLessonProgresses(Set<LessonProgress> lessonProgresses) {
        this.lessonProgresses = lessonProgresses;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "enrollment_date")
    private ZonedDateTime enrollmentDate;

    @Column(name = "completion_date")
    private ZonedDateTime completionDate;

    @Column(name = "is_completed")
    private boolean isCompleted = false;

    @Column(name = "progress_percentage")
    private Integer progressPercentage = 0;

    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private Set<LessonProgress> lessonProgresses = new HashSet<>();

    @OneToOne(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private Certificate certificate;

    @PrePersist
    protected void onCreate() {
        enrollmentDate = ZonedDateTime.now();
    }
}
