package com.longuto.androidtemplet.request.exception;


import com.longuto.androidtemplet.request.data.BaseResponse;

/**
 * Created by yltang3 on 2016/5/31.
 */
public class ApiException extends Exception {
  private int code;
  private String message;

  public ApiException(Throwable throwable, int code) {
    super(throwable);
    this.code = code;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public int getCode() {
    return code;
  }

  public BaseResponse baseResult;

  public ApiException(String detailMessage) {
    super(detailMessage);
    setMessage(detailMessage);
  }

  public ApiException(BaseResponse detailMessage) {
    super(detailMessage.getMessage());
    this.baseResult = detailMessage;
  }
}
