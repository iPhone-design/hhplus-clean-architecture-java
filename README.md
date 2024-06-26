# hhplus-clean-architecture-java

## ERD
```mermaid
---
title: 특강 신청
---
erDiagram
    STUDENT {
        FLOAT user_id pk "유저 ID"
        VARCHAR user_name "유저명"
    }
    HISTORY {
        NUMBER history_no pk "히스토리번호"
        FLOAT user_id fk "유저 ID"
        FLOAT schedule_no fk "스케줄 번호"
        TIMESTAMP create_date "생성일"
    }
    REGISTRATION {
        NUMBER registration_no pk "등록번호"
        FLOAT user_id fk "유저 ID"
        NUMBER schedule_no fk "스케줄 번호"
        TIMESTAMP registration_date "등록일"
    }
    LECTURE_SCHEDULE {
        NUMBER schedule_no pk "스케줄 번호"
        NUMBER lecture_no fk "강의 번호"
        TIMESTAMP open_date "강의오픈날짜"
    }
    LECTURE {
        NUMBER lecture_no pk "강의 번호"
        VARCHAR lecture_name "강의명"
    }
```

## Description

- `특강 신청 서비스`를 구현해 봅니다.
- 항해 플러스 토요일 특강을 신청할 수 있는 서비스를 개발합니다.
- 특강 신청 및 신청자 목록 관리를 RDBMS를 이용해 관리할 방법을 고민합니다.

## Requirements

- 아래 2가지 API 를 구현합니다.
    - 특강 신청 API
    - 특강 신청 여부 조회 API
- 각 기능 및 제약 사항에 대해 단위 테스트를 반드시 하나 이상 작성하도록 합니다.
- 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.
- 동시성 이슈를 고려하여 구현합니다.

## API Specs

1️⃣ **(핵심)** 특강 신청 **API `POST /lectures/apply`**

- 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
- 동일한 신청자는 한 번의 수강 신청만 성공할 수 있습니다.
- 각 강의는 선착순 30명만 신청 가능합니다.
- 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
- 어떤 유저가 특강을 신청했는지 히스토리를 저장해야한다.

**2️⃣ (기본) 특강 목록 API `GET /lectures`**

- 단 한번의 특강을 위한 것이 아닌 날짜별로 특강이 존재할 수 있는 범용적인 서비스로 변화시켜 봅니다.
- 이를 수용하기 위해, 특강 엔티티의 경우 기본 과제 SPEC 을 만족하는 설계에서 변경되어야 할 수 있습니다.
    - 수강신청 API 요청 및 응답 또한 이를 잘 수용할 수 있는 구조로 변경되어야 할 것입니다.
- 특강의 정원은 30명으로 고정이며, 사용자는 각 특강에 신청하기전 목록을 조회해볼 수 있어야 합니다.
    - 추가로 정원이 특강마다 다르다면 어떻게 처리할것인가..?

3️⃣ **(기본)** 특강 신청 완료 여부 조회 API **`GET /lectures/application/{userId}`**

- 특정 userId 로 특강 신청 완료 여부를 조회하는 API 를 작성합니다.
- 특강 신청에 성공한 사용자는 성공했음을, 특강 등록자 명단에 없는 사용자는 실패했음을 반환합니다. (true, false)
