# Campus Course & Records Manager (CCRM)

A console-based Java SE application to manage students, courses, enrollments, grades, and file utilities (import/export/backup).

## How to run
- JDK 11+ recommended.
- From project root:
  - `javac -d out $(find src -name "*.java")`
  - `java -cp out edu.ccrm.cli.Main`

## Project structure
- `src/edu/ccrm/cli` - Menu and CLI
- `src/edu/ccrm/domain` - Domain model: Person, Student, Instructor, Course, Enrollment, enums
- `src/edu/ccrm/service` - Services (StudentService, CourseService, EnrollmentService)
- `src/edu/ccrm/io` - Import/Export & Backup utilities
- `src/edu/ccrm/config` - AppConfig (Singleton)
- `src/edu/ccrm/util` - Helpers & validators

## Sample CSVs
`sample_data/students.csv` and `sample_data/courses.csv` included.

## Notes (short)
- Demonstrates OOP, enums, builder, singleton, exceptions, NIO.2, Streams, Date/Time API, nested classes, lambdas, recursion.
- Assertions: enabled by running with `-ea`.
