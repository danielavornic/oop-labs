package lab1.src.logger;

import java.time.LocalDateTime;

public class Log {
  private LocalDateTime timestamp;
  private String action;
  private String username;

  public Log(String timestamp2, String action, String username) {
    this.timestamp = LocalDateTime.parse(timestamp2);
    this.action = action;
    this.username = username;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getAction() {
    return action;
  }

  public String getUsername() {
    return username;
  }
}
