# 📚 Library App

Spring Boot 기반 **도서 관리 시스템** 토이 프로젝트입니다.  
**Spring Boot 2.8.5 / Java 11 → Spring Boot 3.5.9 / Java 21**로 마이그레이션하며
, EC2 배포까지 직접 구성했습니다.

---

## 🔗 Demo
- http://rkqkdrnportfolio.shop:8080/v1/index.html

---

## 🛠 Tech Stack
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.9
- **Build Tool**: Gradle (Wrapper 8.5)
- **Database**: MySQL 8.0
- **Infra**: AWS EC2 (Amazon Linux 2023)

---

## 🚀 Deployment Strategy (Plan A: Local Build → Upload JAR → Run)
EC2에서 빌드하지 않고, **로컬에서 `bootJar`로 빌드한 JAR을 EC2로 업로드**해 실행합니다.

### 🔄 배포 방식 전환 (Plan B → Plan A)

#### 📌 용어 정의
- **🅰️ Plan A (Local Build & Upload)**: 로컬(맥)에서 JAR 빌드 → `scp`로 EC2 업로드 → EC2는 **실행만** 담당
- **🅱️ Plan B (Build on Server)**: EC2에 소스 보관 → EC2에서 `git pull` + 빌드 → EC2에서 실행

#### 🧾 방식 비교
- **🅱️ 기존(Plan B)**: EC2에서 `git pull` → EC2에서 `./gradlew build` → `java -jar` 실행
- **🅰️ 현재(Plan A)**: 로컬에서 `bootJar` 빌드 → `scp`로 JAR 업로드 → EC2는 실행만 담당

#### ✅ Plan A를 선택한 이유
- **⚡ 속도**: JAR 교체 + 프로세스 재시작만으로 빠른 배포
- **🪶 경량화**: 서버에 Gradle/소스코드가 필수 아님(실행 전용 운영)

---

## 📁 EC2 Runtime Layout
- `/home/ec2-user/app/app.jar` : 배포된 실행 파일
- `/home/ec2-user/app/logs/app.log` : 런타임 로그
- `/home/ec2-user/app/config/` : 외부 설정 분리용

---

## 🧩 Troubleshooting & Lessons Learned
- **Java 21 전환 이슈**: Gradle Wrapper/플러그인 조합 문제로 빌드 실패 → **Wrapper 8.5로 조정**
- **Spring Boot 3.x 마이그레이션**: `javax.*` → `jakarta.*` 네임스페이스 전환 대응
- **보안 사고 대응**: DB 비밀번호 커밋 이력 → **Rotate + `git filter-repo`로 히스토리 제거 + 강제 푸시**
- **인프라 이슈**: Amazon Linux 2023 MySQL 설치 시 **GPG 키 검증 실패** → `RPM-GPG-KEY-mysql-2023` 임포트로 해결