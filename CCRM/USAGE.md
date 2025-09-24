# Usage
1. Compile all java files:
   javac -d out $(find src -name "*.java")
2. Run:
   java -cp out edu.ccrm.cli.Main
3. Sample operations:
   - Manage Students: enter `REG100,John Doe,john@example.com`
   - Manage Courses: enter `CS100,Intro,3,CS`
   - Enroll: enter `S1000,CS100` (use printed student id)
