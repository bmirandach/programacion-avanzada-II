package com.programacionavanzadaii.trabajoPractico1.model;

import java.time.LocalDateTime;

public abstract class MensajeDTO {

  protected String messageId;
  //protected String userId;
  protected String subject;
  protected LocalDateTime  creationTime;
  //protected String absoluteExpiryTime;

  public MensajeDTO(String messageId, String subject, LocalDateTime creationTime) {
    this.messageId = messageId;
    this.subject = subject;
    this.creationTime = creationTime;
  }

  // defino los getter
  public String getMessageId() {
    return messageId;
  }

  public String getSubject() {
    return subject;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

}
