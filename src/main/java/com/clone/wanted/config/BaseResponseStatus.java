package com.clone.wanted.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BaseResponseStatus {

    /** 전역예외 처리 에러코드*/
    GlOBAL_EXCEPTION(401, "전역 예외가 발생했습니다 log 확인."),

    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000 : Request 오류
     */
    /*TODO 필요한 Request 에러 메세지 추가해주세요- 하비 */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    /**
     * 3000 : Response 오류
     */
    /*TODO 필요한 Response 에러 메세지 추가해주세요- 하비 */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),


    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),


    /*TODO 필요한 에러 메세지 추가해주세요- 하비 */
    /**
     * 5000 : User Exception
     */
    USER_NOT_FOUND(false, 5000, "해당 유저를 찾을 수 없습니다."),


    /**
     * 6000 : Employment Exception
     */
    EMPLOYMENT_NOT_FOUND(false, 6000, "해당 공고를 찾을 수 없습니다."),

    /**
     * 7000 : Employment Exception
     */
    APPLICATION_ALREADY_EXIST(false, 7000, "이미 지원한 공고입니다."),
    APPLICATION_NOT_EXIST(false, 7000, "지원 내역이 존재하지 않습니다.");


    private boolean isSuccess;
    private final int code;
    private final String message;
    private HttpStatus status;




    BaseResponseStatus(boolean isSuccess, int code, String message) { //BaseResponseStatus 에서 각 해당하는 코드를 생성자로 맵핑
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

    BaseResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    BaseResponseStatus(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status=status;
    }


}
