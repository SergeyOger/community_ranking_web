package net.broscorp.community_app.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Course {

  @Id
  private String courseTitle;

  private String courseDescription;

  @ElementCollection
  private List<Task> taskList = new ArrayList<>();

  private boolean isActive;
}
