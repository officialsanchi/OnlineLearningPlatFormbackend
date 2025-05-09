package Education.OnlineLearningPlatform.controllers;

import Education.OnlineLearningPlatform.entity.Enrollment;
import Education.OnlineLearningPlatform.services.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*") // For development - restrict in production
public class EnrollmentController {
    private final EnrollmentServiceImpl enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentServiceImpl enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByUser(@PathVariable Long userId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUser(userId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> getEnrollment(@PathVariable Long enrollmentId) {
        Optional<Enrollment> enrollment = enrollmentService.getEnrollment(enrollmentId);
        return enrollment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/enroll")
    public ResponseEntity<Enrollment> enrollUserInCourse(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        try {
            Enrollment enrollment = enrollmentService.enrollUserInCourse(userId, courseId);
            return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{enrollmentId}/progress")
    public ResponseEntity<Enrollment> updateEnrollmentProgress(
            @PathVariable Long enrollmentId,
            @RequestParam Integer progressPercentage) {
        try {
            Enrollment enrollment = enrollmentService.updateEnrollmentProgress(enrollmentId, progressPercentage);
            return new ResponseEntity<>(enrollment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/unenroll")
    public ResponseEntity<HttpStatus> unenrollUserFromCourse(
            @RequestParam Long userId,
            @RequestParam Long courseId) {
        try {
            enrollmentService.unEnrollUserFromCourse(userId, courseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/course/{courseId}/count")
    public ResponseEntity<Long> getCourseEnrollmentCount(@PathVariable Long courseId) {
        Long count = enrollmentService.getCourseEnrollmentCount(courseId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}/progress")
    public ResponseEntity<Double> getAverageCourseProgress(@PathVariable Long courseId) {
        Double averageProgress = enrollmentService.getAverageCourseProgress(courseId);
        return new ResponseEntity<>(averageProgress, HttpStatus.OK);
    }
}