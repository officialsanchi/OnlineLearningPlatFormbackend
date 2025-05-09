package Education.OnlineLearningPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineLearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearningPlatformApplication.class, args);
	}

}
//-- Users table (for students, instructors, and admins)
//CREATE TABLE users (
//		user_id SERIAL PRIMARY KEY,
//		email VARCHAR(255) UNIQUE NOT NULL,
//password VARCHAR(255) NOT NULL,
//first_name VARCHAR(100) NOT NULL,
//last_name VARCHAR(100) NOT NULL,
//user_type VARCHAR(20) NOT NULL CHECK (user_type IN ('STUDENT', 'INSTRUCTOR', 'ADMIN')),
//profile_picture_url VARCHAR(255),
//bio TEXT,
//created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
//);
//
//		-- Courses table
//CREATE TABLE courses (
//		course_id SERIAL PRIMARY KEY,
//		title VARCHAR(255) NOT NULL,
//description TEXT,
//instructor_id INTEGER REFERENCES users(user_id),
//thumbnail_url VARCHAR(255),
//price NUMERIC(10, 2),
//level VARCHAR(50) CHECK (level IN ('BEGINNER', 'INTERMEDIATE', 'ADVANCED', 'ALL_LEVELS')),
//duration_hours INTEGER,
//is_published BOOLEAN DEFAULT FALSE,
//created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
//);
//
//		-- Course Categories
//CREATE TABLE categories (
//		category_id SERIAL PRIMARY KEY,
//		name VARCHAR(100) NOT NULL,
//description TEXT
//);
//
//		-- Course-Category relationship (many-to-many)
//CREATE TABLE course_categories (
//		course_id INTEGER REFERENCES courses(course_id),
//category_id INTEGER REFERENCES categories(category_id),
//PRIMARY KEY (course_id, category_id)
//);
//
//		-- Modules (sections within courses)
//CREATE TABLE modules (
//		module_id SERIAL PRIMARY KEY,
//		course_id INTEGER REFERENCES courses(course_id),
//title VARCHAR(255) NOT NULL,
//description TEXT,
//position INTEGER NOT NULL,
//created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
//);
//
//		-- Lessons (content within modules)
//CREATE TABLE lessons (
//		lesson_id SERIAL PRIMARY KEY,
//		module_id INTEGER REFERENCES modules(module_id),
//title VARCHAR(255) NOT NULL,
//description TEXT,
//content_type VARCHAR(50) NOT NULL CHECK (content_type IN ('VIDEO', 'DOCUMENT', 'QUIZ', 'ASSIGNMENT')),
//content_url VARCHAR(255),
//duration_minutes INTEGER,
//position INTEGER NOT NULL,
//is_free_preview BOOLEAN DEFAULT FALSE,
//created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
//);
//
//		-- Enrollments
//CREATE TABLE enrollments (
//		enrollment_id SERIAL PRIMARY KEY,
//		user_id INTEGER REFERENCES users(user_id),
//course_id INTEGER REFERENCES courses(course_id),
//enrollment_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//completion_date TIMESTAMP WITH TIME ZONE,
//is_completed BOOLEAN DEFAULT FALSE,
//progress_percentage INTEGER DEFAULT 0
//		);
//
//		-- Progress tracking
//CREATE TABLE lesson_progress (
//		progress_id SERIAL PRIMARY KEY,
//		enrollment_id INTEGER REFERENCES enrollments(enrollment_id),
//lesson_id INTEGER REFERENCES lessons(lesson_id),
//is_completed BOOLEAN DEFAULT FALSE,
//last_accessed TIMESTAMP WITH TIME ZONE,
//time_spent_seconds INTEGER DEFAULT 0,
//UNIQUE (enrollment_id, lesson_id)
//);
//
//		-- Quizzes
//CREATE TABLE quizzes (
//		quiz_id SERIAL PRIMARY KEY,
//		lesson_id INTEGER REFERENCES lessons(lesson_id),
//title VARCHAR(255) NOT NULL,
//description TEXT,
//time_limit_minutes INTEGER,
//passing_score INTEGER,
//max_attempts INTEGER
//);
//
//		-- Quiz Questions
//CREATE TABLE quiz_questions (
//		question_id SERIAL PRIMARY KEY,
//		quiz_id INTEGER REFERENCES quizzes(quiz_id),
//question_text TEXT NOT NULL,
//question_type VARCHAR(50) NOT NULL CHECK (question_type IN ('MULTIPLE_CHOICE', 'TRUE_FALSE', 'FILL_BLANK', 'ESSAY')),
//points INTEGER DEFAULT 1,
//position INTEGER NOT NULL
//);
//
//		-- Quiz Answers (for multiple choice and true/false)
//CREATE TABLE quiz_answers (
//		answer_id SERIAL PRIMARY KEY,
//		question_id INTEGER REFERENCES quiz_questions(question_id),
//answer_text TEXT NOT NULL,
//is_correct BOOLEAN DEFAULT FALSE,
//position INTEGER NOT NULL
//);
//
//		-- Quiz Attempts
//CREATE TABLE quiz_attempts (
//		attempt_id SERIAL PRIMARY KEY,
//		quiz_id INTEGER REFERENCES quizzes(quiz_id),
//user_id INTEGER REFERENCES users(user_id),
//start_time TIMESTAMP WITH TIME ZONE,
//end_time TIMESTAMP WITH TIME ZONE,
//score INTEGER,
//is_passed BOOLEAN
//);
//
//		-- Quiz Responses
//CREATE TABLE quiz_responses (
//		response_id SERIAL PRIMARY KEY,
//		attempt_id INTEGER REFERENCES quiz_attempts(attempt_id),
//question_id INTEGER REFERENCES quiz_questions(question_id),
//answer_id INTEGER REFERENCES quiz_answers(answer_id),
//text_response TEXT,
//is_correct BOOLEAN,
//points_earned INTEGER
//);
//
//		-- Discussions/Forums
//CREATE TABLE discussions (
//		discussion_id SERIAL PRIMARY KEY,
//		course_id INTEGER REFERENCES courses(course_id),
//title VARCHAR(255) NOT NULL,
//created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
//);
//
//		-- Discussion Posts
//CREATE TABLE discussion_posts (
//		post_id SERIAL PRIMARY KEY,
//		discussion_id INTEGER REFERENCES discussions(discussion_id),
//user_id INTEGER REFERENCES users(user_id),
//parent_post_id INTEGER REFERENCES discussion_posts(post_id),
//content TEXT NOT NULL,
//created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
//);
//
//		-- Reviews and Ratings
//CREATE TABLE course_reviews (
//		review_id SERIAL PRIMARY KEY,
//		course_id INTEGER REFERENCES courses(course_id),
//user_id INTEGER REFERENCES users(user_id),
//rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
//review_text TEXT,
//created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//UNIQUE (course_id, user_id)
//);
//
//		-- Certificates
//CREATE TABLE certificates (
//		certificate_id SERIAL PRIMARY KEY,
//		enrollment_id INTEGER REFERENCES enrollments(enrollment_id),
//issue_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
//certificate_url VARCHAR(255),
//UNIQUE (enrollment_id)
//);
//
//		-- Create indexes for better performance
//CREATE INDEX idx_courses_instructor_id ON courses(instructor_id);
//CREATE INDEX idx_modules_course_id ON modules(course_id);
//CREATE INDEX idx_lessons_module_id ON lessons(module_id);
//CREATE INDEX idx_enrollments_user_id ON enrollments(user_id);
//CREATE INDEX idx_enrollments_course_id ON enrollments(course_id);
//CREATE INDEX idx_lesson_progress_enrollment_id ON lesson_progress(enrollment_id);
//CREATE INDEX idx_quiz_questions_quiz_id ON quiz_questions(quiz_id);
//CREATE INDEX idx_quiz_answers_question_id ON quiz_answers(question_id);
//CREATE INDEX idx_quiz_attempts_user_id ON quiz_attempts(user_id);
//CREATE INDEX idx_discussion_posts_discussion_id ON discussion_posts(discussion_id);
//CREATE INDEX idx_course_reviews_course_id ON course_reviews(course_id);
