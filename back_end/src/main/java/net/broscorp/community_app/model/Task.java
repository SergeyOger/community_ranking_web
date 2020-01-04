package net.broscorp.community_app.model;

import javax.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Task {

  private String taskTitle;

  private String taskText;

  private double points;
}
